package com.newssite.action.article;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.exception.DuplicateHeadlineException;
import com.newssite.interceptor.AuthenticatedUserAware;
import com.newssite.model.Article;
import com.newssite.service.ImageService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;

@InterceptorRefs(value={@InterceptorRef(value="authAwareStack")})
public class CreateUpdateArticleAction extends AbstractArticleAction implements AuthenticatedUserAware, ModelDriven<Article>,Preparable{

	/**
	 * 
	**/
	private static final long serialVersionUID = -719393820162304708L;
	
	private static Logger logger =  Logger.getLogger(CreateUpdateArticleAction.class);
	
	private ImageService imageService;
	
	private File articlePic;
	
	private Article article;
	private String user,
				   articlePicContentType,
				   articlePicFileName;
	
	private List<String> articleParagraphs;

	@Action(value="createArticle",results={@Result(name="success", type="redirectAction", params={"actionName","loadArticle","headline","${headline}"}),
										   @Result(name="input", type="tiles", location="articleCreateLayout")})
	public String createArticle(){
		try{
	        if(logger.isInfoEnabled()){
	            logger.info(String.format("Creating Article  %s by user %s with paragraphs %s ",article,user,articleParagraphs));
	        }			
			
			if(articlePic != null){
				String fileName= imageService.saveImage(articlePic, articlePicContentType, articlePicFileName);
				article.setImagePath(fileName);
			}
			service.createArticle(article,user,articleParagraphs);
				
			return SUCCESS;
		}catch(DuplicateHeadlineException de){
			addFieldError("article.headline", getText("global.duplicate_headline"));
			return INPUT;
		}catch(Exception e){
			logger.error(String.format("Error creating article %s with paragraphs %s : \n %s",article,articleParagraphs,e));
		}
		return ERROR;
	}
	
	@Action(value="preview",results={@Result(name="success", type="tiles", location="articlePreviewLayout"),
								     @Result(name="input", type="tiles", location="articleCreateLayout")})
	public String preview(){
		try{
			if(articlePic != null){
				imageService.saveImage(articlePic, articlePicContentType, articlePicFileName);
				article.setImagePath(articlePicFileName);
			}
			return SUCCESS;
		}catch(Exception e){
			logger.error(String.format("Error creating article preview with article %s with paragraphs %s \n %s",article,articleParagraphs,e));
		}
		return ERROR;
		
	}
	
	@Action(value="editArticle",results={@Result(name="success", type="redirectAction", params={"actionName","loadArticle","headline","${headline}"}),
										 @Result(name="input", type="tiles", location="editArticleInput")})
	public String editArticle(){
		try{
	        if(logger.isInfoEnabled()){
	            logger.info(String.format("User %s editing article %s", article,user));
	        }	            
			if(articlePic != null){
				String fileName = imageService.saveImage(articlePic, articlePicContentType, articlePicFileName);
				article.setImagePath(fileName);
			}
			service.editArticle(article,articleParagraphs);
			return SUCCESS;
		}catch(DuplicateHeadlineException de){
			addFieldError("headline", getText("global.duplicate_headline"));
			return INPUT;
		}catch(Exception e){
			logger.error(String.format("Error update article %s with paragraphs %s \n %s",article,articleParagraphs,e));
		}
		return ERROR;
	}


	public File getArticlePic() {
		return articlePic;
	}

	public void setArticlePic(File articlePic) {
		this.articlePic = articlePic;
	}

	public String getArticlePicContentType() {
		return articlePicContentType;
	}

	public void setArticlePicContentType(String articlePicContentType) {
		this.articlePicContentType = articlePicContentType;
	}

	public String getArticlePicFileName() {
		return articlePicFileName;
	}

	public void setArticlePicFileName(String articlePicFileName) {
		this.articlePicFileName = articlePicFileName;
	}

	public List<String> getArticleParagraphs() {
		return articleParagraphs;
	}

	public void setArticleParagraphs(List<String> paragraphs) {
		this.articleParagraphs = paragraphs;
	}


	@Autowired
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	@Override
	public void setUser(String user){
		this.user = user;
	}

	@Override
	public Article getModel() {
		return article;
	}

	@VisitorFieldValidator(appendPrefix=true)
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public void prepare() throws Exception {
		article = new Article();
	}
	
	
}
