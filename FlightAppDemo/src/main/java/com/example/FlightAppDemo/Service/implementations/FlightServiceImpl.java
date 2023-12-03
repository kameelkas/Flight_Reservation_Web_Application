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
public class FlightServiceImpl implements FlightService {
    FlightRepository flightRepository;

    FlightServiceImpl(FlightRepository flightRepo) {
        this.flightRepository = flightRepo;
    }

    @Override
    public List<String> getAllDestinations() {
        return flightRepository.findAlldestinationCity();
    }

    @Override
    public List<Flight> getFlightsByDestination(String destination) {
        return flightRepository.findAllFlightsByDestination(destination);
    }

    @Override
    public Flight getFlightById(Integer flightID) {
        return flightRepository.findByflight_id(flightID);
    }

    @Override
    public Integer getPriceByFID(Integer flight_ID) {
        return flightRepository.getPriceUsingID(flight_ID);
    }
}
