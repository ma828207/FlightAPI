package com.flightSearch.api.service;

import com.flightSearch.api.dao.FlightRepository;
import com.flightSearch.api.entity.Flight;
import com.flightSearch.api.model.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Override
    public List<FlightDTO> getAllFlights(String source, String destination, String sortBy) {
        Sort sortByField = Sort.by("price");

        List<Flight> flightLists = flightRepository.findBySourceAndDestination
                (source, destination, sortByField);
       // List<Flight> flightLists = flightRepository.findAll();

        List<FlightDTO> lists =
        flightLists.stream().map(x -> mapToDTO(x)).collect(Collectors.toList());


        return lists;
    }

    // convert Entity into DTO
    private FlightDTO mapToDTO(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setID(flight.getFl_Number());
        flightDTO.setOrigin(flight.getFl_Number());
        flightDTO.setDestination(flight.getDestination());
        flightDTO.setDepartTime(flight.getDepartTime());
        flightDTO.setArrivalTime(flight.getArrivalTime());
        flightDTO.setPrice(flight.getPrice());
        return flightDTO;
    }
}
