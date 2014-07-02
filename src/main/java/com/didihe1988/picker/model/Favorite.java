package com.didihe1988.picker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "favorite")
@IdClass(Favorite.class)
public class Favorite {

	@Id
	@Column(name = "favorite_commentid")
	private int commentId;

	@Id
	@Column(name = "favorite_userid")
	private int userId;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Favorite [commentId=" + commentId + ", userId=" + userId + "]";
	}

	public Favorite() {

	}

	public Favorite(int commentId, int userId) {
		super();
		this.commentId = commentId;
		this.userId = userId;
	}

}
