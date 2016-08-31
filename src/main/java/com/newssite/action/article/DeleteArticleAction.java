package com.newssite.action.article;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;

import com.newssite.interceptor.AuthenticatedUserAware;

@InterceptorRefs(value={@InterceptorRef(value="authAwareStack")})
public class DeleteArticleAction extends AbstractArticleAction  implements AuthenticatedUserAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3545718878412813502L;

	private static Logger logger =  Logger.getLogger(DeleteArticleAction.class);
	
	private String username;
	
	private long id;
	
	@Action(value="delete", results={@Result(name="success",type="redirectAction",params={"namespace","/admin","actionName","welcomeAction"})})
	public String execute(){
		try{
			logger.info(String.format(" User %s Deleting Article with id %d",username,id));
			service.deleteArticle(id);
			return SUCCESS;
		}catch(Exception e){
			logger.error(String.format("Error deleting article with id %d \n %s",id,e));
		}
		return ERROR;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setUser(String username) {
		this.username = username;
	}

	
	
}
