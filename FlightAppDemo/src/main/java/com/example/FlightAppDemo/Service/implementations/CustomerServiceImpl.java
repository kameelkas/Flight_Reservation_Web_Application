package com.example.FlightAppDemo;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository; //object needed as it extends jparepo to perform crud operations and whatnot for db stuff

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerServiceImpl(CustomerRepository customerRepo){  //ctor needs to initialize using aggregation for being able to perform db operations
        this.customerRepository = customerRepo;
    }

    @Override
    public String createCustomer(Customer customer){
        customerRepository.save(customer);  //basically what we were doing before in the main function.
        System.out.println(customer);
        return "Success";
    }

    @Override
    public Customer getCustomer(Integer customer_id) {
        System.out.println("Received customer_id: " + customer_id); // Print the customer_id
        return customerRepository.findById(customer_id).get();
    }
}
