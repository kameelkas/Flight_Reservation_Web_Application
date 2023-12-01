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
public class MembershipServiceImpl implements MembershipService {
    MembershipRepository membershipRepository;

    @Autowired
    MembershipRepository memberInterface;

    MembershipServiceImpl(MembershipRepository membershipRepo) {
        this.membershipRepository = membershipRepo;
    }

    public void saveMembership(Membership MembershipPassedIn) {
        memberInterface.save(MembershipPassedIn);
    }
}
