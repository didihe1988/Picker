package com.didihe1988.picker.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Section;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.BoughtService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.utils.JsonUtils;
import com.google.gson.Gson;

@RestController
public class RestBookController {
	@Autowired
	private BookService bookService;

	@Autowired
	private BoughtService boughtService;

	@Autowired
	private FeedService feedService;

	/**
	 * @description 本书的详细内容
	 */
	@RequestMapping(value = "/json/book/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getBook(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		Book book = bookService.getBookById(id);
		return JsonUtils.getJsonObjectStringFromModel(Constant.KEY_BOOK, book);
	}

	/**
	 * @description 本书下提出的问题
	 */
	@RequestMapping(value = "/json/book/{id}/questions", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getQuestions(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		System.out.println(id);
		List<Feed> list = feedService.getFeedListByBookId(id,
				Feed.TYPE_QUESTION);
		System.out.println(list);
		return JsonUtils.getJsonObjectString(Constant.KEY_QUESTION_LIST, list);
	}

	/**
	 * @description 本书下写的笔记
	 */
	@RequestMapping(value = "/json/book/{id}/notes", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getNotes(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<Feed> list = feedService.getFeedListByBookId(id, Feed.TYPE_NOTE);
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE_LIST, list);
	}

	/*
	 * 测试一下递归的Section List
	 */
	@RequestMapping(value = "/json/test/sections", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getSections() {
		List<Section> subSections1 = new ArrayList<Section>();
		Section sub1 = new Section("1.1", "sub1", 1);
		Section sub2 = new Section("1.2", "sub2", 9);
		Section sub3 = new Section("1.3", "sub3", 16);
		subSections1.add(sub1);
		subSections1.add(sub2);
		subSections1.add(sub3);

		List<Section> subSections2 = new ArrayList<Section>();
		Section sub4 = new Section("2.1", "sub4", 17);
		Section sub5 = new Section("2.2", "sub5", 20);
		Section sub6 = new Section("2.3", "sub6", 24);
		Section sub7 = new Section("2.4", "sub7", 29);
		subSections2.add(sub4);
		subSections2.add(sub5);
		subSections2.add(sub6);
		subSections2.add(sub7);

		Section section = new Section("1", "sec", 1, subSections1);
		Section section2 = new Section("2", "sec2", 17, subSections2);
		List<Section> sectionList = new ArrayList<Section>();
		sectionList.add(section);
		sectionList.add(section2);

		return JsonUtils.getJsonObjectString(Constant.KEY_SECTION_LIST,
				sectionList);

	}
}
