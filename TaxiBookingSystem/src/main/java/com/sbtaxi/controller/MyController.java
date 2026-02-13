package com.sbtaxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sbtaxi.model.BookingForm;
import com.sbtaxi.model.ContactForm;
import com.sbtaxi.model.ServiceForm;
import com.sbtaxi.service.BookingFormService;
import com.sbtaxi.service.ContactFormService;
import com.sbtaxi.service.ServiceFormService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller	
public class MyController {

	@Autowired
	private ContactFormService contactFormService;
	@Autowired
	private BookingFormService bookingFormService;
	
	@Autowired
	private ServiceFormService serviceFormService;
	
	@GetMapping(path={"/","home","index"})
	public String welcomeView(HttpServletRequest req,Model m) {//model used to transfer the value(requestUri) from user in form of key to html page
		String requestURI = req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);//here we will use key ..mycurrentpage..
		m.addAttribute("bookingForm", new BookingForm());
		return "index";
	}
	@GetMapping("about")
	public String aboutView(HttpServletRequest req,Model m) {
		String requestURI = req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		return "about";
	}
	@GetMapping("cars")
	public String carsView(HttpServletRequest req,Model m) {
		String requestURI = req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		return "car";
	}
	@GetMapping("services")
	public String servicesView(HttpServletRequest req,Model m ) {
		String requestURI = req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		//data collect
		
		
		List<ServiceForm> allService = serviceFormService.readAllService();
		m.addAttribute("allservices",allService);
		
		return "services";
	}
	@GetMapping("contacts")
	public String contactsView(HttpServletRequest req,Model m) {
		String requestURI = req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		m.addAttribute("contactForm",new ContactForm());
		return "contacts";
	}
	
	@PostMapping("contactform")
	public String  contactForm( @Valid @ModelAttribute ContactForm contactForm 
			,BindingResult bindingResult, Model m ,RedirectAttributes redirectAttributes)  {
		if(bindingResult.hasErrors()) {
			m.addAttribute("bindingresult",bindingResult);
			return "contacts";
		}
		 ContactForm saveContactFormService = contactFormService.saveContactFormService(contactForm);
		 if(saveContactFormService!=null) {
			  redirectAttributes.addFlashAttribute("message","sent successfully");
		 }else {
			  redirectAttributes.addFlashAttribute("message","something wrong");

		 }
		 
		return "redirect:/contacts";//redirect attribute used to provide message we cannot sent message withod this because of request breakdown 
	}
	@PostMapping("bookingform")
	public String  bookingForm( @Valid @ModelAttribute BookingForm bookingForm 
			,BindingResult bindingResult, Model m ,RedirectAttributes redirectAttributes)  {
		if(bindingResult.hasErrors()) {
			m.addAttribute("bindingresult",bindingResult);
			return "index";
		}
		else if(bookingForm.getAdult() +bookingForm.getChildren()>4) {
			m.addAttribute("message", "total no of passenger cannot exceed 4");
			return "index";
		}
		//service
		BookingForm saveBookingFormService = bookingFormService.saveBookingFormService(bookingForm);
		if(saveBookingFormService!=null) {
			redirectAttributes.addFlashAttribute("message","data sent successfully");
		}else {
			redirectAttributes.addFlashAttribute("message", "something went wrong");
		}
		return "redirect:/index";
	}
	@GetMapping("login")
	public String adminLoginView(HttpServletRequest request, Model model) {
		ServletContext servletContext = request.getServletContext();
		Object attribute = servletContext.getAttribute("logout");
		if(attribute instanceof Boolean) {//if attribute contains true then we send logout attribute through model
			model.addAttribute("logout",attribute);
			servletContext.removeAttribute("logout");
		}
		return "/adminlogin";
	}
	
}