package com.didihe1988.picker.model.message;

public class FootprintFilter implements MessageFilter{
	private static final int TYPE_CODE=1;
	
	public static final int MESSAGE_USER_ADDQUESTION = 1;
	public static final int MESSAGE_USER_ADDANSWER = 2;
	public static final int MESSAGE_USER_ADD_QUESTIONCOMMENT = 3;
	public static final int MESSAGE_USER_ADD_NOTECOMMENT = 4;
	public static final int MESSAGE_USER_ADD_ANSWERCOMMENT = 5;
	public static final int MESSAGE_USER_ADDNOTE = 6;
	public static final int MESSAGE_USER_FOLLOW_OTHER = 7;
	public static final int MESSAGE_USER_FAVORITE_QUESTION = 8;
	public static final int MESSAGE_USER_FAVORITE_NOTE = 9;
	public static final int MESSAGE_USER_FAVORITE_ANSWER = 10;
	public static final int MESSAGE_USER_FAVORITE_COMMENT = 11;
	public static final int MESSAGE_USER_ADDCIRCLE = 12;
	public static final int MESSAGE_USER_JOINCIRCLE = 13;

	@Override
	public int getTypeCode() {
		// TODO Auto-generated method stub
		return this.TYPE_CODE;
	}
	
	
}
