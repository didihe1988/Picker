package com.didihe1988.picker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "question_id")
	private int id;

	@Column(name = "question_bookid")
	private int bookId;

	@Column(name = "question_askerid")
	private int askerId;

	@Column(name = "question_content")
	private String content;

	@Column(name = "question_favoritenum")
	private int favoriteNum;

	public Question() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getAskerId() {
		return askerId;
	}

	public void setAskerId(int askerId) {
		this.askerId = askerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(int favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public Question(int bookId, int askerId, String content, int favoriteNum) {
		super();
		this.bookId = bookId;
		this.askerId = askerId;
		this.content = content;
		this.favoriteNum = favoriteNum;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", bookId=" + bookId + ", askerId="
				+ askerId + ", content=" + content + ", favoriteNum="
				+ favoriteNum + "]";
	}

}
