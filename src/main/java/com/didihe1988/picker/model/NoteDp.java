package com.didihe1988.picker.model;

public class NoteDp extends Note {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bookName;

	private String userName;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public NoteDp() {

	}

	public NoteDp(Note note, String bookName, String userName) {
		super(note.getId(), note.getBookId(), note.getUserId(),
				note.getTitle(), note.getContent(), note.getPublishTime(), note
						.isPublic());
		this.bookName = bookName;
		this.userName = userName;
	}

}
