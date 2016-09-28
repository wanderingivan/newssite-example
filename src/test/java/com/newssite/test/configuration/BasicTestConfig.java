package com.newssite.test.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.newssite.service.ArticleService;
import com.newssite.service.ImageService;
import com.newssite.service.UserService;

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

