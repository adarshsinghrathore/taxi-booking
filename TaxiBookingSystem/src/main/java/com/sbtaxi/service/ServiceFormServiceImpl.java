package com.sbtaxi.service;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbtaxi.dao.ServiceFormDao;
import com.sbtaxi.model.ServiceForm;

import jakarta.transaction.Transactional;

@Service
public class ServiceFormServiceImpl implements ServiceFormService  {

	@Autowired
	private ServiceFormDao serviceFormDao;
	@Override
	@Transactional(rollbackOn = Exception.class)
	public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws Exception {
		// TODO Auto-generated method stub
		ServiceForm save=null;
		
		try {
			
			save = serviceFormDao.save(serviceForm);
			if(save!=null) {
			String path=
   "C:\\Users\\HP\\my sts workspaces\\workspace-spring-tool-suite-4-4.24.0.RELEASE\\taxibookingp\\TaxiBookingSystem\\src\\main\\resources\\static\\serviceimg"
					+multipartFile.getOriginalFilename();
			
			byte[] bytes = multipartFile.getBytes();
		FileOutputStream fos = new	FileOutputStream(path);
		fos.write(bytes);
		}
		
		}catch(Exception e){
			save=null;
			throw e;
		}
		return save;
	}
	@Override
	public List<ServiceForm> readAllService() {
		// TODO Auto-generated method stub
		
		return serviceFormDao.findAll();
	}

}
