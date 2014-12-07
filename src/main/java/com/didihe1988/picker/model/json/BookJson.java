package com.didihe1988.picker.model.json;

public class BookJson {
	private int page = 0;
	private boolean end = false;
	private String status = "success";
	private Book book;

	public BookJson() {

	}

	public BookJson(String name, String cover_image_path, String author,
			String publisher, String date, String brief) {
		this.book = new Book(name, cover_image_path, author, publisher, date,
				brief);
	}

	private class Book {
		private String name;

		private String cover_image_path;

		private String author;

		private String publisher;

		private String date;

		private String brief;

		public Book(String name, String cover_image_path, String author,
				String publisher, String date, String brief) {
			this.name = name;
			this.cover_image_path = cover_image_path;
			this.author = author;
			this.publisher = publisher;
			this.date = date;
			this.brief = brief;
		}
	}

}
