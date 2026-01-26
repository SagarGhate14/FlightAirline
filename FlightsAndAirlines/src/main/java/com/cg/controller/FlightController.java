package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.dto.FlightDTO;
import com.cg.entity.Airline;
import com.cg.entity.AirlineClass;
import com.cg.entity.Flight;
import com.cg.exception.ResourceNotFound;
import com.cg.service.AirlineService;
import com.cg.service.FlightService;

@Controller
@RequestMapping("/api")
public class FlightController {

	@Autowired
	FlightService service;
	@Autowired
	AirlineService AService;

	@GetMapping("/list") // Getting all the flights
	public String getAllFlights(Model model, @RequestParam(required = false) String source,
			@RequestParam(required = false) String dest, @RequestParam(required = false) String airline,
			@RequestParam(required = false) String className) {

		if (source != null && dest != null && airline != null && className != null) {
			AirlineClass flightClass = (className != null && !className.isEmpty()) ? AirlineClass.valueOf(className)
					: null;

			List<Flight> flights = service.searchFlight(source, dest, airline, flightClass);
			model.addAttribute("airlines", AService.getAllAirlines());
			model.addAttribute("flights", flights);
		} else {
			model.addAttribute("airlines", AService.getAllAirlines());
			model.addAttribute("flights", service.getAllFlights());
		}
		return "Flight/index";
	}

	// Adding new Airline
	@GetMapping("/addAir")
	public String addAir(Model model) {
		model.addAttribute("air", new Airline());
		return "Flight/add-air";
	}

	@PostMapping("/addAir")
	public String addedAir(@ModelAttribute("air") Airline air) {
		AService.saveAir(air);
		return "redirect:/api/list";
	}

	// Adding new Flight
	@GetMapping("/add")
	public String addFlight(Model model) {
		model.addAttribute("airlines", AService.getAllAirlines());
		model.addAttribute("flight", new Flight());
		return "Flight/add-flight";
	}

	@PostMapping("/add")
	public String AddedFlight(@ModelAttribute("flight") Flight flight, @RequestParam("enterprise") int airlineId) {
		Airline airline = AService.findById(airlineId).get();
		service.saveFlight(flight, airline);
		return "redirect:/api/list";
	}

	// Shows the All Airlines
	@GetMapping("/showAirline")
	public String showAirlines(Model model) {
		model.addAttribute("allAirlines", AService.getAllAirlines());
		return "Flight/show-AllAirlines";
	}

	// Delete the Flight using Id
	@GetMapping("/delete/{id}")
	public String deleteFlight(@PathVariable int id) {
		service.deleteById(id);
		return "redirect:/api/list";
	}

	// Booking the Flight
	@GetMapping("/book/{id}")
	public String bookFlight(@PathVariable int id, Model model) throws ResourceNotFound {
		model.addAttribute("flight", service.findIdByFlight(id));
		return "Flight/book";
	}

	// using DTO
	@GetMapping("/update/{id}")
	public String updateFlight(@PathVariable int id, Model model) throws ResourceNotFound {
		Flight flight = service.findIdByFlight(id);
		FlightDTO flightdto = service.convertToDTO(flight);
		model.addAttribute("airlines", AService.getAllAirlines());
		model.addAttribute("flight", flightdto);
		return "Flight/update";
	}

	@PostMapping("/update")
	public String updatedFlight(@ModelAttribute("flight") FlightDTO flightdto, @RequestParam("enterprise") int airId)
			throws ResourceNotFound {
		Flight existFlight = service.findIdByFlight(flightdto.getFlightId());
		Airline air = AService.findById(airId).get();
		service.updateFlight(existFlight, flightdto, air);
		return "redirect:/api/list";
	}

	// Update the Flight Using Id
	@GetMapping("/airDelete/{id}")
	public String deleteAirline(@PathVariable("id") int airId) {
		AService.deleteById(airId);
		return "redirect:/api/showAirline";
	}

	// Update the Airline using Id
	@GetMapping("/airUpdate/{id}")
	public String updateAirline(@PathVariable("id") int airId, Model model) {
		Airline air = AService.findById(airId).get();
		model.addAttribute("air", air);
		return "Flight/updateAir";
	}
}
