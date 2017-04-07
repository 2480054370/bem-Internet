package com.bemInternet.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class ResttfulAuth implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter writer;
//		String returnStr = "{name:'" + arg2.getClass()
//				+ "',message:'" + arg2.getMessage() + "'}";
//		System.out.println(this.getClass().toString()+":"+returnStr);
		String returnStr = "error";
		writer = arg1.getWriter();
		writer.write(returnStr);
		writer.flush();
		writer.close();
	}
 
}
