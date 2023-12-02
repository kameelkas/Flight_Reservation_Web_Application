package com.example.FlightAppDemo;

import java.util.*;


public interface TicketService {
    public Integer getPriceBySID(Integer SID);
    public void saveTicket(Ticket ticketPassedIn);
    public Integer getLatestTID();
    public Ticket getTicketByID(Integer TIDPassedIn);
    public List<Integer> getCustomersIDsfromFlightID(Integer fligtht_ID);
    //public Integer getCustomerIDfromTicketID(Integer ticketID);
}
