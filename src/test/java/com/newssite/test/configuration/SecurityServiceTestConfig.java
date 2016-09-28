package com.newssite.test.configuration;

import javax.sql.DataSource;


import org.hibernate.SessionFactory;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

import com.newssite.dao.ArticleDao;
import com.newssite.dao.UserDao;
import com.newssite.service.ArticleService;
import com.newssite.service.UserService;
import com.newssite.service.impl.UserServiceImpl;
import com.newssite.service.impl.ArticleServiceImpl;

@Configuration
@Import({com.newssite.configuration.MethodSecurityConfig.class})
@EnableTransactionManagement
public class SecurityServiceTestConfig {

	
	
	@Bean
	@Autowired
	public UserService userService(MutableAclService aclService){
		return new UserServiceImpl(userDao(),aclService,"",null);
	}
	
	@Bean
	@Autowired
	public ArticleService articleService(MutableAclService aclService){
		return new ArticleServiceImpl(articleDao(),aclService,"");
	}
	@Bean(name="mockUserDao")
	public UserDao userDao(){
		return Mockito.mock(UserDao.class);
	}
	
	@Bean(name="mockArticleDao")
	public ArticleDao articleDao(){
		return Mockito.mock(ArticleDao.class);
	}
	
	@Bean
	public SessionFactory sessionFactory(){
		return Mockito.mock(SessionFactory.class);
	}
	
	@Bean
	public Cache ehCache(){
		return cacheManager().getCache("aclCache");
	}
	
	@Bean CacheManager cacheManager(){
		return new NoOpCacheManager();
	}
	
	@Bean
	@Autowired
	public DaoAuthenticationProvider authProvider(DataSource dataSource){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService(dataSource));
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public UserDetailsService userDetailsService (DataSource dataSource){
		JdbcDaoImpl dao =  new JdbcDaoImpl();
		dao.setDataSource(dataSource);
		dao.setEnableGroups(true);
		dao.setEnableAuthorities(false);
		dao.afterPropertiesSet();
		return dao;
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(12);
	}
	
	
	@Bean(name="txManager")
	@Autowired
	public ResourceTransactionManager transactionManager (DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}
}
	
	
	