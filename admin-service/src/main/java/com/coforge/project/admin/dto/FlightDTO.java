package com.coforge.project.admin.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDTO {

	 private Long id;
	    private String flightNumber;
	    private String airline;
	    private String source;
	    private String destination;
	    private LocalDateTime departureTime;
	    private LocalDateTime arrivalTime;
	    private Double fare;
	    private Integer totalSeats;
	    private Integer availableSeats;

 
}