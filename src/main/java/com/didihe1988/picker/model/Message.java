package com.didihe1988.picker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message implements Serializable {

	/**
	 * �ɹ�ע��������Ϣ �û��Ķ�̬����ʾ
	 */
	// С�� ��������� ����·���������ܹ�����
	public static final int MESSAGE_FOLLOWED_ASKQUESTION = 1;

	// С�� �ص������� �����ÿ��ȱ�����java�������ᵽc++ʱ��������ʲô�� Java ����C++ ��
	public static final int MESSAGE_FOLLOWED_ANSWER_QUESTION = 2;

	// С�� ���˸����� С��4�ֻ���1+�ֻ���nexus5�ֻ�Ӧ�����ĸ���
	public static final int MESSAGE_FOLLOWED_FAVORITE_QEUSTION = 3;

	// С�� ���˸ûش� ��ο������౨�������ź������й���̳�������ţ��ǻ��������ı����� Фӥ���廪��ѧ���������񾭳��������˵�����
	public static final int MESSAGE_FOLLOWED_FAVORITE_ANSWER = 4;

	// С�� ���˸ñʼ� ���ѧϰ����
	public static final int MESSAGE_FOLLOWED_FAVORITE_NOTE = 5;

	// С�� ������������ XXXX
	public static final int MESSAGE_FOLLOWED_FAVORITE_COMMENT = 6;

	public static final int MESSAGE_FOLLOWED_FOLLOWQUESTION = 7;
	public static final int MESSAGE_FOLLOWED_ADDBOUGHT = 8;
	public static final int MESSAGE_FOLLOWED_ADDCOMMENT = 9;
	public static final int MESSAGE_FOLLOWED_ADDCIRCLE = 10;
	public static final int MESSAGE_FOLLOWED_JOINCIRCLE = 11;
	public static final int MESSAGE_FOLLOWED_ADDNOTE = 12;
	public static final int MESSAGE_QUESTION_UPDATE = 13;
	public static final int MESSAGE_USER_FOLLOWED = 14;

	/*
	 * �û���ص���Ϣ �û�����Ϣ֪ͨ�н���
	 */
	public static final int MESSAGE_YOUR_QUESTION_UPDATE = 15;
	public static final int MESSAGE_YOUR_QUESTION_FAVORITED = 16;
	public static final int MESSAGE_YOUR_ANSWER_FAVORITED = 17;
	public static final int MESSAGE_YOUR_COMMENT_FAVORITED = 18;
	public static final int MESSAGE_YOUR_NOTE_FAVORITED = 19;
	public static final int MESSAGE_YOUR_ANSWER_COMMENTED = 20;
	public static final int MESSAGE_YOUR_QUESTION_COMMENTED = 21;

	/*
	 * �û�������������Ϣ ���û������¶�̬����ʾ
	 */
	public static final int MESSAGE_USER_ADDQUESTION = 22;
	public static final int MESSAGE_USER_ADDANSWER = 23;
	public static final int MESSAGE_USER_ADDCOMMENT = 24;
	public static final int MESSAGE_USER_ADDNOTE = 25;
	public static final int MESSAGE_USER_FOLLOW_OTHER = 26;
	public static final int MESSAGE_USER_FAVORITE_QUESTION = 27;
	public static final int MESSAGE_USER_FAVORITE_NOTE = 28;
	public static final int MESSAGE_USER_FAVORITE_ANSWER = 29;
	public static final int MESSAGE_USER_FAVORITE_COMMENT = 30;

	// filter
	public static final int MESSAGE_UNCHECKED = 8;
	public static final int MESSAGE_CHECKED = 9;

	public enum Filter {
		/*
		 * ���Ѷ�̬(���û���ע���˲���) , �û��㼣 ���û�profile����ʾ , �������
		 */
		MESSAGE_DYNAMIC, MESSAGE_FOOTPRINT, MESSAGE_RELATED;
		private final int startType;
		private final int endType;
		Filter()
		{
			switch (ordinal()) {
			case 0:
				startType=1;
				endType=14;
				break;
			case 1:
				startType=22;
				endType=30;
				break;
			case 2:
				startType=15;
				endType=21;
				break;
			default:
				startType=1;
				endType=14;
				break;
			}
		}
		public int getStartType() {
			return startType;
		}
		public int getEndType() {
			return endType;
		}
		
		
		
		
	}

	public static final int NULL_RelatedSourceId = -1;
	public static final String NULL_RelatedSourceContent = "";
	public static final int NULL_receiverId = -1;

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

	@Column(name = "message_time")
	private Date time;

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

	public Message(long id, int receiverId, boolean isChecked, int type,
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
