package com.newssite.test.configuration;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.newssite.dao.UserDao;
import com.newssite.service.ArticleService;
import com.newssite.service.ImageService;
import com.newssite.service.UserService;
import com.newssite.service.impl.UserServiceImpl;

@Configuration
public class BasicTestConfig {
	
	@Bean
	public UserService userService(){
		return Mockito.mock(UserService.class);
	}
	
	@Bean
	public ArticleService articleService(){
		return Mockito.mock(ArticleService.class);
	}
	
	@Bean
	public ImageService imageService(){
		return Mockito.mock(ImageService.class);
	}
}

