package com.newssite.action.message;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.interceptor.AuthenticatedUserAware;
import com.newssite.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage("message")
@InterceptorRefs(value={@InterceptorRef(value="authAwareStack")})
public class CommentVoteAction extends ActionSupport implements AuthenticatedUserAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6431679695076538078L;

	private int commentId;
	private long votes;
	private String  username;
	
	private static final Logger logger = Logger.getLogger(CommentVoteAction.class);
	
	private MessageService service;

	@Action(value="upvote",results={@Result(name="success",type="json")})
	public String upvote(){
		if(logger.isDebugEnabled()){
			logger.debug("Upvote from " + username + " for comment with id" + commentId);
		}
		try{
			votes =  service.upvote(commentId);
			return SUCCESS;
		}catch(Exception e){
			logger.error("Exception caught voting for comment " + commentId + ":\n " + e );
		}
		return ERROR;
	}
	
	@Action(value="downvote",results={@Result(name="success",type="json")})
	public String downvote(){
		if(logger.isDebugEnabled()){
			logger.debug("Downvote from " + username + " for comment with id" + commentId);
		}
		try{
			votes = service.downvote(commentId);
			return SUCCESS;
		}catch(Exception e){
			logger.error("Exception caught voting for comment " + commentId + ":\n " + e );
		}
		return ERROR;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public long getVotes() {
		return votes;
	}


	@Override
	public void setUser(String username) {
		this.username = username;
	}
	
	@Autowired
	public void setService(MessageService service){
		this.service=service;
	}
}
