package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Airline;
import com.cg.repository.IAirlineRepository;

@Service
public class AirlineService {
      
	@Autowired
	 IAirlineRepository repo;
	
	 public List<Airline> getAllAirlines(){
		return repo.findAll();
	 }
	 public Airline saveAir(Airline air) {
		 return repo.save(air);
	 }
	 public Optional<Airline> findById(int id) {
		 Optional<Airline> air = repo.findById(id);
		 return air;
	 }
	 public void deleteById(int id) {
		 repo.deleteById(id);
	 }
}
