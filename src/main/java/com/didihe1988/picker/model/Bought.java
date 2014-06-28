package com.didihe1988.picker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "bought")
// composite key(userid,bookid)
@IdClass(Bought.class)
public class Bought implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "bought_userid")
	private int userId;

	@Id
	@Column(name = "bought_bookid")
	private int bookId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Bought(int userId, int bookId) {
		super();
		this.userId = userId;
		this.bookId = bookId;
	}
}
