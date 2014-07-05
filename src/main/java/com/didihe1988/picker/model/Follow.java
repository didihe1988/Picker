package com.didihe1988.picker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "follow")
public class Follow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int FOLLOW_COMMENT = 0;
	public static final int FOLLOW_USER = 1;

	@Id
	@GeneratedValue
	@Column(name = "follow_id")
	private int id;

	@Column(name = "follow_sourcetype")
	private int sourceType;

	@Column(name = "follow_followerid")
	private int followerId;

	@Column(name = "follow_sourceid")
	private int sourceId;

	@Column(name = "follow_ischecked")
	private boolean isChecked;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	public Follow() {

	}

	@Override
	public String toString() {
		return "Follow [id=" + id + ", sourceType=" + sourceType
				+ ", followerId=" + followerId + ", sourceId=" + sourceId
				+ ", isChecked=" + isChecked + "]";
	}

	public Follow(int id, int sourceType, int followerId, int sourceId,
			boolean isChecked) {
		super();
		this.id = id;
		this.sourceType = sourceType;
		this.followerId = followerId;
		this.sourceId = sourceId;
		this.isChecked = isChecked;
	}
	
	public Follow(int sourceType, int followerId, int sourceId,
			boolean isChecked) {
		super();
		this.sourceType = sourceType;
		this.followerId = followerId;
		this.sourceId = sourceId;
		this.isChecked = isChecked;
	}

}
