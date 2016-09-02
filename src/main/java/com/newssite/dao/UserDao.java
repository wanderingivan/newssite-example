package com.newssite.dao;

import java.util.List;
import java.util.Map;

import com.newssite.model.Article;
import com.newssite.model.Comment;
import com.newssite.model.User;

public interface UserDao {

	 /**
	  * Persists a user into the db
	  * Inserts the user in the group with the lowest authority role.
	  * @param user the user to persist
	  * @return the persisted user's id
	  */
	 long createUser(User user);
	
	 /**
	  * Retrieves an user form the database
	  * @param username
	  * @return
	  */
	 User retrieveUser(String username);

	 void editUser(User user);
	
	 void deleteUser(String username);

	 /**
	  * Finds users matching 
	  * @param username
	  * @return a map containing matching users usernames as key
	  * and imagePaths as values
	  */
	 Map<String, String> findUsers(String username);

	 /**
	  * Loads a user's comments from db
	  * @param username
	  * @param min 
	  * @return
	  */
	 List<Comment> loadUserComments(String username, int min);
	 
	 /**
	  * Loads a user's articles from db
	  * @param username
	  * @param min 
	  * @return
	  */
	 List<Article> loadUserArticles(String username, int min);

	 /**
	  * Loads a list of users ordered by creation date
	  * @return
	  */
	 List<User> newestUsers();

	 /**
	  * Sets a user as unlocked
	  * @param username
	  */
	 void enableUser(String username);
	
	 /**
	  * Locks a user
	  * @param username
	  */
	 void disableUser(String username);

	 void changeAuthority(String username, String authority);

	 void changePassword(String principal, String oldPassword, String newPassword);

}
