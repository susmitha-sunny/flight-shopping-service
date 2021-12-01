package com.gotravel.flightshoppingservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class AirportResponse {
    private List<String> departureAirportList;
    private List<String> arrivalAirportList;
}
