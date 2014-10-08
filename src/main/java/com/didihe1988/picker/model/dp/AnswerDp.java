package com.didihe1988.picker.model.dp;

import java.util.List;

import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.interfaces.IsFavorite;

public class AnswerDp extends Answer implements IsFavorite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String questionName;

	private String replierName;

	private String replierAvatarUrl;

	private boolean isFavorite;

	private List<String> imageUrls;

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

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public AnswerDp() {

	}

	public AnswerDp(Answer answer, String questionName, String replierName,
			String replierAvatarUrl, boolean isFavorite, List<String> imageUrls) {
		super(answer);
		this.questionName = questionName;
		this.replierName = replierName;
		this.replierAvatarUrl = replierAvatarUrl;
		this.isFavorite = isFavorite;
		this.imageUrls = imageUrls;
	}

}
