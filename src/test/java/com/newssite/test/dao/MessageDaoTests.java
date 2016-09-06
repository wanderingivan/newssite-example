package com.newssite.test.dao;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.SortedSet;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.newssite.dao.MessageDao;
import com.newssite.model.Chat;
import com.newssite.model.Comment;
import com.newssite.model.Message;
import com.newssite.model.Task;


public class MessageDaoTests extends AbstractDaoTest {


	private MessageDao dao;
	

	@Test
	@Transactional
	public void testTaskRetrieval(){
		List<Task> tasks = dao.getTasks(false);
		assertNotNull(tasks);
		assertEquals(2, tasks.size());
	}
	
	@Test
	@Transactional
	public void testUserTasksRetrieval(){
		List<Task> tasks = dao.getTasks("username1",false);
		assertNotNull(tasks);
		assertEquals(1, tasks.size());
		Task test = tasks.get(0);
		assertEquals("username2",test.getAssigner());
		assertEquals("username1",test.getAssignee());
	}
	
	@Test
	@Transactional
	public void testCountTasks(){
		int tasks = dao.countPending("username1");
		assertEquals(1,tasks);
	}
	
	@Test
	@Transactional
	public void testAddTask(){
		dao.addTask(new Task("test task 3","test desc"), "username2", "username3");
		List<Task> tasks = dao.getTasks("username3",true);
		assertNotNull(tasks);
		assertEquals(2, tasks.size());
		Task test = tasks.get(0);
		assertEquals("username2",test.getAssigner());
		assertEquals("username3",test.getAssignee());
	}
	
	@Test
	@Transactional
	public void testCompleteTask(){
		dao.completeTask(1, "complete message");
		List<Task> tasks = dao.getTasks("username1",true);
		assertNotNull(tasks);
		assertEquals(1, tasks.size());
		Task test = tasks.get(0);
		assertTrue(test.isComplete());
		assertNotNull(test.getCompleted());
		assertEquals("complete message", test.getComment());
	}
	
	@Test
	@Transactional
	public void testRemoveTask(){
		dao.removeTask(1);
		List<Task> tasks = dao.getTasks("username1",true);
		assertEquals(0, tasks.size());
	}
	
	@Test
	@Transactional
	public void testSendMessage(){
		dao.sendMessage("message4", "username3", "username4");
		SortedSet<Chat> chats = dao.retrieveUserChats("username4");
		assertNotNull(chats);
		assertEquals(1,chats.size());
		assertEquals(1,chats.first().getMessages().size());
	}
	
	@Test
	@Transactional
	public void testSendMessageExistingChat(){
		dao.sendMessage("message5","username3","username2");
		SortedSet<Chat> chats = dao.retrieveUserChats("username3");
		assertNotNull(chats);
		assertEquals(1,chats.size());
		assertEquals(2,chats.first().getMessages().size());
	}

	@Test
	@Transactional
	public void testAddMessage(){
		dao.addMessage("username2", 2, "testMessage");
		SortedSet<Chat> chats = dao.retrieveUserChats("username3");
		assertNotNull(chats);
		assertEquals(1,chats.size());
		assertEquals(2,chats.first().getMessages().size());
		
	}
	
	@Test
	@Transactional
	public void testLoadUserChats(){
		SortedSet<Chat> chats = dao.retrieveUserChats("username1");
		assertEquals(1,chats.size());
		assertEquals(2,chats.first().getMessages().size());
	}
	
	@Test
	@Transactional
	public void testGetComments(){
		
	}
	
	@Test
	@Transactional
	public void testGetChat(){
		Chat c = dao.retrieveChat((long) 1);
		assertNotNull(c);
		assertEquals(new Long(1), c.getId());
		assertEquals(2,c.getMessages().size());
	}

	@Test
	@Transactional
	public void testDeleteMessage(){
		dao.deleteMessage((long) 3);
		SortedSet<Chat> chats = dao.retrieveUserChats("username1");
		assertNotNull(chats);
		assertEquals(1,chats.size());
		assertEquals(1,chats.first().getMessages().size());
	}
	
	@Test
	@Transactional
	public void testRetrieveChat(){
		SortedSet<Chat> chats = dao.retrieveUserChats("username1");
		assertNotNull(chats);
		assertEquals(1,chats.size());
		assertEquals(2,chats.first().getMessages().size());
	}
	
	@Test
	@Transactional
	public void testSendComment(){
		dao.addComment("test message",(long) 4, "username2");
		List<Comment> comments = dao.retrieveArticleComments((long) 4);
		assertNotNull(comments);
		assertEquals(2,comments.size());
		assertEquals("test message", comments.get(0).getMessage());
		
	}
	
	@Test
	@Transactional
	public void testEditComment(){
		dao.editComment("edited", (long) 1000);
		Comment c = dao.retrieveComment((long) 1000);
		assertNotNull(c);
		assertEquals("edited",c.getMessage());
	}
	
	@Test
	@Transactional
	public void testDeleteComment(){
		dao.deleteComment(3);
		List<Comment> comments = dao.retrieveArticleComments((long) 4);
		assertNotNull(comments);
		assertEquals(0,comments.size());
	}
	
	@Test
	@Transactional
	public void testRetrieveArticleComment(){
		List<Comment> comments = dao.retrieveArticleComments((long) 3);
		assertNotNull(comments);
		assertEquals(3,comments.size());
	}

	@Test
	@Transactional
	public void testUpvote(){
		long votes = dao.voteIncrement(2,1);
		assertEquals(1, votes);
	}
	
	@Test
	@Transactional
	public void testDownvote(){
		long votes = dao.voteIncrement(2,-1);
		assertEquals(-1, votes);
	}
	
	@Test
	@Transactional
	public void testCountUnread(){
		int unread = dao.countUnread("username1");
		assertEquals(1, unread);
	}
		
	@Test
	@Transactional
	public void testGetUnread(){
		dao.addMessage("username2", 1, "message");
		List<Message> unread =dao.getUnread("username1");
		assertNotNull(unread);
		assertEquals(2, unread.size());
		//Depends on testCountUnread
		int count =dao.countUnread("username1");
		assertEquals(0,count);
	}
	
	@Override
	protected IDataSet getDataSet() throws MalformedURLException,
			DataSetException {
		return new FlatXmlDataSetBuilder().build(new File(defaultTestResourceFolder.concat("DbTestDataSet.xml")));
	}


	@Autowired
	public void setMessageDao(MessageDao messageDao) {
		this.dao = messageDao;
	}


}
