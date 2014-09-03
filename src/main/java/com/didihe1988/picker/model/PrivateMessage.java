package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm")
public class PrivateMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "pm_id")
	private int id;

	@Column(name = "pm_senderid")
	private int senderId;

	@Column(name = "pm_receiverid")
	private int receiverId;

	@Column(name = "pm_content")
	private String content;

	@Column(name = "pm_time")
	private Date time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "PrivateMessage [id=" + id + ", senderId=" + senderId
				+ ", receiverId=" + receiverId + ", content=" + content
				+ ", time=" + time + "]";
	}

	public PrivateMessage(int id, int senderId, int receiverId, String content,
			Date time) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.content = content;
		this.time = time;
	}

	public PrivateMessage(int senderId, int receiverId, String content,
			Date time) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.content = content;
		this.time = time;
	}

	public PrivateMessage(int senderId, int receiverId, String content) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.content = content;
		this.time = new Date();
	}
}
