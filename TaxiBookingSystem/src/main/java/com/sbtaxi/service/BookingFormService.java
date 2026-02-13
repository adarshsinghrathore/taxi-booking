package com.sbtaxi.service;

import java.util.List;

import com.sbtaxi.model.BookingForm;


public interface BookingFormService {

	public BookingForm saveBookingFormService(BookingForm bookingForm);

	public  List<BookingForm>  readAllBookingsService();
	
	public void deleteBookingService(int id);
}
