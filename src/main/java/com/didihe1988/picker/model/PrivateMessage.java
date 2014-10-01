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
	protected long id;

	@Column(name = "pm_senderid")
	protected int senderId;

	@Column(name = "pm_receiverid")
	protected int receiverId;

	@Column(name = "pm_dialogid")
	protected long dialogId;

	@Column(name = "pm_content")
	protected String content;

	@Column(name = "pm_time")
	protected Date time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public long getDialogId() {
		return dialogId;
	}

	public void setDialogId(long dialogId) {
		this.dialogId = dialogId;
	}

	@Override
	public String toString() {
		return "PrivateMessage [id=" + id + ", senderId=" + senderId
				+ ", receiverId=" + receiverId + ", dialogId=" + dialogId
				+ ", content=" + content + ", time=" + time + "]";
	}

	public PrivateMessage() {

	}

	public PrivateMessage(long id, int senderId, int receiverId, long dialogId,
			String content, Date time) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.dialogId = dialogId;
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

	public PrivateMessage(PrivateMessage privateMessage) {
		this(privateMessage.getId(), privateMessage.getSenderId(),
				privateMessage.getReceiverId(), privateMessage.getDialogId(),
				privateMessage.getContent(), privateMessage.getTime());
	}

	public boolean checkFieldValidation() {
		if ((this.receiverId > 0) && (this.content != null)
				&& (!this.content.equals(""))) {
			return true;
		} else {
			return false;
		}
	}
}
