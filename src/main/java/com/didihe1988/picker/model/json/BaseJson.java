package com.didihe1988.picker.model.json;

import java.util.Date;

public class BaseJson {
	protected String title;

	protected String link;

	protected Date time;

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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public BaseJson() {

	}

	public BaseJson(String title, String link, Date time) {
		super();
		this.title = title;
		this.link = link;
		this.time = time;
	}

}
