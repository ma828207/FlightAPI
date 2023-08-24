package com.flight.api.service;

import com.flight.api.dao.FlightRepository;
import com.flight.api.entity.Flight;
import com.flight.api.exception.NoResultFoundException;
import com.flight.api.model.FlightDTO;
import com.flight.api.util.Utility;
import com.flight.api.validation.InputValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


/**
 * Service class having business logic to implement different flight operations.
 */
@Service
@Slf4j
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private InputValidator inputValidator;

    /**
     * This method returns list of flights with different input parameters.
     *
     * @param source
     * @param destination
     * @param sortBy
     * @param sortType
     * @return Flight List of type FlightDTO
     */
    @Override
    public List<FlightDTO> getFlights(String source, String destination, String sortBy, String sortType)
            throws Exception {

        log.info("Service Class Method named- getFlights Starts with parameters: {}", source, destination, sortBy, sortType);

        List<FlightDTO> flightDTOList ;
        List<Flight> flightLists = new ArrayList<>();
        Boolean validationStatus;

            validationStatus = inputValidator.validateSortFields(sortBy, sortType);
            log.info("Validation Status in Service Class : {}", validationStatus);

            if (Boolean.TRUE.equals(validationStatus)) {
                try {
                    flightLists = flightRepository.findByOriginAndDestination(source, destination);
                    log.info("Repository Response returned {}", flightLists.size());
                } catch (Exception exception) {
                    log.error("Exception Details {}", exception.getMessage());
                    throw new Exception(exception.getMessage());
                }
            }
            if (flightLists != null && !flightLists.isEmpty()) {
                flightDTOList = flightLists.stream().map(Utility::mapToDTO).collect(Collectors.toList());
                flightDTOList = Utility.getSortedFlightDetails(flightDTOList, sortBy, sortType);
                log.info("Flight DTO list in Service Class after sorting: {}", flightDTOList);
            } else {
                throw new NoResultFoundException(messageSource.getMessage("api.error.result.data.not.found", null, Locale.ENGLISH));
            }
        log.info("Service Class Method named- getFlights Ends with response: {}", flightDTOList);
        return flightDTOList;
    }
}
