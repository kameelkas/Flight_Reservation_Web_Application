package com.example.FlightAppDemo;

import java.util.HashSet;
import java.util.Set;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int aircraft_id;

    private String model;
    private int capacity;
    private int num_of_rows;
    private boolean owned;

    public Aircraft() {};

    public Aircraft(String model, int cap,int rownums, boolean Owned){   //aircraftID is auto-generated
        this.model = model;
        this.capacity = cap;
        this.num_of_rows = rownums;
        this.owned = Owned;
    }

}
