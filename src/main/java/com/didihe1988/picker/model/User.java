package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Entity
@Table(name = "user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int id;

	@Column(name = "user_name")
	private String username;

	@Column(name = "user_password")
	private String password;

	@Column(name = "user_lastVisit")
	private Date lastVisit;

	@Column(name = "user_favoritenum")
	private int favoriteNum;

	public User() {

	}

	public User(int id, String username, String password, Date lastVisit) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastVisit = lastVisit;
	}

	public User(String username, String password, Date lastVisit) {
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

	@JsonSerialize(using = DateSerializer.class)
	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public int getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(int favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", lastVisit=" + lastVisit + ", favoriteNum="
				+ favoriteNum + "]";
	}

}
