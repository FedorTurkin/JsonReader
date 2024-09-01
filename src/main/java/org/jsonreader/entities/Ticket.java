package org.jsonreader.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ticket {
    String origin;
    String originName;
    String destination;
    String destinationName;
    Long flightTime;
    String carrier;
    Long stops;
    Long price;

    public Ticket(String origin, String originName, String destination, String destinationName, Long flightTime,
                  String carrier, Long stops, Long price) {
        this.origin = origin;
        this.originName = originName;
        this.destination = destination;
        this.destinationName = destinationName;
        this.flightTime = flightTime;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }
    public String getCarrier() {
        return this.carrier;
    }
    public Long getPrice() {
        return this.price;
    }

    public Long getFlightTime() {
        return this.flightTime;
    }
}