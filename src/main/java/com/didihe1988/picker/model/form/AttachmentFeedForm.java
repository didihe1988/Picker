package com.didihe1988.picker.model.form;

import java.util.List;

public class AttachmentFeedForm {
	private int bookId;

	private int page;

	private String describe;

	private List<Integer> attachmentIds;

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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public List<Integer> getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(List<Integer> attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	public AttachmentFeedForm() {

	}

	public AttachmentFeedForm(int bookId, int page, String describe,
			List<Integer> attachmentIds) {
		super();
		this.bookId = bookId;
		this.page = page;
		this.describe = describe;
		this.attachmentIds = attachmentIds;
	}

	@Override
	public String toString() {
		return "AttachmentFeedForm [bookId=" + bookId + ", page=" + page
				+ ", describe=" + describe + ", attachmentIds=" + attachmentIds
				+ "]";
	}

	public boolean chechValidation() {
		if ((this.bookId > 0) && (this.page >= 0) && (this.describe != null)
				&& (!this.describe.equals("")) && (this.attachmentIds != null)) {
			return true;
		}
		return false;
	}

}
