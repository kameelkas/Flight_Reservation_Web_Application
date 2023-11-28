package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findBydepartureCity(String CityName);

    @Query("SELECT c.destinationCity FROM Flight c")
    List<String> findAlldestinationCity();

    @Query("SELECT f FROM Flight f WHERE f.destinationCity = :destination")
    List<Flight> findAllFlightsByDestination(@Param("destination") String destination);
}
