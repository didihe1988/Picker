package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int COMMENT_QUESTION = 0;
	public static final int COMMENT_ANSWER = 1;

	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	private int id;

	@Column(name = "comment_commentedid")
	private int commentedId;

	@Column(name = "comment_producerid")
	private int producerId;

	@Column(name = "comment_content")
	private String content;

	@Column(name = "comment_type")
	private int type;

	@Column(name = "comment_favoritenum")
	private int favoriteNum;

	@Column(name = "comment_date")
	private Date date;

	public Comment() {

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommentedId() {
		return commentedId;
	}

	public void setCommentedId(int commentedId) {
		this.commentedId = commentedId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getProducerId() {
		return producerId;
	}

	public void setProducerId(int producerId) {
		this.producerId = producerId;
	}

	public int getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(int favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public Comment(int id, int commentedId, int producerId, String content,
			int type, int favoriteNum, Date date) {
		super();
		this.id = id;
		this.commentedId = commentedId;
		this.producerId = producerId;
		this.content = content;
		this.type = type;
		this.favoriteNum = favoriteNum;
		this.date = date;
	}

	public Comment(int commentedId, int producerId, String content, int type,
			Date date) {
		super();
		this.commentedId = commentedId;
		this.producerId = producerId;
		this.content = content;
		this.type = type;
		this.date = date;
	}

	public Comment(int commentedId, int producerId, String content, int type) {
		this(commentedId, producerId, content, type, new Date());
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentedId=" + commentedId
				+ ", producerId=" + producerId + ", content=" + content
				+ ", type=" + type + ", favoriteNum=" + favoriteNum + ", date="
				+ date + "]";
	}

}
