package com.didihe1988.downloader.network;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TagTraverser {
	private String tag;

	private int startPage;

	private int endPage;

	public TagTraverser() {

	}

	public TagTraverser(String tag, int startPage, int endPage) {
		this.tag = tag;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public HashSet<Integer> getIds() throws IOException {
		HashSet<Integer> idSet = new HashSet<Integer>();
		for (int page = startPage; page <= endPage; page++) {
			idSet.addAll(getIdsByPage(page));
		}
		return idSet;
	}

	private HashSet<Integer> getIdsByPage(int page) throws IOException {
		StringBuilder urlBuilder = new StringBuilder();
		// http://book.douban.com/tag/ÃûÖø?start=0&type=T
		urlBuilder.append("http://book.douban.com/tag/").append(this.tag)
				.append("?start=").append((page - 1) * 20).append("&type=S");
		System.out.println(urlBuilder.toString());
		HashSet<Integer> set = new HashSet<Integer>();
		Document doc = Jsoup.connect(urlBuilder.toString()).get();
		Elements aTags = doc.select("a");
		Iterator<Element> iterator = aTags.iterator();
		while (iterator.hasNext()) {
			String url = iterator.next().attr("href");
			int id = -1;
			if ((id = getIdFromUrl(url)) != -1) {
				set.add(id);
			}
		}
		return set;
	}

	private static int getIdFromUrl(String url) {
		String pString = "(http://book.douban.com/subject/)(.*?)(/)";
		Pattern pattern = Pattern.compile(pString, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(url);
		while (matcher.find()) {
			return Integer.parseInt(matcher.group(matcher.groupCount() - 1));
		}
		return -1;
	}
}
