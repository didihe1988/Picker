package com.didihe1988.picker.model.display;

import com.didihe1988.picker.model.PrivateMessage;

/*
 * 没有继承PrivateMessageDp因为我觉得这样清晰一些
 * 有空再重构吧。
 */
public class PrivateMessageSum extends PrivateMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String senderName;

	private String senderAvatarUrl;

	private String receiverName;

	private String receiverAvatarUrl;

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

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

	public PrivateMessageSum() {

	}

	public PrivateMessageSum(PrivateMessage privateMessage, String senderName,
			String senderAvatarUrl, String receiverName,
			String receiverAvatarUrl, int count) {
		super(privateMessage);
		this.senderName = senderName;
		this.senderAvatarUrl = senderAvatarUrl;
		this.receiverName = receiverName;
		this.receiverAvatarUrl = receiverAvatarUrl;
		this.count = count;
	}

}
