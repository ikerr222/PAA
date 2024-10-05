 package paa.airline.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="flightNumber")
public class Flight implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue 
    private Long flightNumber;
	
	@ManyToOne
    private Airport origin;
	@ManyToOne
    private Airport destination;
	@ManyToOne 
    private AircraftType aircraft;

    @OneToMany (mappedBy="flight")
    private List<Ticket> tickets;

    public Flight() {}

    public Flight(Airport origin, Airport destination, AircraftType aircraft) {
        this.origin = origin;
        this.destination = destination;
        this.aircraft = aircraft;
        this.tickets = new ArrayList<>();
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport from) {
        this.origin = from;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport to) {
        this.destination = to;
    }



    public AircraftType getAircraft() {
        return aircraft;
    }

    public void setAircraft(AircraftType aircraft) {
        this.aircraft = aircraft;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        return flightNumber.equals(flight.flightNumber);
    }

    @Override
    public int hashCode() {
        return flightNumber.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s-%s", origin.getIataCode(), destination.getIataCode());

    }
}

