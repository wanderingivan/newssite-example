package com.newssite.action.message;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

@ParentPackage("message")
@Results({@Result(name="success", type="json")})
public class AbstractMessageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -555790676324309028L;

	
	protected MessageService service;
	
	protected String message;
	

	@Autowired
	public void setService(MessageService service) {
		this.service = service;
	}
	public String getMessage() {
		return message;
	}
	
	@RequiredStringValidator(message="message cannot be empty")
	@StringLengthFieldValidator(minLength = "1",
	        					maxLength = "250",
	        					message="field must between ${minLength} and ${maxLength} characters")
	public void setMessage(String message) {
		this.message = message;
	}
	
}
