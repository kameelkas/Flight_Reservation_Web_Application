package com.example.FlightAppDemo;

import java.util.*;

public interface SeatService {
    public void setSeatTaken(Integer seatID);
    public List<Seat> getAllSeats();
}
