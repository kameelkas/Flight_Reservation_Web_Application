package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByname(String name);
    boolean existsByemailAddr(String email);
    
    @Query("SELECT c.customerPassword FROM Customer c")
    List<String> findAllcustomerPassword();
    //findall();
}
