package com.example.FlightAppDemo;

public interface CrewService {
    public Integer getFlightIDfromCrewID(Integer CrewID);
    public Boolean validateCrew(Integer crew_ID, String crew_Password);
}
