package com.newssite.action.message;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.interceptor.AuthenticatedUserAware;
import com.newssite.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 
 *	Provides an action method for retrieving a user's
 *  unread messages count. 
 */
@ParentPackage("message")
@InterceptorRefs(value={@InterceptorRef(value="authAwareStack")})
public class UnreadCountAction extends ActionSupport implements
		AuthenticatedUserAware {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4782191955441013987L;

	private static final Logger logger = Logger.getLogger(UnreadCountAction.class);	

	private MessageService service;
	
	private int unread;
	
	private String username;
	

	@Action(value="unread-count",results={@Result(name="success",type="json")})
	public String execute(){
		try{
			unread = service.countUnread(username);
			if(logger.isDebugEnabled()){
				logger.debug(String.format("Unread count for %s %d",username,unread));
			}
			return SUCCESS;
		}catch(Exception e){
			logger.error("Caught exception counting unread for "+ username + "\n" +e);
		}
		return ERROR;
	}
	
	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}
	
	@Override
	public void setUser(String username) {
		this.username= username;
	}

	
	@Autowired
	public void setService(MessageService service) {
		this.service = service;
	}
}
