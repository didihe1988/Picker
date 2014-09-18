package com.didihe1988.picker.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownUtils {
	public static final void removeTags(String content) {

	}

	private static String removeBold(String content) {
		String brief = content;
		String pString = "(\\*\\*)(.*?)(\\*\\*)";
		Pattern pattern = Pattern.compile(pString, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(brief);
		while (matcher.find()) {
			brief = brief.replace(matcher.group(0),
					matcher.group(matcher.groupCount() - 1));
		}
		return brief;
	}

	private static String removeItalic(String content) {
		String brief = content;
		String pString = "(\\*)(.*?)(\\*)";
		Pattern pattern = Pattern.compile(pString, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(brief);
		while (matcher.find()) {
			brief = brief.replace(matcher.group(0),
					matcher.group(matcher.groupCount() - 1));
		}
		return brief;
	}
}
