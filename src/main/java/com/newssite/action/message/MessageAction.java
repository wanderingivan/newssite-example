package com.newssite.action.message;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;

import com.newssite.interceptor.AuthenticatedUserAware;


@InterceptorRefs(value={@InterceptorRef(value="authAwareStack")})
public class MessageAction extends AbstractMessageAction implements AuthenticatedUserAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1446084119966014047L;
	private static final Logger logger = Logger.getLogger(MessageAction.class);

	private String[] saved;
	private String sender,
				   receiver;
	private long chatId,messageId;
	
	@Action(value="sendMessage",results={@Result(name="success", type="redirectAction", params={"actionName","loadUserMessages","username","${sender}"})})
	public String send(){
		try{
			if(logger.isDebugEnabled()){
				logger.debug(String.format("Sending  message from %s to %s  message %s"
																			,sender,receiver,message));
			}
			service.sendMessage(message,sender,receiver);
			return SUCCESS;
		}catch(Exception e){
		   logger.error(String.format("Exception caught sending  message from %s to %s  message %s \n %s"
																			,sender,receiver,message,e));		
		}
		return ERROR;
	}
	
	@Action(value="addMessage")
	public String add(){
		try{
			if(logger.isDebugEnabled()){
				logger.debug(String.format("Sending  message from %s to %d  message %s"
																			,sender,chatId,message));
			}
			saved = service.addMessage(sender, chatId, message);
			return SUCCESS;
		}catch(Exception e){
		   logger.error(String.format("Exception caught sending  message from %s to %d  message %s \n %s"
																			,sender,chatId,message,e));		
		}
		return ERROR;	
	}
	
	@Action(value="deleteMessage")
	public String delete(){
		try{
			if(logger.isDebugEnabled()){
				logger.debug(String.format("deleting message from %s message %s"
																			,sender,message));
			}
			service.deleteMessage(messageId);
			return SUCCESS;
		}catch(Exception e){
		   logger.error(String.format("Exception caught deleting  message from %s  message %s \n %s"
																			,sender,message,e));		
		}
		return ERROR;
	}


	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	public String [] getSaved() {
		return saved;
	}

	public void setSaved(String [] saved) {
		this.saved = saved;
	}

	@Override
	public void setUser(String username) {
		this.sender = username;
	}
}
