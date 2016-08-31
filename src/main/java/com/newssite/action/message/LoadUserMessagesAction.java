package com.newssite.action.message;

import java.util.SortedSet;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ParentPackage;

import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.model.Chat;
import com.newssite.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("message")
public class LoadUserMessagesAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5800900414828709861L;

	private static final Logger logger = Logger.getLogger(LoadUserMessagesAction.class);
	
	@Autowired
	private MessageService service;
	
	private SortedSet<Chat> chats;
	
	private String username;

	@Action(value="loadUserMessages", results={@Result(name="success",type="tiles",location="userMessagesLayout")})
	public String execute(){
		try{

			chats = service.retrieveUserChats(username);
			if(logger.isDebugEnabled()){
				logger.debug("Loaded messages for user: "+username + " \n" + chats);
			}
			return SUCCESS;
		}catch(Exception e){
		   logger.error("Exception caught loading messages for user: " + username + "\n" +e);		
		}
		return ERROR;
	}

	public MessageService getService() {
		return service;
	}

	public void setService(MessageService service) {
		this.service = service;
	}

	public SortedSet<Chat> getChats() {
		return chats;
	}

	public void setChats(SortedSet<Chat> chats) {
		this.chats = chats;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
