package com.didihe1988.picker.model.message;

public class DynamicFilter {
	private final static MessageFilter type = MessageFilter.DYNAMIC;

	public static MessageFilter getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public static int getTypeCode() {
		// TODO Auto-generated method stub
		return type.ordinal();
	}

	/*
	 * ������Ϣ��
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
	public static final int MESSAGE_FOLLOWED_ADD_QUESTIONCOMMENT = 9;
	public static final int MESSAGE_FOLLOWED_ADD_NOTECOMMENT = 10;
	public static final int MESSAGE_FOLLOWED_ADD_ANSWERCOMMENT = 11;
	public static final int MESSAGE_FOLLOWED_ADDCIRCLE = 12;
	public static final int MESSAGE_FOLLOWED_JOINCIRCLE = 13;
	public static final int MESSAGE_FOLLOWED_ADDNOTE = 14;
	public static final int MESSAGE_QUESTION_EDIT = 15;
	public static final int MESSAGE_QUESTION_NEWANSWER = 16;
	/*
	 * С��(����ע��)��ע��XXX
	 */
	public static final int MESSAGE_FOLLOWEDUSER_FOLLOW = 17;

}
