package com.newssite.test.web;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.newssite.test.web.page.ArticlePage;
import com.newssite.test.web.page.EditArticlePage;
import com.newssite.test.web.page.LoginPage;

@RunAsClient
@RunWith(Arquillian.class)
public class EditArticlePageTests extends AbstractWebPageTest {

    
    @Page
    private EditArticlePage ePage;
    
    @Page
    private ArticlePage aPage;
    
    @Test
    public void testEditArticle(@InitialPage LoginPage login){
        login.logoutIfAuthenticated();
        login.login("username2", "password");
        loadPage("/article/editArticlePage?headline=");
        
    }
    
}
