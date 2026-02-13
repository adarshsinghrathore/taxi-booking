package com.sbtaxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbtaxi.model.BookingForm;

@Repository
public interface BookingFormCrud extends JpaRepository<BookingForm,Integer> {

	
	
}
