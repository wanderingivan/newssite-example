package com.newssite.action.user;



import org.apache.log4j.Logger;

import com.newssite.model.User;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class LoadUsersAction extends AbstractUserAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2455419019369796889L;

	private User user;
	private String username;

	private static Logger logger = Logger.getLogger(LoadUsersAction.class);
	
	@Action(value="loadUser",results={@Result(name="success",type="tiles", location="userPageLayout"),
									  @Result(name="redirect", type="redirectAction", params={"actionName","search",
											                                                  "namespace","/index",
											                                                  "query","${username}",
											                                                  "type","user",
											                                                  "missing","true"})})
	public String loadUser(){
		try{
			if(logger.isTraceEnabled()){
				logger.trace(String.format("Retrieving user with username %s",username));
			}
			user = service.getUser(username);
			if(user == null){ return "redirect";}
			return SUCCESS;
		}catch(Exception e){
			logger.error(String.format("Error retrieving user with username %s:\n %s",username,e));
		}
		return ERROR;
	}


	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
