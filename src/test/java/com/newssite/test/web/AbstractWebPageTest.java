package com.newssite.test.web;

import java.net.URL;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.openqa.selenium.WebDriver;

public class AbstractWebPageTest {

    @Drone
    protected WebDriver driver;
    
    @ArquillianResource
    protected URL deploymentUrl;
    
    
    
    protected void loadPage(String url){
        driver.get(deploymentUrl.toString()
                                .trim()
                                .concat(url));
    }
}
