package com.didihe1988.picker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AttachmentDao;
import com.didihe1988.picker.model.Attachment;
import com.didihe1988.picker.model.dp.AttachmentDp;
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
		/*
		 * check validation 后续加上pull request
		 */
		/*
		 * if (!circleDao.isEstablisherOfCircle(attachment.getUserId(),
		 * attachment.getCircleId())) { return Status.INVALID; }
		 */
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
	public boolean isAttachmentExistsByName(String fileName, int bookId) {
		// TODO Auto-generated method stub
		return attachmentDao.isAttachmentExistsByName(fileName, bookId);
	}

	@Override
	public List<Attachment> getAttachmentsByBookId(int bookId) {
		// TODO Auto-generated method stub
		return attachmentDao.queryAttachmentsByBookId(bookId);
	}

	@Override
	public int getLatestAttachmentByBookId(int bookId) {
		// TODO Auto-generated method stub
		return attachmentDao.getLatestAttachmentByBookId(bookId);
	}

	@Override
	public List<AttachmentDp> getAttachmentDpsByBookId(int bookId) {
		// TODO Auto-generated method stub
		return attachmentDao.queryAttachmentDpsByBookId(bookId);
	}

}
