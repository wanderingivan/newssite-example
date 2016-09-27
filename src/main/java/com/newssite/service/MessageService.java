package com.newssite.service;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;

import org.springframework.security.access.prepost.PreAuthorize;

import com.newssite.model.Chat;
import com.newssite.model.Comment;
import com.newssite.model.Message;
import com.newssite.model.Task;


/**
 * Service Layer interface providing
 * basic operations for messages,comments
 * and admin tasks
 * Defines method security checks.
 * @see Message
 * @see Task
 * @see Comment
 */
public interface MessageService {
	
	/**
	 * Adds a task for the specified user<br/>
	 * The task will appear in a users pending tasks
	 * and is visible to everyone with admin permission 
	 * @param task the 
	 * @param assigner username that creates the task
	 * @param assignee 
	 */
	@PreAuthorize("hasRole='ROLE_ADMIN'")
	void addTask(Task task, String username, String assigned);
	
	/**
	 *  Sets a task as complete 
	 * @param taskId id of the task in db
	 * @param comment optional comment on task completion 
	 */
	@PreAuthorize("hasRole='ROLE_ADMIN'")
	void completeTask(long task_id, String comment);

	/**
	 * Removes a task, 
	 * @param taskId id of the task in db
	 */
	@PreAuthorize("hasRole='ROLE_ADMIN'")
	void removeTask(long task_id);

	/**
	 * List most recent tasks. 
	 * @return a collection of Task ordered by task creation date
	 * @see Task
	 */
	@PreAuthorize("hasRole='ROLE_ADMIN'")
	List<Task> latestTasks();
	
	/**
	 * Loads all tasks. 
	 * @param username the user whose assigned tasks will be fetched
	 * @return a collection of Task ordered by task creation date
	 * @see Task
	 */
	@PreAuthorize("hasRole='ROLE_ADMIN'")
	List<Task> retrieveAllTasks();
	
	/**
	 * Loads all tasks for a particular user.
	 * @param username the user whose assigned tasks will be fetched
	 * @return a collection of Task ordered by task creation date
	 * @see Task
	 */
	@PreAuthorize("hasRole='ROLE_ADMIN'")
	List<Task> retrieveUserTasks(String username);

	/**
	 * Loads recent unfinished tasks for a particular user.
	 * @param username the user whose assigned tasks will be fetched
	 * @return a collection of Task ordered by task creation date
	 * @see Task
	 */
	@PreAuthorize("hasRole='ROLE_ADMIN'")
	List<Task> retrieveUserPendingTasks(String username);
	
	/**
	 * Returns the unfinished tasks count for the admin user. 
	 * @param username
	 * 
	 * @return count
	 */
	@PreAuthorize("hasRole='ROLE_ADMIN'")
	int countPending(String username);
	
	@PreAuthorize("isAuthenticated()")
	public void addComment(String message, Long articleId, String username);

	@PreAuthorize("hasRole='ROLE_ADMIN'")
	public void editComment(String newMessage,Long commentId);	
	
	/**
	 * Sends a messages between two users.
	 * @param message
	 * @param sender
	 * @param receiver
	 */
	@PreAuthorize("isAuthenticated()")
	public void sendMessage(String message, String sender, String receiver);

	@PreAuthorize("hasRole='ROLE_ADMIN'")
	public void deleteComment(Long commentId);
	
	@PreAuthorize("hasRole='ROLE_ADMIN'")
	public void deleteMessage(Long messageId);
	
	/**
	 * Returns the unread  messages count for a user from the database
	 * @param username
	 * 
	 * @return count
	 */
	@PreAuthorize("isAuthenticated()")
	int countUnread(String username);
	
	/**
	 * Returns a list  of unread messages for a user from the database.
	 * The messages will be marked as read after retrieval.
	 * @param user_id the id of the user 
	 * @return a collection of Message ordered by date of post.
	 * @see Message
	 */
	@PreAuthorize("isAuthenticated()")
	List<Message> getUnread(String username);
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.name")
	public SortedSet<Chat> retrieveUserChats(String username);

	/**
	 * Sends  message to a chat
	 * @param sender the username of the sender
	 * @param chatId the chat to add the message to 
	 * @param message the message content to add
	 * @return the message as a String[]
	 */
	@PreAuthorize("isAuthenticated()")
	public String[] addMessage(String sender, long chatId, String message) throws IOException;
	
	public List<Comment> latestComments();
	
	public List<Comment> retrieveArticleComments(Long articleId);

	public List<Comment> getComments(String ordering);
	
	public long upvote(int commentId);

	public long downvote(int commentId);

	public Chat getChat(String username,long chatId);

}
