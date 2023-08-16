package com.flight.api.controller;

import com.flight.api.exception.NoContentException;
import com.flight.api.model.FlightDTO;
import com.flight.api.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class creates the endpoint for the flight resource to get flight list.
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
    @GetMapping(path = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FlightDTO>> getFlights(
            @RequestParam(value = "src") String src,
            @RequestParam(value = "dest") String dest,
            @RequestParam(value = "sortBy", defaultValue = "price", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir
    ) throws NoContentException {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);

        List<FlightDTO> flights = flightService.getAllFlights(src, dest, sort);
        logger.debug("Response returned {}", flights);

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
}

