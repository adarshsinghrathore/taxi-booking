package com.sbtaxi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sbtaxi.model.ServiceForm;

public interface ServiceFormService {

	public ServiceForm addService(ServiceForm serviceForm,MultipartFile multipartFile)throws Exception ;
	public List<ServiceForm> readAllService();
}
