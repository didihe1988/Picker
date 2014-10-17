package com.didihe1988.picker.model.json;

import java.util.Date;

public class BaseJson {

	protected String title;

	protected String picture;

	protected String link;

	protected String time;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public BaseJson() {

	}

	public BaseJson(String title, String picture, String link, String time) {
		this.title = title;
		this.picture = picture;
		this.link = link;
		this.time = time;
	}

}
