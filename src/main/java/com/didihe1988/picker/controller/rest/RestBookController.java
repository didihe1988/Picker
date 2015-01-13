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
	 * @description �������ϸ����
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
	 * @description ���������������
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
	 * @description ������д�ıʼ�
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
	 * ����һ�µݹ��Section List
	 */
	/*
	@RequestMapping(value = "/json/test/sections", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getSectionsTest() {
		int bookId = 1035;
		List<Section> subSections1 = new ArrayList<Section>();
		Section sub1 = new Section(bookId, "��һ��", "�������", 1, 8);
		Section sub2 = new Section(bookId, "�ڶ���", "�̳�", 9, 14);
		Section sub3 = new Section(bookId, "������", "����Ĵ�����������", 15, 10);
		subSections1.add(sub1);
		subSections1.add(sub2);
		subSections1.add(sub3);

		List<Section> subSections2 = new ArrayList<Section>();
		Section sub4 = new Section(bookId, "��һ��", "�����ò��ݶ���", 21, 25);
		Section sub5 = new Section(bookId, "�ڶ���", "�������㴴�����ж���", 26, 30);
		Section sub6 = new Section(bookId, "������", "��Զ����Ҫ���ٶ���", 31, 33);
		Section sub7 = new Section(bookId, "���Ľ�", "��ĵ�һ��java����", 34, 37);
		subSections2.add(sub4);
		subSections2.add(sub5);
		subSections2.add(sub6);
		subSections2.add(sub7);

		List<Section> subSections3 = new ArrayList<Section>();
		Section sub8 = new Section(bookId, "��һ��", "ʹ��java������", 38, 45);
		Section sub9 = new Section(bookId, "�ڶ���", "�����Ĵ���", 46, 52);
		Section sub10 = new Section(bookId, "������", "������С��", 53, 63);
		subSections3.add(sub8);
		subSections3.add(sub9);
		subSections3.add(sub10);

		List<Section> subSections4 = new ArrayList<Section>();
		Section sub11 = new Section(bookId, "��һ��", "if-else", 64, 67);
		Section sub12 = new Section(bookId, "�ڶ���", "����", 68, 72);
		Section sub13 = new Section(bookId, "������", "foreach", 73, 75);
		subSections4.add(sub11);
		subSections4.add(sub12);
		subSections4.add(sub13);

		List<Section> subSections5 = new ArrayList<Section>();
		Section sub14 = new Section(bookId, "��һ��", "this�ؼ���", 76, 83);
		Section sub15 = new Section(bookId, "�ڶ���", "����:��������", 84, 92);
		Section sub16 = new Section(bookId, "������", "��Ա��ʼ��", 93, 100);
		Section sub17 = new Section(bookId, "���Ľ�", "�ܽ�", 101, 107);
		subSections5.add(sub14);
		subSections5.add(sub15);
		subSections5.add(sub16);
		subSections5.add(sub17);

		List<Section> subSections6 = new ArrayList<Section>();
		Section sub18 = new Section(bookId, "��һ��", "��:�ⵥԪ", 108, 112);
		Section sub19 = new Section(bookId, "�ڶ���", "����Ȩ�����δ�", 113, 117);
		Section sub20 = new Section(bookId, "������", "�ӿں�ʵ��", 118, 123);
		subSections6.add(sub18);
		subSections6.add(sub19);
		subSections6.add(sub20);

		List<Section> subSections7 = new ArrayList<Section>();
		Section sub21 = new Section(bookId, "��һ��", "��ϼ̳��﷨", 124, 132);
		Section sub22 = new Section(bookId, "�ڶ���", "protected�ؼ���", 133, 140);
		Section sub23 = new Section(bookId, "������", "final�ؼ���", 141, 147);
		subSections7.add(sub21);
		subSections7.add(sub22);
		subSections7.add(sub23);

		Section section = new Section(bookId, "��һ��", "������", 1, 20,
				subSections1);
		Section section2 = new Section(bookId, "�ڶ���", "һ�ж��Ƕ���", 21, 37,
				subSections2);
		Section section3 = new Section(bookId, "������", "������", 38, 63,
				subSections3);
		Section section4 = new Section(bookId, "������", "����ִ������", 64, 75,
				subSections4);
		Section section5 = new Section(bookId, "������", "��ʼ��������", 76, 107,
				subSections5);
		Section section6 = new Section(bookId, "������", "����Ȩ�޿���", 108, 123,
				subSections6);
		Section section7 = new Section(bookId, "������", "������", 124, 147,
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
