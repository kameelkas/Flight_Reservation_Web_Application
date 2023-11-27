package com.example.FlightAppDemo;

import java.util.*;

public interface CustomerService {
    public String createCustomer(Customer customer);
    public String updateCustomer(Customer customer);
    public String deleteCustomer(Integer customer_id);
    public Customer getCustomer(Integer customer_id);
    public List<Customer> getAllCustomers();
}
