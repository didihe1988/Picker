package com.didihe1988.picker.utils;

import java.io.File;

public class FileUtils {

	public static void createDirectory(String dir) {
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
	}

	public static void deleteFile(String dir) {
		File dirFile = new File(dir);
		dirFile.delete();
	}
}
