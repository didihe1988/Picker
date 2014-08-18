package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "talk")
public class Talk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "talk_id")
	private int id;

	@Column(name = "talk_talkerid")
	private int talkerId;

	@Column(name = "talk_talkedid")
	private int talkedId;

	@Column(name = "talk_dialogid")
	private int dialogId;

	@Column(name = "talk_content")
	private String content;

	@Column(name = "talk_time")
	private Date time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTalkerId() {
		return talkerId;
	}

	public void setTalkerId(int talkerId) {
		this.talkerId = talkerId;
	}

	public int getTalkedId() {
		return talkedId;
	}

	public void setTalkedId(int talkedId) {
		this.talkedId = talkedId;
	}

	public int getDialogId() {
		return dialogId;
	}

	public void setDialogId(int dialogId) {
		this.dialogId = dialogId;
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
		return "Talk [id=" + id + ", talkerId=" + talkerId + ", talkedId="
				+ talkedId + ", dialogId=" + dialogId + ", content=" + content
				+ ", time=" + time + "]";
	}

	public Talk(int id, int talkerId, int talkedId, int dialogId,
			String content, Date time) {
		super();
		this.id = id;
		this.talkerId = talkerId;
		this.talkedId = talkedId;
		this.dialogId = dialogId;
		this.content = content;
		this.time = time;
	}

}
