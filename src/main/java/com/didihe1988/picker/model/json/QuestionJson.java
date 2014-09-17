package com.didihe1988.picker.model.json;

import java.util.Date;

public class QuestionJson extends BaseJson {

	private int comment_cnt;

	private int answers_cnt;

	public int getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	public int getAnswers_cnt() {
		return answers_cnt;
	}

	public void setAnswers_cnt(int answers_cnt) {
		this.answers_cnt = answers_cnt;
	}

	public QuestionJson() {

	}

	public QuestionJson(String title, String link, Date time, int comment_cnt,
			int answers_cnt) {
		super(title, link, time);
		this.comment_cnt = comment_cnt;
		this.answers_cnt = answers_cnt;
	}

}
