package com.sbtaxi.service;

import java.util.List;


import com.sbtaxi.model.ContactForm;


public interface ContactFormService {

	public ContactForm saveContactFormService(ContactForm contactForm);

	public List<ContactForm> readAllContactsService();
	public void deleteContactsService(int id);

	
}
