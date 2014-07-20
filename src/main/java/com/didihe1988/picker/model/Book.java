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

	@Column(name = "book_press")
	private String press;

	@Column(name = "book_follownum")
	private int followNum;

	@Column(name = "book_commentnum")
	private int commentNum;

	@Column(name = "book_questionnum")
	private int questionNum;

	public Book() {

	}

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

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", isbn=" + isbn
				+ ", press=" + press + ", followNum=" + followNum
				+ ", commentNum=" + commentNum + ", questionNum=" + questionNum
				+ "]";
	}

}
