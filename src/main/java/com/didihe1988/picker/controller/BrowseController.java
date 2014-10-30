package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.dp.FeedDp;
import com.didihe1988.picker.model.json.BookJson;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;
import com.google.gson.Gson;

@Controller
public class BrowseController {
	@Autowired
	private UserService userService;

	@Autowired
	private FeedService feedService;

	@Autowired
	private BookService bookService;

	/*
	 * id -bookId
	 */
	@RequestMapping(value = "/browse/{id}/{page}")
	public String browse(@PathVariable int id, @PathVariable int page,
			Model model, HttpServletRequest request) {
		if ((id < 1) || (page < 0)) {
			return "error";
		}
		if (bookService.isBookExistsById(id)) {
			Book book = bookService.getBookById(id);
			List<FeedDp> feedList = feedService.getFeedDpListForBrowse(id,
					HttpUtils.getSessionUserId(request));
			model.addAttribute("book", book);
			model.addAttribute("page", page);
			model.addAttribute("feedList", feedList);
			return "browse";
		} else {
			return "error";
		}
	}

	/*
	 * "status": "success", "end": "false", "page": 0, "book": {
	 * "cover_image_path": "/static/images/books/7.jpg", "author": "[Ӣ] ëķ",
	 * "publisher": "�Ϻ����ĳ�����", "date": "2007-3", "brief":
	 * "<p>�������ļ�������ëķ�Ĵ��������������Ե��Դ�ɫ�ʡ�</p><p>С˵���˹������ա��������׸�ĸ˫��������������м�������Į��İ���Ļ����жȹ���ͯ�꣬�Ը���˹�Ƨ�����С��ڼ���ѧУ�ȹ����������������˲������ѧУ�ƶȵĴݲУ������������������ڰ����Ͼ�������ʹ���ڿ�����������·�ϣ���ÿ��һ������Ҫ������������������˼��͸��Զ�������Ŀ��һֱŬ�������ڽ̺�С������ʶ�����������Լ��������������ͼ�ڻ�����ŵ����������У�Ѱ�����������С�</p>"
	 * }
	 */
	// http://localhost:8090/page/123/0?filter=all&direction=up
	@RequestMapping(value = "/page/{id}/0", produces = "application/json")
	public @ResponseBody String bookDetail(@PathVariable int id) {
		if (id < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		Book book = bookService.getBookById(id);
		Gson gson = new Gson();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("page", 0);
		jsonObject.put("end", "false");
		jsonObject.put("status", "success");
		jsonObject.put("book", gson.toJson(getBookJson(book)));
		return jsonObject.toString();
	}

	private BookJson getBookJson(Book book) {
		return new BookJson(book.getBookName(), book.getImageUrl(),
				book.getWriter(), book.getPress(), book.getDate(), "�����û��");
	}

}
