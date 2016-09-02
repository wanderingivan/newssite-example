package com.newssite.dao;

import java.util.List;
import java.util.SortedSet;

import com.newssite.model.Chat;
import com.newssite.model.Comment;
import com.newssite.model.Message;
import com.newssite.model.Task;

/**
 *	DAO interface providing 
 *  methods for manipulating messages. 
 */
public interface MessageDao {
	

	/**
	 * Returns the unfinished tasks count for the admin user from the database
	 * @param username
	 * 
	 * @return count
	 */
	int countPending(String username);
	
	/**
	 * Inserts a task for the specified user in the database.<br/>
	 * The task will appear in a users pending tasks
	 * and is visible to everyone with admin permission 
	 * @param task the 
	 * @param assigner username that creates the task
	 * @param assignee 
	 */
	void addTask(Task task,String assigner,String assignee);
		
	/**
	 *  Sets a task as complete 
	 * @param taskId id of the task in db
	 * @param comment optional comment on task completion 
	 */
	void completeTask(long taskId,String comment);
	
	/**
	 * Removes a task from the db
	 * @param taskId id of the task in db
	 */
	void removeTask(long taskId);
	
	/**
	 * List tasks from the database.
	 * @param fetchAll optional parameter sets whether all task 
	 *        would be retrieved or just a limited set
	 *        
	 * @return a collection of Task ordered by task creation date
	 * @see Task
	 */
	List<Task> getTasks(boolean fetchAll);

	/**
	 * List tasks from the database for a particular user.
	 * @param username the user whose assigned taskes will be fetched
	 * 
	 * @param fetchAll optional parameter sets whether all task 
	 *        would be retrieved or just a limited set
	 * @return a collection of Task ordered by task creation date
	 * @see Task
	 */
	List<Task> getTasks(String username, boolean fetchAll);


	/**
	 * Adds a comment for article with id 
	 * @param message the comment body
	 * @param articleId the article id to post the comment in
	 * @param username the poster's username
	 */
	void addComment(String message, Long articleId, String username);

	/**
	 * Edits a comment with id
	 * @param message
	 * @param commentId 
	 */
	void editComment(String message, Long commentId);
	
	/**
	 * Deletes a comment by it's id
	 * @param commentId
	 */
    void deleteComment(long commentId);

    /**
     * Retrieve an articles comments by it's id
     * @param articleId
     * @return
     */
	List<Comment> retrieveArticleComments(Long articleId);
	
	/**
	 * Retrieves a comment by id
	 * @param commentId
	 * @return
	 */
	Comment retrieveComment(Long commentId);
		
	/**
	 * Sends a messages between two users.
	 * If a chat with the users doesn't exits creates a new chat
	 * @param message
	 * @param sender
	 * @param receiver
	 */
	void sendMessage(String message, String sender, String receiver);

	/**
	 * Deletes a message by it's id
	 * @param messageId
	 */
	void deleteMessage(Long messageId);
	
	/**
	 * Retrieves all the chats of a user by username
	 * @param username
	 * @return
	 */
	SortedSet<Chat> retrieveUserChats(String username);



	/**
	 * Sends  message to a chat
	 * @param sender the username of the sender
	 * @param chatId the chat to add the message to 
	 * @param message the message content to add
	 */
	Message addMessage(String sender, long chatId, String message);

	/**
	 * Change the vote count for a comment
	 * @param commentId the comment identifier
	 * @param value the value with which to increment the count 
	 * @return vote count for the comment after the operation
	 */
	long voteIncrement(long commentId, long value);

	/**
	 * Retrieve a chat by id 
	 * @param chatId
	 * @return
	 */
	Chat retrieveChat(long chatId);
	
	/**
	 * Returns the unread  messages count for a user from the database
	 * @param username
	 * 
	 * @return count
	 */
	int countUnread(String username);

	/**
	 * Returns a list  of unread messages for a user from the database.
	 * The messages will be marked as read after retrieval.
	 * @param user_id the id of the user 
	 * @return a collection of Message ordered by date of post.
	 * @see Message
	 */
	List<Message> getUnread(String username);

	List<Comment> getPopularComments();

	/**
	 * Return the 10 newest comments
	 * @return
	 */
	List<Comment> getLatestComments();
	
}
