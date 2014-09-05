package com.didihe1988.picker.model;

import com.didihe1988.picker.model.dpInterface.IsFavorite;

public class NoteDp extends Note implements IsFavorite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bookName;

	private String userName;

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

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAvatarUrl() {
		return userAvatarUrl;
	}

	public void setUserAvatarUrl(String userAvatarUrl) {
		this.userAvatarUrl = userAvatarUrl;
	}

	public NoteDp() {

	}

	public NoteDp(Note note, String bookName, String userName,
			String userAvatarUrl, boolean isFavorite) {
		super(note.getId(), note.getBookId(), note.getUserId(),
				note.getTitle(), note.getContent(), note.getPublishTime(), note
						.isPublic(), note.getFavoriteNum(), note
						.getCommentNum(), note.getPage());
		this.bookName = bookName;
		this.userName = userName;
		this.userAvatarUrl = userAvatarUrl;
		this.isFavorite = isFavorite;
	}

}
