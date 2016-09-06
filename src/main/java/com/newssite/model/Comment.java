package com.newssite.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@NamedNativeQueries({
	@NamedNativeQuery(name = "addComment",
					  query = "CALL addComment(:message,:articleId,:username)")
})
@Entity(name="comments")
public class Comment  implements Comparable<Comment>{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id")
	private long id;
	
	@ManyToOne()
	@JoinColumn(name="poster_id")
	private User poster;
	
	@Column
	private Date posted;
	
	@Column
	private String message;

	@Column
	private long votes;
	
	@ManyToOne
	@JoinColumn(name="article_id")
	private Article article;

	public Comment(){
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

	public Date getPosted() {
		return posted;
	}

	public void setPosted(Date posted) {
		this.posted = posted;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	public long getVotes() {
		return votes;
	}

	public void setVotes(long votes) {
		this.votes = votes;
	}

	public void vote(long vote){
		this.votes += vote;
	}
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", poster=" + poster + ", posted="
				+ posted + ", message=" + message + "]";
	}

	@Override
	public int compareTo(Comment other) {
		return other.posted.compareTo(this.posted);
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
		if (!(obj instanceof Comment))
			return false;
		Comment other = (Comment) obj;
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
