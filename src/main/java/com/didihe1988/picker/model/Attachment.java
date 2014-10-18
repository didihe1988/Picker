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
	private int id;

	@Column(name = "attachment_circleid")
	private int circleId;

	/*
	 * 提交人的id
	 */
	@Column(name = "attachment_userid")
	private int userId;

	@Column(name = "attachment_name")
	private String name;

	@Column(name = "attachment_path")
	private String path;

	@Column(name = "attachment_date")
	private Date date;

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

	public int getCircleId() {
		return circleId;
	}

	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Attachment() {

	}

	public Attachment(int id, int circleId, int userId, String name,
			String path, Date date) {
		super();
		this.id = id;
		this.circleId = circleId;
		this.userId = userId;
		this.name = name;
		this.path = path;
		this.date = date;
	}

	public Attachment(int circleId, int userId, String name, String path,
			Date date) {
		super();
		this.circleId = circleId;
		this.userId = userId;
		this.name = name;
		this.path = path;
		this.date = date;
	}

}
