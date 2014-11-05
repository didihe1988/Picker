package com.didihe1988.picker.model.dp;

import com.didihe1988.picker.model.Attachment;
import com.didihe1988.picker.utils.DateUtils;

public class AttachmentDp extends Attachment {

	private String userName;

	private String userAvatarUrl;

	private String strDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAvatarUrl() {
		return userAvatarUrl;
	}

	public void setUserAvatarUrl(String userAvatarUrl) {
		this.userAvatarUrl = userAvatarUrl;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public AttachmentDp(Attachment attachment, String userName,
			String userAvatarUrl) {
		super(attachment);
		this.userAvatarUrl = userAvatarUrl;
		this.userName = userName;
		this.strDate = DateUtils.getDate(attachment.getDate());
	}

	@Override
	public String toString() {
		return "AttachmentDp [userName=" + userName + ", userAvatarUrl="
				+ userAvatarUrl + ", strDate=" + strDate + ", id=" + id
				+ ", bookId=" + bookId + ", userId=" + userId + ", name="
				+ name + ", path=" + path + ", date=" + date + "]";
	}



}
