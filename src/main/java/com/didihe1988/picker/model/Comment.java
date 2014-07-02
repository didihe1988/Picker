package com.didihe1988.picker.model;

import java.io.Serializable;

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
	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	public int id;

	@Column(name = "comment_bookid")
	private int bookId;

	@Column(name = "comment_receiverid")
	public int receiverId;

	@Column(name = "comment_producerid")
	public int producerId;

	@Column(name = "comment_content")
	private String content;

	@Column(name = "comment_favoritenum")
	private int favoriteNum;

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

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public int getProducerId() {
		return producerId;
	}

	public void setProducerId(int producerId) {
		this.producerId = producerId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Comment(int id, int receiverId, int producerId, String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", bookId=" + bookId + ", receiverId="
				+ receiverId + ", producerId=" + producerId + ", content="
				+ content + ", favoriteNum=" + favoriteNum + "]";
	}

}
