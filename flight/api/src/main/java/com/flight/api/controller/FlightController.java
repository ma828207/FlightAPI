package com.flight.api.controller;

import com.flight.api.exception.NoResultFoundException;
import com.flight.api.exception.SortInputDataException;
import com.flight.api.model.FlightDTO;
import com.flight.api.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class to create flight operation endpoints for Flight resource.
 */
@RestController
public class FlightController {

    Logger logger = LoggerFactory.getLogger(FlightController.class);
    @Autowired
    FlightService flightService;

    /**
     * Method returns list of flights based on parameters.
     *
     * @param src
     * @param dest
     * @param sortBy
     * @param sortDir
     * @return ResponseEntity
     */
    @GetMapping(path = "/flights/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FlightDTO>> getFlights(
            @RequestParam(value = "src") String src,
            @RequestParam(value = "dest") String dest,
            @RequestParam(value = "sortBy", defaultValue = "price", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir
    ) throws NoResultFoundException, SortInputDataException {

        /* This method call fetches flight list based on passed parameters*/
        List<FlightDTO> flights = flightService.getFlights(src, dest, sortBy, sortDir);
        logger.debug("Response returned {}", flights);
        return new ResponseEntity<>(flights, HttpStatus.OK);

    }

}

