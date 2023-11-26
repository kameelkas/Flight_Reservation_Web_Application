package com.example.FlightAppDemo;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int flightID;

    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private String departureCity;
    private String departureCountry;
    private String departureAirport;
    private String destinationCity;
    private String destinationCountry;
    private String destinationAirport;
    private Aircraft plane;

    @ManyToOne
    @JoinColumn(name = "aircraft_id", referencedColumnName = "aircraft_id")
    private Aircraft aircraft;

    public Flight() {};
    public Flight(int fID, String depDate, String depTime, String arrDate, String ArrTime, String depCity,
            String depCntry, String depAirport, String destCity, String destCntry, String destAirport, Aircraft plane) {
        this.flightID = fID;
        this.departureDate = depDate;
        this.departureTime = depTime;
        this.arrivalDate = arrDate;
        this.arrivalTime = ArrTime;
        this.departureCity = depCity;
        this.departureCountry = depCntry;
        this.departureAirport = depAirport;
        this.destinationCity = destCity;
        this.destinationCountry = destCntry;
        this.destinationAirport = destAirport;
        this.plane = plane;
    }

    public int getFlightID() {
        return this.flightID;
    }

}
