package com.didihe1988.picker.model.dp;

public class SearchResult {

	public static final int RESULT_USER = 0;
	public static final int RESULT_CIRCLE = 1;
	public static final int RESULT_BOOK = 2;
	public static final int RESULT_QUESTION = 3;
	public static final int RESULT_NOTE = 4;

	private int id;
	private String imageUrl;
	private String name;
	private String content;
	private int type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public SearchResult() {

	}

	public SearchResult(int id, int type, String name, String content,
			String imageUrl) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.content = content;
		this.imageUrl = imageUrl;
	}

	public SearchResult(int id, int type, String name, String content) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.content = content;
	}

	@Override
	public String toString() {
		return "SearchResult [id=" + id + ", type=" + type + ", name=" + name
				+ ", content=" + content + ", imageUrl=" + imageUrl + "]";
	}

}
