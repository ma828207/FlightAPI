package com.flight.api.dao;

import com.flight.api.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Layer for Flight API operations using JPA.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByOriginAndDestination(String origin, String destination);

}


