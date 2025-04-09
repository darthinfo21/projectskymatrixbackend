package com.coforge.project.seatselection.dto;

import lombok.Data;

@Data
public class FlightDTO {
    private Long id;
    private String flightNumber;
    private String airline;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private double fare;
}
