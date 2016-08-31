package com.newssite.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;



@Configuration
@PropertySource("classpath:application.properties")
public class HibernateConfig {
	
	@Value("${hibernate.dialect}")
	private String dialect;
	
	@Value("${hibernate.show_sql}")
	private boolean show_sql;
	
	@Bean
	@Autowired
	public SessionFactory sessionFactory(DataSource dataSource) throws IOException{
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();		
		factory.setDataSource(dataSource);
		factory.setHibernateProperties(hibernateProperties());
		factory.setPackagesToScan("com.newssite.model");
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	
	@Bean
	public Properties hibernateProperties(){
		Properties p = new Properties();
		p.put("hibernate.dialect",dialect);
		if(show_sql){
			p.put("hibernate.show_sql","true");
			p.put("hibernate.format_sql","true");
		}
	    
		return p;
	}
}
