package com.newssite.test.dao;


import java.io.File;
import java.util.Map;
import java.net.MalformedURLException;

import javax.transaction.Transactional;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.dao.UserDao;
import com.newssite.exception.DuplicateUsernameException;
import com.newssite.exception.DuplicateEmailException;
import com.newssite.model.User;

public class UserDaoTests extends AbstractDaoTest {
	
	private static String userToCreate = "create",
						  userToLoad = "username2",
						  userToDelete = "username3",
						  userWithFourArticles = "username2",
					      userWithThreeComments ="username1";
								  
	
	private UserDao userDao;

	@Test
	@Transactional
	public void testUserCreate() {
		userDao.createUser(new User(userToCreate,"","","",null));
		User test = getUser(userToCreate);
		assertEquals(userToCreate,test.getUsername());
	}
	
	@Test(expected=DuplicateUsernameException.class)
	@Transactional
	public void testCreateUserDuplicateUsernameException() {
		userDao.createUser(new User("username2","","","",null));
		User test = getUser(userToCreate);
		assertEquals(userToCreate,test.getUsername());
	}
	
	@Test(expected=DuplicateEmailException.class)
	@Transactional
	public void testCreateUserDuplicateEmailException() {
		try{
		userDao.createUser(new User(userToCreate,"some@email","","",null));
		User test = getUser(userToCreate);
		assertEquals(userToCreate,test.getUsername());
		}catch(ConstraintViolationException ex){
		}
	}
	
	@Test
	@Transactional
	public void testUserRetrieve() {
		User test = getUser(userToLoad);
		
		assertNotNull(test);
		assertEquals(userToLoad, test.getUsername());
	}
	
	@Test
	@Transactional
	public void updateUser() {
		User test = new User("usernameEdit","new Email","placeholder desc","newPass",null);
		test.setId(4);
		userDao.editUser(test);
		test = getUser("usernameEdit");
		assertNotNull(test);
		assertEquals("new Email",test.getEmail());
	}
	

	@Test
	@Transactional
	public void testDeleteUser() { 
		userDao.deleteUser(userToDelete);
		assertNull("User was not deleted!",userDao.retrieveUser(userToDelete));
	}
	
	@Test
	@Transactional
	public void testRetrieveArticles(){
		User user = getUser(userWithFourArticles);
		assertEquals("Incorrect number of articles retrieved",4,user.getArticlesWritten().size());
	}
	
	@Test
	@Transactional
	public void testRetrieveComments(){
		User user = getUser(userWithThreeComments);
		assertEquals("Incorrect number of comments retrieved",3,user.getComments().size());
	}

	@Test
	@Transactional
	public void testFindUsers(){
		Map<String,String> users = userDao.findUsers("username");
		assertNotNull(users);
		assertEquals("Incorrect number of users retrieved",5,users.size());
	}

	@Test
	@Transactional
	public void testUserLock(){
		userDao.disableUser(userToLoad);
		User test = getUser(userToLoad);
		assertFalse("User was not disabled", test.getEnabled());
	}

	@Test
	@Transactional
	public void testUserUnlock(){
		User test = getUser("username3");
		userDao.enableUser(userToLoad);
		assertTrue("User was not enabled", test.getEnabled());
	}	
	
	@Test
	@Transactional
	public void testUserAuthorityChange(){
		userDao.changeAuthority(userToLoad, "ADMIN");
	}

	@Test(expected=IllegalArgumentException.class)
	@Transactional
	public void testAuthorityChangeIllegalArgument(){
		userDao.changeAuthority(userToLoad, "ROLE_");
	}

	@Test
	@Transactional
	public void testChangePassword(){
		userDao.changePassword( "username1", "new password");
		User test = userDao.retrieveUser("username1");
		assertEquals("new password", test.getPassword());
	}
	
	@Test
	@Transactional
	public void testGetPassword(){
		 assertEquals("Bar",userDao.getPassword( "username1"));
	}
	
	private User getUser(String username) {
		User test = userDao.retrieveUser(username);
		if(test == null){
			fail(String.format("No user with username %s was retrieved",username));
		}
		return test;
	}
	
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	protected IDataSet getDataSet() throws MalformedURLException, DataSetException {
		return new FlatXmlDataSetBuilder().build(new File(defaultTestResourceFolder.concat("DbTestDataSet.xml")));
	}
	
}
