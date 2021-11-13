package com.gotravel.flightshoppingservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter @Setter @NoArgsConstructor
public class FlightDetails {
    private String airlineName;
    private String iataCode;
    private String flightNumber;
    private int businessSeats;
    private int economySeats;
    private MealType mealType;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private LocalTime duration;
    private BigDecimal totalFare;
}
