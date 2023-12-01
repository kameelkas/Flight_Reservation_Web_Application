package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByprice(int price);

    @Query("SELECT t.price FROM Ticket t WHERE t.seat.seatID = :seat_id")
    Integer getPriceBySEATID(@Param("seat_id") Integer seat_id);

    @Query(value = "SELECT t FROM Ticket t ORDER BY t.ticket_id DESC")
    Page<Ticket> findTopByOrderByIdDesc(Pageable pageable);
}