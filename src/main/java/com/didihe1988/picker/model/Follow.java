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
	public static final int FOLLOW_QUESTION = 0;
	public static final int FOLLOW_USER = 1;
	
	/*
	 * public static final int FOLLOW_COMMENT = 0; public static final int
	 * FOLLOW_USER_FAVORITE = 1; public static final int FOLLOW_USER_COMMENT =
	 * 2; public static final int FOLLOW_USER_FOLLOWED_COMMENT = 3;
	 */

	@Id
	@GeneratedValue
	@Column(name = "follow_id")
	private int id;

	@Column(name = "follow_sourcetype")
	private int sourceType;

	// 关注者id 关注别人的人 也就是session里的userId
	@Column(name = "follow_followerid")
	private int followerId;
	
	//关注的人或是关注的问题
	@Column(name = "follow_sourceid")
	private int sourceId;

	/*
	 * @Column(name = "follow_ischecked") private boolean isChecked;
	 */

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
				+ ", followerId=" + followerId + ", sourceId=" + sourceId + "]";
	}

	public Follow(int sourceType, int followerId, int sourceId) {
		super();
		this.sourceType = sourceType;
		this.followerId = followerId;
		this.sourceId = sourceId;
	}

}
