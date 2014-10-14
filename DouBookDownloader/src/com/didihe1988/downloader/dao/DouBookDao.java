package com.didihe1988.downloader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.didihe1988.downloader.model.DouBook;

public class DouBookDao extends BaseDao {
	private static DouBookDao dao;

	private DouBookDao() {
		super("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/picker", "root","1234");
	}

	public static DouBookDao getDao() {
		if (dao == null) {
			return new DouBookDao();
		}
		return dao;
	}

	public void insert(DouBook douBook) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		String insertSql = "insert into doubook (doubook_id,doubook_publisher,doubook_title,doubook_pages,doubook_author,doubook_pubdate,doubook_isbn13) values (?,?,?,?,?,?,?)";
		try {
			connection = getConnection();
			statement = connection.prepareStatement(insertSql);
			statement.setInt(1, douBook.getId());
			statement.setString(2, douBook.getPublisher());
			statement.setString(3, douBook.getTitle());
			statement.setInt(4, douBook.getPages());
			statement.setString(5, getAuthorString(douBook.getAuthor()));
			statement.setString(6, douBook.getPubdate());
			statement.setString(7, douBook.getIsbn13());
			statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	private String getAuthorString(List<String> authors) {
		StringBuilder result = new StringBuilder();
		for (String author : authors) {
			result.append(author).append(" ");
		}
		return result.toString();
	}

}
