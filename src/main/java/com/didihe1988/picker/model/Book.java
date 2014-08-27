package com.didihe1988.picker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "book_id")
	private int id;

	@Column(name = "book_name")
	private String bookName;

	@Column(name = "book_isbn")
	private String isbn;

	@Column(name = "book_writer")
	private String writer;

	@Column(name = "book_press")
	private String press;

	@Column(name = "book_follownum")
	private int followNum;

	@Column(name = "book_questionnum")
	private int questionNum;

	@Column(name = "book_notenum")
	private int noteNum;

	@Column(name = "book_imageurl")
	private String imageUrl;

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

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public int getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Book() {

	}

	public Book(int id, String bookName, String isbn, String writer,
			String press, int followNum, int questionNum, String imageUrl) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.isbn = isbn;
		this.writer = writer;
		this.press = press;
		this.followNum = followNum;
		this.questionNum = questionNum;
		this.imageUrl = imageUrl;
	}

	public Book(String bookName, String isbn, String writer, String press,
			String imageUrl) {
		this.bookName = bookName;
		this.isbn = isbn;
		this.writer = writer;
		this.press = press;
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", isbn=" + isbn
				+ ", writer=" + writer + ", press=" + press + ", followNum="
				+ followNum + ", questionNum=" + questionNum + ", noteNum="
				+ noteNum + ", imageUrl=" + imageUrl + "]";
	}

}
