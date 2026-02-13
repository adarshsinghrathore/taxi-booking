package com.sbtaxi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="contactform")
public class ContactForm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="name cannot be empty")
	@Size(min=3,max=20, message="invalid size")
	private String name;
	
	@NotEmpty(message="email cannot be empty")
	@Size(min=10,max=50,message="invalid size")
	private String email;
	
	@NotNull(message="phno cannot be null")
	@Min(value=1000000000,message="no at least 10 digit")
	@Max(value=9999999999L, message="more than 10 digit not allowed")
	private Long phone;
	
	@NotEmpty(message="message cannot be empty")
	@Size(min=3,max=500, message="invalid size")
	private String message;

}
