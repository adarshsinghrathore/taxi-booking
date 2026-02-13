package com.sbtaxi.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class CustomLogOutHandler implements LogoutHandler {

	//use of this class is to open admin dashboard after logout......but i also have to pass a successful message after we log out so we used servlwet context
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		try {
			ServletContext servletContext = request.getServletContext();
			servletContext.setAttribute("logout", true);
			response.sendRedirect("admin/dashboard");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
