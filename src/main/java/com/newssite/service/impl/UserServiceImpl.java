package com.newssite.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import org.springframework.transaction.annotation.Transactional;

import com.newssite.dao.UserDao;
import com.newssite.model.Article;
import com.newssite.model.Comment;
import com.newssite.model.User;
import com.newssite.service.UserService;

/**
 * Implementation of UserService that delegates
 * to a dao
 * transaction management to Spring <br/>
 * and creates ACLs for new users.
 * @see UserService
 * 
 */

@Service
public class UserServiceImpl implements UserService {

	private UserDao dao;
	private MutableAclService aclService;
	private String defaultProfileImagePath;
	private PasswordEncoder encoder;
	
	@Autowired
	public UserServiceImpl(UserDao dao,MutableAclService aclService,String defaultImagePath,PasswordEncoder encoder) {
		super();
		this.dao = dao;
		this.aclService = aclService;
		this.defaultProfileImagePath = defaultImagePath;
		this.encoder = encoder;
	}

	@Override
	@Transactional
	public void createUser(User user) {		
		
		if(user.getImagePath() == null){
			user.setImagePath(defaultProfileImagePath);
		}
		System.out.println("Encoder is null " + (encoder == null));
		user.setPassword(encoder.encode(user.getPassword()));
		long id = dao.createUser(user);
		createAcl(id,new PrincipalSid(user.getUsername()));
	}
	
	@Override
	@Transactional
	public User getUser(String username) {
		return dao.retrieveUser(username);
	}

	@Override
	@Transactional
    @CacheEvict(value="latest_comments", allEntries=true)	
	public void editUser(User user) {
		dao.editUser(user);
	}
	
	@Override
	@Transactional
    @CacheEvict(value="latest_comments", allEntries=true)	
	public void deleteUser(String username) {
		dao.deleteUser(username);
	}

	@Override
	@Transactional
	public Map<String, String> findUsers(String username) {
		return dao.findUsers(username);
	}

	@Override
	@Transactional
	public List<Article> loadUserArticles(String username, int min) {
		return dao.loadUserArticles(username, min);
	}

	@Override
	@Transactional
	public List<Comment> loadUserComments(String username, int min) {
		return dao.loadUserComments(username, min);
	}

	@Override
	@Transactional
	public List<User> newestUsers() {
		return dao.newestUsers();
	}

	@Override
	@Transactional
	public void enableUser(String username) {
		dao.enableUser(username);
	}

	@Override
	@Transactional
	public void disableUser(String username) {
		dao.disableUser(username);
	}

	@Override
	@Transactional
	public void changeAuthority(String username, String authority) {
		dao.changeAuthority(username,authority);
	}

	private void createAcl(long id,Sid sid) {
		MutableAcl acl = aclService.createAcl(new ObjectIdentityImpl(User.class, id));
		acl.setOwner(sid);
		acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, sid, true);
		aclService.updateAcl(acl);
	}

	@Override
	@Transactional
	public void changePassword(String user, String oldPassword,
			String newPassword) {
		oldPassword = encoder.encode(oldPassword);
		newPassword = encoder.encode(newPassword); 
		dao.changePassword(user,oldPassword,newPassword);
	}
	
}
