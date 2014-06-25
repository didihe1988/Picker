package com.didihe1988.picker.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnBookMapper {
	private static List<Map<Integer, Integer>> ownBookList;

	public OwnBookMapper() {
		ownBookList = new ArrayList<Map<Integer, Integer>>();
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		map1.put(0, 0);
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		map2.put(0, 1);
		Map<Integer, Integer> map3 = new HashMap<Integer, Integer>();
		map3.put(0, 2);
		ownBookList.add(map1);
		ownBookList.add(map2);
		ownBookList.add(map3);
	}

	public void add(int bookId, int userId) {
		System.out.println(bookId + "-" + userId);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(userId, bookId);
		ownBookList.add(map);
	}

	public void delete(int bookId, int userId) {
		System.out.println(bookId + "-" + userId);

	}

	public List<Integer> queryBookByUserId(int userId) {
		List<Integer> bookIdList = new ArrayList<Integer>();
		for (int i = 0; i < ownBookList.size(); i++) {
			Map<Integer, Integer> map = ownBookList.get(i);
			bookIdList.add((Integer) map.get(0));
		}
		return bookIdList;
	}

}
