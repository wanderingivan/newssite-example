package com.newssite.model;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="paragraphs")
public class Paragraph  implements Comparable<Paragraph>{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="paragraph_id")
	private int id;
	
	@Column
	private String text;
	
	@Column(name="article_order")
	private int order;
	
	@ManyToOne
	@JoinColumn(name="article_id")
	private Article article;
	
	public Paragraph(){
		super();
	}

	public Paragraph(Article article,String text, int order) {
		this();
		this.text = text;
		this.order = order;
		this.article = article;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public int compareTo(Paragraph other) {
		if(this.order < other.order){
			return -1;
		}else if(this.order > other.order){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public String toString() {
		return "Paragraph [text=" + text + "]";
	}
	
}
