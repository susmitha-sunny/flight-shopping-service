package com.gotravel.flightshoppingservice.controller;

import com.gotravel.flightshoppingservice.entity.FlightSchedule;
import com.gotravel.flightshoppingservice.model.SearchRequest;
import com.gotravel.flightshoppingservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {


    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/search")
    public List<FlightSchedule> execute(@RequestBody final SearchRequest searchRequest) throws Exception {
        return searchService.getSearchResponse(searchRequest);
    }

}
