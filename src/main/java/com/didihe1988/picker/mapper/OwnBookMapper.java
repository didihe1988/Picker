package com.didihe1988.picker.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.didihe1988.picker.common.Status;

@Repository
public class OwnBookMapper {
	private static List<Map<Integer, Integer>> ownBookList;

	public OwnBookMapper() {
		if (ownBookList == null) {
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
	}

	public int add(int bookId, int userId) {
		System.out.println(bookId + "-" + userId);
		List<Integer> userBookList=queryBookByUserId(userId);
		for(int i=0;i<userBookList.size();i++)
		{
			if(bookId==userBookList.get(i))
			{
				//数据库中查询到bookId
				return 1;
			}
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(userId, bookId);
		ownBookList.add(map);
		//数据库中原来没有bookId
		return 0;
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
