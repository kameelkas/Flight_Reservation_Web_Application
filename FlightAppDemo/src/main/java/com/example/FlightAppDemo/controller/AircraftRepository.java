package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

// Then specific interfaces for each entity

public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {
    List<Aircraft> findBymodel(String model);
    List<Aircraft> findByowned(Boolean companyOwned);
}
