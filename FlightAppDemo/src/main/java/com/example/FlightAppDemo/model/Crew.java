package com.example.FlightAppDemo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int crewID;

    private String name;
    private String role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private Flight flight;
    
    public Crew() {};

    public Crew(int cID, String name, String role){
        this.crewID = cID;
        this.name = name;
        this.role = role;
    }

    public void setFlight(Flight flightPassedIn){
        this.flight  = flightPassedIn;  //what flight that crew member operates on can be null if ground crew or something
    }
}
