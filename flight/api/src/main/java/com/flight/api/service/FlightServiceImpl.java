package com.flight.api.service;

import com.flight.api.dao.FlightRepository;
import com.flight.api.entity.Flight;
import com.flight.api.exception.NoContentException;
import com.flight.api.exception.NoResultFoundException;
import com.flight.api.exception.SortInputDataException;
import com.flight.api.model.FlightDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class having business logic to implement different flight operations.
 */
@Service
public class FlightServiceImpl implements FlightService {
    Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
    @Autowired
    FlightRepository flightRepository;

    private final MessageSource messageSource;

    public FlightServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * This method returns list of flights with different input parameters.
     *
     * @param source
     * @param destination
     * @param sortBy
     * @param sortDir
     * @return List of type FlightDTO
     * @throws NoContentException
     */
    @Override
    public List<FlightDTO> getFlights(String source, String destination, String sortBy, String sortDir) throws NoContentException, SortInputDataException, NoResultFoundException {
        List<FlightDTO> flightDTOList ;

        List<Flight> flightLists = flightRepository.findByOriginAndDestination(source, destination);
        logger.debug("Response returned {}", flightLists);

            if (!flightLists.isEmpty()) {

                flightDTOList = flightLists.stream().map(this::mapToDTO).collect(Collectors.toList());

                if (sortBy.equalsIgnoreCase("price") && sortDir.equalsIgnoreCase("desc")) {
                    flightDTOList = flightDTOList.stream().sorted(Collections.reverseOrder(Comparator.comparing(FlightDTO ::getPrice))).collect(Collectors.toList());
                } else if (sortBy.equalsIgnoreCase("price") && sortDir.equalsIgnoreCase("asc")) {
                    flightDTOList = flightDTOList.stream().sorted(Comparator.comparing(FlightDTO ::getPrice)).collect(Collectors.toList());
                } else if (sortBy.equalsIgnoreCase("duration") && sortDir.equalsIgnoreCase("asc")) {
                    flightDTOList = flightDTOList.stream().sorted(Comparator.comparingLong(FlightServiceImpl::getFlightDuration)).collect(Collectors.toList());
                } else if (sortBy.equalsIgnoreCase("duration") && sortDir.equalsIgnoreCase("desc")) {
                    flightDTOList = flightDTOList.stream().sorted(Collections.reverseOrder(Comparator.comparingLong(FlightServiceImpl::getFlightDuration))).collect(Collectors.toList());
                } else {
                    throw new SortInputDataException(messageSource.getMessage("api.error.correct_sort_input.not.found", null, Locale.ENGLISH));
                }

            } else {
                throw new NoResultFoundException(messageSource.getMessage("api.error.content.data.not.found", null, Locale.ENGLISH));
            }
        return flightDTOList;
    }

    /**
     * Converting Entity Response into DTO.
     */
    private FlightDTO mapToDTO(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlNo(flight.getFlNumber());
        flightDTO.setOrigin(flight.getOrigin());
        flightDTO.setDestination(flight.getDestination());
        flightDTO.setDepartTime(flight.getDepartTime());
        flightDTO.setArrivalTime(flight.getArrivalTime());
        flightDTO.setPrice(flight.getPrice());
        flightDTO.setDurationInMins(Duration.between(flightDTO.getDepartTime(), flightDTO.getArrivalTime()).toMinutes());
        return flightDTO;
    }

    /**
     * This method calculates the flight duration needed for sorting
     * @param flightDTO
     * @return
     */
    private static long getFlightDuration(FlightDTO flightDTO) {
        return Duration.between(flightDTO.getDepartTime(), flightDTO.getArrivalTime()).toMinutes();
    }
}
