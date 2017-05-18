package com.newssite.test.web.page;

import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.jboss.arquillian.graphene.Graphene;


@Location("NewsSite")
public class LoginPage {

    @FindBy(id="m_username")
    private WebElement usernameInput;

    @FindBy(id="m_password")
    private WebElement passwordInput;
    
    @FindBy(id="m_loginSubmit")
    private WebElement loginSubmit;
    
    @FindBy(id="logoutSubmit")
    private GrapheneElement logout;
    
    @FindBy(id="loginModalLink")
    private GrapheneElement modalCall;
    
    public void login(String username, String password){
        this.modalCall.click();
        
        Graphene.waitGui()
                .until()
                .element(usernameInput)
                .is()
                .visible();
        
        this.usernameInput.clear();
        this.passwordInput.clear();
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        
        Graphene.guardHttp(loginSubmit)
                .click();
    }
    
    public void loginIfNotAuthenticated(String username, String password){
        if(modalCall.isPresent()){
            login(username,password);
        }
    }
    
    public void logoutIfAuthenticated(){
        if(logout.isPresent()){
            Graphene.guardHttp(logout).click();
        }
        
    }
    
}
