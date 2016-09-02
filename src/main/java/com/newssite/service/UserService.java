package com.newssite.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;

import com.newssite.model.Article;
import com.newssite.model.Comment;
import com.newssite.model.User;

/**
 * Service layer interface
 * Provides methods for CRUD actions on Users 
 * and administrtive methods for locking and
 * changing a users authority role.
 * Defines method security checks
 */
public interface UserService {

	/**
	 * Persists a user into the db
	 * Inserts the user in the group with the lowest authority role.
	 * Must create an acl
	 * @param user the user to persist
	 * @return the persisted user's id
	 */	
	@PreAuthorize("isAnonymous())")
	public void createUser(User user);

	/**
	 * Retrieves an user from the database
	 * @param username
	 * @return
	 */
	public User getUser(String username);
	
	/**
	 * Edits a user
	 * @param user
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasPermission(#user,'WRITE')")
	public void editUser(User user);
	
	/**
	 * Removes users with matching username from the database
	 * @param username
	 * @return
	 */	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteUser(String username);

	/**
	 * Finds users matching 
	 * @param username
	 * @return a map containing matching users usernames as key
	 * and imagePaths as values
	 */	
	public Map<String, String> findUsers(String username);


	public List<Article> loadUserArticles(String username, int min);

	public List<Comment> loadUserComments(String username, int min);
	
    /**
	 * Loads a list of users ordered by creation date
	 * @return
	 */
	public List<User> newestUsers();

	
	/**
	 * Locks a user
	 * @param username
	 */	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void enableUser(String username);

	/**
	 * Sets a user as unlocked
	 * @param username
	 */	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void disableUser(String username);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void changeAuthority(String username, String authority);

	@PreAuthorize("isAuthenticated())")
	public void changePassword(String user, String oldPassword,
			String newPassword);

}
