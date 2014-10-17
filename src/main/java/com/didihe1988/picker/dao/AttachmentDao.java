package com.didihe1988.picker.dao;

import java.util.List;

import com.didihe1988.picker.model.Attachment;

public interface AttachmentDao {
	public Attachment queryAttachmentById(int id);

	public int addAttachment(Attachment attachment);

	public int deleteAttachmentById(int id);

	public int updateAttachment(Attachment attachment);

	public boolean isAttachmentExistsById(int id);

	public List<Attachment> queryAttachmentsByCircleId(int circleId);

	public int getLatestAttachmentByCircleId(int circleId);

}
