package com.flightSearch.api.dao;

import com.flightSearch.api.entity.Flight;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/*@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    //List<Flight> findBySourceAndDestination(String source, String destination, Sort sortBy);

}*/
@Repository
public interface FlightRepository<T, ID> extends CrudRepository<T, ID> {
    List<Flight> findBySourceAndDestination(String source, String destination,Sort sort);

}


