package com.didihe1988.downloader.model;

import java.util.List;

public class DouBook {
	private int innerId;

	private int id;

	private String publisher;

	private String title;

	private String pages;

	private List<String> author;

	private String pubdate;

	private String isbn13;

	private String image;

	public int getInnerId() {
		return innerId;
	}

	public void setInnerId(int innerId) {
		this.innerId = innerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public List<String> getAuthor() {
		return author;
	}

	public void setAuthor(List<String> author) {
		this.author = author;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void resetField()
	{
		image=image.replaceAll("mpic", "lpic");
		pages=pages.replaceAll("ҳ", "");
	}

	public DouBook() {

	}

	public DouBook(int innerId, int id, String publisher, String title,
			String pages, List<String> author, String pubdate, String isbn13,
			String image) {
		super();
		this.innerId = innerId;
		this.id = id;
		this.publisher = publisher;
		this.title = title;
		this.pages = pages;
		this.author = author;
		this.pubdate = pubdate;
		this.isbn13 = isbn13;
		this.image = image;
	}

	public DouBook(int id, String publisher, String title, String pages,
			List<String> author, String pubdate, String isbn13, String image) {
		super();
		this.id = id;
		this.publisher = publisher;
		this.title = title;
		this.pages = pages;
		this.author = author;
		this.pubdate = pubdate;
		this.isbn13 = isbn13;
		this.image = image;
	}

	@Override
	public String toString() {
		return "DouBook [innerId=" + innerId + ", id=" + id + ", publisher="
				+ publisher + ", title=" + title + ", pages=" + pages
				+ ", author=" + author + ", pubdate=" + pubdate + ", isbn13="
				+ isbn13 + ", image=" + image + "]";
	}

}
