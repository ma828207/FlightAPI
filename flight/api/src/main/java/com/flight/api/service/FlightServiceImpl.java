package com.flight.api.service;

import com.flight.api.dao.FlightRepository;
import com.flight.api.entity.Flight;
import com.flight.api.exception.NoContentException;
import com.flight.api.exception.NoResultFoundException;
import com.flight.api.exception.SortInputDataException;
import com.flight.api.model.FlightDTO;
import com.flight.api.util.Utility;
import com.flight.api.validation.ValidateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Service class having business logic to implement different flight operations.
 */
@Service
public class FlightServiceImpl implements FlightService {
    Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ValidateData validateData;

    /**
     * This method returns list of flights with different input parameters.
     *
     * @param source
     * @param destination
     * @param sortBy
     * @param sortDir
     * @return Flight List of type FlightDTO
     * @throws NoContentException
     */
    @Override
    public List<FlightDTO> getFlights(String source, String destination, String sortBy, String sortDir)
            throws SortInputDataException, NoResultFoundException {
        List<FlightDTO> flightDTOList;

        validateData.validateSortFields(sortBy, sortDir);

        List<Flight> flightLists = null;

        flightLists = flightRepository.findByOriginAndDestination(source, destination);
        logger.debug("Response returned {}", flightLists);


        if (!flightLists.isEmpty()) {
            flightDTOList = flightLists.stream().map(Utility::mapToDTO).collect(Collectors.toList());
            flightDTOList = Utility.getSortedFlightDetails(flightDTOList, sortBy, sortDir);
            return flightDTOList;
        } else {
            throw new NoResultFoundException(messageSource.getMessage("api.error.result.data.not.found", null, Locale.ENGLISH));
        }
    }
}
