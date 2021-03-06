package com.newssite.interceptor;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6165549166976305137L;
	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if(invocation.getAction() instanceof AuthenticatedUserAware){
			if(logger.isDebugEnabled()){
				logger.debug("Auth Interceptor Intercepting action " + invocation.getAction().getClass().getSimpleName() + "\n Inserting user" + getUserFromSecurityContext());
			}
			((AuthenticatedUserAware) invocation.getAction()).setUser(getUserFromSecurityContext());
		}
		return invocation.invoke();
	}

	private String getUserFromSecurityContext(){
		try{
			return SecurityContextHolder.getContext()
										.getAuthentication()
										.getName();
		}catch(Exception ex){
			logger.error("Caught exception " + ex + " when loading user info from ctx");
			return null;
		}
	}
}
