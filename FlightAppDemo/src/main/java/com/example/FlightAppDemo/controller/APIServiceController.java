package com.example.FlightAppDemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.FlightAppDemo.response.ResponseHandler;



@RestController
@RequestMapping("/FlightApp")
public class APIServiceController {


    /*Instances of Service Classes*/
    CustomerService customerService;
    FlightService flightService;
    TicketService ticketService;
    SeatService seatService;
    EmailService emailService;

    /*Aggregate service instances in ctor of the controller*/
    public APIServiceController(CustomerService customerServ, FlightService flightServ, TicketService ticketServ, SeatService seatServ, EmailService emailServ){  //passed in so we can call the service function that perform DB operations
        this.customerService = customerServ;
        this.flightService = flightServ;
        this.ticketService = ticketServ;
        this.seatService = seatServ;
        this.emailService = emailServ;
    }

    /*API Endpoints for Customer*/
    @GetMapping("/Customer/Get/{customer_id}")  //GET TO GET STUFF
    public  ResponseEntity<Object> getCustomerDetails(@PathVariable("customer_id") Integer customer_id) {
        return ResponseHandler.responseBuilder("Here are the requested customer details", HttpStatus.OK, customerService.getCustomer(customer_id));
    }

    @GetMapping("/Customer/GetAll")  //GET TO GET STUFF
    public List<Customer> getAllCustomerDetails() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/Customer/GetAllPasswords")
    public List<String> getAllCustomerPasswordDetails() {
        return customerService.getAllPasswords();
    }

    @PostMapping("/Customer/Create")   //POST IN HTTP TO ADD STUFF
    public String createCustomerDetails(@RequestBody Customer customerNew){
        String welcomeMessage = String.format("Hi %s,\n\n" +
            "We at the ENSF 480 FlightApp would like to welcome you to our Flight App " +
            "and hope you have a good experience booking your travel experiences.\n\n" +
            "Best Regards,\nENSF480 Flight App Team", customerNew.getName());
        emailService.sendEmail(customerNew.getEmailAddr(), "Welcome to your ENSF480 Flight Account!", welcomeMessage);
        customerService.createCustomer(customerNew);
        return "Customer Made";
    }

    @PutMapping("/Customer/Put")  //PUT IS THE HTTP OPPERATION TO UPDATE STUFF
    public String updateCustomerDetails(@RequestBody Customer customerUpdate){
        customerService.updateCustomer(customerUpdate);
        return "Customer Updated";
    }

    @DeleteMapping("/Customer/Delete/{customerID}") //PUT IS THE HTTP OPPERATION TO UPDATE STUFF
    public String deleteCustomerDetails(Integer customerID){
        customerService.deleteCustomer(customerID);
        return "Customer Deleted";
    }

    /*API Endpoints for Flight*/

    @GetMapping("/Flight/GetAllDestinations")
    public List<String> getAllDestinationDetails() {
        return flightService.getAllDestinations();
    }

    @GetMapping("/Flight/GetAllFlightsByDestination/{destination}")
    public List<Flight> getFlightsByDestination(@PathVariable("destination") String destination) {
        return flightService.getFlightsByDestination(destination);
    }

    /*API Endpoints for Ticket*/ //mostly to get price based on seat_id
    @GetMapping("/Ticket/GetPrice/{SEAT_ID}")
    public Integer getPriceBySeatID(@PathVariable("SEAT_ID") Integer seatID) {
        return ticketService.getPriceBySID(seatID);
    }

    /*API Endpoints for Seatt*/
    @PatchMapping("/Seat/SeatTaken/{seatID}")
    public void postSeatTaken(@PathVariable("seatID") Integer seat_ID) {
        seatService.setSeatTaken(seat_ID);
    }

    @GetMapping("/Seat/GetAllSeats")
    public List<Seat> getAllSeatDetails() {
        return seatService.getAllSeats();
    }

    @GetMapping("/Seat/GetAllTakenOrNot/ByFlightID/{flightID}")
    public List<Boolean> getSeatTakenOrNot(@PathVariable("flightID") Integer flightID) {
        return seatService.getSeatStatus(flightID);
    }



    //@Post mapping for payment and then call emailservice and fill in body with receipt and whatnot, flight_id, etc
}
