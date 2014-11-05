package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attachment")
public class Attachment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "attachment_id")
	protected int id;

	@Column(name = "attachment_bookid")
	protected int bookId;

	@Column(name = "attachment_userid")
	protected int userId;

	@Column(name = "attachment_name")
	protected String name;

	@Column(name = "attachment_path")
	protected String path;

	@Column(name = "attachment_date")
	protected Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Attachment() {

	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", bookId=" + bookId + ", userId="
				+ userId + ", name=" + name + ", path=" + path + ", date="
				+ date + "]";
	}

	public Attachment(int id, int bookId, int userId, String name, String path,
			Date date) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userId = userId;
		this.name = name;
		this.path = path;
		this.date = date;
	}

	public Attachment(int bookId, int userId, String name, String path) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.name = name;
		this.path = path;
		this.date = new Date();
	}

	public boolean checkFieldValidation() {
		/*
		 * if ((this.bookId >0) && (this.title != null) &&
		 * (!this.title.equals("")) && (this.content != null) &&
		 * (!this.content.equals("")) && (this.page >= 0)) { return true; }
		 * return false;
		 */
		return true;
	}

	public Attachment(Attachment attachment) {
		this(attachment.getId(), attachment.getBookId(),
				attachment.getUserId(), attachment.getName(), attachment
						.getPath(), attachment.getDate());
	}
}
