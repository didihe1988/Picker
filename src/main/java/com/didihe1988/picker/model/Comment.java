package com.didihe1988.picker.model;

public class Comment extends Message {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Comment(int id, int receiverId, int producerId, String content) {
		super(id, receiverId, producerId);
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [content=" + content + ", id=" + id + ", receiverId="
				+ receiverId + ", producerId=" + producerId + "]";
	}

}
