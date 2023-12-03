package com.example.FlightAppDemo;

import java.util.*;

public interface FlightService {
    public List<String> getAllDestinations();
    public List<Flight> getFlightsByDestination(String destination);
    public Flight getFlightById(Integer flightID);
    public Integer getPriceByFID(Integer flight_ID);
}
