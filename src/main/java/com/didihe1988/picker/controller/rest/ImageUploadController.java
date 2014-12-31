package com.didihe1988.picker.controller.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class ImageUploadController {
	@RequestMapping(value = "/json/image_upload", method = RequestMethod.POST, headers = "Accept=application/json")
	public String imageUpload(@RequestParam("image") MultipartFile file) {
		// System.out.println(file.getContentType());
		String name = file.getOriginalFilename();
		System.out.println(name);
		if (!file.isEmpty()) {
			String extension = FilenameUtils.getExtension(name);
			if (!checkExtension(extension)) {
				return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
						Status.INVALID_FORMAT);
			} else {
				String dir = Constant.TMPIMAGEDIR;
				int imageCount = 0;
				File dirFile = new File(dir);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				} else {
					imageCount = dirFile.listFiles().length;
				}
				byte[] bytes;
				try {
					bytes = file.getBytes();
					File serverFile = new File(dir + imageCount + "."
							+ extension);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Constant.STATUS_ERROR;
				}
				String url = "/resources/tmp/image/" + imageCount + "."
						+ extension;
				return JsonUtils
						.getJsonObjectString(Constant.KEY_IMAGEURL, url);
			}
		} else {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.EMPTY);
		}
	}

	private boolean checkExtension(String extension) {
		return ((extension != null) && (extension.equals("jpg"))
				|| (extension.equals("png")) || (extension.equals("bmp")) || (extension
					.equals("gif")));
	}

	private int getImageId(String dir) {
		File dirFile = new File(dir);
		return getImageId(dirFile);
	}

	private int getImageId(File dirFile) {
		File[] files = dirFile.listFiles();
		String name = files[files.length - 1].getName();
		return Integer.parseInt(name);
	}

}
