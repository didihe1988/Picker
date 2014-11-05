package com.didihe1988.picker.controller.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
public class RestAttachmentController {
	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(value = "/json/attachment/{bookId}/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String attachmentUpload(
			@RequestParam("attachment") MultipartFile file,
			@PathVariable int bookId, HttpServletRequest request) {
		if ((bookId < 1) || (!HttpUtils.isSessionUserIdExists(request))
				|| (file.isEmpty())) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		if(file.isEmpty())
		{
			return  JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.EMPTY);
		}
		String name = file.getOriginalFilename();
		if (attachmentService.isAttachmentExistsByName(name, bookId)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.EXISTS);
		}
		String dir = Constant.ROOTDIR + "attachment/book/" + bookId + "/";
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
		String webRoot="/resources/attachment/book/" + bookId + "/";
		Attachment attachment = new Attachment(bookId,
				HttpUtils.getSessionUserId(request), name, webRoot + name);
		int status = attachmentService.addAttachment(attachment);
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS, status);
	}
}
