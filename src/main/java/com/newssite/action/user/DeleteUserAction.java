package com.newssite.action.user;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;

import com.newssite.interceptor.AuthenticatedUserAware;

@InterceptorRefs(value={@InterceptorRef(value="authAwareStack")})
public class DeleteUserAction extends AbstractUserAction implements AuthenticatedUserAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3970178174439812336L;
	
	private static Logger logger = Logger.getLogger(DeleteUserAction.class);

	private String username;
	private String actingUser;
	
	@Action(value="delete",results={@Result(name="success",type="redirectAction",params={"actionName","welcome","namespace","/admin"})})
	public String execute(){
		try{
			logger.info(String.format("%s deleting user with username %s",actingUser,username));
			service.deleteUser(username);
			return SUCCESS;
		}catch(Exception e){
			logger.error(String.format("Error deleting user with username %s :\n %s",username,e));
			e.printStackTrace();
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
