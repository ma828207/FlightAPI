package com.flight.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * Entity Class for Flight
 */
@Entity
@Table(name = "FLIGHT")
@Data
public class Flight {

    private static final long serialVersionUID = 1234567L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer iD;

    @Column(name = "FL_NUM")
    private String flNumber;
    @Column(name = "ORIGIN")
    private String origin;
    @Column(name = "DESTINATION")
    private String destination;
    @Column(name = "DEPART_TIME")
    private LocalTime departTime;
    @Column(name = "ARRIVAL_TIME")
    private LocalTime arrivalTime;
    @Column(name = "PRICE")
    private BigDecimal price;

}
