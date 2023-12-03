package com.example.FlightAppDemo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "membership_id")
    private int membership_ID;

    private String streetAddress;
    private String postalCode;
    private String city;
    private String country;

    @OneToOne  //(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
        
    public Membership() {};

    public Membership(String address, String postal, String city, String country){
        this.streetAddress = address;
        this.postalCode = postal;
        this.city = city;
        this.country = country;
    }

    public void setCustomer(Customer customerPassedIn) {
        this.customer = customerPassedIn;
    }
}
