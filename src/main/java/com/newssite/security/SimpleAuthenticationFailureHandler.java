package com.newssite.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class SimpleAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	Logger logger = Logger.getLogger(getClass());

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
				HttpServletResponse response, AuthenticationException exception) 
							throws ServletException,IOException{
		logger.error("Failed Authenticating user");
		logger.error("Request was: "+request);
		logger.error("Exception was: "+exception);
		redirectStrategy.sendRedirect(request, response, "/login.jsp");
	}

}
