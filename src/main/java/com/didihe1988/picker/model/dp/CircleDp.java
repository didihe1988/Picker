package com.didihe1988.picker.model.dp;

import java.util.List;

import com.didihe1988.picker.model.Attachment;
import com.didihe1988.picker.model.Circle;

public class CircleDp extends Circle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String establisherName;

	private boolean isJoin;

	private List<Attachment> attachmentList;

	public boolean isJoin() {
		return isJoin;
	}

	public void setJoin(boolean isJoin) {
		this.isJoin = isJoin;
	}

	public String getEstablisherName() {
		return establisherName;
	}

	public void setEstablisherName(String establisherName) {
		this.establisherName = establisherName;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public CircleDp(Circle circle, boolean isJoin, String establisherName,
			List<Attachment> attachmentList) {
		super(circle);
		this.isJoin = isJoin;
		this.establisherName = establisherName;
		this.attachmentList = attachmentList;
	}

	public CircleDp() {

	}

}
