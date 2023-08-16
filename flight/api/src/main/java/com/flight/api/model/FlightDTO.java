package com.flight.api.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;

/**
 * DTO class for Flight API
 */
@Data
public class FlightDTO {
    private String flNo;
    private String origin;
    private String destination;
    private Time departTime;
    private Time arrivalTime;
    private BigDecimal price;
}


