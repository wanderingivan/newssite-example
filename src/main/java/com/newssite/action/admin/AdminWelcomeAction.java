package com.newssite.action.admin;

import java.util.List;
import java.util.SortedSet;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.interceptor.AuthenticatedUserAware;
import com.newssite.model.Article;
import com.newssite.model.Chat;
import com.newssite.model.Comment;
import com.newssite.model.User;
import com.newssite.service.ArticleService;
import com.newssite.service.MessageService;
import com.newssite.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("admin")
@InterceptorRefs(value={@InterceptorRef(value="authAwareStack")})
public class AdminWelcomeAction extends ActionSupport implements AuthenticatedUserAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6991332764405014072L;
	private static final Logger logger = Logger.getLogger(AdminWelcomeAction.class);

	
	private MessageService	messageService;
	
	private ArticleService articleService;
	
	private UserService userService;
	
	private List<Article> newestArticles;
	
	private List<Comment> newestComments;
	
	private List<User> newestUsers;
	
	private SortedSet<Chat> userChats;
	
	private String username;

	@Action(value="welcome",results={@Result(name="success",location="/WEB-INF/content/jsp/admin/admin.jsp")})
	public String execute(){
		if(logger.isTraceEnabled()){
			logger.trace("Loading Admin Homepage");
		}
		newestArticles = articleService.latest();
		newestComments = messageService.latestComments();
		newestUsers = userService.newestUsers();
		userChats = messageService.retrieveUserChats(username);
		return SUCCESS;
	}

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@Autowired
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<Article> getNewestArticles() {
		return newestArticles;
	}


	public void setNewestArticles(List<Article> newestArticles) {
		this.newestArticles = newestArticles;
	}


	public List<Comment> getNewestComments() {
		return newestComments;
	}


	public void setNewestComments(List<Comment> newestComments) {
		this.newestComments = newestComments;
	}


	public UserService getUserService() {
		return userService;
	}

	public List<User> getNewestUsers() {
		return newestUsers;
	}

	
	public SortedSet<Chat> getUserChats() {
		return userChats;
	}


	public void setUserChats(SortedSet<Chat> userChats) {
		this.userChats = userChats;
	}


	public void setNewestUsers(List<User> newestUsers) {
		this.newestUsers = newestUsers;
	}


	@Override
	public void setUser(String username) {
		this.username = username;
	}

}
