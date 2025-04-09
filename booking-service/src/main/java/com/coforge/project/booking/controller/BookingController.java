package com.coforge.project.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.project.booking.dto.BookingDTO;
import com.coforge.project.booking.dto.BookingDetailsDTO;
import com.coforge.project.booking.model.Booking;
import com.coforge.project.booking.service.BookingService;

@CrossOrigin(origins = "http://localhost:4200") // Replace with your frontend URL
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

//    @PostMapping("/book")
//    public ResponseEntity<String> bookFlight(@RequestBody BookingDTO bookingDTO) {
//        try {
//            bookingService.bookFlight(bookingDTO);
//            return ResponseEntity.ok("Flight booked successfully!");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
    @PostMapping("/book")
    public ResponseEntity<String> bookFlight(@RequestBody BookingDTO bookingDTO) {
        try {
            Booking booking = bookingService.bookFlight(bookingDTO);
            String responseMessage = "Flight booked successfully! Booking ID: " + booking.getId();
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        try {
            bookingService.cancelBooking(id);
            return ResponseEntity.ok("Booking cancelled successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{bookingId}/details")
    public ResponseEntity<BookingDetailsDTO> getBookingDetails(@PathVariable Long bookingId) {
        BookingDetailsDTO bookingDetails = bookingService.getBookingDetails(bookingId);
        return ResponseEntity.ok(bookingDetails);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }
}
