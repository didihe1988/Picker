package com.didihe1988.picker.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Attachment;
import com.didihe1988.picker.service.AttachmentService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class AttachmentUploadController {
	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private CircleService circleService;

	@RequestMapping(value = "/json/attachment_upload", method = RequestMethod.POST, headers = "Accept=application/json")
	public String attachmentUpload(
			@RequestParam("attachment") MultipartFile file,
			@RequestParam("circleId") int circleId, HttpServletRequest request) {
		if ((circleId < 1)
				|| (!HttpUtils.isSessionUserIdExists(request))
				|| (!circleService.isEstablisherOfCircle(
						HttpUtils.getSessionUserId(request), circleId))
				|| (file.isEmpty())) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		String name = file.getOriginalFilename();
		if (attachmentService.isAttachmentExistsInCircle(name, circleId)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.EXISTS);
		}
		String dir = Constant.ROOTDIR + "image/circle/" + circleId + "/";
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		byte[] bytes;
		try {
			bytes = file.getBytes();
			System.out.println(bytes);
			File saveFile = new File(dir + name);
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(saveFile));
			stream.write(bytes);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.ERROR);
		}
		Attachment attachment = new Attachment(circleId,
				HttpUtils.getSessionUserId(request), name, dir + name,
				new Date());
		int status = attachmentService.addAttachment(attachment);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}
}
