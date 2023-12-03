package com.example.FlightAppDemo;

import java.util.*;

public interface SeatService {
    public void setSeatTaken(Integer seatID);
    public List<Seat> getAllSeats();
    public List<Boolean> getSeatStatus(Integer flightID);
    public Seat getSeatById(Integer seat_ID);
    public Integer getSeatPriceByID(Integer seatID);
}
