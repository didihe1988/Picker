package com.didihe1988.picker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AttachmentFeedDao;
import com.didihe1988.picker.model.AttachmentFeed;
import com.didihe1988.picker.model.dp.AttachmentFeedDp;
import com.didihe1988.picker.service.AttachmentFeedService;

@Service
@Transactional
public class AttachmentFeedServiceImpl implements AttachmentFeedService {
	@Autowired
	private AttachmentFeedDao attachmentFeedDao;

	@Override
	public AttachmentFeed getAttachmentFeedById(int id) {
		// TODO Auto-generated method stub
		return attachmentFeedDao.queryModelById(id);
	}

	@Override
	public int addAttachmentFeed(AttachmentFeed attachmentFeed) {
		// TODO Auto-generated method stub
		if (attachmentFeed == null) {
			return Status.NULLPOINTER;
		}
		int status = attachmentFeedDao.addModel(attachmentFeed);
		if (status == -1) {
			return Status.EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteAttachmentFeedById(int id) {
		// TODO Auto-generated method stub
		int status = attachmentFeedDao.deleteModelById(id);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int updateAttachmentFeed(AttachmentFeed attachmentFeed) {
		// TODO Auto-generated method stub
		if (attachmentFeed == null) {
			return Status.NULLPOINTER;
		}
		if (!attachmentFeedDao.isModelExistsById(attachmentFeed.getId())) {
			return Status.INVALID;
		}
		int status = attachmentFeedDao.updateModel(attachmentFeed);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public boolean isAttachmentFeedExistsById(int id) {
		// TODO Auto-generated method stub
		return attachmentFeedDao.isModelExistsById(id);
	}

	@Override
	public List<AttachmentFeed> getAttachmentFeedsByBookId(int bookId) {
		// TODO Auto-generated method stub
		return attachmentFeedDao.queryAttachmentFeedsByBookId(bookId);
	}

	@Override
	public int getLatestAttachmentFeedByBookId(int bookId) {
		// TODO Auto-generated method stub
		return attachmentFeedDao.getLatestAttachmentFeedByBookId(bookId);
	}

	@Override
	public List<AttachmentFeedDp> getAttachmentFeedDpsByBookId(int bookId) {
		// TODO Auto-generated method stub
		return attachmentFeedDao.queryAttachmentFeedDpsByBookId(bookId);
	}

	@Override
	public List<AttachmentFeedDp> getAttachmentFeedDpsByBookId(int bookId,
			String order) {
		// TODO Auto-generated method stub
		return attachmentFeedDao.queryAttachmentFeedDpsByBookId(bookId, order);
	}

	@Override
	public List<AttachmentFeedDp> getLimitedAttachmentFeedDpsByBookId(
			int bookId, int limit) {
		// TODO Auto-generated method stub
		return attachmentFeedDao.queryLimitedAttachmentFeedDpsByBookId(bookId,
				limit);
	}

	@Override
	public List<AttachmentFeedDp> getLimitedAttachmentFeedDpsByBookId(
			int bookId, int limit, String order) {
		// TODO Auto-generated method stub
		return attachmentFeedDao.queryLimitedAttachmentFeedDpsByBookId(bookId,
				limit, order);
	}

	@Override
	public List<AttachmentFeed> getAttachmentFeedListByPage(int bookId, int page) {
		// TODO Auto-generated method stub
		return attachmentFeedDao.queryModelListByPage(bookId, page);
	}

}
