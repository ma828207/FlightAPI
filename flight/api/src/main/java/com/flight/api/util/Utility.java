package com.flight.api.util;

import com.flight.api.entity.Flight;
import com.flight.api.model.FlightDTO;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class
 */
public final class Utility {

    private Utility() {
    }

    /*
    This utility method return the sorted Flight list based on sort parameters.
     */
    public static List<FlightDTO> getSortedFlightDetails(List<FlightDTO> flightDTOList, String sortBy, String sortDir) {

        if (sortBy.equalsIgnoreCase("price") && sortDir.equalsIgnoreCase("desc")) {
            flightDTOList = flightDTOList.stream().sorted(Collections.reverseOrder(Comparator.comparing(FlightDTO::getPrice))).collect(Collectors.toList());
        } else if (sortBy.equalsIgnoreCase("price") && sortDir.equalsIgnoreCase("asc")) {
            flightDTOList = flightDTOList.stream().sorted(Comparator.comparing(FlightDTO::getPrice)).collect(Collectors.toList());
        } else if (sortBy.equalsIgnoreCase("duration") && sortDir.equalsIgnoreCase("asc")) {
            flightDTOList = flightDTOList.stream().sorted(Comparator.comparingLong(Utility::getFlightDuration)).collect(Collectors.toList());
        } else if (sortBy.equalsIgnoreCase("duration") && sortDir.equalsIgnoreCase("desc")) {
            flightDTOList = flightDTOList.stream().sorted(Collections.reverseOrder(Comparator.comparingLong(Utility::getFlightDuration))).collect(Collectors.toList());
        }
        return flightDTOList;
    }

    /**
     * This utility method returns data list of type FlightDTO
     * @return Flight DTO data List
     */
    public static List<FlightDTO> getFlightDTOList() {
        List<FlightDTO> flightList = new ArrayList<>();
        FlightDTO flightDTO;
        flightDTO = getFlightDTOData();
        flightList.add(flightDTO);
        return flightList;
    }

    /**
     * This utility method returns data list of type Flight
     * @return Flight data list
     */
    public static List<Flight> getFlightList() {
        List<Flight> flightList = new ArrayList<>();
        Flight flight;
        flight = getFlightData();
        flightList.add(flight);
        return flightList;
    }

    /**
     * This Utility method sets values to FlightDTO object
     * @return Flight DTO object having values
     */
    public static FlightDTO getFlightDTOData() {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlNo("G101");
        flightDTO.setDestination("DEL");
        flightDTO.setOrigin("BOM");
        flightDTO.setArrivalTime(LocalTime.parse("19:40:00"));
        flightDTO.setDepartTime(LocalTime.parse("18:00:00"));
        flightDTO.setPrice(BigDecimal.valueOf(150.00));
        return flightDTO;
    }

    /**
     * This Utility method sets values to Flight object
     * @return Flight object having values
     */
    public static Flight getFlightData() {
        Flight flight = new Flight();
        flight.setID(1);
        flight.setFlNumber("G101");
        flight.setDestination("DEL");
        flight.setOrigin("BOM");
        flight.setArrivalTime(LocalTime.parse("19:40:00"));
        flight.setDepartTime(LocalTime.parse("18:00:00"));
        flight.setPrice(BigDecimal.valueOf(150.00));
        return flight;
    }

    /**
     * This method calculates the flight duration needed for sorting
     *
     * @param flightDTO
     * @return Flight duration in Minutes
     */
    public static long getFlightDuration(FlightDTO flightDTO) {
        return Duration.between(flightDTO.getDepartTime(), flightDTO.getArrivalTime()).toMinutes();
    }

    /**
     * This utility method Converts the returned Entity Response into DTO.
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
