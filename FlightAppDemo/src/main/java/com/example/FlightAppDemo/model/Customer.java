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

    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(name = "customer_id")
    // private Integer customer_id;

    private String name;

    @Id
    @Column(name = "email_address")
    private String email_address;

    private String phoneNum;

    public Customer() {};
    public Customer(String lol, String email, String phone) {
        this.name = lol;
        this.email_address = email;
        this.phoneNum = phone;
    }

    // Getter for customerID

    // Setter for customerID
    // public void setCustomerID(int customerID) {
    //     this.customerID = customerID;
    // }

    // // Getter for name
    // public String getName() {
    //     return name;
    // }

    // // Setter for name
    // public void setName(String name) {
    //     this.name = name;
    // }

    // // Getter for emailAddr
    // public String getEmailAddr() {
    //     return emailAddr;
    // }

    // // Setter for emailAddr
    // public void setEmailAddr(String emailAddr) {
    //     this.emailAddr = emailAddr;
    // }

    // // Getter for phoneNum
    // public String getPhoneNum() {
    //     return phoneNum;
    // }

    // // Setter for phoneNum
    // public void setPhoneNum(String phoneNum) {
    //     this.phoneNum = phoneNum;
    // }

    // @Override
    // public String toString() {
    //     return "Customer{" +
    //             "customerID=" + customer_id +
    //             ", name='" + name + '\'' +
    //             ", emailAddr='" + emailAddr + '\'' +
    //             ", phoneNum='" + phoneNum + '\'' +
    //             '}';
    // }
}

