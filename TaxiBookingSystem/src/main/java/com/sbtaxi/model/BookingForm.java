package com.sbtaxi.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="bookingform")
public class BookingForm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="name cannot be empty")
	@NotBlank(message="name cannot be blank")
	@Size(min=3,max=20, message="invalid size")
	@Pattern(regexp="^[A-Za-z][A-Za-z'-]*([ ][A-Za-z][A-Za-z'-]*)*$",message="name must contain alphabet")
	private String name;
	
	@NotEmpty(message="source cannot be empty")
	@NotBlank(message="source cannot be blank")
	@Size(min=3,max=20, message="invalid source length")
	private String source;
	
	@NotEmpty(message="email cannot be empty")
	@Size(min=3,max=50, message="invalid email length")
	@NotBlank(message="email cannot be blank")
	private String email;
	
	@NotEmpty(message="destination cannot be empty")
	@NotBlank(message="destination cannot be blank")	
	@Size(min=3,max=20, message="invalid destination length")
	private String destination;
	
	@NotNull(message="time cannot be empty")
	private LocalTime time;
	
	@NotNull(message="date cannot be empty")
	private LocalDate date;
	
	@NotEmpty(message="comfort cannot be empty")
	private String comfort;
	
	@Min(value=1,message="adult at least 1")
	@Max(value=4,message="adult atmost 4")	
	private int adult;
	
	@Max(value=3,message="children atmost 3")		
	private int children;
	
	@NotEmpty(message="message cannot be empty")
	@Size(min=3,max=500, message="invalid size")
	@NotBlank(message="message cannot be blank")
	private String message;
	
}
