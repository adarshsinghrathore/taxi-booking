package com.sbtaxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbtaxi.dao.ContactFormCrud;
import com.sbtaxi.model.ContactForm;

@Service
public class ContactFormServiceImpl implements ContactFormService {

	@Autowired
      private ContactFormCrud contactFormCrud;
	@Override
	public ContactForm saveContactFormService(ContactForm contactForm) {
		// TODO Auto-generated method stub
		return contactFormCrud.save(contactForm);
		
	}
	@Override
	public List<ContactForm> readAllContactsService() {
		// TODO Auto-generated method stub
		return contactFormCrud.findAll();
	}
	@Override
	public void deleteContactsService(int id) {
		// TODO Auto-generated method stub
		 contactFormCrud.deleteById(id);
	}
	
}
