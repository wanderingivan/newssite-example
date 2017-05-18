package com.newssite.test.web.page;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("NewsSite")
public class CreateUserPageModal {
    
    @FindBy(id="m_create_username")
    private WebElement usernameInput;

    @FindBy(id="m_create_password")
    private WebElement passwordInput;
    
    @FindBy(id="m_create_email")
    private WebElement emailInput;
        
    @FindBy(id="m_create_description")
    private WebElement descriptionInput;

    @FindBy(id="m_createSubmit")
    private WebElement createSubmit;
    
    @FindBy(id="loginModalLink")
    private GrapheneElement modalCall;
    
    public void createUser(String username,String password,String email,String description){
        this.modalCall.click();        
        Graphene.waitGui()
                .until()
                .element(usernameInput)
                .is()
                .visible();
        
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
    
}
