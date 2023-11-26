package com.example.FlightAppDemo;

public class Membership {
    private int membershipID;
    private String startDate;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String country;
    private String terminationDate;
    private Customer registeredUser;

    public Membership(int mID, String start, String address, String postal, String city, String country, String end, Customer customer){
        this.membershipID = mID;
        this.startDate = start;
        this.streetAddress = address;
        this.postalCode = postal;
        this.city = city;
        this.country = country;
        this.terminationDate = end;
        this.registeredUser = customer;
    }
}
