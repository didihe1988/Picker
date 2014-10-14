package com.didihe1988.downloader.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Connection conn = null;
	private String driver;
	private String url;
	private String username;
	private String password;

	public BaseDao() {
		
	}
	
	public BaseDao(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public Connection getConnection() throws Exception {
		if (conn == null) {
			Class.forName(this.driver);
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}
	/*
	public ResultSet query(String sql, Object... args) throws Exception {
		// ���conn==null getConnection���conn��ʼ��
		PreparedStatement statement = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			statement.setObject(i + 1, args[i]);
		}
		return statement.executeQuery();
	}

	public boolean insert(String sql, Object... args) throws Exception {
		if (conn == null)
			this.conn = getConnection();
		PreparedStatement statement = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			statement.setObject(i, args[i]);
		}
		// ���� executeUpdate�ķ���ֵ���״���ʲô
		if (statement.executeUpdate() != 1) {
			return false;
		}
		return true;
	}*/

	public void closeConnection() throws Exception {
		if ((conn != null) && (!conn.isClosed())) {
			conn.close();
		}
	}

}
