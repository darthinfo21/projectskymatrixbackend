package com.coforge.project.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coforge.project.booking.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	
    List<Booking> findByUserId(Long userId);

}