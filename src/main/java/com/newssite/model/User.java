package com.newssite.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;
import org.hibernate.annotations.OrderBy;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;


@Entity(name="users")
@FetchProfiles(value ={@FetchProfile(name="comments_articles", fetchOverrides={@FetchProfile.FetchOverride(entity=User.class,association="articlesWritten", mode= FetchMode.JOIN),
														                       @FetchProfile.FetchOverride(entity=User.class,association="comments", mode= FetchMode.JOIN)}),
					   @FetchProfile(name="chats", fetchOverrides={@FetchProfile.FetchOverride(entity=User.class,association="chats",mode=FetchMode.JOIN)})
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column
	private String email;
	
	@Column
	private String details;
	
	@Column
	private String password;
	
	@Column
	private Date createdOn;
	
	@Column
	private boolean enabled;

	
	@Column
	private String imagePath;
	

	
	@OneToMany(mappedBy="author",fetch=FetchType.LAZY, orphanRemoval=true)
	@OrderBy(clause="lastEdited desc")
	private SortedSet<Article> articlesWritten;

	@OneToMany(mappedBy="poster",fetch=FetchType.LAZY, orphanRemoval=true)
	@OrderBy(clause="posted asc")
	private SortedSet<Comment> comments;
	
	@ManyToMany
	@JoinTable(name="chats_join_table",joinColumns={@JoinColumn(name="user_id")},
									   inverseJoinColumns={@JoinColumn(name="chat_id")})
	@OrderBy(clause="id asc")
	private SortedSet<Chat> chats;
	
	public User(){
		
	}

	public User(String username, String email, String details, String password,
			String imagePath) {
		super();
		this.username = username;
		this.email = email;
		this.details = details;
		this.password = password;
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@RequiredStringValidator(key="global.field_required",fieldName="username")
	@StringLengthFieldValidator(minLength = "4",
	        					maxLength = "25",
	        					key="global.field_between")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@RequiredStringValidator(key="global.field_required",fieldName="email")
	@EmailValidator(key="global.field_valid_email",fieldName="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled(){
		return enabled;
	}
	
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<Article> getArticlesWritten() {
		return new ArrayList<Article>(articlesWritten);
	}

	@StringLengthFieldValidator(maxLength="250",
			                    key="global.field_maxlength",
								fieldName="description")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setArticlesWritten(SortedSet<Article> articlesWritten) {
		this.articlesWritten = articlesWritten;
	}

	public List<Comment> getComments() {
		return new ArrayList<>(comments);
	}

	public void setComments(SortedSet<Comment> comments) {
		this.comments = comments;
	}
	
	public SortedSet<Chat> getChats() {
		return chats;
	}

	public void setChats(SortedSet<Chat> chats) {
		this.chats = chats;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email
				+ ", password='...', enabled=" + enabled + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
