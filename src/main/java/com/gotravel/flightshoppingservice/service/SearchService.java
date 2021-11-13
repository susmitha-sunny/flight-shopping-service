package com.gotravel.flightshoppingservice.service;

import com.gotravel.flightshoppingservice.entity.FlightSchedule;
import com.gotravel.flightshoppingservice.exception.ValueNotFoundException;
import com.gotravel.flightshoppingservice.model.SearchRequest;
import com.gotravel.flightshoppingservice.repository.FlightScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    @Autowired
    private FlightScheduleRepository flightScheduleRepository;

    public List<FlightSchedule> getSearchResponse(final SearchRequest searchRequest) throws ValueNotFoundException {
        List<FlightSchedule> flightScheduleList = flightScheduleRepository.getFlightDetails(
                searchRequest.getDepartureAirport(), searchRequest.getArrivalAirport(),
                convertToDay(searchRequest.getDepartureDate()));
        if (flightScheduleList.isEmpty()) {
            throw new ValueNotFoundException("No schedules found");
        } else {
            return flightScheduleList;
        }

    }

    private int convertToDay(final LocalDate date) {
        return date.getDayOfWeek().getValue();
    }
}
