package com.newssite.action.user;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value="user")
public abstract class AbstractUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -231851388886342994L;

	protected UserService service;

	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}
	
}
