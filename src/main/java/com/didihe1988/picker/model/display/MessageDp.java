package com.didihe1988.picker.model.display;

import com.didihe1988.picker.model.Message;

public class MessageDp extends Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String producerAvatarUrl;

	public String getProducerAvatarUrl() {
		return producerAvatarUrl;
	}

	public void setProducerAvatarUrl(String producerAvatarUrl) {
		this.producerAvatarUrl = producerAvatarUrl;
	}

	public MessageDp() {

	}

	public MessageDp(Message message, String producerAvatarUrl) {
		super(message);
		this.producerAvatarUrl = producerAvatarUrl;
	}
}
