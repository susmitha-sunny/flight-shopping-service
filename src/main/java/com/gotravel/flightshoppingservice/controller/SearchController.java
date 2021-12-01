package com.gotravel.flightshoppingservice.controller;

import com.gotravel.flightshoppingservice.exception.InvalidRequestException;
import com.gotravel.flightshoppingservice.exception.ValueNotFoundException;
import com.gotravel.flightshoppingservice.model.*;
import com.gotravel.flightshoppingservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class SearchController {


    @Autowired
    private SearchService searchService;

//    @CrossOrigin
//    @PostMapping(value = "/search")
//    public SearchResponse executePost(@RequestBody final SearchRequest searchRequest) throws ValueNotFoundException,
//            InvalidRequestException {
//        System.out.println("inside search controller");
//        return searchService.getSearchResponse(searchRequest);
//    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/airports")
    public AirportResponse executeAirports() {
        return searchService.getAirportResponse();
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/search")
    public SearchResponse execute(
            @RequestParam(value = "departureAirport") final String departureAirport,
            @RequestParam(value = "arrivalAirport") final String arrivalAirport,
            @RequestParam(value = "departureDate") final String departureDate,
            @RequestParam(value = "returnDate", required = false) final String returnDate,
            @RequestParam(value = "tripType") final String tripType,
            @RequestParam(value = "adultCount") final int adultCount,
            @RequestParam(value = "childCount", required = false) final int childCount,
            @RequestParam(value = "infantCount", required = false) final int infantCount
    ) throws ValueNotFoundException, InvalidRequestException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setDepartureAirport(departureAirport);
        searchRequest.setArrivalAirport(arrivalAirport);
        if (departureDate == null || departureDate.equals("null")) {
            searchRequest.setDepartureDate(null);
        } else {
            searchRequest.setDepartureDate(LocalDate.parse(departureDate));
        }
        if (returnDate == null || returnDate.equals("null")) {
            searchRequest.setReturnDate(null);
        } else {
            searchRequest.setReturnDate(LocalDate.parse(returnDate));
        }
        searchRequest.setTripType(TripType.valueOf(tripType));
        searchRequest.setAdultCount(adultCount);
        searchRequest.setChildCount(childCount);
        searchRequest.setInfantCount(infantCount);

        return searchService.getSearchResponse(searchRequest);
    }

}
