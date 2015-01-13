package com.didihe1988.picker.controller.rest;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Attachment;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.display.AttachmentFeedDp;
import com.didihe1988.picker.model.form.AttachmentFeedForm;
import com.didihe1988.picker.service.AttachmentService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class RestAttachmentFeedController {
	@Autowired
	private FeedService feedService;

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(value = "/json/attachment_feed/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addAttachmentFeed(@RequestBody AttachmentFeedForm form,
			HttpServletRequest request) {
		if (!form.chechValidation()) {
			return Constant.STATUS_ERROR;
		}
		Feed feed = new Feed(form, HttpUtils.getSessionUserId(request));
		int status = feedService.addFeed(feed);
		if (status == Status.SUCCESS) {
			bindAttachment(form, feedService.getLatestFeedByBookId(
					form.getBookId(), Feed.TYPE_ATTACHMENT_FEED));

		}
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}

	@RequestMapping(value = "/json/attachment_feed/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String afeed(@PathVariable int id) {
		if (id < 1) {
			return Constant.STATUS_ERROR;
		}
		//Feed attFeed = feedService.getFeedById(id);
		AttachmentFeedDp attFeed=feedService.getAttFeedDpByFeedId(id);
		if (attFeed != null) {
			return JsonUtils.getJsonObjectStringFromModel(
					Constant.KEY_ATTACHMENTFEED, attFeed);
		}
		return Constant.STATUS_ERROR;
	}

	private void bindAttachment(AttachmentFeedForm form, int attFeedId) {
		for (int id : form.getAttachmentIds()) {
			Attachment attachment = attachmentService.getAttachmentById(id);
			if (attachment == null) {
				// 需要报错还是怎样？
				break;
			}
			// uplate
			attachment.setaFeedId(attFeedId);
			attachment.setPath("/resources/attachment/book/" + form.getBookId()
					+ "/" + attachment.getName());
			attachmentService.updateAttachment(attachment);
			// move file
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
