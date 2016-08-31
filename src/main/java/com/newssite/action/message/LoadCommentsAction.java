package com.newssite.action.message;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.model.Comment;
import com.newssite.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;

public class LoadCommentsAction extends ActionSupport {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7438582079130122466L;
	private static final Logger logger = Logger.getLogger(LoadCommentsAction.class);
	
	protected MessageService service;

	private List<Comment> comments;
	
	private String ordering;
	private long articleId;
	

	@Action(value="loadArticleComments", results={@Result(name="success",location="/WEB-INF/content/jsp/article/comments.jsp")})
	public String execute(){
		try{
			comments = service.retrieveArticleComments(articleId);
			if(logger.isDebugEnabled()){
				logger.debug("Retrieved Comments  for Article " + articleId + "\n " + comments);
			}
			logger.trace("Returned Comments " + comments);
			return SUCCESS;
		}catch(Exception e){
			logger.error(String.format("Exception caught loading comments for article %d %s ",articleId,e));
		}
		return ERROR;
	}
	

	@Action(value="loadComments", results={@Result(name="success",location="/WEB-INF/content/jsp/base/comments.jsp")})
	public String getOrderedComments(){
		try{
			comments = service.getComments(getOrdering());
			if(logger.isDebugEnabled()){
				logger.debug("Retrieved Comments  ordered by " + getOrdering() + "\n " + comments);
			}
			return SUCCESS;
		}catch(Exception e){
			System.out.println("Error comments " +e);
			logger.error(String.format("Exception caught loading comments ordered by %s %s",getOrdering(),e));
		}
		return ERROR;
	}
	
	@Autowired
	public void setService(MessageService service) {
		this.service = service;
	}

	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public long getArticleId() {
		return articleId;
	}


	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}


	public String getOrdering() {
		return ordering;
	}


	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}
	
}
