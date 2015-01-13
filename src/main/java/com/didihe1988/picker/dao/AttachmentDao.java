package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.interfaces.BaseDao;
import com.didihe1988.picker.model.Attachment;

public interface AttachmentDao extends BaseDao<Attachment> {
	public Attachment queryModelById(int id);

	public int deleteModelById(int id);

	public int queryLatestAttachmentId();
	
	public List<Attachment> queryAttachments(int attFeedId);

}
