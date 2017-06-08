package com.newssite.test.web;

import static org.junit.Assert.*;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.newssite.test.web.page.ArticlePage;
import com.newssite.test.web.page.CreateArticlePage;
import com.newssite.test.web.page.LoginPageForm;

@RunAsClient
@RunWith(Arquillian.class)
public class CreateArticlePageTests extends AbstractWebPageTest {
    
    @Page
    CreateArticlePage cPage;
    
    @Page
    ArticlePage aPage;
    
    @Test
    public void testCreateArticle(@InitialPage LoginPageForm login){

        login.logoutIfAuthenticated();
        loadPage("/user/loginPage");
        login.loginIfNotAuthenticated("username2", "password");
        
        loadPage("/article/createArticlePage");

        cPage.createArticle("headline101", "A caption", "politics");
       
        assertEquals("headline101", aPage.articleHeadline());
        assertEquals("A caption", aPage.articleCaption());
        assertEquals("politics", aPage.articleCategory());
        assertEquals("username2", aPage.articleAuthor());
    }
    
    @Test
    public void testCreateArticleAccessDeniedException(@InitialPage LoginPageForm login){

        login.logoutIfAuthenticated();
        loadPage("/user/loginPage");
        login.login("username1", "password");

        loadPage("/article/createArticlePage");

        cPage.createArticle("headline101", "A caption", "politics");
        
        assertEquals("404 Page",driver.getTitle().trim());
    }
    
    @Test
    public void testCreateArticleInputErrors(@InitialPage LoginPageForm login){
        login.logoutIfAuthenticated();
        loadPage("/user/loginPage");
        login.loginIfNotAuthenticated("username2", "password");
        
        loadPage("/article/createArticlePage");

        cPage.createArticle("he", "cap", "politics");

        assertFalse(cPage.getCaptionError().getText().isEmpty());
        assertFalse(cPage.getHeadlineError().getText().isEmpty());
    }

}
