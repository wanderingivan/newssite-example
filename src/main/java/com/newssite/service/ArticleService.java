package com.newssite.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;

import com.newssite.model.Article;
import com.newssite.model.Comment;

/**
 * 
 * Service Layer interface providing
 * basic CRUD operations for Article objects
 * @see com.newssite.model.Article
 * Defines method security checks.
 */
public interface ArticleService {

	@PreAuthorize("hasRole('ROLE_WRITER')")
	public void createArticle(Article article, String author,List<String> paragraphs);
	
	public Article getArticle(String headline);
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasPermission(#article,'WRITE')")
	public void editArticle(Article article,List<String> paragraphs);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteArticle(long id);

	public Map<String, List<Article>> latestCategories();
	
	public List<Article> latest();

	public List<Article> findArticles(String headline);

	public Map<String, String> getByCategory(String category);

	public List<Comment> loadArticleComments(String headline, int min);

}
