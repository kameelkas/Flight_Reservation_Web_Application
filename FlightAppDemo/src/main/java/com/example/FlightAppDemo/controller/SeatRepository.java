package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat, Integer> {
    List<Seat> findByseatType(int type);
}
