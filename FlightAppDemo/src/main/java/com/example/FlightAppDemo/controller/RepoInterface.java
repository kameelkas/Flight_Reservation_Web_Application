package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RepoInterface extends CrudRepository<Aircraft, Integer>{
    List<Aircraft> findBymodel(String model);
    List<Aircraft> findByowned(Boolean companyOwned);
}