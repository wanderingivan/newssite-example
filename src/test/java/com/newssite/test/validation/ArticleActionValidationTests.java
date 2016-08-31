package com.newssite.test.validation;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.newssite.action.article.CreateUpdateArticleAction;
import com.newssite.service.ArticleService;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleActionValidationTests extends AbstractActionValidationTestCase {

	private final String action ="/article/editArticle";
	
	// Required setup of AuthInterceptor
	static {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	    SecurityContextHolder.getContext().setAuthentication(
	    		new UsernamePasswordAuthenticationToken("username2","password",authorities));
		
	}
	

	@Test
	public void testValidationWithNoErrors() throws Exception{
		
		
		String [] params =  new  String []{"someheadline","caption","politics"};
		
		ActionProxy actionProxy = getProxy(action,params);
		
		CreateUpdateArticleAction action =  (CreateUpdateArticleAction) actionProxy.getAction();
		setUpAction(action);
		assertEquals("The validation didn't pass when it should have",
						ActionSupport.SUCCESS,actionProxy.execute());
		assertTrue("There were errors in the validation when passing correct parameters",
								action.getFieldErrors().size() < 1);
	}

	@Test
	public void testValidationWithErrors() throws Exception{ // Empty field errors
		
		String [] params =  new  String []{"","",""};
		

		ActionProxy actionProxy = getProxy(action,params);

		
		assertNotNull("action proxy shouldn't be null", actionProxy);
		
		CreateUpdateArticleAction action =  (CreateUpdateArticleAction) actionProxy.getAction();
		action.setService(Mockito.mock(ArticleService.class));
		assertEquals("The validation passed when it shouldn't have",
								ActionSupport.INPUT,actionProxy.execute());
		assertTrue("There were less  than expected "
				+ "errors in the validation when passing incorrect parameters",
								action.getFieldErrors().size() >= 3);
	}
	
	@Test
	public void testValidationWithStringLengthErrors() throws Exception{

		String [] params =  new  String []{"he","ca","politics"};
		
		ActionProxy actionProxy = getProxy(action,params);

		assertNotNull("action proxy shouldn't be null", actionProxy);
		
		CreateUpdateArticleAction action =  (CreateUpdateArticleAction) actionProxy.getAction();
		action.setService(Mockito.mock(ArticleService.class));
		
		assertEquals("The validation passed when it shouldn't have",
								ActionSupport.INPUT,actionProxy.execute());
		assertTrue("There were less  than expected "
				+ "errors in the validation when passing incorrect parameters " + action.getFieldErrors().size(),
								action.getFieldErrors().size() == 2);	
	
	}
	
	
	
	@Test
	public void testValidationWithCategoryErrors() throws Exception{

		String [] params =  new  String []{"headline","caption","random cat"};
		
		ActionProxy actionProxy = getProxy(action,params);

		assertNotNull("action proxy shouldn't be null", actionProxy);
		
		CreateUpdateArticleAction action =  (CreateUpdateArticleAction) actionProxy.getAction();
		action.setService(Mockito.mock(ArticleService.class));
		
		assertEquals("The validation passed when it shouldn't have",
								ActionSupport.INPUT,actionProxy.execute());
		assertTrue("There were less  than expected "
				+ "errors in the validation when passing incorrect parameters " + action.getFieldErrors().size(),
								action.getFieldErrors().size() == 1);	
	
	}
	
	protected void setUpRequestTestParams(String [] param){
		request.setParameter("headline",param[0]);
		request.setParameter("caption",param[1]);
		request.setParameter("category",param[2]);
	}
	
	private void setUpAction(CreateUpdateArticleAction action){
		action.setService(Mockito.mock(ArticleService.class));
	}
}
