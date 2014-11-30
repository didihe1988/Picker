package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.dao.interfaces.PageRelatedOperation;
import com.didihe1988.picker.model.AttachmentFeed;
import com.didihe1988.picker.model.dp.AttachmentFeedDp;

public interface AttachmentFeedDao extends PageRelatedOperation<AttachmentFeed> {
	public AttachmentFeed queryAttachmentFeedById(int id);

	public int addAttachmentFeed(AttachmentFeed attachmentFeed);

	public int deleteAttachmentFeedById(int id);

	public int updateAttachmentFeed(AttachmentFeed attachmentFeed);

	public boolean isAttachmentFeedExistsById(int id);

	// public boolean isAttachmentFeedExistsByName(String fileName, int bookId);

	public List<AttachmentFeed> queryAttachmentFeedsByBookId(int bookId);

	public List<AttachmentFeedDp> queryAttachmentFeedDpsByBookId(int bookId);

	public int getLatestAttachmentFeedByBookId(int bookId);

}
