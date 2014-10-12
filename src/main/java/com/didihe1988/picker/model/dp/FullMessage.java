package com.didihe1988.picker.model.dp;

import com.didihe1988.picker.model.Message;

public class FullMessage extends Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String producerAvatarUrl;

	private String parentName;

	private String title;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public FullMessage() {

	}

	public FullMessage(Message message, String producerAvatarUrl,
			String parentName, String title) {
		super(message);
		this.producerAvatarUrl = producerAvatarUrl;
		this.parentName = parentName;
		this.title = title;
	}
}
