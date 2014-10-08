package com.didihe1988.picker.model.dp;

public class SearchResult {

	public enum Type {
		User, Circle, Book, Question, Note;
	}

	private int id;
	private String imageUrl;
	private String name;
	private String content;
	private Type type;

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public SearchResult() {

	}

	public SearchResult(int id, Type type, String name, String content,
			String imageUrl) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.content = content;
		this.imageUrl = imageUrl;
	}

	public SearchResult(int id, Type type, String name, String content) {
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
