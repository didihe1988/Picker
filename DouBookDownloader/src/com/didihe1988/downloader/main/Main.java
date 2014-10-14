package com.didihe1988.downloader.main;

import java.sql.SQLException;

import com.didihe1988.downloader.dao.DouBookDao;
import com.didihe1988.downloader.model.DouBook;
import com.didihe1988.downloader.network.Executor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
	public static void main(String[] args) {
		String url = "https://api.douban.com/v2/book/1858513?fields=id,title,pages,author,publisher,isbn13,pubdate";
		Executor executor = new Executor(url);
		Object result = executor.execute();
		if (result instanceof String) {
			// System.out.println(result);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM").create();
			DouBook douBook = gson.fromJson((String) result, DouBook.class);
			System.out.println(douBook.toString());
			DouBookDao dao = DouBookDao.getDao();
			try {
				dao.insert(douBook);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
