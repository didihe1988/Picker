package com.didihe1988.picker.model.dp;

import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.interfaces.IsFavorite;
import com.didihe1988.picker.utils.DateUtils;

public class AnswerDp extends Answer implements IsFavorite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String questionName;

	private String replierName;

	private String replierSignature;

	private String replierAvatarUrl;

	private boolean isFavorite;

	// private List<String> imageUrls;

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

	public String getReplierSignature() {
		return replierSignature;
	}

	public void setReplierSignature(String replierSignature) {
		this.replierSignature = replierSignature;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public AnswerDp() {

	}

	public AnswerDp(Answer answer, String questionName, String replierName,
			String replierSignature, String replierAvatarUrl, boolean isFavorite) {
		super(answer);
		this.strDate = DateUtils.getDate(answer.getDate());
		this.questionName = questionName;
		this.replierName = replierName;
		this.replierSignature = replierSignature;
		this.replierAvatarUrl = replierAvatarUrl;
		this.isFavorite = isFavorite;
	}

	public AnswerDp(Answer answer, String questionName, String replierName,
			String replierSignature, String replierAvatarUrl) {
		super(answer);
		this.strDate = DateUtils.getDate(answer.getDate());
		this.questionName = questionName;
		this.replierName = replierName;
		this.replierSignature = replierSignature;
		this.replierAvatarUrl = replierAvatarUrl;
		this.isFavorite = false;
	}

	@Override
	public String toString() {
		return "AnswerDp [questionName=" + questionName + ", replierName="
				+ replierName + ", replierSignature=" + replierSignature
				+ ", replierAvatarUrl=" + replierAvatarUrl + ", isFavorite="
				+ isFavorite + ", strDate=" + strDate + ", id=" + id
				+ ", questionId=" + questionId + ", replierId=" + replierId
				+ ", content=" + content + ", brief=" + brief
				+ ", favoriteNum=" + favoriteNum + ", commentNum=" + commentNum
				+ ", date=" + date + "]";
	}

}
