package com.didihe1988.downloader.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class Executor {
	private String url;

	public Executor(String url) {
		this.url = url;
	}

	public Object execute() {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(this.url);
			System.out.println(url.toString());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setReadTimeout(8000);
			connection.setConnectTimeout(8000);
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

	private String getResponse(InputStream inputStream) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream,"utf-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}
