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
    This method return the sorted Flight list
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


    public static List<FlightDTO> getFlightDTOUtilDataList() {
        List<FlightDTO> flightList = new ArrayList<>();
        FlightDTO flightDTO;
        flightDTO = getFlightDTOUtilData();
        flightList.add(flightDTO);
        return flightList;
    }

    public static List<Flight> getFlightUtilDataList() {
        List<Flight> flightList = new ArrayList<>();
        Flight flight;
        flight = getFlightUtilData();
        flightList.add(flight);
        return flightList;
    }

    public static FlightDTO getFlightDTOUtilData() {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlNo("G101");
        flightDTO.setDestination("DEL");
        flightDTO.setOrigin("BOM");
        flightDTO.setArrivalTime(LocalTime.parse("19:40:00"));
        flightDTO.setDepartTime(LocalTime.parse("18:00:00"));
        flightDTO.setPrice(BigDecimal.valueOf(150.00));
        return flightDTO;
    }

    public static Flight getFlightUtilData() {
        Flight flight = new Flight();
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

    /*
     * This method Converts the returned Entity Response into DTO.
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
