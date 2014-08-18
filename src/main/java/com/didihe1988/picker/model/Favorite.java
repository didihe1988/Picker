package com.didihe1988.picker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "favorite")
@IdClass(Favorite.class)
public class Favorite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int FAVORITE_COMMENT = 0;
	public static final int FAVORITE_QUESTION = 1;
	public static final int FAVORITE_ANSWER = 2;

	@Id
	@Column(name = "favorite_objectid")
	private int objectId;

	@Id
	@Column(name = "favorite_userid")
	private int userId;

	@Id
	@Column(name = "favorite_type")
	private int type;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Favorite() {

	}

	@Override
	public String toString() {
		return "Favorite [objectId=" + objectId + ", userId=" + userId
				+ ", type=" + type + "]";
	}

	public Favorite(int objectId, int userId, int type) {
		super();
		this.objectId = objectId;
		this.userId = userId;
		this.type = type;
	}

}
