package com.newssite.test.web.page;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("NewsSite")
public class CreateUserPageForm {
    
    @FindBy(id="f_create_username")
    private WebElement usernameInput;

    @FindBy(id="f_create_password")
    private WebElement passwordInput;
    
    @FindBy(id="f_create_email")
    private WebElement emailInput;
        
    @FindBy(id="f_create_description")
    private WebElement descriptionInput;

    @FindBy(id="f_createSubmit")
    private WebElement createSubmit;
    
    @FindBy(id="loginModalLink")
    private GrapheneElement modalCall;
    
    @FindBy(id="usernameError")
    private WebElement usernameError;
    
    @FindBy(id="passwordError")
    private WebElement passwordError;
    
    @FindBy(id="emailError")
    private WebElement emailError;
    
    @FindBy(id="descriptionError")
    private WebElement descriptionError;
    
    public void createUser(String username,String password,String email,String description){
        
        this.usernameInput.clear();
        this.usernameInput.sendKeys(username);
        this.passwordInput.clear();
        this.passwordInput.sendKeys(password);
        this.emailInput.clear();
        this.emailInput.sendKeys(email);
        this.descriptionInput.clear();
        this.descriptionInput.sendKeys(description);
        
        Graphene.guardHttp(createSubmit)
                .click();
    }
    
    public WebElement getUsernameError(){
        return usernameError;
    }
    
    public WebElement getPasswordError() {
        return passwordError;
    }


    public WebElement getEmailError() {
        return emailError;
    }


    public WebElement getDescriptionError() {
        return descriptionError;
    }
    
}
