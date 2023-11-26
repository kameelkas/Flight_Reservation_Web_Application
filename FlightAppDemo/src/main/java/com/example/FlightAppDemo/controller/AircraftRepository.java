package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// Then specific interfaces for each entity

public interface AircraftRepository extends CrudRepository<Aircraft, Integer> {
    List<Aircraft> findBymodel(String model);
    List<Aircraft> findByowned(Boolean companyOwned);
    // Specific methods for Aircraft entity
    // ...
}


// public interface RepoInterface extends CrudRepository<Aircraft, Integer>{
//     List<Aircraft> findBymodel(String model);
//     List<Aircraft> findByowned(Boolean companyOwned);
// }
