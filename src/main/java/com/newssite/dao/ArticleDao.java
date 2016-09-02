package com.newssite.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.newssite.model.Article;
import com.newssite.model.Comment;

/**
 *	DAO interface providing 
 *  methods for CRUD operations on articles and
 *  their associations. 
 */
public interface ArticleDao {


	 /**
	  * Create an article with a user for author
	  * @param article An {@code Article} object with members headline,caption,category and imagePath set 
	  * @param author the username of the user to set as author
	  * @param paragraphs paragraphs to add to the article
	  * @return the id of the created article 
	  * @see Article
	  */
	 long createArticle(Article article, String author,List<String> paragraphs);

	 /**
	  * Retrieves an article by it's headline
	  * The article will have all collections besides comments initialized
	  * @param headline
	  * @return
	  * @see Article
	  */
	 Article retrieveArticle(String headline);

	 /**
	  * Updates an article and it's paragraphs in the database
	  * @param article An {@code Article} object with members headline,caption,category and imagePath set 
	  * @param paragraphs paragraphs to add to the article
	  * @return the id of the created article 
	  * @see Article
	  */
	 void editArticle(Article article,List<String> paragraphs);
	
	 /**
	  * Removes an article and it's associations from the database
	  * @param headline
	  */
	 void deleteArticle(long id);

	 /**
	  * Finds articles matching headline
	  * Returns articles without their collections
	  * @param headline
	  * @return
	  * @see Article
	  */
	 List<Article> findArticles(String headline);

	 /**
	  * Loads articles with matching category ordered by their last update
	  * Returns articles without their collections
	  * @param category
	  * @return
	  * @see Article
	  */
	 List<Article> loadArticlesByCategory(String category);

	 /**
	  * Loads articles ordered by their last update
	  * Returns articles without their collections
	  * @return
	  * @see Article
	  */
	 List<Article> latest();

	 /**
	  * Loads articles with matching category ordered by their last update
	  * a shortlist containing only the headline and imagePath of the article
	  * @param category
	  * @return a shortlist containing only the headline and imagePath of the article
	  */
	 Map<String, String> getByCategory(String category);

	 /**
	  * Loads comments of an article ordered by their posted date
	  * between the min parameter and min+10
	  * @param headline the article's headline
	  * @param min
	  * @return
	  */
	 List<Comment> loadArticleComments(String headline, int min);

	 /**
	  * Loads articles ordered by the number of their comments
	  * @return a shortlist containing only the headline and imagePath of the article
	  * @see Article
	  */
	 Map<String, String> getMostCommented();

	 /**
	  * Loads articles ordered by the number of their views
	  * @return a shortlist containing only the headline and imagePath of the article
	  * @see Article
	  */
	 Map<String, String> getMostViewed();

	 /**
	  * Updates the hit counter of articles
	  * @param articleHits EntrySet containing an articles headline number of views and whether the hit count has been updated
	  */
	 void updateHits(Set<Entry<String, Object[]>> articleHits);

}
