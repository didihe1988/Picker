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
import com.didihe1988.picker.service.SectionService;
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

	@Autowired
	private SectionService sectionService;

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

	@RequestMapping(value = "/json/book/{id}/sections", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getSections(@PathVariable int id) {
		if (id < 1) {
			return Constant.STATUS_INVALID;
		}
		List<Section> list = sectionService.getSectionsByBookId(id);
		return JsonUtils.getJsonObjectString(Constant.KEY_SECTION_LIST, list);
	}

	/*
	 * 测试一下递归的Section List
	 */
	/*
	@RequestMapping(value = "/json/test/sections", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getSectionsTest() {
		int bookId = 1035;
		List<Section> subSections1 = new ArrayList<Section>();
		Section sub1 = new Section(bookId, "第一节", "抽象过程", 1, 8);
		Section sub2 = new Section(bookId, "第二节", "继承", 9, 14);
		Section sub3 = new Section(bookId, "第三节", "对象的创建和生命期", 15, 10);
		subSections1.add(sub1);
		subSections1.add(sub2);
		subSections1.add(sub3);

		List<Section> subSections2 = new ArrayList<Section>();
		Section sub4 = new Section(bookId, "第一节", "用引用操纵对象", 21, 25);
		Section sub5 = new Section(bookId, "第二节", "必须由你创建所有对象", 26, 30);
		Section sub6 = new Section(bookId, "第三节", "永远不需要销毁对象", 31, 33);
		Section sub7 = new Section(bookId, "第四节", "你的第一个java程序", 34, 37);
		subSections2.add(sub4);
		subSections2.add(sub5);
		subSections2.add(sub6);
		subSections2.add(sub7);

		List<Section> subSections3 = new ArrayList<Section>();
		Section sub8 = new Section(bookId, "第一节", "使用java操作符", 38, 45);
		Section sub9 = new Section(bookId, "第二节", "常犯的错误", 46, 52);
		Section sub10 = new Section(bookId, "第三节", "操作符小结", 53, 63);
		subSections3.add(sub8);
		subSections3.add(sub9);
		subSections3.add(sub10);

		List<Section> subSections4 = new ArrayList<Section>();
		Section sub11 = new Section(bookId, "第一节", "if-else", 64, 67);
		Section sub12 = new Section(bookId, "第二节", "迭代", 68, 72);
		Section sub13 = new Section(bookId, "第三节", "foreach", 73, 75);
		subSections4.add(sub11);
		subSections4.add(sub12);
		subSections4.add(sub13);

		List<Section> subSections5 = new ArrayList<Section>();
		Section sub14 = new Section(bookId, "第一节", "this关键字", 76, 83);
		Section sub15 = new Section(bookId, "第二节", "清理:垃圾回收", 84, 92);
		Section sub16 = new Section(bookId, "第三节", "成员初始化", 93, 100);
		Section sub17 = new Section(bookId, "第四节", "总结", 101, 107);
		subSections5.add(sub14);
		subSections5.add(sub15);
		subSections5.add(sub16);
		subSections5.add(sub17);

		List<Section> subSections6 = new ArrayList<Section>();
		Section sub18 = new Section(bookId, "第一节", "包:库单元", 108, 112);
		Section sub19 = new Section(bookId, "第二节", "访问权限修饰词", 113, 117);
		Section sub20 = new Section(bookId, "第三节", "接口和实现", 118, 123);
		subSections6.add(sub18);
		subSections6.add(sub19);
		subSections6.add(sub20);

		List<Section> subSections7 = new ArrayList<Section>();
		Section sub21 = new Section(bookId, "第一节", "组合继承语法", 124, 132);
		Section sub22 = new Section(bookId, "第二节", "protected关键字", 133, 140);
		Section sub23 = new Section(bookId, "第三节", "final关键字", 141, 147);
		subSections7.add(sub21);
		subSections7.add(sub22);
		subSections7.add(sub23);

		Section section = new Section(bookId, "第一章", "对象导论", 1, 20,
				subSections1);
		Section section2 = new Section(bookId, "第二章", "一切都是对象", 21, 37,
				subSections2);
		Section section3 = new Section(bookId, "第三章", "操作符", 38, 63,
				subSections3);
		Section section4 = new Section(bookId, "第四章", "控制执行流程", 64, 75,
				subSections4);
		Section section5 = new Section(bookId, "第五章", "初始化与清理", 76, 107,
				subSections5);
		Section section6 = new Section(bookId, "第六章", "访问权限控制", 108, 123,
				subSections6);
		Section section7 = new Section(bookId, "第七章", "复用类", 124, 147,
				subSections7);

		List<Section> sectionList = new ArrayList<Section>();
		sectionList.add(section);
		sectionList.add(section2);
		sectionList.add(section3);
		sectionList.add(section4);
		sectionList.add(section5);
		sectionList.add(section6);
		sectionList.add(section7);
		return JsonUtils.getJsonObjectString(Constant.KEY_SECTION_LIST,
				sectionList);
	}*/
}
