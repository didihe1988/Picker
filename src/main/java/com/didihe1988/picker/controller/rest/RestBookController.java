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
			return Constant.STATUS_INVALID;
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
			return Constant.STATUS_INVALID;
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
			return Constant.STATUS_INVALID;
		}
		List<Feed> list = feedService.getFeedListByBookId(id, Feed.TYPE_NOTE);
		return JsonUtils.getJsonObjectString(Constant.KEY_NOTE_LIST, list);
	}

	/*
	 * 测试一下递归的Section List
	 */
	@RequestMapping(value = "/json/test/sections", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getSections() {
		int bookId=1;
		List<Section> subSections1 = new ArrayList<Section>();
		Section sub1 = new Section(bookId,"第一节", "内容提要", 1);
		Section sub2 = new Section(bookId,"第二节", "微积分习题选解", 2);
		Section sub3 = new Section(bookId,"第三节", "工科数学习题选解", 5);
		subSections1.add(sub1);
		subSections1.add(sub2);
		subSections1.add(sub3);

		List<Section> subSections2 = new ArrayList<Section>();
		Section sub4 = new Section(bookId,"第一节", "数列的极限", 6);
		Section sub5 = new Section(bookId,"第二节", "函数的极限", 8);
		Section sub6 = new Section(bookId,"第三节", "极限的性质与运算法则", 12);
		Section sub7 = new Section(bookId,"第四节", "无穷小及其比较", 16);
		subSections2.add(sub4);
		subSections2.add(sub5);
		subSections2.add(sub6);
		subSections2.add(sub7);

		Section section = new Section(bookId,"预备知识", "集合、映射与函数", 1, subSections1);
		Section section2 = new Section(bookId,"第一章", "极限与连续",6, subSections2);
		List<Section> sectionList = new ArrayList<Section>();
		sectionList.add(section);
		sectionList.add(section2);

		return JsonUtils.getJsonObjectString(Constant.KEY_SECTION_LIST,
				sectionList);

	}
}
