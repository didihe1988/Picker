package com.didihe1988.picker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AttachmentDao;
import com.didihe1988.picker.model.Attachment;
import com.didihe1988.picker.service.AttachmentService;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public Attachment getAttachmentById(int id) {
		// TODO Auto-generated method stub
		return attachmentDao.queryModelById(id);
	}

	@Override
	public int addAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		if (attachment == null) {
			return Status.NULLPOINTER;
		}
		int status = attachmentDao.addModel(attachment);
		if (status == -1) {
			return Status.EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteAttachmentById(int id) {
		// TODO Auto-generated method stub
		int status = attachmentDao.deleteModelById(id);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int updateAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		if (attachment == null) {
			return Status.NULLPOINTER;
		}
		if (!attachmentDao.isModelExistsById(attachment.getId())) {
			return Status.INVALID;
		}
		int status = attachmentDao.updateModel(attachment);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public boolean isAttachmentExistsById(int id) {
		// TODO Auto-generated method stub
		return attachmentDao.isModelExistsById(id);
	}

	@Override
	public int getLatestAttachmentId() {
		// TODO Auto-generated method stub
		return attachmentDao.queryLatestAttachmentId();
	}

	@Override
	public List<Attachment> getAttachments(int attFeedId) {
		// TODO Auto-generated method stub
		return attachmentDao.queryAttachments(attFeedId);
	}

}
