package com.flight.api.util;

import com.flight.api.entity.Flight;
import com.flight.api.model.FlightDTO;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for Flight API
 */
@Slf4j
public final class Utility {

    private Utility() {
    }

    /**
     * This utility method return the sorted Flight list based on sort parameters.
     * @param flightDTOList
     * @param sortBy
     * @param sortDir
     * @return sorted flightDTO list.
     */
    public static List<FlightDTO> getSortedFlightDetails(List<FlightDTO> flightDTOList, String sortBy, String sortDir) {

        log.info("Utility Class Method named - getSortedFlightDetails Starts with parameters: {}",
                flightDTOList, sortBy, sortDir);

        if (sortBy.equalsIgnoreCase("price") && sortDir.equalsIgnoreCase("desc")) {
            flightDTOList = flightDTOList.stream().sorted(Collections.reverseOrder(Comparator.comparing(FlightDTO::getPrice))).collect(Collectors.toList());
        } else if (sortBy.equalsIgnoreCase("price") && sortDir.equalsIgnoreCase("asc")) {
            flightDTOList = flightDTOList.stream().sorted(Comparator.comparing(FlightDTO::getPrice)).collect(Collectors.toList());
        } else if (sortBy.equalsIgnoreCase("duration") && sortDir.equalsIgnoreCase("asc")) {
            flightDTOList = flightDTOList.stream().sorted(Comparator.comparingLong(Utility::getFlightDuration)).collect(Collectors.toList());
        } else if (sortBy.equalsIgnoreCase("duration") && sortDir.equalsIgnoreCase("desc")) {
            flightDTOList = flightDTOList.stream().sorted(Collections.reverseOrder(Comparator.comparingLong(Utility::getFlightDuration))).collect(Collectors.toList());
        }
        log.info("Utility Class Method named - getSortedFlightDetails Ends with response: {}", flightDTOList);
        return flightDTOList;
    }

    /**
     * This method calculates the flight duration needed for sorting
     *
     * @param flightDTO
     * @return Flight duration in Minutes
     */
    public static long getFlightDuration(FlightDTO flightDTO) {
        Long flightDuration;
        flightDuration = Duration.between(flightDTO.getDepartTime(), flightDTO.getArrivalTime()).toMinutes();
        log.info("Utility Class getFlightDuration calculated : {}", flightDuration);
        return flightDuration;
    }

    /**
     * This utility method Converts the returned Entity Response into DTO.
     *
     * @param flight
     * @return converted FlightDTO object
     */
    public static FlightDTO mapToDTO(Flight flight) {
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
}
