package com.didihe1988.picker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.didihe1988.picker.model.dp.SearchResult;
import com.didihe1988.picker.model.interfaces.SearchModel;

@Entity
@Table(name = "book")
public class Book implements Serializable, SearchModel {

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

	@Column(name = "book_pages")
	private int pages;

	@Column(name = "book_date")
	private String date;

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

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNoteNum() {
		return noteNum;
	}

	public void setNoteNum(int noteNum) {
		this.noteNum = noteNum;
	}

	public Book() {

	}

	public Book(int id, String bookName, String isbn, String writer,
			String press, int pages, String date, int followNum,
			int questionNum, int noteNum, String imageUrl) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.isbn = isbn;
		this.writer = writer;
		this.press = press;
		this.pages = pages;
		this.date = date;
		this.followNum = followNum;
		this.questionNum = questionNum;
		this.noteNum = noteNum;
		this.imageUrl = imageUrl;
	}

	public Book(String bookName, String isbn, String writer, String press,
			int pages, String date, String imageUrl) {
		super();
		this.bookName = bookName;
		this.isbn = isbn;
		this.writer = writer;
		this.press = press;
		this.pages = pages;
		this.date = date;
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", isbn=" + isbn
				+ ", writer=" + writer + ", press=" + press + ", pages="
				+ pages + ", date=" + date + ", followNum=" + followNum
				+ ", questionNum=" + questionNum + ", noteNum=" + noteNum
				+ ", imageUrl=" + imageUrl + "]";
	}

	@Override
	public SearchResult toSearchResult() {
		// TODO Auto-generated method stub
		StringBuilder content = new StringBuilder();
		content.append("作者: ").append(this.writer).append(" 出版社: ")
				.append(this.questionNum);
		/*
		 * return new SearchResult(this.id, Type.Book, this.bookName, "作者: " +
		 * this.writer, this.imageUrl);
		 */
		return new SearchResult(this.id,SearchResult.RESULT_BOOK, this.bookName, "作者: "
				+ this.writer,this.imageUrl);
	}
	
}
