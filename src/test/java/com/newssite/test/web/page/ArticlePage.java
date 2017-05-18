package com.newssite.test.web.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePage {

    @FindBy(id="article_headline")
    private WebElement headline;
    
    @FindBy(id="article_category")
    private WebElement category;
    
    @FindBy(id="article_caption")
    private WebElement caption;
    
    @FindBy(id="author_username")
    private WebElement author;
    
    public String articleAuthor(){
        return author.getText()
                     .trim()
                     .toLowerCase();
    }
    
    public String articleHeadline(){
        return headline.getText()
                       .trim()
                       .toLowerCase();
    }
    
    public String articleCaption(){
        return caption.getText()
                      .trim();
    }
    
    public String articleCategory(){
        return category.getText()
                       .trim()
                       .toLowerCase();
    }
    
}
