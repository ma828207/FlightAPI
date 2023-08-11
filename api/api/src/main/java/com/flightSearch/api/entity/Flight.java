package com.flightSearch.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;

@Entity
@Table(name = "FLIGHT")
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "FL_NUM")
    private String Fl_Number;
    @Column(name = "ORIGIN")
    private String Origin;
    @Column(name = "DESTINATION")
    private String Destination;
    @Column(name = "DEPART_TIME")
    private Time departTime;
    @Column(name = "ARRIVAL_TIME")
    private Time arrivalTime;
    @Column(name = "PRICE")
    private BigDecimal price;

}
