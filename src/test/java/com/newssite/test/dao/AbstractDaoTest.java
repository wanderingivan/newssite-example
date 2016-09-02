package com.newssite.test.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.newssite.test.configuration.AbstractDBUnitTest;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={com.newssite.test.configuration.DBUnitTestConfig.class,com.newssite.test.configuration.DaoTestConfig.class}, loader=AnnotationConfigContextLoader.class)
public abstract class AbstractDaoTest extends AbstractDBUnitTest{

}
