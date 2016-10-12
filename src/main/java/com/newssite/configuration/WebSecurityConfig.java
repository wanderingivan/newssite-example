package com.newssite.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.newssite.security.*;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth,DaoAuthenticationProvider provider) throws Exception {
		auth.authenticationProvider(provider);
	}
	

	@Override
	public void configure(WebSecurity web) throws Exception {
		/* Disable security on static resources and images
		 * to allow expires filter to set cache control instead
		 * of spring security
		 */
		web.ignoring().antMatchers("/css/*")
		              .antMatchers("/images/*")
		              .antMatchers("/js/*")
		              .antMatchers("/util/loadImage*");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	  http.authorizeRequests()
		  .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		  .antMatchers("/article/createArticle").access("hasRole('ROLE_WRITER')")
		  .antMatchers("/article/editArticle").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_WRITER')")
		  .antMatchers("/user/editUser").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
		  .antMatchers("/**").permitAll()
		  .and()
		  .formLogin().successHandler(authSuccessHandler())
			          .failureHandler(authFailureHandler())
	      .and()
	      .logout().logoutSuccessUrl("/index/latest")
	      .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
	      .and().httpBasic();
		
	}
	
	@Bean(name="authEntryPoint")
	public LoginUrlAuthenticationEntryPoint authenticationEntryPoint(){
		return new LoginUrlAuthenticationEntryPoint("/WEB-INF/content/base/login.jsp");
	}
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler(){
		SavedRequestAwareRoleBasedAuthenticationSuccessHandler handler = new SavedRequestAwareRoleBasedAuthenticationSuccessHandler(roleUrls(),defaultSuccessUrl());
		handler.setDefaultTargetUrl("/index/latest");
		return handler;
	}
	@Bean
	public AuthenticationFailureHandler authFailureHandler(){
		return new SimpleAuthenticationFailureHandler(defaultFailureUrl());
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
		AccessDeniedHandlerImpl handler = new  AccessDeniedHandlerImpl();
		handler.setErrorPage(defaultFailureUrl());
		return handler;
	}
	
	/**
	 * Returns a map with user roles as keys
	 * and default success urls as values
	 * @return
	 */
	private Map<String,String> roleUrls(){
		HashMap<String,String> roleUrls = new HashMap<>();
		roleUrls.put("ROLE_ADMIN", "/admin/welcome");
		roleUrls.put("ROLE_USER", "/index/latest");
		return roleUrls;
	}

	/**
	 * Returns a default authentication success url
	 * @return
	 */
	private String defaultSuccessUrl(){
		return "/index/latest";
	}
	

	/**
	 * Returns a default authentication failure url
	 * @return
	 */
	private String defaultFailureUrl(){
		return "/user/loginPage";
	}
	
}
