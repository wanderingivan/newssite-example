package com.newssite.action;

import com.newssite.model.Article;
import com.newssite.service.ArticleService;
import com.newssite.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Provides methods for searching 
 * trough users and products based on query type.
 *
 */
@ParentPackage(value="index")
public class SearchAction extends ActionSupport{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3367089994085106440L;
	private static final Logger logger = Logger.getLogger(SearchAction.class);
	
	private static final String article = "article",
								user = "user";
	
	private UserService userService;
	
	private ArticleService articleService;
	
	private List<Article> articles;
	private Map<String, String> users;
	
	private String query,type,message;
	private boolean missing;
	
	@Action(value="search",results={@Result(name="article", type="tiles", location="articleListLayout"),
								    @Result(name="user",    type="tiles", location="userListLayout")})
	public String execute(){
		logger.debug("Searching database with params " + query + " " + type);
		try{
			if(type.equals(article)){
				if(isMissing()){
				    setMessage(
				            getText("global.missing_article")
				    );
				}
				else{
				    setMessage(
				            getText("global.articles_matching")
				    );
				}
				
				articles = articleService.findArticles(query);
				return article;
			}else if(type.equals(user)){
				if(isMissing()){
				    setMessage(
				            getText("global.missing_user")
				    );
				}
				else{
				    setMessage(
				            getText("global.users_matching")
				    );
				}
				users = userService.findUsers(query);
				return user;
			}else{
				throw new IllegalArgumentException("Unmapped query type: "+type);
			}
		}catch(Exception e){
			logger.error(String.format("Exception caught searching for query %s with type %s \n %s",query,type,e));
		}
		return ERROR;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Map<String, String> getUsers() {
		return users;
	}

	public void setUsers(Map<String, String> users) {
		this.users = users;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isMissing() {
		return missing;
	}

	public void setMissing(boolean missing) {
		this.missing = missing;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setArticleService(ArticleService service) {
		this.articleService = service;
	}
	
}
