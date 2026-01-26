package com.cg.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.entity.AirlineClass;
import com.cg.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer>{
         
	@Query("SELECT f FROM Flight f WHERE " +
	           "(:source IS NULL OR :source = '' OR f.source = :source) AND " +
	           "(:dest IS NULL OR :dest = '' OR f.destination = :dest) AND " +
	           "(:airline IS NULL OR :airline = '' OR f.airline.AirName = :airline) AND " +
	           "(:className IS NULL OR f.className = :className)")
	    List<Flight> searchFlights(
	        @Param("source") String source, 
	        @Param("dest") String dest, 
	        @Param("airline") String airline, 
	        @Param("className") AirlineClass className
	        );
}
