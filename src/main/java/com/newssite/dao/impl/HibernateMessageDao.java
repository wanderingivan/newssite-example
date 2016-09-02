package com.newssite.dao.impl;


import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newssite.dao.MessageDao;
import com.newssite.model.Chat;
import com.newssite.model.Comment;
import com.newssite.model.Message;
import com.newssite.model.Task;
import com.newssite.model.User;


@Repository
@SuppressWarnings("unchecked")
public class HibernateMessageDao  extends AbstractHibernateDao<Message> implements MessageDao {

	

	@Autowired
	public HibernateMessageDao(SessionFactory sessionFactory) {
		super(sessionFactory,Message.class);
	}

	@Override
	public void addComment(String message, Long articleId, String username) {
		getNamedQuery("addComment")
		              .setParameter("message", message)
		              .setParameter("username", username)
		              .setParameter("articleId",articleId)
		              .executeUpdate();
	}

	@Override
	public void editComment(String message, Long commentId) {
		createQuery("UPDATE comments SET message =:message WHERE id=:commentId")
				      .setString("message",message)
				      .setLong("commentId", commentId)
				      .executeUpdate();
	}

	@Override
	public void deleteComment(long commentId) {
		createQuery("DELETE FROM comments WHERE id = :commentId")
		              .setLong("commentId", commentId)
		              .executeUpdate();
	}

	@Override
	public List<Comment> retrieveArticleComments(Long articleId) {
		return createQuery("FROM comments WHERE article.id=:articleId ORDER BY posted DESC")
		 			  	     .setLong("articleId", articleId)
		 			  	     .list();
	}

	@Override
	public Comment retrieveComment(Long commentId) {
		return (Comment) createQuery("FROM comments WHERE id = :commentId")
					                   .setLong("commentId", commentId)
					                   .setMaxResults(1)
					                   .uniqueResult();
	}

	@Override
	public void sendMessage(String message, String sender, String receiver) {
		getNamedQuery("sendMessage")
					   .setParameter("msg",message)
					   .setParameter("sender", sender)
				       .setParameter("receiver", receiver)
				       .executeUpdate();
	}

	@Override
	public void deleteMessage(Long messageId) {
		createQuery("DELETE FROM messages WHERE id = :messageId")
		              .setLong("messageId", messageId)
		              .executeUpdate();
	}

	@Override
	public SortedSet<Chat> retrieveUserChats(String username) {

		Session session = getSession();
		session.enableFetchProfile("chats");
		User user = (User) session.createCriteria(User.class)
				                  .add(Restrictions.eq("username", username))
				                  .uniqueResult();
		session.disableFetchProfile("chats");
		return user.getChats();
	}


	@Override
	public List<Comment> getLatestComments() {
		return getComments("posted");
	}

	@Override
	public List<Comment> getPopularComments() {
		return getComments("votes");
		
	}
	
	private List<Comment> getComments(String ordering){
		return createCriteria(Comment.class)
				             .addOrder(Order.desc(ordering))
				             .setMaxResults(10)
		                     .list();
	}
	
	@Override
	public Message addMessage(String sender, long chatId, String message) {
		return (Message) getNamedQuery("addMessageToChat")
						               .setParameter("sender", sender)
						               .setParameter("chatId", chatId)
						               .setParameter("message",message)
						               .uniqueResult();
	}

	@Override
	@Transactional
	public long voteIncrement(long commentId,long value){
		return (Long) getSession()
	                                .createSQLQuery("CALL addVote(:commentId,:vote)")
				                    .addScalar("votes", new LongType())
							        .setParameter("commentId", commentId)
							        .setParameter("vote",value)
							        .uniqueResult();
	}

	@Override
	public Chat retrieveChat(long chatId) {
		return (Chat) createCriteria(Chat.class)
							        .add(Restrictions.eq("id", chatId))
							        .uniqueResult();
	}

	@Override
	public int countPending(String username) {
		return (Integer) getSession().createSQLQuery("SELECT count(*) as cnt FROM tasks WHERE assignee = :assignee AND complete = 0")
				                     .addScalar("cnt", new IntegerType())
				                     .setString("assignee", username)
				                     .uniqueResult();
	}

	@Override
	public void addTask(Task task, String assigner, String assignee) {
		task.setAssignee(assignee);
		task.setAssigner(assigner);
		task.setCreated(new Date());
		getSession().persist(task);
	}

	@Override
	public void completeTask(long taskId, String comment) {
		createQuery("UPDATE tasks SET message = :comment, complete = true, completed = NOW() WHERE id = :id")
		            .setString("comment", comment)
		            .setLong("id",taskId)
		            .executeUpdate();
	}

	@Override
	public void removeTask(long taskId) {
		createQuery("DELETE FROM tasks where id = :id")
		            .setLong("id", taskId)
		            .executeUpdate();
	}

	@Override
	public List<Task> getTasks(boolean fetchAll) {
		Criteria crit = createCriteria(Task.class)
				                    .addOrder(Order.desc("created"));
		if(!fetchAll){ crit.setMaxResults(10);}
		return crit.list();
	}

	@Override
	public List<Task> getTasks(String username, boolean fetchAll) {
		Criteria crit = createCriteria(Task.class)
				                    .add(Restrictions.eq("assignee",username))
				                    .addOrder(Order.desc("created"))
				                    .addOrder(Order.desc("complete"));
		if(!fetchAll){ 
			crit.add(Restrictions.eq("complete", false));
			crit.setMaxResults(10);
		}
		
		return crit.list();
	}

	@Override
	public int countUnread(String username) {
		return (int) getSession().createSQLQuery("CALL countUnread(:username)")
						   .addScalar("unread",new IntegerType())
				           .setString("username", username)
				           .uniqueResult();
	}

	@Override
	public List<Message> getUnread(String username) {
		return getNamedQuery("unread")
		                   .setString("username", username)
		                   .list();
	}
	
}
