package com.didihe1988.picker.model.form;

public class FeedForm {
	private String type;

	private String title;

	private int page;

	private String raw;

	private String attachmentIds;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(String attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	public FeedForm() {

	}

	public FeedForm(String type, String title, int page, String raw,
			String attachmentIds) {
		this.type = type;
		this.title = title;
		this.page = page;
		this.raw = raw;
		this.attachmentIds = attachmentIds;
	}

	public boolean checkValidation() {

		if ((type == null) || (title == null) || (raw == null)
				|| (raw.equals(""))) {
			return false;
		}
		if (!(type.equals("note") || type.equals("question") || type
				.equals("attachment"))) {
			return false;
		}
		return true;

	}

	@Override
	public String toString() {
		return "FeedForm [type=" + type + ", title=" + title + ", page=" + page
				+ ", raw=" + raw + ", attachmentIds=" + attachmentIds + "]";
	}

}
