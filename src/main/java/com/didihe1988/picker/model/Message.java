package com.didihe1988.picker.model;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	public static final int MESSAGE_FOLLOWED_COMMENT = 1;
	public static final int MESSAGE_FOLLOWED_FAVORITE = 2;
	public static final int MESSAGE_FOLLOWED_FOLLOW_COMMENT = 3;
	public static final int MESSAGE_COMMENT_UPDATE = 4;

	// to display
	public static final int MESSAGE_UNCHECKED = 5;
	public static final int MESSAGE_CHECKED = 6;
	public static final int MESSAGE_COMMENT = 7;
	public static final int MESSAGE_FOLLOWED = 8;
	public static final int MESSAGE_ALL= 7;

	private static final long serialVersionUID = 1L;

	private int id;
	// 消息接受者的id session里的user
	private int receiverId;
	private boolean isChecked;
	private int type;
	public int sourceId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", receiverId=" + receiverId
				+ ", isChecked=" + isChecked + ", type=" + type + ", sourceId="
				+ sourceId + "]";
	}

	public Message(int id, int receiverId, boolean isChecked, int type,
			int sourceId) {
		super();
		this.id = id;
		this.receiverId = receiverId;
		this.isChecked = isChecked;
		this.type = type;
		this.sourceId = sourceId;
	}

	public Message(int receiverId, boolean isChecked, int type, int sourceId) {
		super();
		this.receiverId = receiverId;
		this.isChecked = isChecked;
		this.type = type;
		this.sourceId = sourceId;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

}
