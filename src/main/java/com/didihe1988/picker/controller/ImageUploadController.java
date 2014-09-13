package com.didihe1988.picker.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

public class ImageUploadController {
	@RequestMapping(value = "/json/question/{id}/imageup", method = RequestMethod.POST, headers = "Accept=application/json")
	public String imageUpload(@PathVariable int id,
			@RequestParam("iamge") MultipartFile file) {
		if (!file.isEmpty()) {
			if (!checkExtension(file.getName())) {
				return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
						Status.INVALID);
			} else {
				String dir = Constant.TMPDIR;
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
					File serverFile = new File(dir + imageCount);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
							Status.ERROR);
				}
				return JsonUtils.getJsonObjectString(Constant.KEY_IMAGEID,
						String.valueOf(imageCount));
			}
		} else {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.EMPTY);
		}
	}

	private boolean checkExtension(String name) {
		return (name.endsWith("jpg") || name.endsWith("png"));
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
