package com.didihe1988.picker.model.dp;

import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.interfaces.IsFavorite;

public class CommentDp extends Comment implements IsFavorite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String producerName;

	private String commentedName;

	private String userAvatarUrl;

	private boolean isFavorite;

	@Override
	public boolean isFavorite() {
		// TODO Auto-generated method stub
		return this.isFavorite;
	}

	@Override
	public void setFavorite(boolean value) {
		// TODO Auto-generated method stub
		this.isFavorite = value;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getCommentedName() {
		return commentedName;
	}

	public void setCommentedName(String commentedName) {
		this.commentedName = commentedName;
	}

	public String getUserAvatarUrl() {
		return userAvatarUrl;
	}

	public void setUserAvatarUrl(String userAvatarUrl) {
		this.userAvatarUrl = userAvatarUrl;
	}

	public CommentDp() {

	}

	public CommentDp(Comment comment, String producerName,
			String commentedName, String userAvatarUrl, boolean isFavorite) {
		super(comment);
		this.producerName = producerName;
		this.commentedName = commentedName;
		this.userAvatarUrl = userAvatarUrl;
		this.isFavorite = isFavorite;
	}

}
