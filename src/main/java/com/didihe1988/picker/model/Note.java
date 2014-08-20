package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "note")
public class Note implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "note_id")
	private int id;

	@Column(name = "note_bookid")
	private int bookId;

	@Column(name = "note_userid")
	private int userId;

	@Column(name = "note_title")
	private String title;

	@Column(name = "note_content")
	private String content;

	@Column(name = "note_publishtime")
	private Date publishTime;

	@Column(name = "note_ispublic")
	private boolean isPublic;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	

	@Override
	public String toString() {
		return "Note [id=" + id + ", bookId=" + bookId + ", userId=" + userId
				+ ", title=" + title + ", content=" + content
				+ ", publishTime=" + publishTime + ", isPublic=" + isPublic
				+ "]";
	}

	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Note(int id, int bookId, int userId, String title, String content,
			Date publishTime, boolean isPublic) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.publishTime = publishTime;
		this.isPublic = isPublic;
	}

	public Note(int bookId, int userId, String title, String content,
			Date publishTime, boolean isPublic) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.publishTime = publishTime;
		this.isPublic = isPublic;
	}

	public Note(int bookId, int userId, String title, String content,
			boolean isPublic) {
		this(bookId, userId, title, content, new Date(), isPublic);
	}

	public Note(int bookId, int userId, String title, String content) {
		this(bookId, userId, title, content, true);
	}

}
