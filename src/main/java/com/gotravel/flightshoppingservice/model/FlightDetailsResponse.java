package com.gotravel.flightshoppingservice.model;

import com.gotravel.flightshoppingservice.entity.FlightSchedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FlightDetailsResponse {
    private FlightSchedule flightSchedule;
    private FlightSchedule returnFlightSchedule;
    private BigDecimal totalFare;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private TripType tripType;
    private int adultCount;
    private int childCount;
    private int infantCount;
    //private ErrorType error;
}
