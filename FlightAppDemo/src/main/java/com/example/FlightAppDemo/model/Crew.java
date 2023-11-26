package com.example.FlightAppDemo;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int crewID;
    
    private String name;
    private String role;
    private Flight workingFLight;

    public Crew(int cID, String name, String role, Flight flight){
        this.crewID = cID;
        this.name = name;
        this.role = role;
        this.workingFLight = flight;
    }
}
