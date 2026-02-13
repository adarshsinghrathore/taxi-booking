 package com.sbtaxi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sbtaxi.model.Admin;

import jakarta.transaction.Transactional;

public interface AdminDao extends JpaRepository<Admin, Integer> {

	Optional<Admin> findByUsername(String username);//spring security needs data from database............by this method we got username in admin object 
	//it check username in database if username matches then it return username in form of admin object
	
	//custom method for update 
	@Modifying
	@Transactional
	@Query(value = "UPDATE admin SET username = :newusername, password = :newpassword WHERE username = :oldusername", nativeQuery = true)	
	public int updateCredentials(
			@Param ("newusername")  String newusername,//param annotation used to bind data coming from mysql to our string
			@Param ("newpassword") String newpassword,
			@Param ("oldusername") String oldusername
			
			
			) ;
		
		
	
}
