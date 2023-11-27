package com.example.FlightAppDemo;

import java.util.*;

public interface CustomerService {
    public void createCustomer(Customer customer);
    // public String updateCustomer(Customer customer);
    // public String deleteCustomer(String customer_id);
    public Customer getCustomer(Integer customer_id);
    // public List<Customer> getAllCustomers();
    // public List<Customer> getByCustomerName(String name);
}
