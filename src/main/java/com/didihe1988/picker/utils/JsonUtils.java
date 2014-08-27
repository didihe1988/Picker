package com.didihe1988.picker.utils;

import org.json.JSONObject;

public class JsonUtils {
	private JsonUtils() {

	}

	public static final String getJsonObjectString(String key, Object value) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);
		return jsonObject.toString();
	}
}
