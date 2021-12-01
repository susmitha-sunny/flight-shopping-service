package com.gotravel.flightshoppingservice.repository;

import com.gotravel.flightshoppingservice.entity.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Integer> {

    //@Cacheable(value = "scheduleCache", keyGenerator = "scheduleCacheKeyGenerator")
    @Query(value = "SELECT * FROM schedule s INNER JOIN flight f ON s.flight_id = f.flight_id "
            + "INNER JOIN airline a ON f.airline_id=a.airline_id "
            + "WHERE s.departure_airport = ?1 and s.arrival_airport = ?2 and SUBSTRING(s.days, ?3, 1) LIKE '1'",
            nativeQuery = true)
    List<FlightSchedule> getFlightScheduleList(String departureAirport, String arrivalAirport, int day);

    //@Cacheable(value = "scheduleCache", key = "#id")
    FlightSchedule findById(int id);

    //@Cacheable(value = "departureAirportCache")
    @Query(value = "SELECT distinct(s.departure_airport) FROM schedule s", nativeQuery = true)
    List<String> getDepartureAirportList();

    //@Cacheable(value = "arrivalAirportCache")
    @Query(value = "SELECT distinct(s.arrival_airport) FROM schedule s", nativeQuery = true)
    List<String> getArrivalAirportList();

}
