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
@Table(name = "section")
public class Section implements Serializable {

	public static final int CHAPTER = 1;
	public static final int SECTION = 2;
	public static final int SUBSECTION = 3;

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
	@Column(name = "section_id")
	private long id;

	/*
	 * 用于扫页码出章节相关资源功能 由前端在编辑时自动填充，对用户透明
	 */
	@Column(name = "section_bookid")
	private int bookId;

	@Column(name = "section_type")
	private int type;

	// 1.1 1.1.2 ...
	@Column(name = "section_num")
	private String num;

	@Column(name = "section_name")
	private String name;

	// 每一章节下的小节的页码范围应该在章节页码之间，可以通过这个选取List<小节>
	@Column(name = "section_startpage")
	private int startPage;

	/*
	 * 用于扫页码时快速得到该页所属章节 由前端在编辑时自动填充，对用户透明
	 */
	@Column(name = "section_endpage")
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
		// 从数据库查询出来后保证subSections不为null
		if (subSections == null) {
			this.subSections = NULL_SUB_SECTIONS;
		}
	}

	public Section(long id, int bookId, int type, String num, String name,
			int startPage, int endPage, List<Section> subSections) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.type = type;
		this.num = num;
		this.name = name;
		this.startPage = startPage;
		this.endPage = endPage;
		this.subSections = subSections;
	}

	public Section(int bookId, int type, String num, String name,
			int startPage, int endPage, List<Section> subSections) {
		this.bookId = bookId;
		this.type = type;
		this.num = num;
		this.name = name;
		this.startPage = startPage;
		this.endPage = endPage;
		this.subSections = subSections;
	}

	public Section(int bookId, int type, String num, String name,
			int startPage, int endPage) {
		this.bookId = bookId;
		this.type = type;
		this.num = num;
		this.name = name;
		this.startPage = startPage;
		this.endPage = endPage;
		this.subSections = NULL_SUB_SECTIONS;
	}

	public boolean hasSubSections() {
		return this.subSections.size() != 0;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", bookId=" + bookId + ", type=" + type
				+ ", num=" + num + ", name=" + name + ", startPage="
				+ startPage + ", endPage=" + endPage + ", subSections="
				+ subSections + "]";
	}

}
