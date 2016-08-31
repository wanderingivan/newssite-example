package com.newssite.action.article;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.model.Article;
import com.newssite.util.HitResolver;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class LoadArticlesAction extends AbstractArticleAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2985917795937072176L;
	private static final Logger logger = Logger.getLogger(LoadArticlesAction.class);
	private Article article;
	

	
	private HitResolver hitResolver;
	private String headline;

	@Action(value="loadArticle",results={@Result(name="success",type="tiles",location="articlePageLayout"),
									     @Result(name="redirect", type="redirectAction", params={"actionName","search",
											                                                     "namespace","/index",
											                                                     "query","${headline}",
											                                                     "type","article",
											                                                     "missing","true"})})
	public String loadArticle(){
		try{
			if(logger.isTraceEnabled()){
				logger.trace("Retrieving article with headline " + headline);
			}
			article = service.getArticle(headline);
			if(article == null){ return "redirect";}
			hitResolver.resolve(article);
			return SUCCESS;
		}catch(Exception e){
			logger.error("Error retrieving article with headline " + headline + "\n " + e);
			e.printStackTrace();
		}
		return ERROR;
	}

	
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}
	
	@Autowired
	public void setHitResolver(HitResolver hitResolver) {
		this.hitResolver = hitResolver;
	}
	
}
