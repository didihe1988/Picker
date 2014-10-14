package com.didihe1988.downloader.main;

import java.sql.SQLException;

import com.didihe1988.downloader.dao.DouBookDao;
import com.didihe1988.downloader.model.DouBook;
import com.didihe1988.downloader.network.Executor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
	public static void main(String[] args) {
		String url = "https://api.douban.com/v2/book/17604305?fields=id,title,pages,author,publisher,isbn13,pubdate,image";
		Executor executor = new Executor(url);
		Object result = executor.httpGet();
		if (result instanceof String) {
			// System.out.println(result);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM").create();
			DouBook douBook = gson.fromJson((String) result, DouBook.class);
			douBook.toLargeImageUrl();
			System.out.println(douBook.toString());
			DouBookDao dao = DouBookDao.getDao();
			try {
				boolean success = dao.insert(douBook);
				if (success) {
					executor.setUrl(douBook.getImage());
					int innerId = dao.queryLatestBookId();
					System.out.println(innerId);
					executor.downloadImage(innerId);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				dao.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
