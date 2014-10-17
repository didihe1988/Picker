package com.didihe1988.downloader.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

import com.didihe1988.downloader.dao.DouBookDao;
import com.didihe1988.downloader.model.DouBook;
import com.didihe1988.downloader.network.Executor;
import com.didihe1988.downloader.network.TagTraverser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
	public static void main(String[] args) {
		TagTraverser traverser = new TagTraverser("½ü´úÊ· ",1,10);
		try {
			HashSet<Integer> idSet = traverser.getIds();
			System.out.println(idSet);
			Iterator<Integer> iterator = idSet.iterator();
			
			while (iterator.hasNext()) {
				downloadNewBook(iterator.next());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exceptio
			e.printStackTrace();
		}
	}

	private static void downloadNewBook(int id) throws Exception {
		String url = "https://api.douban.com/v2/book/"
				+ id
				+ "?fields=id,title,pages,author,publisher,isbn13,pubdate,image";
		Executor executor = new Executor(url);
		Object result = executor.httpGet();
		if (result instanceof String) {
			// System.out.println(result);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM").create();
			DouBook douBook = gson.fromJson((String) result, DouBook.class);
			douBook.resetField();
			System.out.println(douBook.toString());
			DouBookDao dao = DouBookDao.getDao();
			boolean success = false;
			if (!dao.isExistsByDouId(douBook.getId())) {
				success = dao.insert(douBook);
			}
			if (success) {
				executor.setUrl(douBook.getImage());
				int innerId = dao.queryLatestBookId();
				System.out.println(innerId);
				executor.downloadImage(innerId);
			}
			dao.closeConnection();
		}
	}
}
