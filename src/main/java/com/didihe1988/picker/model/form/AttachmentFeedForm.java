package com.didihe1988.picker.model.form;

import java.util.List;

public class AttachmentFeedForm {
	private int bookId;

	private int page;

	private String title;

	private String content;

	private List<Integer> attachmentIds;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Integer> getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(List<Integer> attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	public AttachmentFeedForm() {

	}

	public AttachmentFeedForm(int bookId, int page, String title,
			String content, List<Integer> attachmentIds) {
		super();
		this.bookId = bookId;
		this.page = page;
		this.title = title;
		this.content = content;
		this.attachmentIds = attachmentIds;
	}

	@Override
	public String toString() {
		return "AttachmentFeedForm [bookId=" + bookId + ", page=" + page
				+ ", title=" + title + ", content=" + content
				+ ", attachmentIds=" + attachmentIds + "]";
	}

	public boolean chechValidation() {
		if ((this.bookId > 0) && (this.page >= 0) && (this.content != null)
				&& (!this.content.equals("")) && (this.attachmentIds != null)
				&& (this.title != null) && (!this.title.equals(""))) {
			return true;
		}
		return false;
	}

}
