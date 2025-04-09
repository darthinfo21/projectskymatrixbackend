package com.coforge.project.booking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.coforge.project.booking.dto.BookingDTO;
import com.coforge.project.booking.service.BookingService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBookFlightSuccess() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setUserId(1L);
        bookingDTO.setFlightId(1L);
        bookingDTO.setNumberOfPassengers(2);
        bookingDTO.setBookingTime(LocalDateTime.now());

        doNothing().when(bookingService).bookFlight(bookingDTO);

        HttpEntity<BookingDTO> request = new HttpEntity<>(bookingDTO);
        ResponseEntity<String> response = restTemplate.postForEntity(createURLWithPort("/api/bookings/book"), request, String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Flight booked successfully!", response.getBody());
    }

    @Test
    public void testBookFlightFailure() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setUserId(1L);
        bookingDTO.setFlightId(1L);
        bookingDTO.setNumberOfPassengers(2);
        bookingDTO.setBookingTime(LocalDateTime.now());

        doThrow(new RuntimeException("Flight not found!")).when(bookingService).bookFlight(bookingDTO);

        HttpEntity<BookingDTO> request = new HttpEntity<>(bookingDTO);
        ResponseEntity<String> response = restTemplate.postForEntity(createURLWithPort("/api/bookings/book"), request, String.class);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Flight not found!", response.getBody());
    }

    @Test
    public void testCancelBookingSuccess() {
        Long bookingId = 1L;

        doNothing().when(bookingService).cancelBooking(bookingId);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/bookings/cancel/" + bookingId), HttpMethod.POST, null, String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Booking cancelled successfully!", response.getBody());
    }

    @Test
    public void testCancelBookingFailure() {
        Long bookingId = 1L;

        doThrow(new RuntimeException("Booking not found!")).when(bookingService).cancelBooking(bookingId);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/bookings/cancel/" + bookingId), HttpMethod.POST, null, String.class);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Booking not found!", response.getBody());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}