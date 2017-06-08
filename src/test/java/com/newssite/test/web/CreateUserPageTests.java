package com.newssite.test.web;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.newssite.test.web.page.CreateUserPageForm;
import com.newssite.test.web.page.CreateUserPageModal;
import com.newssite.test.web.page.LoginPageForm;
import com.newssite.test.web.page.UserPage;

import static org.junit.Assert.*;

@RunAsClient
@RunWith(Arquillian.class)
public class CreateUserPageTests extends AbstractWebPageTest {

    
    @Page
    private CreateUserPageModal cPage;
    
    @Page
    private CreateUserPageForm formCPage;
    
    @Page
    private UserPage uPage;
    

    
    @Test
    public void testCreateUserAcessDeniedException(@InitialPage LoginPageForm login){
        login.loginIfNotAuthenticated("username1", "password");
        loadPage("/user/createUserPage");
        formCPage.createUser("username6", "password", "email@email6.com", "description");
        assertEquals("404 Page",driver.getTitle().trim());
    }
    
    
    @Test
    public void testCreateUser(@InitialPage LoginPageForm login){
        login.logoutIfAuthenticated();
        loadPage("/user/createUserPage");
        formCPage.createUser("username6", "password", "email@email6.com", "description");
        assertEquals("username6's Profile", uPage.getUsername());
    }

    @Test
    public void testCreateUserInputErrors(@InitialPage LoginPageForm login){
        login.logoutIfAuthenticated();
        loadPage("/user/createUserPage");
        formCPage.createUser("", "", "email@email6", "description");
        
        assertFalse(formCPage.getUsernameError().getText().isEmpty());
        assertFalse(formCPage.getEmailError().getText().isEmpty());
    }
    
}
