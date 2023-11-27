package com.example.FlightAppDemo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seat_id")
    private int seatID;

    private String seatNumber;
    private int seatRow;
    private int seatType;   //for seat type 1 for economy, 2 for business, 3 for first class
    
    @ManyToOne  //(cascade = CascadeType.ALL)   //A flight can have multiple crew members, but each crew member is assigned to only one flight at a time. 
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private Flight flight;

    public Seat() {};

    public Seat(String seatNum, int seatrow, int seatType) {
        this.seatNumber = seatNum;
        this.seatRow = seatrow;
        this.seatType = seatType;
    }

    public void setFlight(Flight flightPassedIn){
        this.flight  = flightPassedIn;  //what flight that crew member operates on, can be null if ground crew or something
    }
    // public int getSeatID() {
    //     return this.seatID;
    // }

}
