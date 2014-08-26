package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "answer_id")
	protected int id;

	@Column(name = "answer_questionid")
	protected int questionId;

	@Column(name = "answer_replierid")
	protected int replierId;

	@Column(name = "answer_content")
	protected String content;

	@Column(name = "answer_favoritenum")
	protected int favoriteNum;

	@Column(name = "answer_commentnum")
	protected int commentNum;

	@Column(name = "answer_date")
	protected Date date;

	public Answer() {

	}

	public Answer(int id, int questionId, int replierId, String content,
			int favoriteNum, int commentNum, Date date) {
		super();
		this.id = id;
		this.questionId = questionId;
		this.replierId = replierId;
		this.content = content;
		this.favoriteNum = favoriteNum;
		this.commentNum = commentNum;
		this.date = date;
	}

	public Answer(int questionId, int replierId, String content, Date date) {
		super();
		this.questionId = questionId;
		this.replierId = replierId;
		this.content = content;
		this.date = date;
	}

	public Answer(int questionId, int replierId, String content) {
		this(questionId, replierId, content, new Date());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
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

	public int getReplierId() {
		return replierId;
	}

	public void setReplierId(int replierId) {
		this.replierId = replierId;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", questionId=" + questionId + ", content="
				+ content + ", favoriteNum=" + favoriteNum + ", date=" + date
				+ "]";
	}

}
