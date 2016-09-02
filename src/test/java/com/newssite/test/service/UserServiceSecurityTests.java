package com.newssite.test.service;

import java.io.File;
import java.net.MalformedURLException;



import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import com.newssite.dao.UserDao;
import com.newssite.model.User;
import com.newssite.service.UserService;



@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceSecurityTests extends AbstractServiceSecurityTest {

	@Autowired
	private UserService service;

	@Autowired
	@Qualifier(value="mockUserDao")
	private  UserDao mockDao;
	
	/* User doesn't have role
	 * required for method
	 */
	@Test(expected=AccessDeniedException.class)
	@WithMockUser(username="username2",password="password",authorities={"ROLE_USER"})
	public void testDeleteAccessDenied(){
		service.deleteUser("username2");

	}
	
	/*
	 * User has admin role and can
	 * pass permission checks
	 */	
	@Test
	@WithMockUser(username="username2",password="password",authorities={"ROLE_ADMIN"})
	public void testDeleteAccessGrantedToAdmin(){
	    service.deleteUser("username2");
		Mockito.verify(mockDao,Mockito.times(1)).deleteUser("username2");
	}
	
	/*
	 * User has role but no permission for
	 * object
	 */
	@Test(expected=AccessDeniedException.class)
	@WithMockUser(username="username2",password="password",authorities={"ROLE_USER"})
	public void testAccessDeniedExceptionFromEditUser(){
	    User test  = new User("username3","","","",null); 
	    test.setId(3);
		service.editUser(test);

	}
	
	/*
	 * User has role and permission for method and object
	 */
	@Test
	@WithMockUser(username="username3",password="password",authorities={"ROLE_USER"})
	public void testEditUserAccessGranted(){
		User test  = new User("username3","","","",null); 
		test.setId(3);
		service.editUser(test);
		Mockito.verify(mockDao,Mockito.times(1)).editUser(test);
	}
	
	/*
	 * User has admin role and can
	 * pass permission checks
	 */
	@Test
	@WithMockUser(username="username2",password="password",authorities={"ROLE_ADMIN"})
	public void testAccessGrantedToAdmin(){
		User test  = new User("username3","","","",null); 
		test.setId(3);
		service.editUser(test);
		Mockito.verify(mockDao,Mockito.times(1)).editUser(test);
	}
	
	public void setService(UserService service) {
		this.service = service;
	}
	

	@Override
	protected IDataSet getDataSet() throws MalformedURLException,
			DataSetException {
		return new FlatXmlDataSetBuilder().build(new File(defaultTestResourceFolder.concat("DbSecTestDataSet.xml")));
	}
	
}
