package com.coforge.project.booking.dto;

import com.coforge.project.booking.model.Booking;




public class BookingDetailsDTO {

	private Booking booking;
	private UserDTO user;
	private FlightDTO flight;
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public FlightDTO getFlight() {
		return flight;
	}
	public void setFlight(FlightDTO flight) {
		this.flight = flight;
	}
	
	
}
