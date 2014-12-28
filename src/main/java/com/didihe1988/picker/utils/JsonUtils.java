package com.didihe1988.picker.utils;

import java.util.List;

import org.json.JSONObject;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {
	private JsonUtils() {

	}

	public static String getJsonObjectString(String key, Object value) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);
		return jsonObject.toString();
	}

	public static String getJsonObjectString(Entity... entities) {
		JSONObject jsonObject = new JSONObject();
		for (Entity entity : entities) {
			jsonObject.put(entity.getKey(), entity.getValue());
		}
		return jsonObject.toString();
	}

	public static String getJsonObjectStringFromModel(String key, Object value) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.m")
				.create();
		String jsonString = gson.toJson(value);
		return getJsonObjectString(key, jsonString);
	}

	public static List<Integer> getIntegerList(String listString) {
		Gson gson = new Gson();
		List<Integer> list = gson.fromJson(listString,
				new TypeToken<List<Integer>>() {
				}.getType());
		return list;
	}

	public static List<String> getStringList(String listString) {
		Gson gson = new Gson();
		List<String> list = gson.fromJson(listString,
				new TypeToken<List<String>>() {
				}.getType());
		return list;
	}
	
	public static String invalidStatus()
	{
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
				Status.INVALID);
	}
}
