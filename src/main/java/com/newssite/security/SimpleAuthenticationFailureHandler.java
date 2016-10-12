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
	
	Logger logger = Logger.getLogger(SimpleAuthenticationFailureHandler.class);
	private final String defaultUrl;
	
	public SimpleAuthenticationFailureHandler(String url){
	    this.defaultUrl = url;
	}
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
				HttpServletResponse response, AuthenticationException exception) 
							throws ServletException,IOException{
		logger.info("Failed Authenticating user");
		logger.info("Request was: "+request);
		logger.info("Exception was: "+exception);
		redirectStrategy.sendRedirect(request, response, defaultUrl);
	}

}
