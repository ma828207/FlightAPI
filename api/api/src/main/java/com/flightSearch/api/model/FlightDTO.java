package com.flightSearch.api.model;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Time;

    @Data
    public class FlightDTO {
        private String ID;
        private String Origin;
        private String Destination;
        private Time departTime;
        private Time arrivalTime;
        private BigDecimal price;

    }


