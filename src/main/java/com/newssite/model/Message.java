package com.newssite.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@NamedNativeQueries({
	@NamedNativeQuery(
			name = "addMessageToChat",
			query = "CALL addMessage(:sender,:chatId,:message)",
			resultClass = Message.class
	),
	@NamedNativeQuery(
			name = "sendMessage",
			query = "CALL sendMessage(:msg,:sender,:receiver)"
    ),
	@NamedNativeQuery(
			name = "unread",
			query = "CALL unread(:username)",
			resultClass = Message.class
	)
})
@Entity(name="messages")
public class Message implements Comparable<Message>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="message_id")
	private long id;

	@Column
	private String message;
	
	@Column(name="posted")
	private Date sent;
	
	@ManyToOne
	@JoinColumn(name="poster_id")
	private User poster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="chat_id")
	private Chat chat;

	public Message(){}

	public Message(User poster,String message,Chat chat) {
		super();
		this.message = message;
		this.poster = poster;
		this.chat = chat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSent() {
		return sent;
	}

	public void setSent(Date sent) {
		this.sent = sent;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	@Override
	public int compareTo(Message other) {
		return this.sent.compareTo(other.getSent());
	}

	@Override
	public String toString() {
		return "Message [message=" + message + ", sent=" + sent + ", poster="
				+ poster + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Message))
			return false;
		Message other = (Message) obj;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
	
	
	
}
