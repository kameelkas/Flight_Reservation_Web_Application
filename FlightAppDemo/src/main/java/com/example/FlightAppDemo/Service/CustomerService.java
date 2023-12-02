package com.example.FlightAppDemo;

import java.util.*;

public interface CustomerService {
    public String createCustomer(Customer customer);
    public String updateCustomer(Customer customer);
    public String deleteCustomer(Integer customer_id);
    public Customer getCustomer(Integer customer_id);
    public List<Customer> getAllCustomers();
    public List<String> getAllPasswords();
    public Customer findCustomerByEmailAddr(String emailAddress);
    //sendMail() //call this in service functions for post payment and post customer for welcome email and payment receipt.

    public List<Customer> getCustomersFromCustomerIDs(List<Integer> CIDs);

    public Boolean validateCredentials(String email_address, String customer_pw);
}
