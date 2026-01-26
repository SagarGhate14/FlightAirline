package com.cg.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.dto.FlightDTO;
import com.cg.entity.Airline;
import com.cg.entity.AirlineClass;
import com.cg.entity.Flight;
import com.cg.exception.ResourceNotFound;
import com.cg.repository.FlightRepository;

@Service
public class FlightService implements IFlightService{
	@Autowired
	FlightRepository repo;
	
	private static final Logger log = LoggerFactory.getLogger(FlightService.class);
	
	//Using Stream API to retrieve all flight sorted by price ascending
	public List<Flight> getAllFlights(){
		log.info("Getting the all flightss.....");
		return repo.findAll().stream()
				.sorted(Comparator.comparingDouble(Flight::getFlightPrice))
				.toList();
	}
	public Flight saveFlight(Flight flight,Airline airline) {
		log.info("Saving the New Flight");
		flight.setAirline(airline);
		return repo.save(flight);
	}
	
	//Using lambda to handle missing flight record safely
	public Flight findIdByFlight(int id) throws ResourceNotFound{
		log.info("Searching for the flight with ID{} "+id);
		 return repo.findById(id).orElseThrow(()-> new ResourceNotFound("Flight not find by id :"+id));
		
	}
	@Override
	public void deleteById(int id) {
		log.info("Admin Warning : Flight was Deleting....");
		 repo.deleteById(id);;
	}
	
	
	@Override
	public List<Flight> searchFlight(String source, String dest, String airline, AirlineClass className) {
		return repo.searchFlights(source, dest, airline, className);
	}
	
	//Convert entity values to class object values.
	public FlightDTO convertToDTO(Flight flight) {
		 if(flight == null) return null;
		 return new FlightDTO(
				flight.getFlightId(),
				flight.getEnterprise(),
				flight.getFlightPrice(),
				flight.getSource(),
				flight.getDestination(),
				flight.getDepartureDate(),
				flight.getDepartureTime(),
				flight.getArrivalDate(),
				flight.getArrivalTime(),
				flight.getDuration(),
				flight.getTotSeat(),
				flight.getAvailSeat(),
				flight.getClassName(),
				flight.getAirline().getAirName()
				 );
	}
	@Override
	public ResponseEntity<Flight> updateFlight(Flight existFlight, FlightDTO flightdto, Airline air) {
                existFlight.setEnterprise(flightdto.getEnterprise());
                existFlight.setSource(flightdto.getSource());
                existFlight.setDepartureDate(flightdto.getDepartureDate());
                existFlight.setDepartureTime(flightdto.getDepartureTime());
                existFlight.setDestination(flightdto.getDestination());
                existFlight.setArrivalDate(flightdto.getArrivalDate());
                existFlight.setArrivalTime(flightdto.getArrivalTime());
                existFlight.setAvailSeat(flightdto.getAvailSeat());
                existFlight.setTotSeat(flightdto.getTotSeat());
                existFlight.setDuration(flightdto.getDuration());
                existFlight.setFlightPrice(flightdto.getFlightPrice());
                existFlight.setClassName(flightdto.getClassName());
                existFlight.setAirline(air);
                
             return ResponseEntity.ok(repo.save(existFlight));
                
	}
	

}
