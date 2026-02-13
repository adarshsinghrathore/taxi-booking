package com.sbtaxi.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbtaxi.dao.AdminDao;
import com.sbtaxi.model.Admin;

import jakarta.annotation.PostConstruct;
@Service
public class UsersDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostConstruct//annotation used to mark a method to run the method immediately after dependency injection
	public void init() {
		long count = adminDao.count();
		if(count==0) {
			Admin admin = new Admin();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("admin123"));
			
			adminDao.save(admin);
		}
	}
	
	
	
	@Autowired
	private AdminDao adminDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//checking query will be select sn,username,passwordfrom admin where username=?
		Optional<Admin> byUsername = adminDao.findByUsername(username);
		Admin admin = byUsername.orElseThrow(()->new UsernameNotFoundException("user does not exist"));
		
		return User.withUsername(admin.getUsername()).password(admin.getPassword()).build();
		
	}

}
