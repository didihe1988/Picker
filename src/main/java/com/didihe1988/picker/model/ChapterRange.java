package com.didihe1988.picker.model;

public class ChapterRange {
	private int startPage;

	private int endPage;

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

	public ChapterRange(int startPage, int endPage) {
		super();
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public static ChapterRange toChapterRange(Section section) {
		return new ChapterRange(section.getStartPage(), section.getEndPage());
	}

	public static ChapterRange toChapterRange(int page, int range) {
		return new ChapterRange(page - range < 0 ? 0 : page - range, page
				+ range);
	}

}
