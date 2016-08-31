package com.newssite.test.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.hibernate.SessionFactory;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

import com.newssite.service.ImageService;
import com.newssite.util.ImageUtil;


@Configuration
@EnableTransactionManagement
@ComponentScan({"com.newssite.dao.impl"})
public class DaoTestConfig {
	
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

	
	@Bean(name="txManager")
	@Autowired
	public ResourceTransactionManager transactionManager(DataSource dataSource) throws IOException{
		ResourceTransactionManager manager = new HibernateTransactionManager(sessionFactory(dataSource));
		return manager;
	}
	
    @Bean
	public Properties hibernateProperties(){
		Properties p = new Properties();

		p.put("hibernate.show_sql","false");
		p.put("hibernate.format_sql","true");
	
		p.put("hibernate.c3p0.min_size","5");
		p.put("hibernate.c3p0.max_size","20");
		p.put("hibernate.c3p0.timeout","300");
		p.put("hibernate.c3p0.max_statements","50");
		p.put("hibernate.c3p0.idle_test_period","3000");
		return p;
	}
	
    @Bean
    public ImageService imageService (){
    	return Mockito.mock(ImageService.class);
    }

}
