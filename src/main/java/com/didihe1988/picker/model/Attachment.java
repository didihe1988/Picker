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

	@Column(name = "attachment_afeedid")
	protected int aFeedId;

	@Column(name = "attachment_name")
	protected String name;

	@Column(name = "attachment_path")
	protected String path;

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

	public int getaFeedId() {
		return aFeedId;
	}

	public void setaFeedId(int aFeedId) {
		this.aFeedId = aFeedId;
	}

	public Attachment() {

	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", aFeedId=" + aFeedId + ", name="
				+ name + ", path=" + path + "]";
	}

	public Attachment(int id, int aFeedId, String name, String path) {
		super();
		this.id = id;
		this.aFeedId = aFeedId;
		this.name = name;
		this.path = path;
	}

	public Attachment(String name) {
		super();
		this.name = name;
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

	/*
	 * public Attachment(Attachment attachment) { this(attachment.getId(),
	 * attachment.getBookId(), attachment.getUserId(), attachment.getName(),
	 * attachment .getPath(), attachment.getDate()); }
	 */
}
