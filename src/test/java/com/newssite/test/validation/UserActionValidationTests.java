package com.newssite.test.validation;


import org.junit.Test;

import com.newssite.action.user.CreateUpdateUserAction;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

public class UserActionValidationTests extends AbstractActionValidationTestCase{

	private final String action = "/user/editUser";
	
	protected void setUpRequestTestParams(String [] param){
		request.setParameter("username",param[0]);
		request.setParameter("email",param[1]);
		request.setParameter("password",param[2]);
	}
	
	@Test
	public void testValidationWithNoErros() throws Exception{
		ActionProxy actionProxy = getProxy(action,new String []{"username","email@email.com","password"});
		CreateUpdateUserAction action =  (CreateUpdateUserAction) actionProxy.getAction();	
		assertEquals("The validation didn't pass when it should have",
								ActionSupport.SUCCESS,actionProxy.execute());
		assertTrue("There were errors in the validation when passing correct parameters",
								action.getFieldErrors().size() < 1);
	}
	
	
	@Test
	public void testValidationWithThreeErrors() throws Exception{ // Empty field errors

		
		ActionProxy actionProxy = getProxy(action,new String [] {"","",""});
		CreateUpdateUserAction action =  (CreateUpdateUserAction) actionProxy.getAction();	
		assertEquals("The validation passed when it shouldn't have",
								ActionSupport.INPUT,actionProxy.execute());
		assertTrue(String.format("There were less  than expected "
				+ "errors in the validation when passing incorrect parameters"
				+ "expected 2 but were [%d]",action.getFieldErrors().size()),
								action.getFieldErrors().size() >= 2);
		
	}
	
	@Test
	public void testValidationWithStringLengthErrors() throws Exception{

		
		ActionProxy actionProxy = getProxy(action,new String [] {"use","email@email.com","password"});
		CreateUpdateUserAction action =  (CreateUpdateUserAction) actionProxy.getAction();	
		
		
		
		assertEquals("The validation passed when it shouldn't have",
								ActionSupport.INPUT,actionProxy.execute());
		assertTrue("There were less  than expected "
				+ "errors in the validation when passing incorrect parameters",
								action.getFieldErrors().size() >= 1);	

	}
	

}
