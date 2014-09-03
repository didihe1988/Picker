package com.didihe1988.picker.model;

import com.didihe1988.picker.model.dpInterface.IsFavorite;

public class AnswerDp extends Answer implements IsFavorite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String questionName;

	private String replierName;

	private String replierAvatarUrl;

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

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getReplierName() {
		return replierName;
	}

	public void setReplierName(String replierName) {
		this.replierName = replierName;
	}

	public String getReplierAvatarUrl() {
		return replierAvatarUrl;
	}

	public void setReplierAvatarUrl(String replierAvatarUrl) {
		this.replierAvatarUrl = replierAvatarUrl;
	}

	public AnswerDp() {

	}

	public AnswerDp(Answer answer, String questionName, String replierName,
			String replierAvatarUrl) {
		super(answer.getId(), answer.getQuestionId(), answer.getReplierId(),
				answer.getContent(), answer.getFavoriteNum(), answer
						.getCommentNum(), answer.getDate());
		this.questionName = questionName;
		this.replierName = replierName;
		this.replierAvatarUrl = replierAvatarUrl;
	}

}
