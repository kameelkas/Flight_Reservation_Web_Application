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

    /*Aggregate service instances in ctor of the controller*/
    public APIServiceController(CustomerService customerServ, FlightService flightServ){  //passed in so we can call the service function that perform DB operations
        this.customerService = customerServ;
        flightService = flightServ;
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

    /*API Endpoints for Ticket*/
}
