package com.example.FlightAppDemo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "flight_id")
    private int flight_id;

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
    private Integer flightPrice;

    public Flight() {};
    public Flight(String depDate, String depTime, String arrDate, String ArrTime, String depCity,
            String depCntry, String depAirport, String destCity, String destCntry, String destAirport, Integer fp) {
        
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
        this.flightPrice = fp;
    }

    @ManyToOne
    @JoinColumn(name = "aircraft_id", referencedColumnName = "aircraft_id")
    private Aircraft aircraft;

    // Getters
    public int getflight_id() {
        return flight_id;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getDepartureCountry() {
        return departureCountry;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public Integer getflightPrice() {
        return this.flightPrice;
    }

    // Setters
    public void setFlight_ID(int flight_ID) {
        this.flight_id = flight_ID;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setDepartureCountry(String departureCountry) {
        this.departureCountry = departureCountry;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public void setflightPrice(Integer fp) {
        this.flightPrice = fp;
    }
}


