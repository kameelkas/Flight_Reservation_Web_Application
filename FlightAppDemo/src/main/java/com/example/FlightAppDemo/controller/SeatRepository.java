package com.example.FlightAppDemo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByseatType(int type);

    @Transactional
    @Modifying
    @Query("UPDATE Seat s SET s.seat_taken = true WHERE s.seatID = :seatId")
    void setSeatTaken(Integer seatId);

    List<Seat> findAll();

    @Transactional
    @Query("SELECT s.seat_taken FROM Seat s WHERE s.flight.flight_id = :flight_iD")
    List <Boolean> getSeatStauses(Integer flight_iD);
}
