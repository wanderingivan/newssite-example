package com.newssite.test.web.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserPage {

    @FindBy(id="userTitle")
    private WebElement username;
    
    @FindBy(id="userDescription")
    private WebElement description;
    
    public String getUsername(){
        return username.getText().trim();
    }
    
    public String getDescription(){
        return description.getText().trim();
    }
}
