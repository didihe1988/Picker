package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dialog")
public class Dialog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "dialog_id")
	private int id;

	@Column(name = "dialog_user1id")
	private int user1Id;

	@Column(name = "dialog_user2id")
	private int user2Id;

	@Column(name = "dialog_starttime")
	private Date startTime;

	@Column(name = "dialog_latesttime")
	private Date latestTime;

	public int getUser1Id() {
		return user1Id;
	}

	public void setUser1Id(int user1Id) {
		this.user1Id = user1Id;
	}

	public int getUser2Id() {
		return user2Id;
	}

	public void setUser2Id(int user2Id) {
		this.user2Id = user2Id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(Date latestTime) {
		this.latestTime = latestTime;
	}

	@Override
	public String toString() {
		return "Dialog [id=" + id + ", user1Id=" + user1Id + ", user2Id="
				+ user2Id + ", startTime=" + startTime + ", latestTime="
				+ latestTime + "]";
	}

	public Dialog(int id, int user1Id, int user2Id, Date startTime,
			Date latestTime) {
		super();
		this.id = id;
		this.user1Id = user1Id;
		this.user2Id = user2Id;
		this.startTime = startTime;
		this.latestTime = latestTime;
	}

}
