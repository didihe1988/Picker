package com.didihe1988.picker.model.json;

public class CommentJson {
	private int user_id;

	private String user_name;

	private String message;

	private String photo_link;

	private String time;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhoto_link() {
		return photo_link;
	}

	public void setPhoto_link(String photo_link) {
		this.photo_link = photo_link;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public CommentJson() {

	}

	public CommentJson(int user_id, String user_name, String message,
			String photo_link, String time) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.message = message;
		this.photo_link = photo_link;
		this.time = time;
	}

	@Override
	public String toString() {
		return "CommentJson [user_id=" + user_id + ", user_name=" + user_name
				+ ", message=" + message + ", photo_link=" + photo_link
				+ ", time=" + time + "]";
	}

}
