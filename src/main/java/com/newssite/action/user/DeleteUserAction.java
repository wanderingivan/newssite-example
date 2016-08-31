package com.newssite.action.user;

import org.apache.log4j.Logger;

import com.newssite.interceptor.AuthenticatedUserAware;

public class DeleteUserAction extends AbstractUserAction implements AuthenticatedUserAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3970178174439812336L;
	
	private static Logger logger = Logger.getLogger(DeleteUserAction.class);

	private String username;
	private String actingUser;
	
	public String execute(){
		try{
			logger.info(String.format("%s deleting user with username %s",actingUser,username));
			service.deleteUser(username);
			return SUCCESS;
		}catch(Exception e){
			logger.error(String.format("Error deleting user with username %s :\n %s",username,e));
		}
		return ERROR;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public void setUser(String username) {
		this.actingUser = username;
	}

}
