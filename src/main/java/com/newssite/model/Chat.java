package com.newssite.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;
import org.hibernate.annotations.OrderBy;


@Entity(name="chats")
@FetchProfiles(value ={@FetchProfile(name="chat", fetchOverrides={@FetchProfile.FetchOverride(entity=Chat.class,association="users",mode=FetchMode.JOIN),
																  @FetchProfile.FetchOverride(entity=Chat.class,association="messages",mode=FetchMode.JOIN)})})
public class Chat implements Comparable<Chat>{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="chat_id")
	private Long id;
	
	@Column
	private Date lastUpdate;
	
	@OneToMany(mappedBy="chat")
	@Fetch(FetchMode.JOIN)
	@OrderBy(clause="sent asc")
	private SortedSet<Message> messages;
	
	@ManyToMany()
	@Fetch(FetchMode.JOIN)
	@JoinTable(name="chats_join_table",joinColumns={@JoinColumn(name="chat_id")},
									   inverseJoinColumns={@JoinColumn(name="user_id")} )
	private Set<User> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public SortedSet<Message> getMessages() {
		return messages;
	}

	public void setMessages(SortedSet<Message> messages) {
		this.messages = messages;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int compareTo(Chat other) {
		return this.id.compareTo(other.getId()); //FIXME This doesn't give the ordering that's expected !!!
	}

	public void addMessage(Message message) {
		if(getMessages()==null){setMessages(new TreeSet<Message>());}
		getMessages().add(message);
	}

	public void addUser(User user) {
		if(getUsers()==null){setUsers(new HashSet<User>());}
		getUsers().add(user);
	}

	public User getOther(String username){
		for(User u : users){
			if(!(u.getUsername().equals(username))){
				return u;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", lastUpdate=" + lastUpdate + ", messages="
				+ messages + ", users=" + users + "]";
	}
	
	
	
	
}
