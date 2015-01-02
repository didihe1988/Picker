package com.didihe1988.picker.model.display;

import com.didihe1988.picker.model.Message;
import com.didihe1988.picker.utils.DateUtils;

public class Footprint extends Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String producerAvatarUrl;

	private String parentName;

	private String strDate;

	// private String title;

	public String getProducerAvatarUrl() {
		return producerAvatarUrl;
	}

	public void setProducerAvatarUrl(String producerAvatarUrl) {
		this.producerAvatarUrl = producerAvatarUrl;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public Footprint() {

	}

	public Footprint(Message message, String producerAvatarUrl,
			String parentName) {
		super(message);
		this.strDate = DateUtils.getDate(message.getTime());
		this.producerAvatarUrl = producerAvatarUrl;
		this.parentName = parentName;
	}
}
