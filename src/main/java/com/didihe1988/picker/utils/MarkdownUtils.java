package com.didihe1988.picker.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownUtils {
	public static final String removeTags(String content) {
		String brief = content;
		if (brief != "") {
			brief = removeBold(brief);
			brief = removeItalic(brief);
			brief = removeImgTag(brief);
			brief = removeHeader(brief);
			brief = removeHr(brief);
		}
		return brief;
	}

	private static String removeBold(String brief) {
		String pString = "(\\*\\*)(.*?)(\\*\\*)";
		Pattern pattern = Pattern.compile(pString, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(brief);
		while (matcher.find()) {
			brief = brief.replace(matcher.group(0),
					matcher.group(matcher.groupCount() - 1));
		}
		return brief;
	}

	private static String removeItalic(String brief) {
		String pString = "(\\*)(.*?)(\\*)";
		Pattern pattern = Pattern.compile(pString, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(brief);
		while (matcher.find()) {
			brief = brief.replace(matcher.group(0),
					matcher.group(matcher.groupCount() - 1));
		}
		return brief;
	}

	private static String removeHeader(String brief) {
		String pString = "(\\#)(.*)";
		Pattern pattern = Pattern.compile(pString, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(brief);
		while (matcher.find()) {
			String line = matcher.group(0);
			line = line.replace("#", "");
			brief = brief.replace(matcher.group(0), line);
		}
		return brief;
	}

	private static String removeImgTag(String brief) {
		String pString = "(\\!\\[image)(.*?)(\\]\\()(.*?)(\\))";
		Pattern pattern = Pattern.compile(pString, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(brief);
		while (matcher.find()) {
			brief = brief.replace(matcher.group(0), "");
		}
		return brief;
	}

	private static String removeHr(String brief) {
		brief = brief.replace("---", "");
		brief = brief.replace("***", "");
		brief = brief.replace("- - - -", "");
		return brief;
	}

}
