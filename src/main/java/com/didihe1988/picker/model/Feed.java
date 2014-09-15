package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feed")
public class Feed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int TYPE_QUESTION = 1;
	public static final int TYPE_NOTE = 2;
	public static final int TYPE_ALL = 0;

	@Id
	@GeneratedValue
	@Column(name = "feed_id")
	protected int id;

	@Column(name = "feed_bookid")
	protected int bookId;

	@Column(name = "feed_userid")
	protected int userId;

	@Column(name = "feed_title")
	protected String title;

	@Column(name = "feed_content")
	protected String content;

	@Column(name = "feed_date")
	protected Date date;

	@Column(name = "feed_page")
	protected int page;

	@Column(name = "feed_type")
	protected int type;

	@Column(name = "feed_ispublic")
	protected boolean isPublic;

	@Column(name = "feed_favoritenum")
	protected int favoriteNum;

	@Column(name = "feed_answernum")
	protected int answerNum;

	@Column(name = "feed_commentnum")
	protected int commentNum;

	@Column(name = "feed_follownum")
	protected int followNum;

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public int getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(int favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public int getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
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

	public Feed() {

	}

	public Feed(int id, int bookId, int userId, String title, String content,
			Date date, int page, int type, boolean isPublic, int favoriteNum,
			int answerNum, int commentNum, int followNum) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.date = date;
		this.page = page;
		this.type = type;
		this.isPublic = isPublic;
		this.favoriteNum = favoriteNum;
		this.answerNum = answerNum;
		this.commentNum = commentNum;
		this.followNum = followNum;
	}

	public Feed(int bookId, int userId, String title, String content,
			Date date, int page, int type, boolean isPublic) {
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.date = date;
		this.page = page;
		this.type = type;
		this.isPublic = isPublic;
	}

	public Feed(int bookId, int userId, String title, String content, int page,
			int type, boolean isPublic) {
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.date = new Date();
		this.page = page;
		this.type = type;
		this.isPublic = isPublic;
	}

	public Feed(int bookId, int userId, String title, String content, int page,
			int type) {
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.date = new Date();
		this.page = page;
		this.type = type;
		this.isPublic = true;
	}

	@Override
	public String toString() {
		return "Feed [id=" + id + ", bookId=" + bookId + ", userId=" + userId
				+ ", title=" + title + ", content=" + content + ", date="
				+ date + ", page=" + page + ", type=" + type + ", isPublic="
				+ isPublic + ", favoriteNum=" + favoriteNum + ", answerNum="
				+ answerNum + ", commentNum=" + commentNum + ", followNum="
				+ followNum + "]";
	}

}
