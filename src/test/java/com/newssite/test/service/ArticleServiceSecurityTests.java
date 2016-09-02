package com.newssite.test.service;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.newssite.dao.ArticleDao;
import com.newssite.model.Article;
import com.newssite.service.ArticleService;
import com.newssite.service.impl.ArticleServiceImpl;

@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class ArticleServiceSecurityTests extends AbstractServiceSecurityTest {

	@Autowired
	private  ArticleService service;
	

	@Autowired
	@Qualifier(value="mockArticleDao")
	private  ArticleDao mockDao;
	


	
	/* User doesn't have role
	 * required for method
	 */
	@Test(expected=AccessDeniedException.class)
	@WithMockUser(username="username2",password="password",authorities={"ROLE_USER"})
	public void testDeleteAccessDenied(){
		service.deleteArticle(5);
	}
	
	@Test(expected=AccessDeniedException.class)
	@WithMockUser(username="username2",password="password",authorities={"ROLE_USER"})
	public void testCreateArticleAccessDenied(){
		service.createArticle(new Article("headline2","","",""),"username2",new ArrayList<String>());
	}
	
	
	/*
	 * User has role for operation
	 */
	@Test
	@WithMockUser(username="username2",password="password",authorities={"ROLE_WRITER"})
	public void testCreateArticleAccessGranted(){
		Article a = new Article("headline2","","","");
		List<String> paragraphs = new ArrayList<>();
	    service.createArticle(a,"username2",paragraphs);
		Mockito.verify(mockDao,Mockito.times(1)).createArticle(a,"username2",paragraphs);
	}
			
	/*
	 * User has role but no permission for
	 * object
	 */
	@Test(expected=AccessDeniedException.class)
	@WithMockUser(username="username2",password="password",authorities={"ROLE_WRITER"})
	public void testAccessDeniedExceptionFromAcl(){
	    Article test = new Article("headline4","text","category","");
	    test.setId(5);
		service.editArticle(test,new ArrayList<String>());
	}
	
	/*
	 * User has role and permission for method and object
	 */
	@Test
	@WithMockUser(username="username3",password="password",authorities={"ROLE_WRITER"})
	public void testAccessGrantedFromAcl(){
		Article test = new Article("headline4","text","category","");
		List<String> paragraphs = new ArrayList<>();
		test.setId(5);
		service.editArticle(test,paragraphs);
		Mockito.verify(mockDao,Mockito.times(1)).editArticle(test,paragraphs);
	}
	
	/*
	 * User has admin role and can
	 * skip permission checks
	 */
	@Test
	@WithMockUser(username="username3",password="password",authorities={"ROLE_ADMIN"})
	public void testAccessGrantedToAdmin(){
		Article test = new Article("headline4","text","category","");
		List<String> paragraphs = new ArrayList<>();
		test.setId(5);
		service.editArticle(test,paragraphs);
		Mockito.verify(mockDao,Mockito.times(1)).editArticle(test,paragraphs);
	}	
	
	public void setService(ArticleServiceImpl service) {
		this.service = service;
	}

	@Override
	protected IDataSet getDataSet() throws MalformedURLException,
			DataSetException {
		return new FlatXmlDataSetBuilder().build(new File(defaultTestResourceFolder.concat("DbSecTestDataSet.xml")));
	}
	
	
}
