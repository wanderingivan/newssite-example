package com.newssite.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.FilterType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.newssite.dao.ArticleDao;
import com.newssite.dao.UserDao;
import com.newssite.service.ArticleService;
import com.newssite.service.ImageService;
import com.newssite.service.LogService;
import com.newssite.service.UserService;
import com.newssite.service.impl.ArticleServiceImpl;
import com.newssite.service.impl.BasicLogService;
import com.newssite.service.impl.ImageServiceImpl;
import com.newssite.service.impl.UserServiceImpl;
import com.newssite.util.ImageUtil;

@Configuration
@ComponentScan(basePackages={"com.newssite.service.impl","com.newssite.dao.impl","com.newssite.util"},excludeFilters={@ComponentScan.Filter(type= FilterType.REGEX,pattern="com.newssite.test.*")})
@Import({DataSourceConfig.class,HibernateConfig.class,CacheConfig.class,TransactionConfig.class,SecurityConfig.class})
public class NewsSiteConfig {

	
	@Value("${image.folder}")
	private String imageFolder;
	
	@Value("${image.convert.jpg}")
	private boolean convertToJpg;
	
	@Value("${image.maxUncompressed.size}")
	private long maxUncompressedSize;
	
	@Value("${image.placeholder.filename}")
	private String placeholder;
	
	@Value("${image.defaultProfileImagePath}")
	private String defaultProfileImagePath;
	
	@Value("${image.defaultArticleImagePath}")
	private String defaultArticleImagePathl;
	
	@Bean
	public LogService logService(){
		Map<String,String> aliases = new HashMap<>();
	    //Aliases for log filenames
		aliases.put("action","newssite.actionDebug.log");
		aliases.put("error","newssite.error.log");
		aliases.put("security","newssite.security.log");
		aliases.put("dao","newssite.daoDebug.log");
		return new BasicLogService(aliases);
	}
	
   	/* 
   	 *  Multipart resolver so we can include spring security 
   	 *  csrf tokens in multipart posts 
   	 */
	@Bean(name="filterMultipartResolver")
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(2000000);
		return resolver;
	}

	@Autowired
	@Bean(name="userServiceImpl")
	public UserService userService(UserDao userDao,MutableAclService aclService,PasswordEncoder encoder){
		return new UserServiceImpl(userDao,aclService,defaultProfileImagePath,null);//FIXME Add Password Encoder !!!
	}

	@Autowired
	@Bean(name="articleServiceImpl")
	public ArticleService articleService(ArticleDao articleDao,MutableAclService aclService){
		return new ArticleServiceImpl(articleDao,aclService,defaultArticleImagePathl);
	}
	
	@Bean
	@Autowired
	public ImageService imageService(ImageUtil imageUtil){
		return new ImageServiceImpl(imageUtil, placeholder);
	}
	
	@Bean
	public ImageUtil imageUtil(){
		ImageUtil util = new ImageUtil(imageFolder,convertToJpg,maxUncompressedSize);
		return util;
	}
	
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
