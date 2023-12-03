package com.example.FlightAppDemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CrewRepository extends JpaRepository<Crew, Integer> {
    List<Crew> findByrole(String role);

    @Query("SELECT c.flight.flight_id FROM Crew c WHERE c.crewID = :crewId") 
    Integer getFIDfromCrewID(@Param("crewId") Integer CrewID);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Crew c WHERE c.id = :cID AND c.crewPassword = :cp")
    public Boolean checkCredentials(Integer cID, String cp);
}
