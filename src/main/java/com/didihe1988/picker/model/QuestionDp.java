package com.didihe1988.picker.model;

import java.util.Date;

public class QuestionDp extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bookName;

	private String askerName;

	private String askerAvatarUrl;

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

	public QuestionDp(int id, int bookId, String bookName, int askerId,
			String askerName, String title, String content, int favoriteNum,
			int answerNum, int commentNum, int followNum, Date date) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.bookName = bookName;
		this.askerId = askerId;
		this.askerName = askerName;
		this.title = title;
		this.content = content;
		this.favoriteNum = favoriteNum;
		this.answerNum = answerNum;
		this.commentNum = commentNum;
		this.followNum = followNum;
		this.date = date;
	}

	public QuestionDp(Question question, String bookName, String askerName,
			String askerAvatarUrl) {
		super(question.getId(), question.getBookId(), question.getAskerId(),
				question.getTitle(), question.getContent(), question
						.getFavoriteNum(), question.getAnswerNum(), question
						.getCommentNum(), question.getFollowNum(), question
						.getDate());
		this.bookName = bookName;
		this.askerName = askerName;
		this.askerAvatarUrl = askerAvatarUrl;
	}
}
