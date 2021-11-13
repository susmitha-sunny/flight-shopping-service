package com.gotravel.flightshoppingservice.controller;

import com.gotravel.flightshoppingservice.model.FlightDetailsRequest;
import com.gotravel.flightshoppingservice.model.FlightDetailsResponse;
import com.gotravel.flightshoppingservice.service.FlightDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightDetailsController {


    @Autowired
    private FlightDetailsService flightDetailsService;

    @GetMapping(value = "/flight-shopping-service/flightdetails")
    public FlightDetailsResponse execute(@RequestBody final FlightDetailsRequest flightDetailsRequest) throws Exception {
        return flightDetailsService.getFlightDetails(flightDetailsRequest);
    }

}