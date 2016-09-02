package com.newssite.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.newssite.test.service.*;

import junit.framework.TestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { UserServiceSecurityTests.class,
							  ArticleServiceSecurityTests.class })
public class ServiceSuiteTest extends TestCase {

}
