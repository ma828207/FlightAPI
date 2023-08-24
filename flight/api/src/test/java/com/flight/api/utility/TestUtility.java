package com.flight.api.utility;

import com.flight.api.entity.Flight;
import com.flight.api.model.FlightDTO;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Test Utility Class
 */
public final class TestUtility {


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
}
