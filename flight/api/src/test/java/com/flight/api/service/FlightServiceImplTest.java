package com.flight.api.service;


import com.flight.api.dao.FlightRepository;
import com.flight.api.entity.Flight;
import com.flight.api.exception.NoResultFoundException;
import com.flight.api.exception.SortInputDataException;
import com.flight.api.model.FlightDTO;
import com.flight.api.utility.TestUtility;
import com.flight.api.validation.InputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This test class covers unit tests for functionalities in FlightService.
 */
@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

    @Mock
    FlightRepository flightRepo;
    @InjectMocks
    FlightServiceImpl flightSearchService;
    @Mock
    InputValidator validator;

    /**
     * This method covers test case for fetching flight list.
     *
     * @throws Exception
     */
    @Test
    void testFlightSearchByOriginAndDestination() throws SortInputDataException, NoResultFoundException {
        List<Flight> flightList = TestUtility.getFlightList();

        when(validator.validateSortFields(any(), any())).thenReturn(true);
        when(flightRepo.findByOriginAndDestination(any(), any())).thenReturn(flightList);

        List<FlightDTO> flighSearchResponseList = flightSearchService
                .getFlights("BOM", "DEL", "price", "asc");
        assertThat(flighSearchResponseList).isNotEmpty()
                .allMatch(f -> "BOM".equals(f.getOrigin()) && "DEL".equals(f.getDestination()));
        verify(flightRepo).findByOriginAndDestination("BOM", "DEL");
    }

    /**
     * This method covers test case for NullPointerException thrown while fetching flight list.
     *
     * @throws SortInputDataException
     */

    @Test
    void testFlightSearchByOriginAndDestinationWithException() throws SortInputDataException {
        List<Flight> flightList = new ArrayList<>();
        when(validator.validateSortFields(any(), any())).thenReturn(true);
        when(flightRepo.findByOriginAndDestination(any(), any()))
                .thenReturn(flightList);
        Assertions.assertThrows(NullPointerException.class, () -> {
            flightSearchService.getFlights("BOM", "DEL", "price", "asc");
        });
    }
}

