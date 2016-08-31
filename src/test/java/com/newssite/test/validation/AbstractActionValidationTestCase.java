package com.newssite.test.validation;

import org.apache.struts2.StrutsTestCase;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.newssite.test.configuration.BasicTestConfig;
import com.opensymphony.xwork2.ActionProxy;

public abstract class AbstractActionValidationTestCase extends StrutsTestCase {
	
	@SuppressWarnings("rawtypes")
	private final Class DEFAULT_CONFIG_CLASS = BasicTestConfig.class;

	private GenericApplicationContext applicationContext;
	
	protected void setupBeforeInitDispatcher() throws Exception{
		if(applicationContext == null){
			applicationContext = new AnnotationConfigApplicationContext(DEFAULT_CONFIG_CLASS);
		}
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, applicationContext);
	}
    
    protected final ActionProxy getProxy(String action,String [] params){
    	setUpRequestTestParams(params);
		ActionProxy actionProxy = getActionProxy(action);
		assertNotNull("Action proxy shouldn't be null", actionProxy);
		actionProxy.setExecuteResult(false);
		return actionProxy;	    	
    }
    
    protected abstract void setUpRequestTestParams(String [] param);
}
