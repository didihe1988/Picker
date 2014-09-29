package com.didihe1988.picker.common;

public class Status {
	private Status() {

	}

	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	public static final int EXISTS = 2;
	public static final int NOT_EXISTS = 3;
	public static final int UNCHANGED = 4;
	public static final int NULLPOINTER = 5;
	public static final int UNKNOWN = 6;
	public static final int INVALID = 7;
	public static final int EMPTY = 8;
	public static final int INVALID_FORMAT = 9;
	public static final int OVER_SIZE = 10;
	public static final int INVALID_FIELD = 11;
	public static final int NULLSESSION=12;

	public static final String EMAIL_USED_STRING = "email used";
	public static final String USERNAME_USED_STRING = "name used";
	public static final String SUCCESS_STRING = "success";
	public static final String OVERLONG_STRING = "overlong";
	public static final String OVERSHORT_STRING = "overshort";
	public static final String INVALID_EMAIL_STRING = "invalid email format";

}
