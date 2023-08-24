package com.flight.api.service;

import com.flight.api.model.FlightDTO;

import java.util.List;

/**
 * Service contract for all flights operations.
 */
public interface FlightService {
   List<FlightDTO>  getFlights(String source, String destination, String sortBy,String sortDir) throws Exception;
}
