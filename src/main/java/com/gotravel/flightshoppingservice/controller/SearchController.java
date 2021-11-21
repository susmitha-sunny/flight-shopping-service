package com.gotravel.flightshoppingservice.controller;

import com.gotravel.flightshoppingservice.exception.InvalidRequestException;
import com.gotravel.flightshoppingservice.exception.ValueNotFoundException;
import com.gotravel.flightshoppingservice.model.SearchRequest;
import com.gotravel.flightshoppingservice.model.SearchResponse;
import com.gotravel.flightshoppingservice.model.SearchResponseWrapper;
import com.gotravel.flightshoppingservice.model.TripType;
import com.gotravel.flightshoppingservice.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @CrossOrigin
    @GetMapping(value = "/search")
    public SearchResponseWrapper execute(
            @RequestParam(value="departureAirport") final String departureAirport,
            @RequestParam(value="arrivalAirport") final String arrivalAirport,
            @RequestParam(value="departureDate") final String departureDate,
            @RequestParam(value="returnDate", required = false) final String returnDate,
            @RequestParam(value="tripType") final String tripType,
            @RequestParam(value="adultCount") final int adultCount,
            @RequestParam(value="childCount", required = false) final int childCount,
            @RequestParam(value="infantCount", required = false) final int infantCount
    ) throws ValueNotFoundException, InvalidRequestException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setDepartureAirport(departureAirport);
        searchRequest.setArrivalAirport(arrivalAirport);
        if(departureDate == null || departureDate.equals("null")) {
            searchRequest.setDepartureDate(null);
        } else {
            searchRequest.setDepartureDate(LocalDate.parse(departureDate));
        }
        if(returnDate == null || returnDate.equals("null")) {
            searchRequest.setReturnDate(null);
        } else {
            searchRequest.setReturnDate(LocalDate.parse(returnDate));
        }
        searchRequest.setTripType(TripType.valueOf(tripType));
        searchRequest.setAdultCount(adultCount);
        searchRequest.setChildCount(childCount);
        searchRequest.setInfantCount(infantCount);
        SearchResponseWrapper wrapper = new SearchResponseWrapper();
        wrapper.setSearchResponse(searchService.getSearchResponse(searchRequest));
        return wrapper;
    }

}
