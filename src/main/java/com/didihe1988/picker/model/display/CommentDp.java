package com.didihe1988.picker.model.display;

import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.interfaces.IsFavorite;
import com.didihe1988.picker.utils.DateUtils;

public class CommentDp extends Comment implements IsFavorite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String producerName;
	/*
	 * 目测好像不需要
	 */
	// private String commentedName;

	private String userAvatarUrl;

	private boolean isFavorite;

	private String strDate;

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

	public String getUserAvatarUrl() {
		return userAvatarUrl;
	}

	public void setUserAvatarUrl(String userAvatarUrl) {
		this.userAvatarUrl = userAvatarUrl;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public CommentDp() {

	}
	
	public CommentDp(Comment comment, String producerName,
			String userAvatarUrl, boolean isFavorite) {
		super(comment);
		this.producerName = producerName;
		this.userAvatarUrl = userAvatarUrl;
		this.isFavorite = isFavorite;
		this.strDate = DateUtils.toDate(comment.getDate());
	}

	public CommentDp(Comment comment, String producerName, String userAvatarUrl) {
		super(comment);
		this.strDate = DateUtils.toDate(comment.getDate());
		this.producerName = producerName;
		this.userAvatarUrl = userAvatarUrl;
		this.isFavorite = false;
	}

}
