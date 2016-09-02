package com.newssite.test.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.newssite.test.configuration.AbstractDBUnitTest;
import com.newssite.test.configuration.DBUnitTestConfig;
import com.newssite.test.configuration.SecurityServiceTestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DBUnitTestConfig.class, SecurityServiceTestConfig.class}, loader=AnnotationConfigContextLoader.class)
public abstract class AbstractServiceSecurityTest extends AbstractDBUnitTest {


}
