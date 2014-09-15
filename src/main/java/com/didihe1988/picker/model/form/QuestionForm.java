package com.didihe1988.picker.model.form;

import java.util.Date;
import java.util.List;

import com.didihe1988.picker.model.Question;

public class QuestionForm extends Question {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String imageUrls;

	public QuestionForm() {

	}

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public Question getQuestion() {
		return new Question(getBookId(), getAskerId(), getTitle(),
				getContent(), new Date(), getPage());
	}
}
