package com.gotravel.flightshoppingservice.controller;

import com.gotravel.flightshoppingservice.exception.InvalidRequestException;
import com.gotravel.flightshoppingservice.exception.ValueNotFoundException;
import com.gotravel.flightshoppingservice.model.FlightDetailsRequest;
import com.gotravel.flightshoppingservice.model.FlightDetailsResponse;
import com.gotravel.flightshoppingservice.model.TripType;
import com.gotravel.flightshoppingservice.service.FlightDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class FlightDetailsController {


    @Autowired
    private FlightDetailsService flightDetailsService;

    @CrossOrigin
    @GetMapping(value = "/flightdetails")
    public FlightDetailsResponse execute(
            @RequestParam(value="scheduleId") final int scheduleId,
            @RequestParam(value="returnScheduleId", required = false) final int returnScheduleId,
            @RequestParam(value="departureDate") final String departureDate,
            @RequestParam(value="returnDate", required = false) final String returnDate,
            @RequestParam(value="tripType") final String tripType,
            @RequestParam(value="adultCount") final int adultCount,
            @RequestParam(value="childCount", required = false) final int childCount,
            @RequestParam(value="infantCount", required = false) final int infantCount) throws ValueNotFoundException, InvalidRequestException {
        FlightDetailsRequest request = new FlightDetailsRequest();
        request.setScheduleId(scheduleId);
        request.setReturnScheduleId(returnScheduleId);
        if(departureDate == null || departureDate.equals("null")) {
            request.setDepartureDate(null);
        } else {
            request.setDepartureDate(LocalDate.parse(departureDate));
        }
        if(returnDate == null || returnDate.equals("null")) {
            request.setReturnDate(null);
        } else {
            request.setReturnDate(LocalDate.parse(returnDate));
        }
        request.setTripType(TripType.valueOf(tripType));
        request.setAdultCount(adultCount);
        request.setChildCount(childCount);
        request.setInfantCount(infantCount);

        return flightDetailsService.getFlightDetails(request);
    }

}
