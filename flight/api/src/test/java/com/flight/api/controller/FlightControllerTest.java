package com.flight.api.controller;

import com.flight.api.exception.NoResultFoundException;
import com.flight.api.exception.SortInputDataException;
import com.flight.api.model.FlightDTO;
import com.flight.api.service.FlightServiceImpl;
import com.flight.api.utility.TestUtility;
import com.flight.api.validation.InputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * This test class covers unit tests for functionalities in FlightController.
 */
@ExtendWith(MockitoExtension.class)
class FlightControllerTest {

    @InjectMocks
    private FlightController flightController;
    @Mock
    private FlightServiceImpl flightService;
    @Mock
    InputValidator validator;

    @BeforeEach
    public void initEach() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    /**
     * This method covers positive test case for fetching flight list.
     *
     * @throws NoResultFoundException
     * @throws SortInputDataException
     */
    @Test
    void testFlightSearchByOriginAndDestination() throws Exception {

        List<FlightDTO> flightDTOList = TestUtility.getFlightDTOList();
        when(flightService.getFlights("BOM", "DEL", "price", "asc"))
                .thenReturn(flightDTOList);

        ResponseEntity<List<FlightDTO>> flightSearchResponseList = flightController.getFlights("BOM", "DEL", "price", "asc");

        assertThat(flightSearchResponseList.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertNotNull(flightSearchResponseList);
        Assertions.assertEquals(1, flightSearchResponseList.getBody().size());
        assertThat(flightSearchResponseList.getBody().get(0).getFlNo()).isEqualTo("G101");
        assertThat(flightSearchResponseList.getBody().get(0).getOrigin()).isEqualTo("BOM");
        assertThat(flightSearchResponseList.getBody().get(0).getDestination()).isEqualTo("DEL");
        assertThat(flightSearchResponseList.getBody().get(0).getArrivalTime()).isEqualTo(LocalTime.parse("19:40:00"));
        assertThat(flightSearchResponseList.getBody().get(0).getDepartTime()).isEqualTo(LocalTime.parse("18:00:00"));
        assertThat(flightSearchResponseList.getBody().get(0).getPrice()).isEqualTo(BigDecimal.valueOf(150.00));
    }
}
