package com.flight.api.service;

import com.flight.api.constant.Constants;
import com.flight.api.dao.FlightRepository;
import com.flight.api.entity.Flight;
import com.flight.api.exception.NoContentException;
import com.flight.api.model.FlightDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class having business logic to implement different flight operations.
 */
@Service
public class FlightServiceImpl implements FlightService {
    Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
    @Autowired
    FlightRepository flightRepository;

    /**
     * This method returns list of flights with different input parameters.
     * @param source
     * @param destination
     * @param sort
     * @return List of type Flight DTO
     */
    @Override
    public List<FlightDTO> getAllFlights(String source, String destination, Sort sort) throws NoContentException {

        List<Flight> flightLists = flightRepository.findByOriginAndDestination(source, destination, sort);
        logger.debug("Response returned {}", flightLists);

        if (!flightLists.isEmpty()) {
            return flightLists.stream().map(this::mapToDTO).collect(Collectors.toList());
        } else {
            logger.error("Error Response returned {}", flightLists);
            throw new NoContentException(Constants.ERROR_MSG);
        }
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
        return flightDTO;
    }


}
