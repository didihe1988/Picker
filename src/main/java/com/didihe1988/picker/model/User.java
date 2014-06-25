package com.didihe1988.picker.model;

import java.util.Date;
import java.util.List;

public class User {
	private int id;
	private String username;
	private String password;
	private Date lastVisit;

	public User(int id, String username, String password, Date lastVisit) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastVisit = lastVisit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

}
