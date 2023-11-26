package com.example.FlightAppDemo;

public class Payment {
    private int cardNumber;
    private String expiryDate;
    private int CVV;
    private int paidAmount;
    private Ticket purchasedTicket;

    public Payment(int cardNum, String expDate, int cvv, int amount, Ticket ticket){
        this.cardNumber = cardNum;
        this.expiryDate = expDate;
        this.CVV = cvv;
        this.paidAmount = amount;
        this.purchasedTicket = ticket;
    }   
}

