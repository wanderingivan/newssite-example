package com.newssite.model;

import java.sql.Timestamp;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.OrderBy;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

@Entity(name="articles")
@FetchProfile(name="full", fetchOverrides={@FetchProfile.FetchOverride(entity=Article.class,association="comments",mode=FetchMode.JOIN),
										   @FetchProfile.FetchOverride(entity=Article.class,association="author", mode=FetchMode.JOIN),
										   @FetchProfile.FetchOverride(entity=Article.class,association="paragraphs",mode=FetchMode.JOIN)})
public class Article implements Comparable<Article>{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="article_id")
	private int id;
	
	@Column
	private String headline;
	
	@Column
	private String caption;
	
	@Column
	private long hits;

	@Column
	private String category;
	
	@Column
	private String imagePath;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author; 

	@OneToMany(mappedBy="article", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OrderBy(clause="order desc")
	private SortedSet<Paragraph> paragraphs;
	
	@OneToMany(mappedBy="article", fetch=FetchType.LAZY, orphanRemoval=true)
	@OrderBy(clause="posted asc")
	private SortedSet<Comment> comments;
	
	@Column()
	private Timestamp lastEdited;
	
	public Article(){}

	public Article(String headline, String caption, String category,
			String imagePath) {
		super();
		this.headline = headline;
		this.caption = caption;
		this.category = category;
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@RequiredStringValidator(message="Headline cannot be empty",fieldName="headline")
	@StringLengthFieldValidator(minLength = "6",
	        					maxLength = "25",
	    	        		    key="global.field_between",
	    	        		    fieldName="headline")
	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}



	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Timestamp getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(Timestamp lastEdited) {
		this.lastEdited = lastEdited;
	}

	@RequiredStringValidator(key="global.field_required", fieldName="caption")
	@StringLengthFieldValidator(minLength = "6",
	        					maxLength = "300",
	        				    key="global.field_between",
	        				    fieldName="headline")
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public SortedSet<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(SortedSet<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}


	public SortedSet<Comment> getComments() {
		return comments;
	}

	public void setComments(SortedSet<Comment> comments) {
		this.comments = comments;
	}

	@RequiredStringValidator(key="global.field_required",fieldName="category")
	@RegexFieldValidator(regex="(politics|international|sports|entertainment)",fieldName="category",message="global.field_valid_category")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

	
	@Override
	public String toString() {
		return "Article [id=" + id + ", headline=" + headline + ", category="
				+ category + "]";
	}

	@Override
	public int compareTo(Article o) {
		if(this.lastEdited == null || o.lastEdited == null){ return 0; }//Apparently hibernate likes to throw in articles with uninitialized EAGER Fetch properties
		return o.lastEdited.compareTo(this.lastEdited);
		//return this.lastEdited.compareTo(o.lastEdited);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((headline == null) ? 0 : headline.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Article))
			return false;
		Article other = (Article) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void addParagraph(Paragraph paragraph) {
		if(paragraphs == null){
			paragraphs = new TreeSet<Paragraph>();
		}
		getParagraphs().add(paragraph);
	}
	
}