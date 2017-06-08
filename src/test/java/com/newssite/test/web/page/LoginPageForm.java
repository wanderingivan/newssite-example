package com.newssite.test.web.page;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("NewsSite/user/loginPage")
public class LoginPageForm {

    @FindBy(id="f_username")
    private WebElement usernameInput;

    @FindBy(id="f_password")
    private WebElement passwordInput;
    
    @FindBy(id="loginSubmit")
    private WebElement loginSubmit;
    
    @FindBy(id="logoutSubmit")
    private GrapheneElement logout;
    
    public void login(String username, String password){

        
        this.usernameInput.clear();
        this.passwordInput.clear();
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        
        Graphene.guardHttp(loginSubmit)
                .click();
    }
    
    public void loginIfNotAuthenticated(String username, String password){
        if(!logout.isPresent()){
            login(username,password);
        }
    }
    
    public void logoutIfAuthenticated(){
        if(logout.isPresent()){
            Graphene.guardHttp(logout).click();
        }
    }
    
}
