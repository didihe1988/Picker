package com.didihe1988.picker.model.message;

public class SelfRelatedFilter{
	private static final int TYPE_CODE=2;
	
	// ������������µĻش�
	public static final int MESSAGE_YOUR_QUESTION_UPDATE = 1;
	
	// ������ⱻ����
	public static final int MESSAGE_YOUR_QUESTION_FAVORITED = 2;
	
	// ��Ļش𱻵���
	public static final int MESSAGE_YOUR_ANSWER_FAVORITED = 3;
	
	// ������۱�����
	public static final int MESSAGE_YOUR_COMMENT_FAVORITED = 4;
	
	// ��ıʼǱ�����
	public static final int MESSAGE_YOUR_NOTE_FAVORITED = 5;
	
	// ��Ļش�����
	public static final int MESSAGE_YOUR_ANSWER_COMMENTED = 6;
	
	// ������ⱻ����
	public static final int MESSAGE_YOUR_QUESTION_COMMENTED = 7;
	
	// �㱻��עXXX��
	public static final int MESSAGE_OTHERS_FOLLOW_YOU = 8;
	
	//XXX�������㽨����Ȧ�� 
	public static final int MESSAGE_OTHERS_JOIN_YOUR_CIRCLE = 9;

	public static int getTypeCode() {
		// TODO Auto-generated method stub
		return TYPE_CODE;
	}
	
}
