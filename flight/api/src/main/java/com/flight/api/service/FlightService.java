package com.flight.api.service;

import com.flight.api.exception.NoContentException;
import com.flight.api.model.FlightDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Service contract for all flights operations.
 */
public interface FlightService {
   List<FlightDTO>  getAllFlights(String source, String destination, Sort sort) throws NoContentException;
}
