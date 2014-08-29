package com.didihe1988.picker.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {
	private StringUtils() {

	}

	public static String getMd5String(String input) {
		String md5String = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			md5String = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md5String;
	}

	/**
	 * @description É¾³ý·´Ð±Ïß
	 */
	public static String deleteBackslash(String rawString) {
		int length = rawString.length();
		Character backslash = new Character('\\');
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			Character character = new Character(rawString.charAt(i));
			// returns the value 0 if the argument Character is equal to this
			// Character
			if (character.compareTo(backslash) != 0) {
				stringBuffer.append(character);
			}
		}
		return stringBuffer.toString();
	}

	public static String confineStringLength(String rawString, int length) {
		if (rawString.length() < length) {
			return rawString;
		}
		return rawString.substring(0, length - 1);
	}

	public static boolean isStringLengthOverLong(String string, int length) {
		if (string.length() > length) {
			return true;
		}
		return false;
	}

	public static boolean isStringLengthOverShort(String string, int length) {
		if (string.length() < length) {
			return true;
		}
		return false;
	}

	public static boolean isStringSuitable(String string, int maxLength,
			int minLength) {
		if (isStringLengthOverLong(string, maxLength)
				|| isStringLengthOverShort(string, minLength)) {
			return false;
		}
		return true;
	}

}
