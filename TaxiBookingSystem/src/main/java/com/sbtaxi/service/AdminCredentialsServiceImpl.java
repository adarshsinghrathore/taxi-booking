package com.sbtaxi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbtaxi.dao.AdminDao;
import com.sbtaxi.model.Admin;

@Service
public class AdminCredentialsServiceImpl implements AdminCredentialsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdminDao adminDao;
	@Override
	public String changeAdminCredentials(String oldusername, String oldpassword) {
		// TODO Auto-generated method stub
		  Optional<Admin> byUsername = adminDao.findByUsername(oldusername);//oldusername gave by user
		  if(byUsername.isPresent()) {
			  
			  Admin admin = byUsername.get(); //admin contains sn username password
			  boolean matches = passwordEncoder.matches(oldpassword, admin.getPassword());
			  //matches is a method provides bypasswordencoder which match old password with new 
			  if(matches) {
				  return "success";
			  }else {
				  return "wrong old credentials";
			  }
		  }else {
			  return "wrong old credentials";
		  }
		 
	}
	@Override
	public String updateCredentialsService(String newusername, String newpassword, String oldusername) {
		// TODO Auto-generated method stub
		int updateCredentials = adminDao.updateCredentials(newusername,passwordEncoder.encode(newpassword),oldusername);
		if(updateCredentials==1) { //if updated then we get value 1
			return "CREDENTIALS UPDATED";
		}else {
			return "FAILED TO UPDATE";
		}
	}

}
