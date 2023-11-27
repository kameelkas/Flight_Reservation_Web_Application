package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findByprice(int price);
}