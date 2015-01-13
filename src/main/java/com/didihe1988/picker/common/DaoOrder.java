package com.didihe1988.picker.common;

public enum DaoOrder {
	//前面必须留有空格 
	FeedPageOrder(" order by f.page,f.date asc"), FeedIdOrder(
			" order by f.id asc");

	private String content;

	private DaoOrder(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
