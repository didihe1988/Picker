package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Attachment;

public interface AttachmentService {
	public Attachment getAttachmentById(int id);

	public int addAttachment(Attachment attachment);

	public int deleteAttachmentById(int id);

	public int updateAttachment(Attachment attachment);

	public boolean isAttachmentExistsById(int id);

	public List<Attachment> getAttachmentsByCircleId(int circleId);

	public int getLatestAttachmentByCircleId(int circleId);
}
