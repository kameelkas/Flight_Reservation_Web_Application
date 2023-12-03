package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByCVV(int cvv);
}
