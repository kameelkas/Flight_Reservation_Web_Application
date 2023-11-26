package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CrewRepository extends CrudRepository<Crew, Integer> {
    List<Crew> findByrole(String role);
}
