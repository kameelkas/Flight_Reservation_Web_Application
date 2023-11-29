package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.*;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByseatType(int type);

    @Transactional
    @Modifying
    @Query("UPDATE Seat s SET s.seat_taken = true WHERE s.seatID = :seatId")
    void setSeatTaken(Integer seatId);
}
