package com.example.FlightAppDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    TicketRepository ticketRepository;

    @Autowired
	TicketRepository ticketInterface;

    TicketServiceImpl(TicketRepository TicketRepo) {
        this.ticketRepository = TicketRepo;
    }

    @Override
    public Integer getPriceBySID(Integer SID) {
        return ticketRepository.getPriceBySEATID(SID);
    }

    @Override
    public void saveTicket(Ticket ticketPassedIn) {
        ticketInterface.save(ticketPassedIn);
    }
}
