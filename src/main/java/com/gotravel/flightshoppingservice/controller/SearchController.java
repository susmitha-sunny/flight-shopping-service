package com.gotravel.flightshoppingservice.controller;

import com.gotravel.flightshoppingservice.exception.InvalidRequestException;
import com.gotravel.flightshoppingservice.exception.ValueNotFoundException;
import com.gotravel.flightshoppingservice.model.SearchRequest;
import com.gotravel.flightshoppingservice.model.SearchResponse;
import com.gotravel.flightshoppingservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {


    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/flight-shopping-service/search")
    public SearchResponse execute(@RequestBody final SearchRequest searchRequest) throws ValueNotFoundException,
            InvalidRequestException {
        return searchService.getSearchResponse(searchRequest);
    }

}
