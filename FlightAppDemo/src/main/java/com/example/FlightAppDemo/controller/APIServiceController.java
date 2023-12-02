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
    PaymentService paymentService;
    MembershipService membershipService;
    CrewService crewService;

    /*Aggregate service instances in ctor of the controller*/
    public APIServiceController(CustomerService customerServ, FlightService flightServ, TicketService ticketServ, SeatService seatServ, EmailService emailServ, PaymentService paymentServ, MembershipService membershipServ, CrewService crewServ){  //passed in so we can call the service function that perform DB operations
        this.customerService = customerServ;
        this.flightService = flightServ;
        this.ticketService = ticketServ;
        this.seatService = seatServ;
        this.emailService = emailServ;
        this.paymentService = paymentServ;
        this.membershipService = membershipServ;
        this.crewService = crewServ;
    }

    /*API Endpoints for Customer & Membership*/
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

    @PostMapping("/Customer/Create/{address}/{postal}/{city}/{country}")   //POST IN HTTP TO ADD STUFF //CREATING MEMBER AS SOON AS CUSTOMER SIGNS UP
    public String createCustomerDetails(@RequestBody Customer customerNew, @PathVariable("address") String address, @PathVariable("postal") String postal, @PathVariable("city") String city, @PathVariable("country") String country){     /////UNCOMMENT THIS BEFORE SUBMISSION.
        String welcomeMessage = String.format("Hi %s,\n\n" +
            "We at the ENSF 480 FlightApp would like to welcome you to our Flight App " +
            "and hope you have a good experience booking your travel experiences.\n\n" +
            "Best Regards,\nENSF480 Flight App Team", customerNew.getName());
        emailService.sendEmail(customerNew.getEmailAddr(), "Welcome to your ENSF480 Flight Account!", welcomeMessage);
        customerService.createCustomer(customerNew);

        Membership membership = new Membership(address, postal, city, country);
        membership.setCustomer(customerNew);
        membershipService.saveMembership(membership);
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

    @GetMapping("/Customer/Validate/{email}/{customerPW}")
    public Boolean validateCustomerCredentials(@PathVariable("email") String email, @PathVariable("customerPW") String customerPW) {
        return customerService.validateCredentials(email, customerPW);  //returns customerID based on user credentials.
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

    @GetMapping("/Flight/GetPassengerList/{crewID}")
    public List<Customer> getPassengerListDetails(@PathVariable("crewID") Integer crewID) {
        //get flight_id based on crew_id, call to crewServ
        Integer flightID = crewService.getFlightIDfromCrewID(crewID);
        //get ticket_ids based on flight_id, call to ticketService
        List<Integer> customerIDs = ticketService.getCustomersIDsfromFlightID(flightID);
        //get customer_ids associated with those ticket_ids, call to ticketService
        //get customers based on those customer_ids, and hold in list<customer>
        List<Customer> customers = customerService.getCustomersFromCustomerIDs(customerIDs);
        //send them back to the frontend, return
        return customers;
    }

    /*API Endpoints for Ticket*/ //mostly to get price based on seat_id
    @GetMapping("/Ticket/GetPrice/{SEAT_ID}")
    public Integer getPriceBySeatID(@PathVariable("SEAT_ID") Integer seatID) {
        return ticketService.getPriceBySID(seatID);
    }

    /*API Endpoints for Seatt*/
    @PostMapping("/Seat/SeatTaken/{seatID}")
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

    /*API Endpoints for Ticket*/
    @PostMapping("/Ticket/Create/{emailAddr}/{flight_id}/{seat_id}/{price}/{ticketCancelled}/{cancellationInsurance}")
    public ResponseEntity<?> createTicket(@PathVariable("emailAddr") String emailAddr, 
                                        @PathVariable("flight_id") Integer flight_id, 
                                        @PathVariable("seat_id") Integer seat_id,
                                        @PathVariable("price") float price,
                                        @PathVariable("ticketCancelled") Boolean ticketCancelled,
                                        @PathVariable("cancellationInsurance") Boolean cancellationInsurance) {
        
        // Fetch or create Customer, Flight, and Seat entities based on path variables
        Customer customer = customerService.findCustomerByEmailAddr(emailAddr);
        Flight flight = flightService.getFlightById(flight_id);
        Seat seat = seatService.getSeatById(seat_id);

        if (customer == null || flight == null || seat == null) {
            // Handle the case where one of the entities doesn't exist
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data provided");
        }

        // Create a new Ticket instance
        Ticket ticket = new Ticket(price, ticketCancelled, cancellationInsurance);

        // Set associated entities
        ticket.setPassenger(customer);
        ticket.setFlight(flight);
        ticket.setSeat(seat);

        // Save the Ticket entity
        ticketService.saveTicket(ticket);  //uses saving logic from the main function in the service layer
        return ResponseEntity.ok("Ticket created successfully");
    }

    //@Post mapping for payment and then call emailservice and fill in body with receipt and whatnot, flight_id, etc
    // @GetMapping("/Ticket/GetRecentTID")
    // public Integer getRecentTicketID() {
    //     return ticketService.getLatestTID(); //JUST USE THIS DONT NEED TO MAKE ANOTHER API CALL
    // }

    /*API Endpoints for Payments*/
    @PostMapping("/Payment/Create/{cardNum}/{expDate}/{cvv}/{paidAmount}")
    public String createPayment(@PathVariable("cardNum") String cardNum, @PathVariable("expDate") String expDate, @PathVariable("cvv") Integer cvv, @PathVariable("paidAmount") Integer paidAmount) {

        //Creating the ticket object that payment because of the foreign key
        Ticket ticket = ticketService.getTicketByID(ticketService.getLatestTID());  //get lastest ticket from the database.

        Payment payment = new Payment(cardNum, expDate, cvv, paidAmount);

        payment.setTicket(ticket);

        //saving the new payment
        paymentService.savePayment(payment);  //uses saving logic from the main function in the service layer.

        //Getting customer_id using ticket_id
        //ticketService.getCustomerIDfromTicketID(ticketService.getLatestTID());

        //Getting customer using customer_id

        // /*Payment confirmation and receipt with ticket*/
        // String welcomeMessage = String.format("Hi %s,\n\n" +
        //     "---------------------------TICKET----------------------------------\n\n"  +
        //     "This email is being sent to confirm your booking on flight %d to %s. "    +
        //     "Your flight is on &s at %s from"                                          +
        //     "Your ticket ID is: ", Name)                                               +
        //     "---------------------------RECEIPT----------------------------------\n\n" +
        //     "Your payment using a Visa Credit Card ending with %d has been approved."); 

        // emailService.sendEmail(customerNew.getEmailAddr(), "Payment Confirmation and Flight Details", welcomeMessage);
        return "Payment succesfully sent and receipt sent with ticket details";
    }

    /*API Endpoints for Crew*/
    @GetMapping("/Crew/Validation/{crewID}/{crewPassword}")
    public Boolean validateCrewCredentials(@PathVariable("crewID") Integer crewID, @PathVariable("crewPassword") String crewPassword) {
        return crewService.validateCrew(crewID, crewPassword);
    }

}
