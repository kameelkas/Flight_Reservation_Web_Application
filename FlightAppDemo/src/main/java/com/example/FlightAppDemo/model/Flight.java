package com.example.FlightAppDemo;

import com.example.FlightAppDemo.Aircraft;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Flight {  //unidirectional to aircraft, 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "flight_id")
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
    // private Aircraft airplaneUsed;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id", referencedColumnName = "aircraft_id")
    private Aircraft aircraft;

    public Flight() {};
    public Flight(String depDate, String depTime, String arrDate, String ArrTime, String depCity,
            String depCntry, String depAirport, String destCity, String destCntry, String destAirport) {
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
    }

    public int getFlightID() {
        return this.flightID;
    }

    public void setAircraft(Aircraft planePassedIn) {
        this.aircraft = planePassedIn;
    }

}
