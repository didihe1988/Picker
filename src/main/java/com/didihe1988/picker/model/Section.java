package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "chapter")
public class Section implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final List<Section> NULL_SUB_SECTIONS = new ArrayList<Section>();
	/*
	 * 每个Hibernate entity必须有id
	 */
	@Id
	@GeneratedValue
	@Column(name = "chapter_id")
	private long id;
	
	/*
	 * 用于扫页码出章节相关资源功能
	 * 由前端在编辑时自动填充，对用户透明
	 */
	@Column(name = "chapter_bookid")
	private int bookId;

	// 1.1 1.1.2 ...
	@Column(name = "chapter_num")
	private String num;

	@Column(name = "chapter_name")
	private String name;
	
	@Column(name = "chapter_startpage")
	private int startPage;
	
	/*
	 * 用于扫页码时快速得到该页所属章节
	 * 由前端在编辑时自动填充，对用户透明
	 */
	@Column(name = "chapter_endpage")
	private int endPage;

	@Transient
	private List<Section> subSections;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public List<Section> getSubSections() {
		return subSections;
	}

	public void setSubSections(List<Section> subSections) {
		this.subSections = subSections;
	}
	
	public Section() {

	}

	public Section(long id, int bookId, String num, String name, int startPage,int endPage,
			List<Section> subSections) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.num = num;
		this.name = name;
		this.startPage=startPage;
		this.endPage=endPage;
		this.subSections = subSections;
	}

	public Section(int bookId, String num, String name, int startPage,int endPage,
			List<Section> subSections) {
		this.bookId = bookId;
		this.num = num;
		this.name = name;
		this.startPage=startPage;
		this.endPage=endPage;
		this.subSections = subSections;
	}

	public Section(int bookId, String num, String name, int startPage,int endPage) {
		this.bookId = bookId;
		this.num = num;
		this.name = name;
		this.startPage=startPage;
		this.endPage=endPage;
		this.subSections = NULL_SUB_SECTIONS;
	}

	public boolean hasSubSections() {
		return this.subSections.size() != 0;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", bookId=" + bookId + ", num=" + num
				+ ", name=" + name + ", startPage=" + startPage + ", endPage="
				+ endPage + ", subSections=" + subSections + "]";
	}


	
}
