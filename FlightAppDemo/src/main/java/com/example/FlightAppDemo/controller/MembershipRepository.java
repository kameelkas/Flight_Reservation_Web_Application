package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MembershipRepository extends CrudRepository<Membership, Integer> {
    List<Membership> findBycity(String cityName);
}
