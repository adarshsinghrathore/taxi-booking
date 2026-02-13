package com.sbtaxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbtaxi.dao.BookingFormCrud;
import com.sbtaxi.model.BookingForm;

@Service
public class BookingFormServiceImpl implements BookingFormService {
	
	@Autowired
	private BookingFormCrud bookingFormCrud;

	@Override
	public BookingForm saveBookingFormService(BookingForm bookingForm) {
		// TODO Auto-generated method stub
		return bookingFormCrud.save(bookingForm);
	}

	@Override
	public List<BookingForm> readAllBookingsService() {
		// TODO Auto-generated method stub
		return bookingFormCrud.findAll();
	}

	@Override
	public void deleteBookingService(int id) {
		// TODO Auto-generated method stub
		bookingFormCrud.deleteById(id);
	}

}
