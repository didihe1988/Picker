package com.didihe1988.picker.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.RelatedImage;

public class ImageUtils {
	public static List<String> getTmpUrlsFromContent(String content) {
		String pString = "(\\!\\[image)(.*?)(\\]\\()(.*?)(\\))";
		Pattern pattern = Pattern.compile(pString, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(content);
		List<String> list = new ArrayList<String>();
		while (matcher.find()) {
			list.add(matcher.group(matcher.groupCount() - 1));
		}
		return list;
	}

	public static String getNewImageUrl(int type, int objId, int index,
			String oldImageUrl) {
		String typeString = getTypeStringFromType(type);
		return "/resources/image/" + typeString + "/" + objId + "/"
				+ getNewFileName(index, oldImageUrl);
	}

	private static String getExtension(String filename) {
		int i = filename.lastIndexOf('.');
		return filename.substring(i + 1);
	}

	public static String getNewFileName(int index, String oldImageUrl) {
		return index + "." + getExtension(oldImageUrl);
	}

	private static String getTypeStringFromType(int type) {
		switch (type) {
		case RelatedImage.QUESTION_IMAGE:
			return "question";
		case RelatedImage.NOTE_IMAGE:
			return "note";
		case RelatedImage.ANSWER_IMAGE:
			return "answer";
		default:
			return "nontype";
		}
	}

	public static boolean moveImage(int type, int objId, String content) {
		List<String> imageUrls = new ArrayList<String>();
		if (content != null) {
			imageUrls = getTmpUrlsFromContent(content);
		}
		if (imageUrls.size() == 0) {
			return false;
		}
		String dstDirString = Constant.ROOTDIR + getTypeStringFromType(type)
				+ "/" + objId;
		File dstDir = new File(dstDirString);
		if (!dstDir.exists()) {
			dstDir.mkdirs();
		}
		for (int index = 0; index < imageUrls.size(); index++) {
			String filename = getFileNameFromTmpUrl(imageUrls.get(index));
			String tmpDirString = Constant.TMPDIR + filename;
			// System.out.println(tmpDirString);
			// System.out.println(dstDirString);
			// /question/1/0.png
			File tmpDir = new File(tmpDirString);
			if (!tmpDir.exists()) {
				System.out.println("tmpDir not exists");
				return false;
			} else {
				try {
					// System.out.println("in copy");
					String newFileName = ImageUtils.getNewFileName(index,
							filename);
					String dstFileString = dstDirString + "/" + newFileName;
					System.out.println(dstFileString);
					tmpDir.renameTo(new File(dstFileString));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	private static String getFileNameFromTmpUrl(String imageUrl) {
		int i = imageUrl.lastIndexOf('/');
		return imageUrl.substring(i + 1);
	}

}
