package com.flightSearch.api.service;

import com.flightSearch.api.entity.Flight;
import com.flightSearch.api.model.FlightDTO;

import java.util.List;


public interface FlightService {
    List<FlightDTO>  getAllFlights(String source, String destination, String sortBy);
}
