package com.gotravel.flightshoppingservice.model;

import com.gotravel.flightshoppingservice.entity.FlightSchedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class SearchResponse {
    private List<FlightSchedule> flightScheduleList;
    private List<FlightSchedule> returnFlightScheduleList;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private TripType tripType;
    private int adultCount;
    private int childCount;
    private int infantCount;
    //private ErrorType error;
}
