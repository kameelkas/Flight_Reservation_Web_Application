package com.example.FlightAppDemo;

import java.util.*;

public interface TicketService {
    public Integer getPriceBySID(Integer SID);
    public void saveTicket(Ticket ticketPassedIn);
}
