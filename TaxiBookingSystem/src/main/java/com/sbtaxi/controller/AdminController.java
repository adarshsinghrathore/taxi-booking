package com.sbtaxi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sbtaxi.model.ServiceForm;
import com.sbtaxi.service.AdminCredentialsService;
import com.sbtaxi.service.BookingFormService;
import com.sbtaxi.service.ContactFormService;
import com.sbtaxi.service.ServiceFormService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdminCredentialsService adminCredentialsService;
	@Autowired
	private ContactFormService contactFormService;
	
	@Autowired
	private BookingFormService bookingFormService;
	
	@Autowired
	private ServiceFormService serviceFormService;
	
	
	@GetMapping("dashboard")
	public String adminDashBoard() {
		return "admin/dashboard";
	}
	
	//========================readAllContacts=================================================================================================
	@GetMapping("readAllContacts")
	public String readAllContacts(Model m) {//model is used to send data in form of key to html page
		m.addAttribute("allcontacts",  contactFormService.readAllContactsService());
		return "admin/readallcontacts";
	}
	
	@GetMapping("deleteContact/{id}")
	public String deleteContact(@PathVariable int id,RedirectAttributes redirectAttributes) {
		
		contactFormService.deleteContactsService(id);
		
		redirectAttributes.addFlashAttribute("message", "contact deleted successfully");
		
		return "redirect:/admin/readAllContacts";
	}
	
	//======================readAllBookings=============================================================================================
	
	@GetMapping("readAllBookings")
	public String readAllBookings(Model m) {//model is used to send data in form of key to html page
		m.addAttribute("allbookings",  bookingFormService.readAllBookingsService());
		return "admin/readallbookings";
	}
	
	@GetMapping("deleteBooking/{id}")
	public String deleteBooking(@PathVariable int id,RedirectAttributes redirectAttributes) {
		
		bookingFormService.deleteBookingService(id);
		
		redirectAttributes.addFlashAttribute("message", "booking deleted successfully");
		
		return "redirect:/admin/readAllBookings";
	}
	
	//=========================changecredentials===================================================================================
	
	
	@GetMapping("changeCredentials")
	public String changeCredentialView() {
		return "admin/changecredentials";
	}
	
	@PostMapping("changeCredentials")
	public String changeCredentials(
			@RequestParam("oldusername") String oldusername,
			@RequestParam("oldpassword") String oldpassword,
			@RequestParam("newusername") String newusername,
			@RequestParam("newpassword") String newpassword,RedirectAttributes redirectAttributes
	) {
		
		String result = adminCredentialsService.changeAdminCredentials(oldusername, oldpassword);
		if(result.equals("success")) {
		result=adminCredentialsService.updateCredentialsService(newusername, newpassword, oldusername);
		redirectAttributes.addFlashAttribute("message",result);
		}else {
			redirectAttributes.addFlashAttribute("message",result);

		}
		return "redirect:/admin/dashboard";
	}
	
	//========================add service======================================
	
	@GetMapping("addService")
	public String addServiceview() {
		return "admin/adminservice";
	}
	//we have image which has different type so we cannot bind it through modelattribute so we have to stop binding that we can do by @InitBinder
	@InitBinder
	public void stopBinding(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("image");
	}
	
	@PostMapping("addService")
	public String addService(@ModelAttribute ServiceForm serviceForm,@RequestParam("image") MultipartFile multipartFile,RedirectAttributes redirectAttributes ) {
		
		//to save file info in dao
		String originalFilename = multipartFile.getOriginalFilename();
		serviceForm.setImage(originalFilename);
		
		//service logic 
		try {
			ServiceForm service = serviceFormService.addService(serviceForm, multipartFile);
			if(service!=null) {
				redirectAttributes.addFlashAttribute("message","service added successfully");
			}else {
				redirectAttributes.addFlashAttribute("message","something went wrong");
	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message","something went wrong");

		}
		return "redirect:/admin/addService";
	}
}

