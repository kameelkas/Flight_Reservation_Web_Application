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
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private int payment_id;
    
    private String cardNumber;
    private String expiryDate;
    private int CVV;
    private float paidAmount;

    @OneToOne  
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
    private Ticket ticketPaidFor;

    public Payment() {};
    public Payment(String cardNum, String expDate, int cvv, float amount){
        this.cardNumber = cardNum;
        this.expiryDate = expDate;
        this.CVV = cvv;
        this.paidAmount = amount;
    }
    
    public void setTicket(Ticket passedInTicket) {
        this.ticketPaidFor = passedInTicket;
    }
}

