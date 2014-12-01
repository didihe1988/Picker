package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.interfaces.BaseDao;
import com.didihe1988.picker.dao.interfaces.PageRelatedOperation;
import com.didihe1988.picker.model.AttachmentFeed;
import com.didihe1988.picker.model.dp.AttachmentFeedDp;

public interface AttachmentFeedDao extends BaseDao<AttachmentFeed>,
		PageRelatedOperation<AttachmentFeed> {
	public AttachmentFeed queryAttachmentFeedById(int id);

	public int deleteAttachmentFeedById(int id);

	// public boolean isAttachmentFeedExistsByName(String fileName, int bookId);

	public List<AttachmentFeed> queryAttachmentFeedsByBookId(int bookId);

	public List<AttachmentFeedDp> queryAttachmentFeedDpsByBookId(int bookId);

	public int getLatestAttachmentFeedByBookId(int bookId);

}
