package com.flight.api.service;

import com.flight.api.dao.FlightRepository;
import com.flight.api.entity.Flight;
import com.flight.api.exception.NoResultFoundException;
import com.flight.api.exception.SortInputDataException;
import com.flight.api.model.FlightDTO;
import com.flight.api.util.BusinessUtility;
import com.flight.api.validation.InputValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
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
    public List<FlightDTO> getFlights(String source, String destination, String sortBy, String sortType) throws SortInputDataException, NoResultFoundException {

        log.info("Service Class, Method named- getFlights Starts with parameters: {}", source, destination, sortBy, sortType);

        List<FlightDTO> flightDTOList;
        List<Flight> flightLists = new ArrayList<>();
        Boolean validationStatus;

        validationStatus = inputValidator.validateSortFields(sortBy, sortType);
        log.info("Validation Status in Service Class : {}", validationStatus);

        if (Boolean.TRUE.equals(validationStatus)) {
            flightLists = flightRepository.findByOriginAndDestination(source, destination);
            log.info("Repository Response returned {}", flightLists.size());
        }
        if (!CollectionUtils.isEmpty(flightLists)) {
            flightDTOList = flightLists.stream().map(BusinessUtility::mapToDTO).collect(Collectors.toList());
            flightDTOList = getSortedFlightDetails(flightDTOList, sortBy, sortType);
            log.info("Flight DTO list in Service Class after sorting: {}", flightDTOList);
        } else {
            throw new NoResultFoundException(messageSource.getMessage("api.error.result.data.not.found", null, Locale.ENGLISH));
        }
        log.info("Service Class, Method named- getFlights Ends with response: {}", flightDTOList);
        return flightDTOList;
    }

    /**
     * This method return the sorted Flight list based on sort parameters.
     *
     * @param flightDTOList
     * @param sortBy
     * @param sortDir
     * @return sorted flightDTO list.
     */
    private List<FlightDTO> getSortedFlightDetails(List<FlightDTO> flightDTOList, String sortBy, String sortDir) {

        log.info("Service Class, Method named - getSortedFlightDetails Starts with parameters: {}",
                flightDTOList, sortBy, sortDir);

        if (sortBy.equalsIgnoreCase("price") && sortDir.equalsIgnoreCase("desc")) {
            flightDTOList = flightDTOList.stream().sorted(Collections.reverseOrder(Comparator.comparing(FlightDTO::getPrice))).collect(Collectors.toList());
        } else if (sortBy.equalsIgnoreCase("price") && sortDir.equalsIgnoreCase("asc")) {
            flightDTOList = flightDTOList.stream().sorted(Comparator.comparing(FlightDTO::getPrice)).collect(Collectors.toList());
        } else if (sortBy.equalsIgnoreCase("duration") && sortDir.equalsIgnoreCase("asc")) {
            flightDTOList = flightDTOList.stream().sorted(Comparator.comparingLong(BusinessUtility::getFlightDuration)).collect(Collectors.toList());
        } else if (sortBy.equalsIgnoreCase("duration") && sortDir.equalsIgnoreCase("desc")) {
            flightDTOList = flightDTOList.stream().sorted(Collections.reverseOrder(Comparator.comparingLong(BusinessUtility::getFlightDuration))).collect(Collectors.toList());
        }
        log.info("Service Class, Method named - getSortedFlightDetails Ends with response: {}", flightDTOList);
        return flightDTOList;
    }
}
