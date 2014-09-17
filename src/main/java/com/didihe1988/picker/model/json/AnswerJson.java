package com.didihe1988.picker.model.json;

import java.util.Date;

public class AnswerJson extends BaseJson {
	private String brief;

	private String picture;

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public AnswerJson() {

	}

	public AnswerJson(String title, String link, String brief, String picture,
			Date time) {
		super(title, link, time);
		this.brief = brief;
		this.picture = picture;
	}

}
