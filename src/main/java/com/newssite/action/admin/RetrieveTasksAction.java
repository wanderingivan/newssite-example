package com.newssite.action.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.interceptor.AuthenticatedUserAware;
import com.newssite.model.Task;
import com.newssite.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage("admin")
@InterceptorRefs(value={@InterceptorRef(value="authAwareStack")})
public class RetrieveTasksAction extends ActionSupport implements AuthenticatedUserAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2729760218923879973L;
	private static final Logger logger = Logger.getLogger(RetrieveTasksAction.class);
	
	private MessageService service;
	
	private List<Task> tasks;
	private String username;

	@Action(value="userTasks", results={@Result(name="success",location="/WEB-INF/content/jsp/admin/tasks.jsp")})
	public String userTasks(){
		try{
			tasks = service.retrieveUserTasks(username);
			return SUCCESS;
		}catch(Exception e){
			logger.error("Exception caught loading tasks \n" + e);
		}
		return ERROR;
	}
	
	@Action(value="pendingTasks", results={@Result(name="success",location="json")})
	public String pendingTasks(){
		try{
			tasks = service.retrieveUserPendingTasks(username);
			return SUCCESS;
		}catch(Exception e){
			logger.error("Exception caught loading tasks \n" + e);
		}
		return ERROR;
	}
		
	@Action(value="latestTasks", results={@Result(name="success",location="/WEB-INF/content/jsp/admin/tasks.jsp")})
	public String latestTasks(){
		try{
			tasks = service.latestTasks();
			return SUCCESS;
		}catch(Exception e){
			logger.error("Exception caught loading tasks \n" + e);
		}
		return ERROR;
	}
	
	@Action(value="retrieveTasks", results={@Result(name="success",location="/WEB-INF/content/jsp/admin/tasks.jsp")})
	public String retrieveTasks(){
		try{
			tasks = service.retrieveAllTasks();
			return SUCCESS;
		}catch(Exception e){
			logger.error("Exception caught loading tasks \n" + e);
		}
		return ERROR;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Autowired
	public void setService(MessageService service) {
		this.service = service;
	}

	@Override
	public void setUser(String username) {
		this.username = username;
	}
}
