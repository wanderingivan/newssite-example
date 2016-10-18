package com.newssite.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newssite.dao.ArticleDao;
import com.newssite.model.Article;
import com.newssite.model.Comment;
import com.newssite.service.ArticleService;

/**
 * Implementation of UserService
 * that provides transaction support,caching
 * acl support and delegates CRUD options to a dao.
 * @see com.pollapp.dao.MessageDao
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	
	private ArticleDao dao;
	private MutableAclService aclService;
	private String defaultArticleImagePath;
	
	@Autowired
	public ArticleServiceImpl(ArticleDao dao,MutableAclService aclService,String defaultArticleImagePath) {
		super();
		this.dao = dao;
		this.aclService = aclService;
		this.defaultArticleImagePath = defaultArticleImagePath;
	}

	@Override
	@Transactional
	@Caching(evict={@CacheEvict(value="article_short", allEntries=true),
					@CacheEvict(value="article", key="#headline")})
	public void createArticle(Article article, String author,List<String> paragraphs){
		if(article.getImagePath() == null){
			article.setImagePath(defaultArticleImagePath);
		}
		long id = dao.createArticle(article,author,paragraphs);
		createAcl(id,new PrincipalSid(author));

	}
	

	@Override
	@Transactional
	@Cacheable(value="article", key="#headline")
	public Article getArticle(String headline) {
		return dao.retrieveArticle(headline);
	}

	@Override
	@Transactional
	@Caching(evict={@CacheEvict(value="article_short", allEntries=true),
					@CacheEvict(value="article", key="#article.headline")})
	public void editArticle(Article article,List<String> paragraphs){
		dao.editArticle(article,paragraphs);
	}
	
	@Override
	@Transactional
	@Caching(evict={@CacheEvict(value="article_short", allEntries=true)})
	public void deleteArticle(long id) {
		dao.deleteArticle(id);
		deleteAcl(id);
	}

	@Override
	@Transactional
	@Cacheable(value="article_short", key="#root.methodName")
	public Map<String, List<Article>> latestCategories() {	
		return sortCategories(dao.latest());
	}

	//@Override
	@Transactional
	@Cacheable(value="article_short", key="#category")
	public List<Article> getCategory(String category) {
		return dao.loadArticlesByCategory(category);
	}

	@Override
	@Transactional
	@Cacheable(value="article_short", key="#headline")
	public List<Article> findArticles(String headline) {
		return dao.findArticles(headline);
	}


	@Override
	@Transactional
	@Cacheable(value="article_short", key="#category")
	public Map<String, String> getByCategory(String category) {
		return dao.getByCategory(category);
	}

	@Override
	@Transactional
	public List<Comment> loadArticleComments(String headline, int min) {
		return dao.loadArticleComments(headline, min);
	}

	@Override
	@Transactional
	public List<Article> latest() {
		return dao.latest();
	}

	private Map<String,List<Article>> sortCategories(List<Article> articles) {
		Map<String,List<Article>> sorted = new HashMap<>();
		sorted.put("latest",new LinkedList<Article>(articles.subList(0, 5)));
		sorted.put("politics",new LinkedList<Article>());
		sorted.put("international",new LinkedList<Article>());
		sorted.put("sports",new LinkedList<Article>());
		sorted.put("entertainment",new LinkedList<Article>());
		
		for(Article a : articles){
			switch(a.getCategory()){
				case "politics" :
					sorted.get("politics").add(a);
					break;
				case "international" :
					sorted.get("international").add(a);
					break;
				case "sports" :
					sorted.get("sports").add(a);
					break;
				case "entertainment" :
					sorted.get("entertainment").add(a);
					break;
				default :
					throw new IllegalArgumentException("Unknown category. Did you insert a new category ? " +a.getCategory());
			}
		}
		return sorted;
	}

	private void createAcl(long id,Sid sid) {
		MutableAcl acl = aclService.createAcl(new ObjectIdentityImpl(Article.class, id));
		acl.setOwner(sid);
		acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, sid, true);
		aclService.updateAcl(acl);
	}

	private void deleteAcl(long id){
		aclService.deleteAcl(new ObjectIdentityImpl(Article.class,id), true);
	}

}
