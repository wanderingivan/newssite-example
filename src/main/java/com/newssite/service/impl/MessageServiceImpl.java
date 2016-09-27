package com.newssite.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newssite.dao.MessageDao;
import com.newssite.service.ImageService;
import com.newssite.service.MessageService;
import com.newssite.model.Chat;
import com.newssite.model.Comment;
import com.newssite.model.Message;
import com.newssite.model.Task;

/**
 * Implementation of UserService
 * that provides transaction support
 * and delegates CRUD options to a dao.
 * @see MessageDao
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

	private final static String POPULAR = "popular",
			                    LATEST = "latest";
	
	private MessageDao messageDao;
	private ImageService imageService;
	
	
	@Autowired
	public MessageServiceImpl(MessageDao messageDao,ImageService imageService) {
		super();
		this.messageDao = messageDao;
		this.imageService = imageService;
	}

	@Override
	@Transactional
	public void addComment(String message, Long articleId, String username) {
		messageDao.addComment(message, articleId, username);
	}
	
	@Override
	@Transactional
	public void editComment(String newMessage, Long commentId) {
		messageDao.editComment(newMessage, commentId);
	}

	@Override
	@Transactional
	public List<Comment> retrieveArticleComments(Long articleId) {
		return messageDao.retrieveArticleComments(articleId);
	}
	
	@Override
	@Transactional
	public List<Comment> latestComments() {
		return messageDao.getLatestComments();
	}

	@Override
	@Transactional
	public void deleteComment(Long commentId) {
		messageDao.deleteComment(commentId);
	}
	
	@Override
	@Transactional
	public void sendMessage(String message, String sender, String receiver) {
		messageDao.sendMessage(message,sender,receiver);
	}

	@Override
	@Transactional
	public void deleteMessage(Long messageId) {
		messageDao.deleteMessage(messageId);
	}

	@Override
	@Transactional
	public SortedSet<Chat> retrieveUserChats(String username) {
		return messageDao.retrieveUserChats(username);
	}

	@Override
	@Transactional
	public String [] addMessage(String sender, long chatId, String message) throws IOException {
		
		Message saved = messageDao.addMessage(sender,chatId,message);
		String[] result = new String[]{saved.getPoster().getUsername(),
				                       imageService.getB64(saved.getPoster().getImagePath()),
				                       saved.getMessage()};
		return result;
	}

	@Override
	@Transactional
	public long upvote(int commentId) {
		return messageDao.voteIncrement(commentId,1);
	}

	@Override
	@Transactional
	public long downvote(int commentId) {
		return messageDao.voteIncrement(commentId,-1);
	}

	@Override
	@Transactional
	public Chat getChat(String username,long chatId) {
		return messageDao.retrieveChat(chatId);
	}

	@Override
	@Transactional
	public List<Comment> getComments(String ordering) {
		if(ordering.equals(POPULAR)){
			return messageDao.getPopularComments();
		}else if(ordering.equals(LATEST)){
			return messageDao.getLatestComments();
		}else{
			throw new IllegalArgumentException(ordering);
		}
	}

	@Override
	@Transactional
	public void addTask(Task task, String username, String assigned) {
		messageDao.addTask(task, username, assigned);
	}

	@Override
	@Transactional
	public void completeTask(long taskId, String comment) {
		messageDao.completeTask(taskId, comment);
	}

	@Override
	@Transactional
	public void removeTask(long taskId) {
		messageDao.removeTask(taskId);
	}

	@Override
	@Transactional
	public List<Task> latestTasks() {
		return messageDao.getTasks(false);
	}

	@Override
	@Transactional
	public List<Task> retrieveAllTasks(){
		return messageDao.getTasks(true);
	}
	
	@Override
	@Transactional
	public List<Task> retrieveUserPendingTasks(String username){
		return messageDao.getTasks(username, false);
	}
	
	@Override
	@Transactional
	public List<Task> retrieveUserTasks(String username) {
		return messageDao.getTasks(username, true);
	}

	@Override
	@Transactional
	public int countPending(String username) {
		return messageDao.countPending(username);
	}

	@Override
	@Transactional
	public int countUnread(String username) {
		return messageDao.countUnread(username);
	}

	@Override
	@Transactional
	public List<Message> getUnread(String username) {
		return messageDao.getUnread(username);
	}
}
