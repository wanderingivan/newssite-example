package com.newssite.test.dao;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.newssite.dao.ArticleDao;
import com.newssite.model.Article;
import com.newssite.model.User;

public class ArticleDaoTests extends AbstractDaoTest {

	private static String testArticle = "headline1",
			  testArticleDelete = "headline4",
			  testArticleUpdate = "headline2",
			  testArticleCreate = "createheadline";	
	
	@Autowired
	private ArticleDao articleDao;
	
	@Test 
	@Transactional
	public void testCreateArticle(){
		String [] paragraphs = new String [] {"paragraph","paragraph","paragraph"};
		Article test = new Article(testArticleCreate,"caption","politics",null);
		articleDao.createArticle(test,"username1",Arrays.asList(paragraphs));
		test = getArticle(testArticleCreate);
		assertNotNull("article was not persisted",test);
		assertEquals("article was not correctly persisted",testArticleCreate,test.getHeadline());
	}
	
	@Test
	@Transactional
	public void testArticleRetrieve(){
		Article article = getArticle(testArticle);
		assertEquals("Incorrect article retrieved",testArticle,article.getHeadline());
		assertEquals("Incorrect article text retrieved","sometext",article.getCaption());
		
	}

	@Test
	@Transactional
	public void testGetLatest(){
		List<Article> latest = articleDao.latest();
		assertEquals("Incorrect number of articles retrieved",8,latest.size());
		
	}
	
	@Test
	@Transactional
	public void testArticleUpdate(){
		String [] paragraphs = new String [] {"paragraph","paragraph","paragraph","4th paragraph"};
		Article test = new Article(testArticleUpdate,"new text", "new category",null);
		test.setId(4);
		articleDao.editArticle(test,Arrays.asList(paragraphs));
		test = getArticle(testArticleUpdate);
		assertEquals("Incorrect text saved","new text",test.getCaption());
		assertEquals("Incorrect category saved","new category",test.getCategory());
		assertEquals("Incorrect number of paragraphs saved",4,test.getParagraphs().size());
	}
	
	@Test
	@Transactional
	public void testArticleDelete(){
		articleDao.deleteArticle(5);
		assertNull("Article was not deleted",articleDao.retrieveArticle(testArticleDelete));
	}
	
	@Test
	@Transactional
	public void testAuthorRetrieval(){
		Article test = getArticle("headline2");
		User user = test.getAuthor();
		assertNotNull("User was not loaded ?",user);
		assertEquals("Incorrect user was retrieved","username2",user.getUsername());
	}
	
	@Test
	@Transactional
	public void testCommentsRetrieval(){
		Article test = getArticle(testArticle);
		assertEquals("Incorrect number of comments retrieved",3,test.getComments().size());
	}
	
	@Test
	@Transactional
	public void testFindArticles(){
		List<Article> articles = articleDao.findArticles("headline");
		assertNotNull(articles);
		assertEquals("Incorrect number of articles retrieved",8,articles.size());
	}

	@Test
	@Transactional
	public void testFindByCategory(){
		String[] categories = new String []{"politics","international","sports","entertainment"};
		Map<String,String> articles = null;
		for(String category : categories){
			articles = articleDao.getByCategory(category);
			assertNotNull(articles);
			assertEquals("Incorrect number of articles retrieved",2,articles.size());
		}
	}

	@Test
	@Transactional
	public void testGetMostViewed(){
		Map<String,String> articles =  articleDao.getMostViewed();
	}
	
	@Test
	@Transactional
	public void testGetMostCommented(){
		Map<String,String> articles =  articleDao.getMostCommented();
	}

	private Article getArticle(String headline){
		Article test = articleDao.retrieveArticle(headline);
		if(test == null){
			fail(String.format("No Article with headline %s",headline));
		}
		return test;
	}
	
	@Override
	protected IDataSet getDataSet() throws MalformedURLException,
			DataSetException {
		return new FlatXmlDataSetBuilder().build(new File(defaultTestResourceFolder.concat("DbTestDataSet.xml")));
	}
}
