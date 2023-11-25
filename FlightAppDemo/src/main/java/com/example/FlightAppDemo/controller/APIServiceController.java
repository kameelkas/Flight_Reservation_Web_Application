package com.example.FlightAppDemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/FlightApp")
public class APIServiceController {

    @GetMapping("{customerID}")
    public Customer getCustomerDetails(Integer customerID) {
        return new Customer(1, "Rohil", "rohil1710@gmail.com", "4038913266");
    }
}
