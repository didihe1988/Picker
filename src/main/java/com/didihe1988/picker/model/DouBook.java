package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doubook")
public class DouBook implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "doubook_id")
	private int id;

	@Column(name = "doubook_douid")
	private int douId;

	@Column(name = "doubook_name")
	private String bookName;

	@Column(name = "doubook_isbn")
	private String isbn;

	@Column(name = "doubook_writer")
	private String writer;

	@Column(name = "doubook_press")
	private String press;

	@Column(name = "doubook_imageurl")
	private String imageUrl;

	@Column(name = "doubook_date")
	private Date date;

	public DouBook() {

	}
}
