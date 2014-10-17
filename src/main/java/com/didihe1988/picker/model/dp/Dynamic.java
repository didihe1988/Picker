package com.didihe1988.picker.model.dp;

import java.util.Date;

import com.didihe1988.picker.model.Message;

public class Dynamic extends Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String producerAvatarUrl;

	private String parentName;

	private String imageUrl;

	public String getProducerAvatarUrl() {
		return producerAvatarUrl;
	}

	public void setProducerAvatarUrl(String producerAvatarUrl) {
		this.producerAvatarUrl = producerAvatarUrl;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Dynamic(Message message, String producerAvatarUrl,
			String parentName, String imageUrl) {
		super(message);
		this.producerAvatarUrl = producerAvatarUrl;
		this.parentName = parentName;
		this.imageUrl = imageUrl;
	}
}
