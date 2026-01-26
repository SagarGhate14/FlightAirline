package com.cg.entity;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="airlines")
public class Airline {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Aid;

	private String AirName;

	@OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
	private List<Flight> flights = new ArrayList<>();

	public Airline() {

	}

	public Airline(int aid, String airName) {
		super();
		Aid = aid;
		AirName = airName;
	}

	public int getAid() {
		return Aid;
	}

	public void setAid(int aid) {
		Aid = aid;
	}

	public String getAirName() {
		return AirName;
	}

	public void setAirName(String airName) {
		AirName = airName;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Airline [Aid=" + Aid + ", AirName=" + AirName + ", flights=" + flights + "]";
	}

}
