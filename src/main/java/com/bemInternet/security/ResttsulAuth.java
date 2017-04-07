package com.bemInternet.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.bemInternet.Service.UserService;
import com.bemInternet.web.BaseController;

public class ResttsulAuth implements AuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//BaseController base = new BaseController();
		System.err.println("-----------" + auth.getName());
		response.sendRedirect("/");
		//userService.findUserByStudentld(auth.getName());
		
		return;
	}

}
