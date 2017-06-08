package com.newssite.test.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CreateArticlePageTests.class,
               CreateUserPageTests.class})
public class WebPageITTests {

}
