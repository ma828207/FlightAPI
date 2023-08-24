package com.flight.api.controller;

import com.flight.api.model.FlightDTO;
import com.flight.api.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Controller class to create flight operation endpoints for Flight resource.
 */
@Slf4j
@Validated
@RestController
public class FlightController {

    @Autowired
    FlightService flightService;

    /**
     * Method returns list of flights based on parameters.
     *
     * @param source
     * @param destination
     * @param sortBy
     * @param sortType
     * @return ResponseEntity
     */
    @GetMapping(path = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FlightDTO>> getFlights(

            @RequestParam(value = "source") @NotEmpty(message = "Source is required.") String source,
            @RequestParam(value = "destination") @NotEmpty(message = "Destination is required.") String destination,
            @RequestParam(value = "sortBy", defaultValue = "price", required = false) String sortBy,
            @RequestParam(value = "sortType", defaultValue = "asc", required = false) String sortType
    ) throws Exception {

        log.info("Controller Method - getFlights Starts with parameters: {}", source, destination, sortBy, sortType);
        /* This method call fetches flight list based on passed parameters*/
        List<FlightDTO> flights = flightService.getFlights(source, destination, sortBy, sortType);
        log.info("Controller Method - getFlights Ends with Response returned: {}", flights);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
}

