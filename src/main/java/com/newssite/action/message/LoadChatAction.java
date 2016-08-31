package com.newssite.action.message;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.interceptor.AuthenticatedUserAware;
import com.newssite.model.Chat;
import com.newssite.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;

public class LoadChatAction extends ActionSupport implements AuthenticatedUserAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6727533307694015630L;
	private static final Logger logger = Logger.getLogger(LoadChatAction.class);
	
	private long chatId;
	
	private Chat chat;
	
	private MessageService service;
	
	private String username;
	

	@Action(value="loadChat", results={@Result(name="success",location="/WEB-INF/content/jsp/user/chat.jsp")},interceptorRefs={@InterceptorRef(value="authAwareStack")})
	public String execute(){
		try{
			chat = service.getChat(username,chatId);
			return SUCCESS;
		}catch(Exception e){
			logger.error("Exception caught when loading chat " + chatId + " \n " +e);
		}
		return ERROR;
	}


	public long getChatId() {
		return chatId;
	}


	public void setChatId(long chatId) {
		this.chatId = chatId;
	}


	public Chat getChat() {
		return chat;
	}


	public void setChat(Chat chat) {
		this.chat = chat;
	}

	@Autowired
	public void setService(MessageService service) {
		this.service = service;
	}


	@Override
	public void setUser(String username) {
		this.username = username;
	}
	
}
