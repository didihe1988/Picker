package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

	/**
	 * 
	 */
	// 小明 提出了问题 家用路由器会遭受攻击吗？
	public static final int MESSAGE_FOLLOWED_ASKQUESTION = 1;

	// 小明 回单了问题 我们用咖啡杯代表java，而当提到c++时，我们用什么？ Java 咖啡C++ 烫
	public static final int MESSAGE_FOLLOWED_ANSWER_QUESTION = 2;

	// 小明 赞了该问题 小米4手机、1+手机和nexus5手机应该买哪个？
	public static final int MESSAGE_FOLLOWED_FAVORITE_QEUSTION = 3;

	// 小明 赞了该回答 如何看待中青报发表的天才韩寒是中国文坛的最大丑闻？是基于怎样的背景？ 肖鹰是清华大学教授里最像经常留言骂人的网民。
	public static final int MESSAGE_FOLLOWED_FAVORITE_ANSWER = 4;

	// 小明 赞了该笔记 如何学习姿势
	public static final int MESSAGE_FOLLOWED_FAVORITE_NOTE = 5;

	// 小明 赞了您的评论 XXXX
	public static final int MESSAGE_FOLLOWED_FAVORITE_COMMENT = 6;

	public static final int MESSAGE_FOLLOWED_FOLLOWQUESTION = 4;
	public static final int MESSAGE_FOLLOWED_ADDBOUGHT = 5;
	public static final int MESSAGE_FOLLOWED_ADDCOMMENT = 6;
	public static final int MESSAGE_QUESTION_UPDATE = 7;

	public static final int MESSAGE_YOUR_QUESTION_UPDATE = 8;
	public static final int MESSAGE_YOUR_QUESTION_FAVORITED = 10;
	public static final int MESSAGE_YOUR_ANSWER_FAVORITED = 11;
	public static final int MESSAGE_YOUR_COMMENT_FAVORITED = 12;
	public static final int MESSAGE_YOUR_NOTE_FAVORITED = 13;
	public static final int MESSAGE_USER_FOLLOWED = 9;

	// to display
	public static final int MESSAGE_UNCHECKED = 8;
	public static final int MESSAGE_CHECKED = 9;
	public static final int MESSAGE_QUESTION = 10;
	public static final int MESSAGE_FOLLOWED = 11;
	public static final int MESSAGE_ALL = 12;

	public static final int NULL_RelatedSourceId = 0;
	public static final String NULL_RelatedSourceContent = "";

	private static final long serialVersionUID = 1L;

	private int id;
	// 消息接受者的id session里的user
	private int receiverId;
	private boolean isChecked;
	private int type;
	private int producerId;
	private String producerName;
	private int relatedSourceId;
	private String relatedSourceContent;
	private Date time;

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

	public int getProducerId() {
		return producerId;
	}

	public void setProducerId(int producerId) {
		this.producerId = producerId;
	}

	public int getRelatedSourceId() {
		return relatedSourceId;
	}

	public void setRelatedSourceId(int relatedSourceId) {
		this.relatedSourceId = relatedSourceId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getRelatedSourceContent() {
		return relatedSourceContent;
	}

	public void setRelatedSourceContent(String relatedSourceContent) {
		this.relatedSourceContent = relatedSourceContent;
	}

	public Message(int id, int receiverId, boolean isChecked, int type,
			int producerId, String producerName, int relatedSourceId,
			String relatedSourceContent, Date time) {
		super();
		this.id = id;
		this.receiverId = receiverId;
		this.isChecked = isChecked;
		this.type = type;
		this.producerId = producerId;
		this.producerName = producerName;
		this.relatedSourceId = relatedSourceId;
		this.relatedSourceContent = relatedSourceContent;
		this.time = time;
	}

	public Message(int receiverId, boolean isChecked, int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent, Date time) {
		super();
		this.receiverId = receiverId;
		this.isChecked = isChecked;
		this.type = type;
		this.producerId = producerId;
		this.producerName = producerName;
		this.relatedSourceId = relatedSourceId;
		this.relatedSourceContent = relatedSourceContent;
		this.time = time;
	}

	public Message(int receiverId, int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent) {
		this(receiverId, false, type, producerId, producerName,
				relatedSourceId, relatedSourceContent, new Date());
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

}
