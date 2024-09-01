package org.jsonreader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsonreader.entities.Ticket;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Calculations calculations = new Calculations();
        String filePath = args[0];
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(filePath));
        JsonNode ticketsNode = rootNode.get("tickets");
        List<Ticket> vvoTlvTickets = new ArrayList<>();
        for (JsonNode ticketNode : ticketsNode) {
            String origin = ticketNode.get("origin").asText();
            String originName = ticketNode.get("origin_name").asText();
            String destination = ticketNode.get("destination").asText();
            String destinationName = ticketNode.get("destination_name").asText();
            LocalDate departureDate = LocalDate.parse(ticketNode.get("departure_date").asText(), DateTimeFormatter.ofPattern("dd.MM.yy"));
            LocalTime departureTime = LocalTime.parse(ticketNode.get("departure_time").asText(), DateTimeFormatter.ofPattern("H:mm"));
            LocalDate arrivalDate = LocalDate.parse(ticketNode.get("arrival_date").asText(), DateTimeFormatter.ofPattern("dd.MM.yy"));
            LocalTime arrivalTime = LocalTime.parse(ticketNode.get("arrival_time").asText(), DateTimeFormatter.ofPattern("H:mm"));
            LocalDateTime departureDateTime = LocalDateTime.of(departureDate, departureTime);
            LocalDateTime arrivalDateTime = LocalDateTime.of(arrivalDate, arrivalTime);
            Long flightTime = Duration.between(departureDateTime, arrivalDateTime).toMinutes();
            String carrier = ticketNode.get("carrier").asText();
            Long stops = ticketNode.get("stops").asLong();
            Long price = ticketNode.get("price").asLong();
            if (originName.equals("Владивосток") && destinationName.equals("Тель-Авив")) {
                vvoTlvTickets.add(new Ticket(origin, originName, destination, destinationName, flightTime, carrier, stops, price));
            }
        }
        calculations.calculateMinimumFlightTimeByCarriers(vvoTlvTickets);
        calculations.calculatePriceStatistics(vvoTlvTickets);
    }


}