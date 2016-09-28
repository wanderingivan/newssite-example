package com.newssite.dao.impl;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.dao.ArticleDao;
import com.newssite.exception.extractor.ConstraintExceptionConverter;
import com.newssite.model.Article;
import com.newssite.model.Comment;
import com.newssite.model.Paragraph;
import com.newssite.model.User;

/**
 * ArticleDao implementation 
 *	that uses hibernate to perform operations. 
 * 
 */
@Repository
public class HibernateArticleDao extends AbstractHibernateDao<Article> implements ArticleDao {

	
	private static final Logger logger = Logger.getLogger(HibernateArticleDao.class);

	@Autowired
	public HibernateArticleDao(SessionFactory sessionFactory){
		super(sessionFactory,Article.class);
	}
	
    
	@Override
	public long createArticle(Article article, String author,List<String> paragraphs){
		Session session = getSession();
		if(logger.isDebugEnabled()){
			logger.debug(String.format("Received parameters headline %s caption %s category %s imagePath %s username %s with paragraphs %s",
											article.getHeadline(),article.getCaption(),article.getCategory(),article.getImagePath(),author,paragraphs));
		}
		Criteria crit = session.createCriteria(User.class)
		                       .add(Restrictions.eq("username", author));

		User user = (User) crit.uniqueResult();
		article.setAuthor(user);
		setParagraphs(article,paragraphs);
		try{
			session.persist(article);
			return article.getId();
		}catch(ConstraintViolationException ce){
			RuntimeException ex = ConstraintExceptionConverter.convertException(ce);
			throw ex;
		}
	}
	
	@Override
	public Article retrieveArticle(String headline){
			Session session = getSession();
			Criteria crit = session.createCriteria(Article.class)
			                       .add(Restrictions.eq("headline",headline));
			
			session.enableFetchProfile("full"); // Load all associations on the first go since we're not using session in view
			Article a = (Article) crit.uniqueResult();
			session.disableFetchProfile("full");
			
			return a;											   
	}
	
	@Override
	public void editArticle(Article update,List<String> paragraphs){
		Article article = load(update.getId());
		article.setHeadline(update.getHeadline());
		article.setCaption(update.getCaption());
		article.setCategory(update.getCategory());
		article.setLastEdited(new Timestamp(System.currentTimeMillis()));
		if(update.getImagePath() != null){
			article.setImagePath(update.getImagePath());
		}
		updateParagraphs(article, paragraphs);
	}

	@Override
	public void deleteArticle(long id) {
		createQuery("DELETE FROM articles WHERE id=:id")
				   .setLong("id", id)
		           .executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Article> findArticles(String headline){
		StringBuilder  restrictionBuilder = new StringBuilder("%").append(headline)
						                                          .append("%");
		return createCriteria()
		                   .add(Restrictions.ilike("headline",restrictionBuilder.toString()))
		                   .addOrder(Order.desc("lastEdited"))
		                   .setMaxResults(10)
		                   .list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> loadArticleComments(String headline,int min){
		return createCriteria()
						   .add(Restrictions.eq("headline",headline))
						   .setFirstResult(min)
						   .setMaxResults(min+10)
						   .list();	
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Article> loadArticlesByCategory(String category){
		return createCriteria()
						   .add(Restrictions.eq("category",category))
						   .addOrder(Order.desc("lastEdited"))
						   .setMaxResults(5)
						   .list();

	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Article> latest(){
		return createCriteria()
						   .addOrder(Order.desc("lastEdited"))
						   .setMaxResults(20)
						   .list();
	}

	@Override
	public Map<String,String> getByCategory(String category){
		return getMap(createCriteria()
		                          .add(Restrictions.eq("category",category))
		                          .addOrder(Order.desc("lastEdited"))
						          .setProjection(getCommonProjectionList())
		                          .setMaxResults(3));
	}
	


	@Override
	public Map<String, String> getMostCommented() {
		  return getMap(createCriteria(Article.class,"article")
		                            .createAlias("article.comments", "comments")
		                            .setProjection(getCommonProjectionList().add(Projections.groupProperty("article.id"))
							                                                .add(Projections.count("comments.id").as("commentsCount")))
		                            .addOrder(Order.desc("commentsCount"))
		                            .addOrder(Order.desc("lastEdited"))
		                            .setMaxResults(3));
	}

	@Override
	public Map<String, String> getMostViewed() {
		  return getMap(createCriteria()
									.setProjection(getCommonProjectionList())
									.addOrder(Order.desc("hits"))
									.setMaxResults(3));
	}
	

	@Override
	public void updateHits(final Set<Entry<String, Object[]>> articleHits){
		Session session = getSession();
		Query query = session.createQuery("UPDATE articles SET hits=:hits WHERE headline=:headline");
		int count = 0;
		for(Entry<String,Object[]> e : articleHits){
			if(!((boolean)e.getValue()[1])){continue;} // skip if hit count is not updated
			query.setString("headline",e.getKey())
			     .setLong("hits",(long)e.getValue()[0])
			     .executeUpdate();
			count++;
			if(count % 20 == 0){// Avoid ending up with huge batch updates
				session.flush();
				session.clear();
			}
		}
	}
	
	/**
	 * Convenience method that adds paragraphs to an article
	 * @param article the article to use
	 * @param paragraphs list of paragraph bodies
	 * 
	 */
	private void setParagraphs(Article article, List<String> paragraphs) {
		for(int i =0;i < paragraphs.size();i++){
			article.addParagraph(new Paragraph(article,paragraphs.get(i),i));
		}
	}

	/**
	 * Returns a projection list configured to 
	 * extract an article's headline and imagePath
	 * as scalars
	 * @return {@code ProjectionList}
	 * @see ProjectionList
	 */
	private ProjectionList getCommonProjectionList(){
		return Projections.projectionList()
					      .add(Projections.property("headline"))
					      .add(Projections.property("imagePath"));
	}
	
	/**
	 * Executes a {@code ProjectionList} configured {@code Criteria}
	 * and extracts the result set into a {@code java.util.Map}
	 * while preserving the order (if any) specified in the criteria.<br/>
	 * <p>Assumes the results set is of length 2.</p>
	 * <p>Sets first object in rs as key and second as value.</p> 
	 * @param crit the Criteria to use
	 * @return the result set extracted into a map
	 * @see ProjectionList
	 */
	@SuppressWarnings("unchecked")
	private Map<String,String> getMap(Criteria crit){
		Map<String,String> map = new LinkedHashMap<>();
		List<Object []> cursor = crit.list();
		for(Object [] row : cursor){
			map.put((String) row[0], (String) row[1]);
		}
		return map;
	}

	/**
	 * Convenience method to update article paragraphs.
	 * If a paragraph exists it only updates the paragraph's
	 * message else it creates and adds a new paragraph to the action.
	 * @param article article to update
	 * @param paragraphs a list of paragraph bodies
	 */
	private void updateParagraphs(Article article, List<String> paragraphs) {
		
		Iterator<Paragraph> it = article.getParagraphs().iterator();
		
		int size = article.getParagraphs().size(),
		    listSize = paragraphs.size();
		
		for(int i=0;i<listSize;i++){
			if(i < size){
				it.next()
			        .setText(paragraphs.get(i));
			}else{
				article.addParagraph(new Paragraph(article,paragraphs.get(i),size));
				size++;
			}
		}
	}

}
