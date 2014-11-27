package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.AttachmentFeed;
import com.didihe1988.picker.model.dp.AttachmentFeedDp;

public interface AttachmentFeedService {
	public AttachmentFeed getAttachmentFeedById(int id);

	public int addAttachmentFeed(AttachmentFeed attachmentFeed);

	public int deleteAttachmentFeedById(int id);

	public int updateAttachmentFeed(AttachmentFeed attachmentFeed);

	public boolean isAttachmentFeedExistsById(int id);

	// public boolean isAttachmentExistsByName(String fileName, int bookId);

	public List<AttachmentFeed> getAttachmentFeedsByBookId(int bookId);

	public List<AttachmentFeedDp> getAttachmentFeedDpsByBookId(int bookId);

	public int getLatestAttachmentFeedByBookId(int bookId);
}