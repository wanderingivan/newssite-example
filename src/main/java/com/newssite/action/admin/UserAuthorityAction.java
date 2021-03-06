package com.newssite.action.admin;

import org.apache.log4j.Logger;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.interceptor.AuthenticatedUserAware;
import com.newssite.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("admin")
@InterceptorRefs(value={@InterceptorRef(value="authAwareStack")})
public class UserAuthorityAction extends ActionSupport implements AuthenticatedUserAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8274829047769652424L;
	
	private static Logger logger  = Logger.getLogger(UserAuthorityAction.class);
	
	@Autowired
	private UserService service;
	
	private String username,
				   authority,
				   actingUser;


	@Action(value="changeRole",results={@Result(name="success",type="redirectAction",params={"namespace","/user","actionName","loadUser","username","${username}"})})
	public String execute(){
		try{
	        if(logger.isInfoEnabled()){
	            logger.info(String.format("User %s changing user authority for %s to %s",actingUser,username,authority));
	        }            
			service.changeAuthority(username,authority);
		}catch(Exception e){
			logger.error(String.format("Error changing user authority for %s to %s \n %s",username,authority,e));
		}
		return ERROR;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public void setUser(String username) {
		this.actingUser = username;
	}
	
	
	
}
