package com.newssite.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.newssite.test.validation.*;

import junit.framework.TestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {ArticleActionValidationTests.class,
							 UserActionValidationTests.class})
public class ValidationsSuiteTest extends TestCase {

}
