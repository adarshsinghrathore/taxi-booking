package com.sbtaxi.service;

public interface AdminCredentialsService {

	public String changeAdminCredentials(String oldusername, String oldpassword);
	public String updateCredentialsService(String newusername,String newpassword,String oldusername);
}
