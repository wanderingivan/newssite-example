package com.newssite.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newssite.dao.UserDao;
import com.newssite.exception.extractor.ConstraintExceptionConverter;
import com.newssite.model.Article;
import com.newssite.model.Comment;
import com.newssite.model.User;


@Repository
public class HibernateUserDao extends AbstractHibernateDao<User> implements UserDao {

	private static final Logger logger = Logger.getLogger(HibernateUserDao.class);
	
	@Autowired
	public HibernateUserDao(SessionFactory sessionFactory){
		super(sessionFactory,User.class);
	}

	@Override
	public long createUser(User user) {	
		try{
			Session session = getSession();
			user.setEnabled(true);
			user.setCreatedOn(new Date());
			session.persist(user);
			SQLQuery query  =(SQLQuery) session.createSQLQuery("INSERT into group_members(username,group_id) VALUES(:username,:group_id)")
			                               	   .setString("username", user.getUsername())
			                               	   .setLong("group_id", resolveGroup("user"));
			query.executeUpdate();
		}catch(ConstraintViolationException ce){			
			RuntimeException ex = ConstraintExceptionConverter.convertException(ce);
			throw ex;
		}
	    return user.getId();
	}
	
	
	@Override
	public User retrieveUser(String username){
		Session session = getSession();
		
		Criteria crit = session.createCriteria(User.class)
		                       .add(Restrictions.eq("username",username));

		session.enableFetchProfile("comments_articles"); // Load all associations on the first go since we're not using session in view
		User u = (User) crit.uniqueResult();
		session.disableFetchProfile("comments_articles");
		return u;
	}

	@Override
	public void editUser(User edit) {
		try{
			createQuery("UPDATE users SET username = :username, email = :email, details = :details, imagePath = :imagePath WHERE id = :id")
					.setString("username", edit.getUsername())
		            .setString("email",edit.getEmail())
		            .setString("details",edit.getDetails())
		            .setString("imagePath",edit.getImagePath())
		            .setLong("id",edit.getId())
		            .executeUpdate();
		}catch(ConstraintViolationException ce){			
			RuntimeException ex = ConstraintExceptionConverter.convertException(ce);
			throw ex;
		}
	}
	
	


	@Override
	public void deleteUser(String username) {
		createQuery("delete from users where username=:username")
			        .setString("username", username)
			        .executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String,String> findUsers(String username){
		Map<String,String> map = new HashMap<>();
		ProjectionList prList = Projections.projectionList()
				                           .add(Projections.property("username"))
		                                   .add(Projections.property("imagePath"));
		Criteria crit = createCriteria()
                                    .add(Restrictions.ilike("username",username.concat("%")))
	                                .setProjection(prList);
		List<Object []> cursor = crit.list();
		for(Object [] row : cursor){
			map.put((String) row[0], (String) row[1]);
		}
		return map;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Article> loadUserArticles(String username,int min){
		return createCriteria(Article.class)
						   .add(Restrictions.eq("username",username))
						   .setFirstResult(min)
						   .setMaxResults(min+10)
						   .list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> loadUserComments(String username,int min){
		return createCriteria(Comment.class)
						   .add(Restrictions.eq("username",username))
						   .setFirstResult(min)
						   .setMaxResults(min+10)
						   .list();	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> newestUsers() {
		return createCriteria()
			               .addOrder(Order.desc("createdOn"))
						   .setMaxResults(10)
						   .list();
	}

	@Override
	public void enableUser(String username) {
		changeUserLock(username,true);
	}

	@Override
	public void disableUser(String username) {
		changeUserLock(username,false);
	}
	

	@Override
	public void changeAuthority(String username, String authority) {
	    logger.info(String.format("Changing role to %s for username %s",authority,username));			
		createQuery("update group_members set group_id=:group where username=:username")
		            .setLong("group", resolveGroup(authority))
			        .setString("username",username)
			        .executeUpdate();
	}
	
	@Override
	public void changePassword(String principal,
			String newPassword) {
		logger.info("Changing password for user " + principal);
	    createQuery("UPDATE users SET password = :password WHERE username=:username")
		            .setString("password", newPassword)
		            .setString("username", principal)
		            .executeUpdate();
	}
	
	@Override
	public String getPassword(String principal){
		 return (String) createQuery("SELECT password FROM users u WHERE username = :username")
				      		         .setString("username", principal)
				      		         .uniqueResult();
	}
	
	/**
	 * Locks or unlocks a user
	 * @param username the user to lock or unlock 
	 * @param enabled whether to lock or unlock user 
	 */
	private void changeUserLock(String username,boolean enabled){
		logger.info(String.format("Setting enabled to %s for username %s",enabled,username));			
		createQuery("update users set enabled=:enabled where username=:username")
		            .setBoolean("enabled", enabled)
			        .setString("username",username)
			        .executeUpdate();
	}
	
	/**
	 * Resolves an authority to corresponding group id
	 * in the db
	 * @param authority the authority name to resolve
	 * @return the id of the group that has the authority
	 * @throws IllegalArgumentException if the authority role is not mapped
	 */
	private long resolveGroup(String authority){
		long gId =0;
		authority = authority.toUpperCase();
		switch(authority){
		case "USER":
			gId=1;
			break;
		case "WRITER":
			gId=2;
			break;
		case "ADMIN":
			gId=3;
			break;
		default:
			throw new IllegalArgumentException("Received an unmapped role as argument "+authority);
		}
		return gId;
	}
	
}
