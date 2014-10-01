package com.didihe1988.picker.model.dp;

import com.didihe1988.picker.model.PrivateMessage;

public class PrivateMessageDp extends PrivateMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String senderName;

	private String senderAvatarUrl;

	private String receiverName;

	private String receiverAvatarUrl;

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAvatarUrl() {
		return senderAvatarUrl;
	}

	public void setSenderAvatarUrl(String senderAvatarUrl) {
		this.senderAvatarUrl = senderAvatarUrl;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverAvatarUrl() {
		return receiverAvatarUrl;
	}

	public void setReceiverAvatarUrl(String receiverAvatarUrl) {
		this.receiverAvatarUrl = receiverAvatarUrl;
	}

	public PrivateMessageDp() {

	}

	public PrivateMessageDp(PrivateMessage privateMessage, String senderName,
			String senderAvatarUrl, String receiverName,
			String receiverAvatarUrl) {
		super(privateMessage);
		this.senderName = senderName;
		this.senderAvatarUrl = senderAvatarUrl;
		this.receiverName = receiverName;
		this.receiverAvatarUrl = receiverAvatarUrl;
	}
}
