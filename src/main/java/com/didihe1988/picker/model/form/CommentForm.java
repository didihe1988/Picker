package com.didihe1988.picker.model.form;

public class CommentForm {
	private int commentedId;

	private String content;

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

	public CommentForm() {

	}

	public CommentForm(int commentedId, String content) {
		super();
		this.commentedId = commentedId;
		this.content = content;
	}

	public boolean checkFieldValidation() {
		if ((this.commentedId > 0) && (this.content != null)
				&& (!this.content.equals(""))) {
			return true;
		}
		return false;
	}

}
