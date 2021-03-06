package com.didihe1988.picker.common;

import com.didihe1988.picker.utils.JsonUtils;

public class Constant {
	private Constant() {

	}

	// public static final String ROOTDIR =
	// "F:/sts_workspace/Picker/src/main/webapp/resources/";
	// public static final String ROOTDIR =
	// "F:/apache-tomcat-7.0.55/webapps/ROOT/resources/";
	public static final String ROOTDIR = "/usr/share/tomcat7/webapps/ROOT/resources/";
	public static final String TMPIMAGEDIR = ROOTDIR + "/tmp/image/";

	public static final String USER = "picker_user";
	public static final String USERID = "picker_userId";
	public static final String USERNAME = "picker_userName";
	public static final String FAVORITE_NUM = "favoriteNum";
	public static final String COMMENT_NUM = "commentNum";
	public static final String FOLLOW_NUM = "followNum";
	public static final String FOLLOW_OTHERSNUM = "followOthersNum";
	public static final String ANSWER_NUM = "answerNum";
	public static final String CIRCLE_NUM = "circleNum";
	public static final String NOTE_NUM = "noteNum";
	public static final String QUESTION_NUM = "questionNum";
	public static final String ATTACH_FEED_NUM = "attachmentNum";
	public static final String BOOK_NUM = "bookNum";

	public static final String KEY_SESSIONID = "JSESSIONID";
	public static final String KEY_STATUS = "status";
	public static final String KEY_QUESTION_LIST = "questionList";
	public static final String KEY_COMMENT_LIST = "commentList";
	public static final String KEY_ANSWER_LIST = "answerList";
	public static final String KEY_ATTACHMENT_LIST = "attachmentList";
	public static final String KEY_CIRCLE_LIST = "circleList";
	public static final String KEY_FOLLOW_LIST = "followList";
	public static final String KEY_NOTE_LIST = "noteList";
	public static final String KEY_BOOK_LIST = "bookList";
	public static final String KEY_USER_LIST = "userList";
	public static final String KEY_MESSAGE_LIST = "messageList";
	public static final String KEY_PRIVATEMESSAGE_LIST = "privateMessageList";
	public static final String KEY_SEARCHRESULT_LIST = "searchResultList";
	public static final String KEY_FEED_LIST = "feedList";
	public static final String KEY_QUESTION = "question";
	public static final String KEY_COMMENT = "comment";
	public static final String KEY_COMMENTNUM = "commentNum";
	public static final String KEY_ANSWER = "answer";
	public static final String KEY_CIRCLE = "circle";
	public static final String KEY_FOLLOW = "follow";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_NOTE = "note";
	public static final String KEY_BOOK = "book";
	public static final String KEY_USER = "user";
	public static final String KEY_IMAGEID = "imageId";
	public static final String KEY_IMAGEURL = "imageUrl";
	public static final String KEY_FAVORITENUM = "favoriteNum";
	public static final String KEY_ATTACHMENT_ID = "attachmentId";
	public static final String KEY_ATTACHMENTFEED = "attachmentFeed";
	public static final String KEY_SECTION_LIST = "sectionList";

	public static final String PLATFORM_ANDROID = "android";

	public static final String STATUS_INVALID = JsonUtils.getJsonObjectString(
			Constant.KEY_STATUS, Status.INVALID);
	public static final String STATUS_SUCCESS = JsonUtils.getJsonObjectString(
			Constant.KEY_STATUS, Status.SUCCESS);
	public static final String STATUS_ERROR = JsonUtils.getJsonObjectString(
			Constant.KEY_STATUS, Status.ERROR);

	public static final int MESSAGE_LENGTH = 20;
	public static final int MAX_PASSWORD_LENGTH = 20;
	public static final int MIN_PASSWORD_LENGTH = 6;
	public static final int MAX_EMAIL_LENGTH = 20;
	public static final int MIN_EMAIL_LENGTH = 6;
	public static final int MAX_USERNAME_LENGTH = 20;
	public static final int MIN_USERNAME_LENGTH = 6;

	public static final int DEFAULT_QUERYRESULT = 10;
	public static final int DEFAULT_SEARCH_LIMITNUM = 9;

	public static final int DEFAULT_PAGE_RAGE = 5;

	public static final int DEFAULT_BRIEF_LENGTH = 120;
}
