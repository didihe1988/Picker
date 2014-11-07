package com.didihe1988.picker.model.dp;

import com.didihe1988.picker.model.AttachmentFeed;
import com.didihe1988.picker.utils.DateUtils;

public class AttachmentFeedDp extends AttachmentFeed {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public AttachmentFeedDp(AttachmentFeed attachmentFeed, String userName,
			String userAvatarUrl) {
		super(attachmentFeed);
		this.userAvatarUrl = userAvatarUrl;
		this.userName = userName;
		this.strDate = DateUtils.getDate(attachmentFeed.getDate());
	}

	@Override
	public String toString() {
		return "AttachmentFeedDp [userName=" + userName + ", userAvatarUrl="
				+ userAvatarUrl + ", strDate=" + strDate + "]";
	}

}
