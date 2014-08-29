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
	protected int id;

	@Column(name = "user_name")
	protected String username;

	@Column(name = "user_email")
	protected String email;

	@Column(name = "user_password")
	private String password;

	@Column(name = "user_lastVisit")
	protected Date lastVisit;

	@Column(name = "user_registertime")
	protected Date registerTime;

	@Column(name = "user_favoritenum")
	protected int favoriteNum;

	/*
	 * 被关注数
	 */
	@Column(name = "user_follownum")
	protected int followNum;

	@Column(name = "user_followothersnum")
	protected int followOthersNum;

	@Column(name = "user_questionnum")
	protected int questionNum;

	@Column(name = "user_answernum")
	protected int answerNum;

	@Column(name = "user_notenum")
	protected int noteNum;

	@Column(name = "user_circlenum")
	protected int circleNum;

	@Column(name = "user_booknum")
	protected int bookNum;

	@Column(name = "user_avatarurl")
	protected String avatarUrl;

	public User() {

	}

	public User(int id, String username, String email, String password,
			Date lastVisit, Date registerTime, int favoriteNum, int followNum,
			int followOthersNum, int questionNum, int answerNum, int noteNum,
			int circleNum, int bookNum, String avatarUrl) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.lastVisit = lastVisit;
		this.registerTime = registerTime;
		this.favoriteNum = favoriteNum;
		this.followNum = followNum;
		this.followOthersNum = followOthersNum;
		this.questionNum = questionNum;
		this.answerNum = answerNum;
		this.noteNum = noteNum;
		this.circleNum = circleNum;
		this.bookNum = bookNum;
		this.avatarUrl = avatarUrl;
	}

	/*
	 * 没有 password
	 */
	public User(int id, String username, String email, Date lastVisit,
			Date registerTime, int favoriteNum, int followNum,
			int followOthersNum, int questionNum, int answerNum, int noteNum,
			int circleNum, int bookNum, String avatarUrl) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.lastVisit = lastVisit;
		this.registerTime = registerTime;
		this.favoriteNum = favoriteNum;
		this.followNum = followNum;
		this.followOthersNum = followOthersNum;
		this.questionNum = questionNum;
		this.answerNum = answerNum;
		this.noteNum = noteNum;
		this.circleNum = circleNum;
		this.bookNum = bookNum;
		this.avatarUrl = avatarUrl;
	}

	public User(String username, String password, String email, Date lastVisit,
			Date registerTime, String avatarUrl) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.lastVisit = lastVisit;
		this.registerTime = registerTime;
		this.avatarUrl = avatarUrl;
	}

	public User(String username, String email, String password, String avatarUrl) {
		this(username, password, email, new Date(), new Date(), avatarUrl);
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

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public int getFollowOthersNum() {
		return followOthersNum;
	}

	public void setFollowOthersNum(int followOthersNum) {
		this.followOthersNum = followOthersNum;
	}

	public int getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}

	public int getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}

	public int getNoteNum() {
		return noteNum;
	}

	public void setNoteNum(int noteNum) {
		this.noteNum = noteNum;
	}

	public int getCircleNum() {
		return circleNum;
	}

	public void setCircleNum(int circleNum) {
		this.circleNum = circleNum;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", lastVisit=" + lastVisit
				+ ", registerTime=" + registerTime + ", favoriteNum="
				+ favoriteNum + ", followNum=" + followNum
				+ ", followOthersNum=" + followOthersNum + ", questionNum="
				+ questionNum + ", answerNum=" + answerNum + ", noteNum="
				+ noteNum + ", circleNum=" + circleNum + ", bookNum=" + bookNum
				+ ", avatarUrl=" + avatarUrl + "]";
	}

}
