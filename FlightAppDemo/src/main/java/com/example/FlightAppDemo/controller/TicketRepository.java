package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByprice(int price);

    @Query("SELECT t.price FROM Ticket t WHERE t.seat.seatID = :seat_id")
    Integer getPriceBySEATID(@Param("seat_id") Integer seat_id);

    @Query(value = "SELECT t FROM Ticket t ORDER BY t.ticket_id DESC")
    Page<Ticket> findTopByOrderByIdDesc(Pageable pageable);

    @Query("SELECT t FROM Ticket t WHERE t.ticket_id = :ticket_id")
    Ticket findByticket_id(Integer ticket_id);


    @Query("SELECT t.passenger.customer_id FROM Ticket t WHERE t.flight.flight_id = :flight_id")
    List<Integer> getCIDsfromFID(Integer flight_id);

    @Query("SELECT t.passenger.customer_id FROM Ticket t WHERE t.ticket_id = :TID")
    public Integer getCIDfromTID(@Param("TID") Integer TID);

    @Query("SELECT t.flight.flight_id FROM Ticket t WHERE t.ticket_id = :TID")
    public Integer getFIDfromTID(@Param("TID") Integer TID);

    @Modifying
    @Query("UPDATE Ticket t SET t.ticket_cancelled = true WHERE t.ticket_id = :ticketID")
    public Integer cancelTicketUsingTID(Integer ticketID);
}