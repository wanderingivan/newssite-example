package com.newssite.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@Import({WebSecurityConfig.class,MethodSecurityConfig.class})
public class SecurityConfig {
	
	@Bean
	@Autowired
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

}