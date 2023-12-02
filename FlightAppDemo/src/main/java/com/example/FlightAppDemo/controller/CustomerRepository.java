package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByname(String name);
    boolean existsByemailAddr(String email);
    
    @Query("SELECT c.customerPassword FROM Customer c")
    List<String> findAllcustomerPassword();
    //findall();

    Customer findByemailAddr(String email);

    @Query("SELECT c FROM Customer c WHERE c.id IN :CIDs")
    public List<Customer> getCustomersFromIDs(List<Integer> CIDs);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.emailAddr = :email AND c.customerPassword = :password")
    Boolean Validate(@Param("email") String email, @Param("password") String password);
}
