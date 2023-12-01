package com.example.FlightAppDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService{
    PaymentRepository paymentRepository;

    @Autowired
	PaymentRepository paymentInterface;

    PaymentServiceImpl(PaymentRepository paymentRepo) {
        this.paymentRepository = paymentRepo;
    }

    @Override
    public String savePayment(Payment paymentPassedIn) {
        paymentInterface.save(paymentPassedIn);
        return "Success";
    }

}
