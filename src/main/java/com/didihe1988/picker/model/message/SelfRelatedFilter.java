package com.didihe1988.picker.model.message;

public class SelfRelatedFilter{
	private static final int TYPE_CODE=2;
	
	// 你的问题有了新的回答
	public static final int MESSAGE_YOUR_QUESTION_UPDATE = 1;
	
	// 你的问题被点赞
	public static final int MESSAGE_YOUR_QUESTION_FAVORITED = 2;
	
	// 你的回答被点赞
	public static final int MESSAGE_YOUR_ANSWER_FAVORITED = 3;
	
	// 你的评论被点赞
	public static final int MESSAGE_YOUR_COMMENT_FAVORITED = 4;
	
	// 你的笔记被点赞
	public static final int MESSAGE_YOUR_NOTE_FAVORITED = 5;
	
	// 你的回答被评论
	public static final int MESSAGE_YOUR_ANSWER_COMMENTED = 6;
	
	// 你的问题被评论
	public static final int MESSAGE_YOUR_QUESTION_COMMENTED = 7;
	
	// 你被关注XXX了
	public static final int MESSAGE_OTHERS_FOLLOW_YOU = 8;
	
	//XXX加入了你建立的圈子 
	public static final int MESSAGE_OTHERS_JOIN_YOUR_CIRCLE = 9;

	public static int getTypeCode() {
		// TODO Auto-generated method stub
		return TYPE_CODE;
	}
	
}
