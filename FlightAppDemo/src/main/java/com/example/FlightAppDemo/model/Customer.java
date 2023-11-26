package com.example.FlightAppDemo;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerID;

    private String name;
    private String emailAddr;
    private String phoneNum;

    public Customer() {};
    public Customer(String lol, String email, String phone) {
        this.name = lol;
        this.emailAddr = email;
        this.phoneNum = phone;
    }

    // Getter for customerID
    public int getCustomerID() {
        return customerID;
    }

    // Setter for customerID
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for emailAddr
    public String getEmailAddr() {
        return emailAddr;
    }

    // Setter for emailAddr
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    // Getter for phoneNum
    public String getPhoneNum() {
        return phoneNum;
    }

    // Setter for phoneNum
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}

