package com.newssite.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.newssite.action.article.AbstractArticleAction;
import com.newssite.model.Article;

@ParentPackage(value="index")
@Results({@Result(name="success",type="tiles",location="indexLayout")})
public class IndexAction extends AbstractArticleAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8582563204335664300L;
	private static final Logger logger = Logger.getLogger(IndexAction.class);

	
	
	private List<Article> carousel;
	private List<Article> category1;
	private List<Article> category2;
	private List<Article> category3;
	private List<Article> category4;

	private String carouselCategory;
	
	@Action(value="latest")
	public String execute(){
		try{
			populateArticles(new String[]{"latest",
					 					  "politics",
					 					  "international",
					 					  "sports",
					 					  "entertainment"},service.latestCategories());
			setCarouselCategory("latest");
			return SUCCESS;
		}catch(Exception e){
			logger.error("Error loading articles in indexAction "+ e);
		}
		return ERROR;
	}
 

	@Action(value="politics")
	public String politics(){
		try{
			populateArticles(new String[]{"politics",
										  "international",
										  "sports",
										  "entertainment",
										   null},service.latestCategories());
			setCarouselCategory("politics");
			return SUCCESS;
		}catch(Exception e){
			logger.error("Error loading articles in indexAction "+ e);
		}
		return ERROR;
	}
	 
	@Action(value="international")
	public String international(){
		try{
			populateArticles(new String[]{"international",
										  "politics",
										  "sports",
										  "entertainment",
			                               null},service.latestCategories());
			setCarouselCategory("international");
			return SUCCESS;
		}catch(Exception e){
			logger.error("Error loading articles in indexAction "+ e);
		}
		return ERROR;
	}
  
	@Action(value="sports")
	public String sports(){
		try{
			populateArticles(new String[]{"sports",
										  "entertainment",
										  "international",
										  "politics",
			                               null},service.latestCategories());
			setCarouselCategory("sports");
			return SUCCESS;
		}catch(Exception e){
			logger.error("Error loading articles in indexAction "+ e);
		}
		return ERROR;
	}
  
	@Action(value="entertainment")
	public String entertainment(){
		try{
			populateArticles(new String[]{"entertainment",
										  "sports",
										  "international",
										  "politics",
			                               null},service.latestCategories());
			setCarouselCategory("entertainment");
			return SUCCESS;
		}catch(Exception e){
			logger.error("Error loading articles in indexAction "+ e);
		}
		return ERROR;
	}
 
	/**
	 * Populates article lists based on ordering of categories 
	 * @param ordering
	 * @param articles
	 */
	private void populateArticles(String[] ordering,Map<String,List<Article>> articles){
		carousel = articles.get(ordering[0]);
		category1 = articles.get(ordering[1]);
		category2 = articles.get(ordering[2]);
		category3 = articles.get(ordering[3]);
		category4 = articles.get(ordering[4]);
	}
	
	
	public List<Article> getCarousel() {
		return carousel;
	}


	public void setCarousel(List<Article> carousel) {
		this.carousel = carousel;
	}


	public List<Article> getCategory1() {
		return category1;
	}


	public void setCategory1(List<Article> category1) {
		this.category1 = category1;
	}


	public List<Article> getCategory2() {
		return category2;
	}


	public void setCategory2(List<Article> category2) {
		this.category2 = category2;
	}


	public List<Article> getCategory3() {
		return category3;
	}


	public void setCategory3(List<Article> category3) {
		this.category3 = category3;
	}


	public List<Article> getCategory4() {
		return category4;
	}


	public void setCategory4(List<Article> category4) {
		this.category4 = category4;
	}


	public String getCarouselCategory() {
		return carouselCategory;
	}


	public void setCarouselCategory(String carouselCategory) {
		this.carouselCategory = carouselCategory;
	}
	
}
