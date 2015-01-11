package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message implements Serializable {
	/*
	 * comment����Ϣֻ��footprint��dynamic��
	 */
	/*
	 * // ---DYNAMIC Starts �ɹ�ע��������Ϣ �û��Ķ�̬����ʾ--- // С�� ��������� ����·���������ܹ����� public
	 * static final int MESSAGE_FOLLOWED_ASKQUESTION = 1;
	 * 
	 * // С�� �ص������� �����ÿ��ȱ�����java�������ᵽc++ʱ��������ʲô�� Java ����C++ �� public static final
	 * int MESSAGE_FOLLOWED_ANSWER_QUESTION = 2;
	 * 
	 * // С�� ���˸����� С��4�ֻ���1+�ֻ���nexus5�ֻ�Ӧ�����ĸ��� public static final int
	 * MESSAGE_FOLLOWED_FAVORITE_QEUSTION = 3;
	 * 
	 * // С�� ���˸ûش� ��ο������౨�������ź������й���̳�������ţ��ǻ��������ı����� Фӥ���廪��ѧ���������񾭳��������˵�����
	 * public static final int MESSAGE_FOLLOWED_FAVORITE_ANSWER = 4;
	 * 
	 * // С�� ���˸ñʼ� ���ѧϰ���� public static final int MESSAGE_FOLLOWED_FAVORITE_NOTE
	 * = 5;
	 * 
	 * // С�� ������������ XXXX public static final int
	 * MESSAGE_FOLLOWED_FAVORITE_COMMENT = 6;
	 * 
	 * public static final int MESSAGE_FOLLOWED_FOLLOWQUESTION = 7; public
	 * static final int MESSAGE_FOLLOWED_ADDBOUGHT = 8; public static final int
	 * MESSAGE_FOLLOWED_ADD_QUESTIONCOMMENT = 9; public static final int
	 * MESSAGE_FOLLOWED_ADD_NOTECOMMENT = 10; public static final int
	 * MESSAGE_FOLLOWED_ADD_ANSWERCOMMENT = 11; public static final int
	 * MESSAGE_FOLLOWED_ADDCIRCLE = 12; public static final int
	 * MESSAGE_FOLLOWED_JOINCIRCLE = 13; public static final int
	 * MESSAGE_FOLLOWED_ADDNOTE = 14; public static final int
	 * MESSAGE_QUESTION_EDIT = 15; public static final int
	 * MESSAGE_QUESTION_NEWANSWER = 16;
	 * 
	 * // С��(����ע��)��ע��XXX public static final int MESSAGE_FOLLOWEDUSER_FOLLOW =
	 * 17; // ---DYNAMIC Ends---
	 * 
	 * // ---UserRelated Starts �û���ص���Ϣ �û�����Ϣ֪ͨ�н���--- // ������������µĻش� public
	 * static final int MESSAGE_YOUR_QUESTION_UPDATE = 18; // ������ⱻ���� public
	 * static final int MESSAGE_YOUR_QUESTION_FAVORITED = 19; // ��Ļش𱻵��� public
	 * static final int MESSAGE_YOUR_ANSWER_FAVORITED = 20; // ������۱����� public
	 * static final int MESSAGE_YOUR_COMMENT_FAVORITED = 21; // ��ıʼǱ����� public
	 * static final int MESSAGE_YOUR_NOTE_FAVORITED = 22; // ��Ļش����� public
	 * static final int MESSAGE_YOUR_ANSWER_COMMENTED = 23; // ������ⱻ���� public
	 * static final int MESSAGE_YOUR_QUESTION_COMMENTED = 24; // �㱻��עXXX�� public
	 * static final int MESSAGE_OTHERS_FOLLOW_YOU = 25;
	 * 
	 * // ȱ��:XXX�������㴴����Ȧ�� UserRelated Ends // ---UserRelated Ends
	 * 
	 * //---Footprint Starts �û�������������Ϣ ���û���profile����ʾ--- public static final int
	 * MESSAGE_USER_ADDQUESTION = 26; public static final int
	 * MESSAGE_USER_ADDANSWER = 27; public static final int
	 * MESSAGE_USER_ADD_QUESTIONCOMMENT = 28; public static final int
	 * MESSAGE_USER_ADD_NOTECOMMENT = 29; public static final int
	 * MESSAGE_USER_ADD_ANSWERCOMMENT = 30; public static final int
	 * MESSAGE_USER_ADDNOTE = 31; public static final int
	 * MESSAGE_USER_FOLLOW_OTHER = 32; public static final int
	 * MESSAGE_USER_FAVORITE_QUESTION = 33; public static final int
	 * MESSAGE_USER_FAVORITE_NOTE = 34; public static final int
	 * MESSAGE_USER_FAVORITE_ANSWER = 35; public static final int
	 * MESSAGE_USER_FAVORITE_COMMENT = 36; public static final int
	 * MESSAGE_USER_ADDCIRCLE = 37; public static final int
	 * MESSAGE_USER_JOINCIRCLE = 38; //---Footprint Ends---
	 */
	/*
	 * public enum Filter { MESSAGE_DYNAMIC, MESSAGE_FOOTPRINT,
	 * MESSAGE_SELF_RELATED; private final int startType; private final int
	 * endType;
	 * 
	 * Filter() { switch (ordinal()) { case 0: startType = 1; endType = 17;
	 * break; case 1: startType = 26; endType = 38; break; case 2: startType =
	 * 18; endType = 25; break; default: startType = 1; endType = 17; break; } }
	 * 
	 * public int getStartType() { return startType; }
	 * 
	 * public int getEndType() { return endType; }
	 * 
	 * }
	 */

	public static final String NULL_RelatedSourceContent = "";
	public static final String NULL_ExtraContent = "";
	public static final int NULL_RelatedSourceId = -1;
	public static final int NULL_receiverId = -1;
	public static final int NULL_parentId = -1;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "message_id")
	private long id;

	// ��Ϣ�����ߵ�id session���user
	@Column(name = "message_receiverid")
	private int receiverId;

	@Column(name = "message_ischecked")
	private boolean isChecked;

	/*
	 * �����Feed��ص���Ϣ������ʾ��̬��Ϣ�ǻ��ѯ����(parentName)
	 */
	@Column(name = "message_isfeedrelated")
	private boolean isFeedRelated;

	@Column(name = "message_filtertype")
	private int filterType;

	@Column(name = "message_type")
	private int type;

	@Column(name = "message_producerid")
	private int producerId;

	@Column(name = "message_producername")
	private String producerName;

	@Column(name = "message_relatedsourceid")
	private int relatedSourceId;

	@Column(name = "message_relatedsourcecontent")
	private String relatedSourceContent;

	@Column(name = "message_extracontent")
	private String extraContent;

	@Column(name = "message_time")
	private Date time;

	@Column(name = "message_parentid")
	private int parentId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public boolean isFeedRelated() {
		return isFeedRelated;
	}

	public void setFeedRelated(boolean isFeedRelated) {
		this.isFeedRelated = isFeedRelated;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFilterType() {
		return filterType;
	}

	public void setFilterType(int filterType) {
		this.filterType = filterType;
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getExtraContent() {
		return extraContent;
	}

	public void setExtraContent(String extraContent) {
		this.extraContent = extraContent;
	}

	public Message(long id, int receiverId, boolean isChecked,
			boolean isFeedRelated, int filterType, int type, int producerId,
			String producerName, int relatedSourceId,
			String relatedSourceContent, String extraContent, Date time,
			int parentId) {
		this.id = id;
		this.receiverId = receiverId;
		this.isChecked = isChecked;
		this.isFeedRelated = isFeedRelated;
		this.type = type;
		this.filterType = filterType;
		this.producerId = producerId;
		this.producerName = producerName;
		this.relatedSourceId = relatedSourceId;
		this.relatedSourceContent = relatedSourceContent;
		this.extraContent = extraContent;
		this.time = time;
		this.parentId = parentId;
	}

	public Message(int receiverId, boolean isChecked, boolean isFeedRelated,
			int filterType, int type, int producerId, String producerName,
			int relatedSourceId, String relatedSourceContent,
			String extraContent, Date time, int parentId) {
		this.receiverId = receiverId;
		this.isChecked = isChecked;
		this.isFeedRelated = isFeedRelated;
		this.type = type;
		this.filterType = filterType;
		this.producerId = producerId;
		this.producerName = producerName;
		this.relatedSourceId = relatedSourceId;
		this.relatedSourceContent = relatedSourceContent;
		this.extraContent = extraContent;
		this.time = time;
		this.parentId = parentId;
	}

	public Message(int receiverId, boolean isFeedRelated, int filterType, int type,
			int producerId, String producerName,
			int relatedSourceId, String relatedSourceContent,
			String extraContent, int parentId) {
		this(receiverId, false, isFeedRelated, filterType, type, producerId,
				producerName, relatedSourceId, relatedSourceContent,
				extraContent, new Date(), parentId);
	}

	public Message(Message message) {
		this(message.getId(), message.getReceiverId(), message.isChecked(),
				message.isFeedRelated(), message.getFilterType(), message
						.getType(), message.getProducerId(), message
						.getProducerName(), message.getRelatedSourceId(),
				message.getRelatedSourceContent(), message.getExtraContent(),
				message.getTime(), message.getParentId());
	}

	public Message() {

	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", receiverId=" + receiverId
				+ ", isChecked=" + isChecked + ", isFeedRelated="
				+ isFeedRelated + ", filterType=" + filterType + ", type="
				+ type + ", producerId=" + producerId + ", producerName="
				+ producerName + ", relatedSourceId=" + relatedSourceId
				+ ", relatedSourceContent=" + relatedSourceContent
				+ ", extraContent=" + extraContent + ", time=" + time
				+ ", parentId=" + parentId + "]";
	}

}
