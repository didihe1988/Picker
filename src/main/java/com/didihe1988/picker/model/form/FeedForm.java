package com.didihe1988.picker.model.form;

import java.util.Date;

import com.didihe1988.picker.model.Feed;

public class FeedForm extends Feed {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String imageUrls;

	public FeedForm() {

	}

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	/*
	 * 在前端/安卓端设置type
	 */
	public Feed getFeed() {
		return new Feed(getBookId(), getUserId(), getTitle(), getContent(),
				new Date(), getPage(), getType(), true);
	}
}
