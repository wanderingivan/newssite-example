package com.newssite.action.article;

import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.service.ArticleService;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage("article")
public abstract class AbstractArticleAction extends ActionSupport {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2104698531790319721L;

	@Autowired
	protected ArticleService service;
	
	public void setService(ArticleService service) {
		this.service = service;
	}


	
	
}
