package com.newssite.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.newssite.test.dao.*;

import junit.framework.TestCase;


@RunWith(Suite.class)
@Suite.SuiteClasses(value = {UserDaoTests.class,
							 ArticleDaoTests.class,
							 MessageDaoTests.class })
public class DaoSuiteTest extends TestCase {

}
