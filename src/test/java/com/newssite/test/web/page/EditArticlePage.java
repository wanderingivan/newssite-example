package com.newssite.test.web.page;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class EditArticlePage {

    @FindBy(id="headline")
    private WebElement headlineInput;

    @FindBy(id="caption")
    private WebElement captionInput;

    @FindBy(id="category")
    private Select categoryInput;
    
    @FindBy(id="headlineError")
    private WebElement headlineError;
    
    @FindBy(id="captionError")
    private WebElement captionError;
    
    @FindBy(id="editArticleSubmit")
    private WebElement submit;
 
    public void editArticle(String headline,String caption,String category){
        headlineInput.clear();
        headlineInput.sendKeys(headline);
        captionInput.clear();
        captionInput.sendKeys(caption);
        categoryInput.selectByValue(category);
        Graphene.guardHttp(submit).click();
    }

    public WebElement getHeadlineError() {
        return headlineError;
    }

    public void setHeadlineError(WebElement headlineError) {
        this.headlineError = headlineError;
    }

    public WebElement getCaptionError() {
        return captionError;
    }

    public void setCaptionError(WebElement captionError) {
        this.captionError = captionError;
    }
    
}


