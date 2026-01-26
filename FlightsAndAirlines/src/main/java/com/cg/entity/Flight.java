package com.cg.entity;



import java.time.LocalDate;
import java.time.LocalTime;





import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="flights")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightId;

	private String enterprise;
	private double flightPrice;
	private String source;
	private String destination;

	@JsonFormat(pattern = "h:mm a", locale = "en")
	private LocalTime departureTime;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate arrivalDate;

	@JsonFormat(pattern = "h:mm a", locale = "en")
	private LocalTime arrivalTime;

	private int duration;
	private int totSeat;
	private int availSeat;

	@Enumerated(EnumType.STRING)
	private AirlineClass className;

	@ManyToOne
	@JoinColumn(name = "airline_id")
	private Airline airline;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate departureDate;

	public Flight() {

	}

	public Flight(int flightId, String enterprise, double flightPrice, String source, String destination,
			LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
			int duration, int totSeat, int availSeat, AirlineClass className, Airline airline) {
		super();
		this.flightId = flightId;
		this.enterprise = enterprise;
		this.flightPrice = flightPrice;
		this.source = source;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		this.totSeat = totSeat;
		this.availSeat = availSeat;
		this.className = className;
		this.airline = airline;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public double getFlightPrice() {
		return flightPrice;
	}

	public void setFlightPrice(double flightPrice) {
		this.flightPrice = flightPrice;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getTotSeat() {
		return totSeat;
	}

	public void setTotSeat(int totSeat) {
		this.totSeat = totSeat;
	}

	public int getAvailSeat() {
		return availSeat;
	}

	public void setAvailSeat(int availSeat) {
		this.availSeat = availSeat;
	}

	public AirlineClass getClassName() {
		return className;
	}

	public void setClassName(AirlineClass className) {
		this.className = className;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

}
