package com.example.FlightAppDemo;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.*;

@Service
public class CrewServiceImpl implements CrewService {
    CrewRepository crewRepository;

    public CrewServiceImpl(CrewRepository crewRepo) {
        this.crewRepository = crewRepo;
    }

    @Override
    public Integer getFlightIDfromCrewID(Integer CrewID) {
        return crewRepository.getFIDfromCrewID(CrewID);
    }

    @Override
    public Boolean validateCrew(Integer crew_ID, String crew_Password) {
        return crewRepository.checkCredentials(crew_ID, crew_Password);
    }
}
