package com.didihe1988.picker.model.form;

public class CommentForm {
	private int commentedId;

	private String content;

	private int type;

	public int getCommentedId() {
		return commentedId;
	}

	public void setCommentedId(int commentedId) {
		this.commentedId = commentedId;
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

	public CommentForm() {

	}

	public CommentForm(int commentedId, String content, int type) {
		super();
		this.commentedId = commentedId;
		this.content = content;
		this.type = type;
	}

	public boolean checkFieldValidation() {
		if ((this.commentedId > 0) && (this.content != null)
				&& (!this.content.equals("")) && (this.type >= 0)
				&& (this.type <= 2)) {
			return true;
		}
		return false;
	}

}
