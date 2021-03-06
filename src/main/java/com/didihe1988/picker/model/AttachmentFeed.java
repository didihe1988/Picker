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

import com.didihe1988.picker.model.display.SearchResult;
import com.didihe1988.picker.model.form.AttachmentFeedForm;
import com.didihe1988.picker.model.interfaces.SearchModel;

@Entity
@Table(name = "afeed")
public class AttachmentFeed implements Serializable, SearchModel {
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

	@Column(name = "afeed_title")
	protected String title;

	@Column(name = "afeed_describe")
	protected String describe;

	@Column(name = "afeed_date")
	protected Date date;

	@OneToMany(fetch = FetchType.EAGER)
	// 目测是Hibernate自动在Attachment一端加上外加、有待探究
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public AttachmentFeed() {

	}

	public AttachmentFeed(int bookId, int page, int userId, String title,
			String describe, Date date) {
		super();
		this.bookId = bookId;
		this.page = page;
		this.userId = userId;
		this.title = title;
		this.describe = describe;
		this.date = date;
	}

	public AttachmentFeed(int id, int bookId, int page, int userId,
			String title, String describe, Date date,
			List<Attachment> attachments) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.page = page;
		this.userId = userId;
		this.title = title;
		this.describe = describe;
		this.date = date;
		this.attachments = attachments;
	}

	public AttachmentFeed(AttachmentFeed aFeed) {
		this(aFeed.getId(), aFeed.getBookId(), aFeed.getPage(), aFeed
				.getUserId(), aFeed.getTitle(), aFeed.getDescribe(), aFeed
				.getDate(), aFeed.getAttachments());
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
				form.getTitle(), form.getDescribe(), new Date());
	}

	@Override
	public SearchResult toSearchResult() {
		// TODO Auto-generated method stub
		return new SearchResult(this.id, SearchResult.RESULT_AFEED, this.title,
				this.describe);
	}

}
