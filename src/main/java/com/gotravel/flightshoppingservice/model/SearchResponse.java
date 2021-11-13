package com.gotravel.flightshoppingservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class SearchResponse {
    private List<FlightDetails> flightDetailsList;
    private List<FlightDetails> returnFlightDetailsList;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int adultCount;
    private int childCount;
    private int infantCount;
    //private ErrorType error;
}
