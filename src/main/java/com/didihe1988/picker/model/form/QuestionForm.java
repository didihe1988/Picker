package com.didihe1988.picker.model.form;

import java.util.Date;
import java.util.List;

import com.didihe1988.picker.model.Question;

public class QuestionForm extends Question {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private Integer[] imageIds;
	private String imageIds;

	/*
	 * public Integer[] getImageIds() { return imageIds; }
	 * 
	 * public void setImageIds(Integer[] imageIds) { this.imageIds = imageIds; }
	 * 
	 * public QuestionForm(int id, int bookId, int askerId, String title, String
	 * content, int favoriteNum, int answerNum, int commentNum, int followNum,
	 * Date date, int page, Integer[] imageIds) { super(id, bookId, askerId,
	 * title, content, favoriteNum, answerNum, commentNum, followNum, date,
	 * page); this.imageIds = imageIds; }
	 */

	public QuestionForm() {

	}

	public String getImageIds() {
		return imageIds;
	}

	public void setImageIds(String imageIds) {
		this.imageIds = imageIds;
	}

	public Question getQuestion() {
		return new Question(getBookId(), getAskerId(), getTitle(),
				getContent(), new Date(), getPage());
	}
}
