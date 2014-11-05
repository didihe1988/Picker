package com.didihe1988.picker.service;

import java.util.List;

import com.didihe1988.picker.model.Attachment;
import com.didihe1988.picker.model.dp.AttachmentDp;

public interface AttachmentService {
	public Attachment getAttachmentById(int id);

	public int addAttachment(Attachment attachment);

	public int deleteAttachmentById(int id);

	public int updateAttachment(Attachment attachment);

	public boolean isAttachmentExistsById(int id);
	
	public boolean isAttachmentExistsByName(String fileName, int bookId);

	public List<Attachment> getAttachmentsByBookId(int bookId);
	
	public List<AttachmentDp> getAttachmentDpsByBookId(int bookId);

	public int getLatestAttachmentByBookId(int bookId);
}
