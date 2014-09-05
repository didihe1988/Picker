package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

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
	protected int id;

	@Column(name = "question_bookid")
	protected int bookId;

	@Column(name = "question_askerid")
	protected int askerId;

	@Column(name = "question_title")
	protected String title;

	@Column(name = "question_content")
	protected String content;

	@Column(name = "question_favoritenum")
	protected int favoriteNum;

	@Column(name = "question_answernum")
	protected int answerNum;

	@Column(name = "question_commentnum")
	protected int commentNum;

	@Column(name = "question_follownum")
	protected int followNum;

	@Column(name = "question_date")
	protected Date date;

	@Column(name = "question_page")
	protected int page;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Question(int id, int bookId, int askerId, String title,
			String content, int favoriteNum, int answerNum, int commentNum,
			int followNum, Date date, int page) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.askerId = askerId;
		this.title = title;
		this.content = content;
		this.favoriteNum = favoriteNum;
		this.answerNum = answerNum;
		this.commentNum = commentNum;
		this.followNum = followNum;
		this.date = date;
		this.page = page;
	}

	public Question(int bookId, int askerId, String title, String content,
			Date date, int page) {
		super();
		this.bookId = bookId;
		this.askerId = askerId;
		this.title = title;
		this.content = content;
		this.date = date;
		this.page = page;
	}

	public Question(int bookId, int askerId, String title, String content,
			int page) {
		this(bookId, askerId, title, content, new Date(), page);
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", bookId=" + bookId + ", askerId="
				+ askerId + ", title=" + title + ", content=" + content
				+ ", favoriteNum=" + favoriteNum + ", answerNum=" + answerNum
				+ ", commentNum=" + commentNum + ", followNum=" + followNum
				+ ", date=" + date + ", page=" + page + "]";
	}

}
