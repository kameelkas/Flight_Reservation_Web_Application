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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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

    @Override
    public Integer getLatestTID() {
        Page<Ticket> page = ticketRepository.findTopByOrderByIdDesc(PageRequest.of(0, 1));
        if (!page.hasContent()) {
            // Handle the case where there are no tickets
            return null;
        }
        return page.getContent().get(0).getTicket_id(); // Assuming there is a getter for ticket_id
    }

    @Override
    public Ticket getTicketByID(Integer TIDPassedIn) {
        return ticketRepository.findByticket_id(TIDPassedIn);
    }

    @Override
    public List<Integer> getCustomersIDsfromFlightID(Integer fligth_ID) {
       return ticketRepository.getCIDsfromFID(fligth_ID);
    }

}
