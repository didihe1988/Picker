package com.didihe1988.downloader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.didihe1988.downloader.model.DouBook;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class DouBookDao extends BaseDao {
	private static DouBookDao dao;

	private DouBookDao() {
		super("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/picker",
				"root", "1234");
	}

	public static DouBookDao getDao() {
		if (dao == null) {
			return new DouBookDao();
		}
		return dao;
	}

	public boolean insert(DouBook douBook) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		String insertSql = "insert into doubook (doubook_id,doubook_publisher,doubook_title,doubook_pages,doubook_author,doubook_pubdate,doubook_isbn13,doubook_image) values (?,?,?,?,?,?,?,?)";
		try {
			connection = getConnection();
			statement = connection.prepareStatement(insertSql);
			statement.setInt(1, douBook.getId());
			statement.setString(2, douBook.getPublisher());
			statement.setString(3, douBook.getTitle());
			statement.setInt(4, toInteger(douBook.getPages()));
			statement.setString(5, getAuthorString(douBook.getAuthor()));
			statement.setString(6, douBook.getPubdate());
			statement.setString(7, douBook.getIsbn13());
			statement.setString(8, douBook.getImage());
			int row = statement.executeUpdate();
			if (row == 1) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
		return false;
	}

	private int toInteger(String pages) {
		if ((pages == null) || (pages.equals(""))) {
			return 0;
		}
		return Integer.parseInt(pages);
	}

	public int queryLatestBookId() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		String querySql = "select max(doubook_innerid) from doubook";
		try {
			connection = getConnection();
			statement = connection.prepareStatement(querySql);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				return set.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
		return -1;
	}

	public boolean isExistsByDouId(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		String querySql = "select * from doubook where doubook_id =?";
		try {
			connection = getConnection();
			statement = connection.prepareStatement(querySql);
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			if (set.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
		return false;
	}

	private String getAuthorString(List<String> authors) {
		StringBuilder result = new StringBuilder();
		for (String author : authors) {
			result.append(author).append(" ");
		}
		return result.toString();
	}

}
