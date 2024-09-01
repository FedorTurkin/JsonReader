package org.jsonreader;

import org.jsonreader.entities.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Calculations {
    public void calculateMinimumFlightTimeByCarriers(List<Ticket> tickets) {
        Map<String, Long> minFlightTimes = new HashMap<>();

        for (Ticket ticket : tickets) {
            minFlightTimes.merge(ticket.getCarrier(), ticket.getFlightTime(), Math::min);
        }

        System.out.println("Минимальное время полета для каждого авиаперевозчика:");
        minFlightTimes.forEach((carrier, flightTime) -> System.out.println(carrier + ": " + flightTime / 60 + " часов " + flightTime % 60 + " минут."));
    }

    public void calculatePriceStatistics(List<Ticket> tickets) {
        List<Long> prices = tickets.stream().map(Ticket::getPrice).sorted().collect(Collectors.toList());
        double averagePrice = prices.stream().mapToLong(Long::longValue).average().orElse(0);

        double medianPrice;
        int size = prices.size();
        if (size % 2 == 0) {
            medianPrice = (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            medianPrice = prices.get(size / 2);
        }

        double priceDifference = averagePrice - medianPrice;

        System.out.printf("Разница между средней ценой и медианой: %.2f\n", priceDifference);
    }

}
