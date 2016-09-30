package com.newssite.action.message;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;

import com.newssite.interceptor.AuthenticatedUserAware;

@InterceptorRefs(value={@InterceptorRef(value="authAwareStack")})
public class CommentAction extends AbstractMessageAction implements AuthenticatedUserAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 558238082038582045L;
	private static final Logger logger = Logger.getLogger(CommentAction.class);
	
	private String username,headline;
	private long articleId,commentId;
	
	@Action(value="addComment",results={@Result(name="success",type="redirectAction",params={"namespace","/article","actionName","loadArticle","headline","${headline}"})})
	public String post(){
		try{
			if(logger.isDebugEnabled()){
				logger.debug(String.format("Posting comment message: %s article: %d  from :%s"
																			,message,articleId,username));
			}
			service.addComment(message, articleId, username);
			return SUCCESS;
		}catch(Exception e){
				logger.error(String.format("Exception  caugh posting comment message: %s article: %d  from :%s \n %s"
																			,message,articleId,username,e));
		}
		return ERROR;		
	}
	
	@Action(value="editComment",results={@Result(name="success",type="redirectAction",params={"namespace","/article","actionName","loadArticle","headline","${headline}"})})
	public String edit(){
		try{
			if(logger.isDebugEnabled()){
				logger.debug(String.format("Editing comment message: %s article: %s  from :%s"
																			,message,articleId,username));
			}
			message.concat(" Edited by " + username);
			service.editComment(message,articleId);
		}catch(Exception e){
			logger.error(String.format("Exception  caugh posting comment message: %s article: %d  from :%s \n %s"
																			,message,articleId,username,e));
		}
		return ERROR;
	}
	
	@Action(value="delete",results={@Result(name="success",type="redirectAction",params={"namespace","/article","actionName","loadArticle","headline","${headline}"})})
	public String delete(){
		try{
			if(logger.isDebugEnabled()){
				logger.debug(String.format("Deleting comment message: %s article: %d  from :%s"
																			,message,articleId,username));
			}
			service.deleteComment(commentId);
			return SUCCESS;
		}catch(Exception e){
			logger.error(String.format("Exception  caught posting comment message: %d article: %s  from :%s \n %s"
																			,message,articleId,username,e));
			logger.error(e);		
		}
		return ERROR;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	
	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	@Override
	public void setUser(String username) {
		this.username = username;
	}
	
}
