package com.example.FlightAppDemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.FlightAppDemo.response.ResponseHandler;



@RestController
@RequestMapping("/FlightApp/Customer")
public class APIServiceController {

    // Customer guy = new Customer("Rohil", "rohil1710@gmail.com", "4038913266");
    // Customer guy2;

    CustomerService customerService;

    public APIServiceController(CustomerService customerServ){  //passed in so we can call the service function that perform DB operations
        this.customerService = customerServ;
    }

    @GetMapping("/{customer_id}")  //GET TO GET STUFF
    public ResponseEntity<Object> getCustomerDetails(@PathVariable("customer_id") String customer_id) {
        return ResponseHandler.responseBuilder("Here are the requested customer details", HttpStatus.OK, customerService.getCustomer(customer_id));
    }

    @PostMapping("/")   //POST IN HTTP TO ADD STUFF
    public String createCustomer(@RequestBody Customer customerout){
        customerService.createCustomer(customerout);
        return "Customer Made";
    }

    // @PutMapping //PUT IS THE HTTP OPPERATION TO UPDATE STUFF
    // public String updateCustomer(@RequestBody Customer guyFromPostOp){
    //     this.guy2 = guyFromPostOp;
    //     return "Customer Updated";
    // }

    // @DeleteMapping("{customerID}") //PUT IS THE HTTP OPPERATION TO UPDATE STUFF
    // public String deleteCustomer(Integer customerID){
    //     this.guy2 = null;   //hardcoded values so we just make it null
    //     return "Customer Deleted";
    // }
}
