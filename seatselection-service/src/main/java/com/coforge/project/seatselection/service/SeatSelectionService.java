package com.coforge.project.seatselection.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.coforge.project.seatselection.model.SeatSelection;
import com.coforge.project.seatselection.repository.SeatSelectionRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;

@Service
public class SeatSelectionService {

    @Autowired
    private SeatSelectionRepository seatSelectionRepository;

    
    @Transactional
    @CircuitBreaker(name = "selectSeat", fallbackMethod = "selectSeatFallback")
    public SeatSelection selectSeat(SeatSelection seatSelection) {
    	try {
            return seatSelectionRepository.save(seatSelection);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException("The seat selection was updated by another transaction. Please try again.");
        }
    	}

    @CircuitBreaker(name = "getSeatsByFlightId", fallbackMethod = "getSeatsByFlightIdFallback")
    public List<SeatSelection> getSeatsByFlightId(Long flightId) {
        return seatSelectionRepository.findByFlightId(flightId);
    }

    @CircuitBreaker(name = "getSeatsByUserId", fallbackMethod = "getSeatsByUserIdFallback")
    public List<SeatSelection> getSeatsByUserId(Long userId) {
        return seatSelectionRepository.findByUserId(userId);
    }
    
 // Fallback methods
    public SeatSelection selectSeatFallback(SeatSelection seatSelection, Throwable t) {
        SeatSelection fallbackSeatSelection = new SeatSelection();
        fallbackSeatSelection.setMessage("Fallback: Unable to select seat. Please try again later.");
        return fallbackSeatSelection;
    }

    public List<SeatSelection> getSeatsByFlightIdFallback(Long flightId, Throwable t) {
        SeatSelection fallbackSeatSelection = new SeatSelection();
        fallbackSeatSelection.setMessage("Fallback: Unable to retrieve seats by flight ID. Please try again later.");
        return Collections.singletonList(fallbackSeatSelection);
    }

    public List<SeatSelection> getSeatsByUserIdFallback(Long userId, Throwable t) {
        SeatSelection fallbackSeatSelection = new SeatSelection();
        fallbackSeatSelection.setMessage("Fallback: Unable to retrieve seats by user ID. Please try again later.");
        return Collections.singletonList(fallbackSeatSelection);
    }
}