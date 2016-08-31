package com.newssite.action.article;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class LoadCategoryAction extends AbstractArticleAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5444485359001669993L;
	private static Logger logger = Logger.getLogger(LoadCategoryAction.class);
	private static final String MOST_READ = "mostRead",
			                    MOST_COMMENTS = "mostComments";
	
	private Map<String,String> articles; 
	private String category;

	@Action(value="loadCategory",results={@Result(name="success",location="/WEB-INF/content/jsp/article/category.jsp")})
	public String execute(){
		try{
			if(logger.isDebugEnabled()){
				logger.debug("Loading articles from category " + category);
			}
			if(category.equals(MOST_READ)){
				articles = service.mostViewed();
			}else if(category.equals(MOST_COMMENTS)){
				articles = service.mostCommented();
			}else{
				articles = service.getByCategory(category);
			}
			return SUCCESS;
		}catch(Exception e){
			
		}
		return ERROR;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Map<String, String> getArticles() {
		return articles;
	}

	public void setArticles(Map<String, String> articles) {
		this.articles = articles;
	}
	
	
}
