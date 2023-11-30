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
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_id")
    private int ticket_id;

    private float price;
    private String ticket_cancalled;
    //private String ticket_cancellation_insurance;

    @OneToOne  //(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer passenger;

    @OneToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "seat_id")
    private Seat seat;

    @OneToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private Flight flight;


    public Ticket() {};
    public Ticket(float price, String tc){
        this.price = price;
        this.ticket_cancalled = tc;
    }

    public void setPassenger(Customer customerPassedIn) {
        this.passenger = customerPassedIn;
    }

    public void setFlight(Flight flightPassedIn){
        this.flight  = flightPassedIn;  //what flight that crew member operates on, can be null if ground crew or something
    }

    public void setSeat(Seat passedInSeat) {
        this.seat  = passedInSeat;
    }
}