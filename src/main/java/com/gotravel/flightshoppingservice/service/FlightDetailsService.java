package com.gotravel.flightshoppingservice.service;

import com.gotravel.flightshoppingservice.entity.FlightSchedule;
import com.gotravel.flightshoppingservice.exception.InvalidRequestException;
import com.gotravel.flightshoppingservice.exception.ValueNotFoundException;
import com.gotravel.flightshoppingservice.model.*;
import com.gotravel.flightshoppingservice.repository.FlightScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class FlightDetailsService {

    @Autowired
    private FlightScheduleRepository flightScheduleRepository;

    public FlightDetailsResponse getFlightDetails(final FlightDetailsRequest flightDetailsRequest)
            throws ValueNotFoundException, InvalidRequestException {
        preValidate(flightDetailsRequest);
        FlightSchedule flightSchedule;
        FlightSchedule returnFlightSchedule = null;

        flightSchedule = flightScheduleRepository.findById(flightDetailsRequest.getScheduleId());

        if (flightDetailsRequest.getTripType().equals(TripType.RT)) {
            returnFlightSchedule = flightScheduleRepository.findById(flightDetailsRequest.getReturnScheduleId());
        }

        postValidate(flightSchedule, flightDetailsRequest.getTripType(), returnFlightSchedule);

        return buildFlightDetailsResponse(flightSchedule, returnFlightSchedule, flightDetailsRequest);

    }

    private void preValidate(final FlightDetailsRequest flightDetailsRequest) throws InvalidRequestException {
        if (Objects.isNull(flightDetailsRequest.getDepartureDate())) {
            throw new InvalidRequestException("Missing Departure Date");
        }
        if (Objects.isNull(flightDetailsRequest.getTripType())) {
            throw new InvalidRequestException("Missing Trip Type");
        }
        if (flightDetailsRequest.getTripType().equals(TripType.RT) && Objects.isNull(flightDetailsRequest.getReturnDate())) {
            throw new InvalidRequestException("Missing Return Date");
        }
    }

    private void postValidate(final FlightSchedule flightSchedule,
                              final TripType tripType, final FlightSchedule returnFlightSchedule)
            throws ValueNotFoundException {
        if (Objects.isNull(flightSchedule)) {
            throw new ValueNotFoundException("No matching schedule found");
        }
        if (tripType.equals(TripType.RT) && Objects.isNull(returnFlightSchedule)) {
            throw new ValueNotFoundException("No matching return schedule found");
        }
    }

    private FlightDetailsResponse buildFlightDetailsResponse(final FlightSchedule flightSchedule,
                                                             final FlightSchedule returnFlightSchedule,
                                                             final FlightDetailsRequest flightDetailsRequest) {
    FlightDetailsResponse flightDetailsResponse = new FlightDetailsResponse();
    flightDetailsResponse.setFlightSchedule(flightSchedule);
    flightDetailsResponse.setReturnFlightSchedule(returnFlightSchedule);
    flightDetailsResponse.setDepartureDate(flightDetailsRequest.getDepartureDate());
    flightDetailsResponse.setReturnDate(flightDetailsRequest.getReturnDate());
    flightDetailsResponse.setTripType(flightDetailsRequest.getTripType());
    flightDetailsResponse.setAdultCount(flightDetailsRequest.getAdultCount());
    flightDetailsResponse.setChildCount(flightDetailsRequest.getChildCount());
    flightDetailsResponse.setInfantCount(flightDetailsRequest.getInfantCount());
    findTotalFare(flightDetailsResponse);
    return flightDetailsResponse;
    }

    private void findTotalFare(final FlightDetailsResponse flightDetailsResponse) {
        //Infant does not have fare
        int paxCount = flightDetailsResponse.getAdultCount() + flightDetailsResponse.getChildCount();
        BigDecimal totalReturnFare = BigDecimal.ZERO;
        BigDecimal totalFare = flightDetailsResponse.getFlightSchedule().getFare().multiply(BigDecimal.valueOf(paxCount));
        if (Objects.nonNull(flightDetailsResponse.getReturnFlightSchedule())) {
            totalReturnFare = flightDetailsResponse.getReturnFlightSchedule().getFare().multiply(BigDecimal.valueOf(paxCount));
        }
        flightDetailsResponse.setTotalFare(totalFare.add(totalReturnFare));
    }
}
