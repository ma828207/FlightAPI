package com.flight.api.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * DTO class for Flight API
 */
@Data
public class FlightDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String flNo;
    private String origin;
    private String destination;
    private LocalTime departTime;
    private LocalTime arrivalTime;
    private BigDecimal price;
    private Long durationInMins;
}


