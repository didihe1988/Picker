package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.form.AnswerForm;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.MarkdownUtils;
import com.didihe1988.picker.utils.StringUtils;

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

	@Column(name = "answer_brief")
	protected String brief;

	@Column(name = "answer_favoritenum")
	protected int favoriteNum;

	@Column(name = "answer_commentnum")
	protected int commentNum;

	@Column(name = "answer_date")
	protected Date date;

	public Answer() {

	}

	public Answer(int id, int questionId, int replierId, String content,
			String brief, int favoriteNum, int commentNum, Date date) {
		super();
		this.id = id;
		this.questionId = questionId;
		this.replierId = replierId;
		this.content = content;
		this.brief = brief;
		this.favoriteNum = favoriteNum;
		this.commentNum = commentNum;
		this.date = date;
	}

	public Answer(int questionId, int replierId, String content, String brief,
			Date date) {
		this.questionId = questionId;
		this.replierId = replierId;
		this.content = content;
		this.brief = brief;
		this.date = date;
	}

	public Answer(int questionId, int replierId, String content, String brief) {
		this(questionId, replierId, content, brief, new Date());
	}

	public Answer(Answer answer) {
		this(answer.getId(), answer.getQuestionId(), answer.getReplierId(),
				answer.getContent(), answer.getBrief(),
				answer.getFavoriteNum(), answer.getCommentNum(), answer
						.getDate());
	}

	public Answer(AnswerForm answerForm, int userId) {
		this(answerForm.getQuestionId(), userId, answerForm.getRaw(),
				StringUtils.toBrief(answerForm.getRaw()), new Date());
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

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public void setBriefByContent() {
		this.brief = StringUtils.toBrief(this.content);
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", questionId=" + questionId
				+ ", replierId=" + replierId + ", content=" + content
				+ ", brief=" + brief + ", favoriteNum=" + favoriteNum
				+ ", commentNum=" + commentNum + ", date=" + date + "]";
	}

	public boolean checkFieldValidation() {
		if ((this.questionId > 0) && (this.content != null)
				&& (!this.content.equals(""))) {
			return true;
		}
		return false;
	}

}
