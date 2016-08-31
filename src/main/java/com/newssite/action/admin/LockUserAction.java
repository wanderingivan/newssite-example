package com.newssite.action.admin;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.service.UserService;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage("admin")
@Results({@Result(name="success",type="redirectAction",params={"namespace","/user","actionName","loadUser","username","${username}"})})
public class LockUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8311269827905963276L;
	private static final Logger logger = Logger.getLogger(LockUserAction.class); 
	
	@Autowired
	private UserService service;
	
	private String username;
	

	@Action(value="enableUser")
	public String execute(){
		logger.info("Locking user with username: " + username);
		try{
			service.enableUser(username);
			return SUCCESS;
		}catch(Exception e){
			logger.error("Error enabling user: "+ username +"\n" +e);
		}
		return ERROR;
	}

	@Action(value="disableUser")
	public String disableUser() {
		logger.info("Locking user with username: " + username);
		try{
			service.disableUser(username);
			return SUCCESS;
		}catch(Exception e){
			logger.error("Error enabling user: "+ username +"\n" +e);
		}
		return ERROR;	
	}
	
	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
