package com.didihe1988.picker.model;

import com.didihe1988.picker.model.dpInterface.IsFavorite;
import com.didihe1988.picker.model.dpInterface.IsFollow;

public class QuestionDp extends Question implements IsFollow, IsFavorite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bookName;

	private String askerName;

	private String askerAvatarUrl;

	private boolean isFollow;

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

	@Override
	public boolean isFollow() {
		// TODO Auto-generated method stub
		return this.isFollow;
	}

	@Override
	public void setFollow(boolean value) {
		// TODO Auto-generated method stub
		this.isFollow = value;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAskerName() {
		return askerName;
	}

	public void setAskerName(String askerName) {
		this.askerName = askerName;
	}

	public String getAskerAvatarUrl() {
		return askerAvatarUrl;
	}

	public void setAskerAvatarUrl(String askerAvatarUrl) {
		this.askerAvatarUrl = askerAvatarUrl;
	}

	public QuestionDp() {
	}

	public QuestionDp(Question question, String bookName, String askerName,
			String askerAvatarUrl, boolean isFollow, boolean isFavorite) {
		super(question.getId(), question.getBookId(), question.getAskerId(),
				question.getTitle(), question.getContent(), question
						.getFavoriteNum(), question.getAnswerNum(), question
						.getCommentNum(), question.getFollowNum(), question
						.getDate(), question.getPage());
		this.bookName = bookName;
		this.askerName = askerName;
		this.askerAvatarUrl = askerAvatarUrl;
		this.isFollow = isFollow;
		this.isFavorite = isFavorite;
	}
}
