package com.didihe1988.picker.utils;

import org.json.JSONObject;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.model.UserDp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
	private JsonUtils() {

	}

	public static final String getJsonObjectString(String key, Object value) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);
		return jsonObject.toString();
	}

	public static final String getJsonObjectStringFromModel(String key,
			Object value) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.m")
				.create();
		String jsonString = gson.toJson(value);
		return getJsonObjectString(key, jsonString);
	}
}
