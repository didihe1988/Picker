package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

public class Attachment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private int circieId;

	/*
	 * 提交人的id
	 */
	private int userId;

	private String name;

	private String path;

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

	public int getCircieId() {
		return circieId;
	}

	public void setCircieId(int circieId) {
		this.circieId = circieId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Attachment() {

	}

	public Attachment(int id, int circieId, int userId, String name,
			String path, Date date) {
		super();
		this.id = id;
		this.circieId = circieId;
		this.userId = userId;
		this.name = name;
		this.path = path;
		this.date = date;
	}

	public Attachment(int circieId, int userId, String name, String path,
			Date date) {
		super();
		this.circieId = circieId;
		this.userId = userId;
		this.name = name;
		this.path = path;
		this.date = date;
	}

}
