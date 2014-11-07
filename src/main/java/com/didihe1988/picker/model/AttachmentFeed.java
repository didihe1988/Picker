package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.didihe1988.picker.model.form.AttachmentFeedForm;

@Entity
@Table(name = "afeed")
public class AttachmentFeed implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "afeed_id")
	protected int id;

	@Column(name = "afeed_bookid")
	protected int bookId;

	@Column(name = "afeed_page")
	protected int page;

	@Column(name = "afeed_userid")
	protected int userId;

	@Column(name = "afeed_describe")
	protected String describe;

	@Column(name = "afeed_date")
	protected Date date;

	@OneToMany(fetch = FetchType.EAGER)
	//目测是Hibernate自动在Attachment一端加上外加、有待探究
	@JoinColumn(name = "attachment_afeedid")
	protected List<Attachment> attachments;

	/*
	 * AttachmentList的映射最后再加上
	 */

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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public AttachmentFeed() {

	}

	public AttachmentFeed(int id, int bookId, int page, int userId,
			String describe, Date date) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.page = page;
		this.userId = userId;
		this.describe = describe;
		this.date = date;
	}

	public AttachmentFeed(int bookId, int page, int userId, String describe,
			Date date) {
		super();
		this.bookId = bookId;
		this.page = page;
		this.userId = userId;
		this.describe = describe;
		this.date = date;
	}

	public AttachmentFeed(int id, int bookId, int page, int userId,
			String describe, Date date, List<Attachment> attachments) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.page = page;
		this.userId = userId;
		this.describe = describe;
		this.date = date;
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "AttachmentFeed [id=" + id + ", bookId=" + bookId + ", page="
				+ page + ", userId=" + userId + ", describe=" + describe
				+ ", date=" + date + ", attachments=" + attachments + "]";
	}

	public static AttachmentFeed getAttachmentFeed(AttachmentFeedForm form,
			int userId) {
		return new AttachmentFeed(form.getBookId(), form.getPage(), userId,
				form.getDescribe(), new Date());
	}

}
