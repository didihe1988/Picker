package com.didihe1988.picker.controller.rest;

import java.io.File;
import java.text.Normalizer.Form;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.patterns.IfPointcut.IfFalsePointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Attachment;
import com.didihe1988.picker.model.AttachmentFeed;
import com.didihe1988.picker.model.form.AttachmentFeedForm;
import com.didihe1988.picker.service.AttachmentFeedService;
import com.didihe1988.picker.service.AttachmentService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class RestAttachmentFeedController {
	@Autowired
	private AttachmentFeedService aFeedService;

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(value = "/json/attachment_feed/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addAttachmentFeed(@RequestBody AttachmentFeedForm form,
			HttpServletRequest request) {
		if (!form.chechValidation()) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.ERROR);
		}
		System.out.println(form);
		AttachmentFeed aFeed = AttachmentFeed.getAttachmentFeed(form,
				HttpUtils.getSessionUserId(request));
		int status = aFeedService.addAttachmentFeed(aFeed);
		if (status == Status.SUCCESS) {
			refreshAttachment(form,
					aFeedService.getLatestAttachmentFeedByBookId(form
							.getBookId()));
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	private void refreshAttachment(AttachmentFeedForm form, int aFeedId) {
		for (int id : form.getAttachmentIds()) {
			Attachment attachment = attachmentService.getAttachmentById(id);
			if (attachment == null) {
				// 需要报错还是怎样？
				break;
			}
			/*
			 * uplate
			 */
			attachment.setaFeedId(aFeedId);
			attachment.setPath("/resources/attachment/book/" + form.getBookId()
					+ "/"+attachment.getName());
			attachmentService.updateAttachment(attachment);
			/*
			 * move file
			 */
			moveAttachmentFile(form.getBookId(), attachment.getName());

		}
	}

	private void moveAttachmentFile(int bookId, String fileName) {
		String dirString = Constant.ROOTDIR + "attachment/book/" + bookId + "/";
		File dir = new File(dirString);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File oldFile = new File(Constant.ROOTDIR + "tmp/attachment/" + fileName);
		if (!oldFile.exists()) {
			return;
		}
		try {
			oldFile.renameTo(new File(dirString + fileName));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
