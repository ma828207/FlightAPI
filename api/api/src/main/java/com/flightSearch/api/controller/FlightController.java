package com.flightSearch.api.controller;

import com.flightSearch.api.entity.Flight;
import com.flightSearch.api.model.FlightDTO;
import com.flightSearch.api.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {
    @Autowired
    FlightService flightService;

    @GetMapping("/getAllFlights/")
    public ResponseEntity<List<FlightDTO>> getFlights(
            @RequestParam(value = "source") String source,
            @RequestParam(value = "destination") String destination,
            @RequestParam(value = "sortBy", defaultValue = "price", required = false) String sortBy
    ) {
        List<FlightDTO> flights = flightService.getAllFlights(source, destination,sortBy);

        if (!flights.isEmpty() && flights.size() > 0) {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
