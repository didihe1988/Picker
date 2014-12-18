package com.didihe1988.picker.model;

import java.util.ArrayList;
import java.util.List;

public class Section {
	private static final List<Section> NULL_SUB_SECTIONS = new ArrayList<Section>();

	// 1.1 1.1.2 ...
	private String num;

	private String name;

	private int page;

	private List<Section> subSections;

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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Section> getSubSections() {
		return subSections;
	}

	public void setSubSections(List<Section> subSections) {
		this.subSections = subSections;
	}

	public Section(String num, String name, int page, List<Section> subSections) {
		this.num = num;
		this.name = name;
		this.page = page;
		this.subSections = subSections;
	}

	public Section(String num, String name, int page) {
		this.num = num;
		this.name = name;
		this.page = page;
		this.subSections = NULL_SUB_SECTIONS;
	}

	public Section() {

	}

	public boolean hasSubSections() {
		return this.subSections.size() != 0;
	}

}
