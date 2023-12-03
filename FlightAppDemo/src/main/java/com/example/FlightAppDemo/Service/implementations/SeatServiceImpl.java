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
public class SeatServiceImpl implements SeatService {
    SeatRepository seatRepository;

    SeatServiceImpl(SeatRepository seatRepo) {
        this.seatRepository = seatRepo;
    }

    @Override
    public void setSeatTaken(Integer seatID) {
        seatRepository.setSeatTaken(seatID);
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public List<Boolean> getSeatStatus(Integer flight_ID) {
        return seatRepository.getSeatStauses(flight_ID);
    }

    @Override
    public Seat getSeatById(Integer seat_ID) {
        return seatRepository.findByseatID(seat_ID);
    }

    @Override
    public Integer getSeatPriceByID(Integer seatID) {
        return seatRepository.getPriceBySID(seatID);
    }


}
