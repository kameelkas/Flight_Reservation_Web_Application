package com.example.FlightAppDemo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Table(name="Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Integer customer_id;

    private String name;
    private String emailAddr;
    private String phoneNum;
    private String customerPassword;

    public Customer() {};
    public Customer(String namepassed, String email, String phone, String password) {
        this.name = namepassed;
        this.emailAddr = email;
        this.phoneNum = phone;
        this.customerPassword = password;
    }

    //Getter for customer_id
    public int getCustomerID() {
        return customer_id;
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

    public String getcustomerPassword() {
        return customerPassword;
    }

    // Setter for emailAddr
    public void setEmailAddr(String emailAddrs) {
        this.emailAddr = emailAddrs;
    }

    // Getter for phoneNum
    public String getPhoneNum() {
        return phoneNum;
    }

    // Setter for phoneNum
    public void setPhoneNum(String phoneN) {
        this.phoneNum = phoneN;
    }

    public void setcustomerPassword(String password) {
        this.customerPassword = password;
    }
}

