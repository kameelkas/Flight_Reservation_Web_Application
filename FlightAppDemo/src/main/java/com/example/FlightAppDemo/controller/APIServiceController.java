package com.example.FlightAppDemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/FlightApp")
public class APIServiceController {

    Customer guy = new Customer(1, "Rohil", "rohil1710@gmail.com", "4038913266");
    Customer guy2;

    @GetMapping("{customerID}")  //GET TO GET STUFF
    public Customer getCustomerDetails(Integer customerID) {
        return guy2;
    }

    @PostMapping  //POST IN HTTP TO ADD STUFF
    public String createCustomer(@RequestBody Customer guyFromPostOp){
        this.guy2 = guyFromPostOp;
        return "Customer Made";
    }

    @PutMapping //PUT IS THE HTTP OPPERATION TO UPDATE STUFF
    public String updateCustomer(@RequestBody Customer guyFromPostOp){
        this.guy2 = guyFromPostOp;
        return "Customer Updated";
    }

    @DeleteMapping("{customerID}") //PUT IS THE HTTP OPPERATION TO UPDATE STUFF
    public String deleteCustomer(Integer customerID){
        this.guy2 = null;   //hardcoded values so we just make it null
        return "Customer Deleted";
    }
}
