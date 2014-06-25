package com.didihe1988.picker.model;

public class Book {
	private int id;
	private String bookName;
	private String isbn;
	private String press;

	public Book(int id, String bookName, String isbn, String press) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.isbn = isbn;
		this.press = press;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

}
