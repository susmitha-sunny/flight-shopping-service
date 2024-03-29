package com.gotravel.flightshoppingservice.service;

import com.gotravel.flightshoppingservice.entity.FlightSchedule;
import com.gotravel.flightshoppingservice.exception.InvalidRequestException;
import com.gotravel.flightshoppingservice.exception.ValueNotFoundException;
import com.gotravel.flightshoppingservice.model.*;
import com.gotravel.flightshoppingservice.repository.FlightScheduleRepository;
import com.gotravel.flightshoppingservice.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private FlightScheduleRepository flightScheduleRepository;

    public SearchResponse getSearchResponse(final SearchRequest searchRequest) throws ValueNotFoundException,
            InvalidRequestException {
        preValidate(searchRequest);

        List<FlightSchedule> flightScheduleList;
        List<FlightSchedule> returnFlightScheduleList = new ArrayList<>();

        flightScheduleList = flightScheduleRepository.getFlightScheduleList(
                searchRequest.getDepartureAirport(), searchRequest.getArrivalAirport(),
                DateUtil.convertToDay(searchRequest.getDepartureDate()));
        if (searchRequest.getTripType().equals(TripType.RT)) {
            returnFlightScheduleList = flightScheduleRepository.getFlightScheduleList(
                    searchRequest.getArrivalAirport(), searchRequest.getDepartureAirport(),
                    DateUtil.convertToDay(searchRequest.getReturnDate()));
        }

        postValidate(flightScheduleList, searchRequest.getTripType(), returnFlightScheduleList);
        flightScheduleList = filterInactiveAirlines(flightScheduleList);
        returnFlightScheduleList = filterInactiveAirlines(returnFlightScheduleList);

        return buildSearchResponse(flightScheduleList, returnFlightScheduleList, searchRequest);

    }

    private void preValidate(final SearchRequest searchRequest) throws InvalidRequestException {
        if (StringUtils.isBlank(searchRequest.getDepartureAirport())) {
            throw new InvalidRequestException("Missing Departure Airport");
        }
        if (StringUtils.isBlank(searchRequest.getArrivalAirport())) {
            throw new InvalidRequestException("Missing Arrival Airport");
        }
        if (Objects.isNull(searchRequest.getDepartureDate())) {
            throw new InvalidRequestException("Missing Departure Date");
        }
        if (Objects.isNull(searchRequest.getTripType())) {
            throw new InvalidRequestException("Missing Trip Type");
        }
        if (searchRequest.getTripType().equals(TripType.RT) && Objects.isNull(searchRequest.getReturnDate())) {
            throw new InvalidRequestException("Missing Return Date");
        }
    }

    private void postValidate(final List<FlightSchedule> flightScheduleList,
                              final TripType tripType,
                              final List<FlightSchedule> returnFlightScheduleList) throws ValueNotFoundException {
        if (flightScheduleList.isEmpty()) {
            throw new ValueNotFoundException("No schedules found");
        }

        if (tripType.equals(TripType.RT) && returnFlightScheduleList.isEmpty()) {
            throw new ValueNotFoundException("No schedules found for return flight");
        }

    }

    private List<FlightSchedule> filterInactiveAirlines(final List<FlightSchedule> flightScheduleList) {
         return flightScheduleList.stream()
               .filter(flightSchedule -> flightSchedule.getFlight().getAirline().getAirlineStatus()
                       .equals(AirlineStatusType.ACTIVE))
               .collect(Collectors.toList());
    }

    private SearchResponse buildSearchResponse(final List<FlightSchedule> flightScheduleList,
                                               final List<FlightSchedule> returnFlightScheduleList,
                                               final SearchRequest searchRequest) {
    SearchResponse searchResponse = new SearchResponse();
    searchResponse.setFlightScheduleList(flightScheduleList);
    searchResponse.setReturnFlightScheduleList(returnFlightScheduleList);
    searchResponse.setDepartureAirport(searchRequest.getDepartureAirport());
    searchResponse.setArrivalAirport(searchRequest.getArrivalAirport());
    searchResponse.setDepartureDate(searchRequest.getDepartureDate());
    searchResponse.setReturnDate(searchRequest.getReturnDate());
    searchResponse.setTripType(searchRequest.getTripType());
    searchResponse.setAdultCount(searchRequest.getAdultCount());
    searchResponse.setChildCount(searchRequest.getChildCount());
    searchResponse.setInfantCount(searchRequest.getInfantCount());
    return searchResponse;
    }

    public AirportResponse getAirportResponse() {
        AirportResponse airportResponse = new AirportResponse();
        airportResponse.setDepartureAirportList(flightScheduleRepository.getDepartureAirportList());
        airportResponse.setArrivalAirportList(flightScheduleRepository.getArrivalAirportList());
        return airportResponse;
    }
}
