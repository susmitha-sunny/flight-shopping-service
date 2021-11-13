package com.gotravel.flightshoppingservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
public class SearchRequest {
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private TripType tripType;
    private int adultCount;
    private int childCount;
    private int infantCount;
}
