package com.didihe1988.picker.service.impl;

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
		return attachmentDao.queryAttachmentById(id);
	}

	@Override
	public int addAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		if (attachment == null) {
			return Status.NULLPOINTER;
		}
		int status = attachmentDao.addAttachment(attachment);
		if (status == -1) {
			return Status.EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteAttachmentById(int id) {
		// TODO Auto-generated method stub
		int status = attachmentDao.deleteAttachmentById(id);
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
		if (!attachmentDao.isAttachmentExistsById(attachment.getId())) {
			return Status.INVALID;
		}
		int status = attachmentDao.updateAttachment(attachment);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public boolean isAttachmentExistsById(int id) {
		// TODO Auto-generated method stub
		return attachmentDao.isAttachmentExistsById(id);
	}

	@Override
	public int getLatestAttachmentId() {
		// TODO Auto-generated method stub
		return attachmentDao.queryLatestAttachmentId();
	}

}