package com.cg.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.dto.FlightDTO;
import com.cg.entity.Airline;
import com.cg.entity.AirlineClass;
import com.cg.entity.Flight;
import com.cg.exception.ResourceNotFound;

public interface IFlightService {
       public List<Flight> getAllFlights();
       public Flight saveFlight(Flight flight,Airline airline);
       public Flight findIdByFlight(int id) throws ResourceNotFound;
       public void deleteById(int id);
       public ResponseEntity<Flight> updateFlight(Flight flight,FlightDTO flightdto,Airline air);
       public List<Flight> searchFlight(String source,String dest,String airline,AirlineClass className);
}
