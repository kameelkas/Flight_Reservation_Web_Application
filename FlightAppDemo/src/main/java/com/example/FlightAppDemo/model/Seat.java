package com.example.FlightAppDemo;

public class Seat {
    private int seatID;
    private String seatNumber;
    private int seatType;
    private Aircraft aircraft;

    public Seat(int seatId, String seatNum, int seatType, Aircraft aircraft) {
        this.seatID = seatId;
        this.seatNumber = seatNum;
        this.seatType = seatType;
        this.aircraft = aircraft;
    }

    public int getSeatID() {
        return this.seatID;
    }

}
