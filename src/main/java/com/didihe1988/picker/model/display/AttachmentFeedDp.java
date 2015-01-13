package com.didihe1988.picker.model.display;

import java.util.List;

import com.didihe1988.picker.model.Attachment;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.utils.DateUtils;

public class AttachmentFeedDp extends Feed {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	private String userAvatarUrl;

	private String strDate;

	private List<Attachment> attachments;

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

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public AttachmentFeedDp(Feed feed, String userName, String userAvatarUrl) {
		super(feed);
		this.userAvatarUrl = userAvatarUrl;
		this.userName = userName;
		this.strDate = DateUtils.toDate(feed.getDate());
	}

	public AttachmentFeedDp(Feed feed, String userName, String userAvatarUrl,
			List<Attachment> attachments) {
		super(feed);
		this.userAvatarUrl = userAvatarUrl;
		this.userName = userName;
		this.strDate = DateUtils.toDate(feed.getDate());
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "AttachmentFeedDp [userName=" + userName + ", userAvatarUrl="
				+ userAvatarUrl + ", strDate=" + strDate + ", attachments="
				+ attachments + "]";
	}

}
