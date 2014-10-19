package com.didihe1988.downloader.network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;

public class Executor {
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Executor(String url) {
		this.url = url;
	}

	public Object httpGet() {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(this.url);
			System.out.println(url.toString());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setReadTimeout(20000);
			connection.setConnectTimeout(20000);
			return getResponse(connection.getInputStream());
		} catch (ProtocolException e) {
			e.printStackTrace();
			return e;
		} catch (IOException e) {
			e.printStackTrace();
			return e;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public void downloadImage(int innerId) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(this.url);
			System.out.println(url.toString());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setReadTimeout(8000);
			connection.setConnectTimeout(8000);
			saveImage(connection.getInputStream(), innerId);
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	private void saveImage(InputStream inputStream, int innerId)
			throws IOException {
		String dirString = "F:/DouBookImage/" + innerId;
		File dir = new File(dirString);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		FileOutputStream output = new FileOutputStream(dirString + "/lpic.jpg");
		byte[] buffer = new byte[1024];
		int bytes = 0;
		while ((bytes = inputStream.read(buffer)) != -1) {
			output.write(buffer, 0, bytes);
		}
		inputStream.close();
		output.close();
	}

	private String getResponse(InputStream inputStream) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream, "utf-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}
