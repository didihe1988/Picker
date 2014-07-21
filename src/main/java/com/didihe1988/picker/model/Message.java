package com.didihe1988.picker.model;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	public static final int MESSAGE_FOLLOWED_ASKQUESTION = 1;
	public static final int MESSAGE_FOLLOWED_ANSWER_QUESTION = 2;
	public static final int MESSAGE_FOLLOWED_FAVORITE = 3;
	public static final int MESSAGE_FOLLOWED_FOLLOWQUESTION = 4;
	public static final int MESSAGE_FOLLOWED_ADDBOUGHT = 5;
	public static final int MESSAGE_QUESTION_UPDATE = 6;

	// to display
	public static final int MESSAGE_UNCHECKED = 7;
	public static final int MESSAGE_CHECKED = 8;
	public static final int MESSAGE_QUESTION = 9;
	public static final int MESSAGE_FOLLOWED = 10;
	public static final int MESSAGE_ALL = 11;

	private static final long serialVersionUID = 1L;

	private int id;
	// 消息接受者的id session里的user
	private int receiverId;
	private boolean isChecked;
	private int type;
	private int mainSourceId;
	private int relatedSourceId;

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

	public int getMainSourceId() {
		return mainSourceId;
	}

	public void setMainSourceId(int mainSourceId) {
		this.mainSourceId = mainSourceId;
	}

	public int getRelatedSourceId() {
		return relatedSourceId;
	}

	public void setRelatedSourceId(int relatedSourceId) {
		this.relatedSourceId = relatedSourceId;
	}

	public Message(int id, int receiverId, boolean isChecked, int type,
			int mainSourceId, int relatedSourceId) {
		super();
		this.id = id;
		this.receiverId = receiverId;
		this.isChecked = isChecked;
		this.type = type;
		this.mainSourceId = mainSourceId;
		this.relatedSourceId = relatedSourceId;
	}

	public Message(int receiverId, boolean isChecked, int type,
			int mainSourceId, int relatedSourceId) {
		super();
		this.id = id;
		this.receiverId = receiverId;
		this.isChecked = isChecked;
		this.type = type;
		this.mainSourceId = mainSourceId;
		this.relatedSourceId = relatedSourceId;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

}
