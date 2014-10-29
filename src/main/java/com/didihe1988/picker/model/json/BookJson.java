package com.didihe1988.picker.model.json;

import org.omg.CORBA.PRIVATE_MEMBER;

public class BookJson {
	private String cover_image_path;

	private String author;

	private String publisher;

	private String date;

	private String brief;

	public String getCover_image_path() {
		return cover_image_path;
	}

	public void setCover_image_path(String cover_image_path) {
		this.cover_image_path = cover_image_path;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public BookJson() {

	}

	public BookJson(String cover_image_path, String author, String publisher,
			String date, String brief) {
		super();
		this.cover_image_path = cover_image_path;
		this.author = author;
		this.publisher = publisher;
		this.date = date;
		this.brief = brief;
	}

}
