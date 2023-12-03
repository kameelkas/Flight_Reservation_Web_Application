package com.example.FlightAppDemo;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.*;


@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository; //object needed as it extends jparepo to perform crud operations and whatnot for db stuff

    public CustomerServiceImpl(CustomerRepository customerRepo){  //ctor needs to initialize using aggregation for being able to perform db operations
        this.customerRepository = customerRepo;
    }

    @Override
    public String createCustomer(Customer customer){
        customerRepository.save(customer);  //basically what we were doing before in the main function.
        return "Success";
    }

    @Override
    public String updateCustomer(Customer customer) {
        String email = customer.getEmailAddr(); // Assuming this gets the customer email
        
        if (email != null && customerRepository.existsByemailAddr(email)) {
            customerRepository.save(customer);
            return "Customer updated successfully";
        } else {
            return "Customer not found or invalid email"; // Or handle the case where the customer does not exist
        }
    }

    @Override
    public String deleteCustomer(Integer customer_id) {
        customerRepository.deleteById(customer_id);
        return "Success";
    }

    @Override
    public Customer getCustomer(Integer customer_id) {
        return customerRepository.findById(customer_id).get();
    }

    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Override
    public List<String> getAllPasswords() {
        return customerRepository.findAllcustomerPassword();
    }

    @Override
    public Customer findCustomerByEmailAddr(String emailAddress) {
        return customerRepository.findByemailAddr(emailAddress);
    }

    @Override
    public List<Customer> getCustomersFromCustomerIDs(List<Integer> CIDs) {
        return customerRepository.getCustomersFromIDs(CIDs);
    }

    @Override
    public Boolean validateCredentials(String email_address, String customer_pw) {
        return customerRepository.Validate(email_address, customer_pw);
    }
}
