package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.type.SetType;
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
import com.didihe1988.picker.model.display.FeedDp;
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
			String type = (String) request.getParameter("type");
			if ((type == null) || (type.equals("")) || (!isTypeValid(type))) {
				type = "all";
			}
			model.addAttribute("type", type);
			return "browse";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/browse/{bookId}/feeds/{page}")
	public String getFeeds(@PathVariable int bookId, @PathVariable int page,
			Model model, HttpServletRequest request) {
		if ((bookId < 1) || (page < 0)) {
			return "error";
		}
		if (bookService.isBookExistsById(bookId)) {
			addCommonModel(model, bookId, page);
			model.addAttribute("type","feed");
			return "browse";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/browse/{bookId}/questions/{page}")
	public String getQuestions(@PathVariable int bookId,
			@PathVariable int page, Model model, HttpServletRequest request) {
		if ((bookId < 1) || (page < 0)) {
			return "error";
		}
		if (bookService.isBookExistsById(bookId)) {
			addCommonModel(model, bookId, page);
			model.addAttribute("type","question");
			return "browse";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/browse/{bookId}/notes/{page}")
	public String getNotes(@PathVariable int bookId, @PathVariable int page,
			Model model, HttpServletRequest request) {
		if ((bookId < 1) || (page < 0)) {
			return "error";
		}
		if (bookService.isBookExistsById(bookId)) {
			addCommonModel(model, bookId, page);
			model.addAttribute("type","note");
			return "browse";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/browse/{bookId}/attachments/{page}")
	public String getAttachments(@PathVariable int bookId,
			@PathVariable int page, Model model, HttpServletRequest request) {
		if ((bookId < 1) || (page < 0)) {
			return "error";
		}
		if (bookService.isBookExistsById(bookId)) {
			addCommonModel(model, bookId, page);
			model.addAttribute("type","attachment");
			return "browse";
		} else {
			return "error";
		}
	}

	private void addCommonModel(Model model, int bookId, int page) {
		Book book = bookService.getBookById(bookId);
		model.addAttribute("book", book);
		model.addAttribute("page", page);
	}

	private boolean isTypeValid(String type) {
		if (type.equals("attachment") || type.equals("question")
				|| type.equals("note")) {
			return true;
		}
		return false;
	}

	// http://localhost:8090/page/123/0?filter=all&direction=up
	@RequestMapping(value = "/page/{bookId}/0", produces = "application/json")
	public @ResponseBody String bookDetail(@PathVariable int bookId) {
		if (bookId < 1) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		Book book = bookService.getBookById(bookId);

		Gson gson = new Gson();
		/*
		 * JSONObject jsonObject = new JSONObject();
		 * 
		 * jsonObject.put("page", 0); jsonObject.put("end", "false");
		 * jsonObject.put("status", "success"); jsonObject.put("book",
		 * gson.toJson(getBookJson(book))); return jsonObject.toString();
		 */
		return gson.toJson(getBookJson(book));
	}

	private BookJson getBookJson(Book book) {
		return new BookJson(
				book.getBookName(),
				book.getImageUrl(),
				book.getWriter(),
				book.getPress(),
				book.getDate(),
				"  本书赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在Bruce Eckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。\n  从本书获得的各项大奖以及来自世界各地的读者评论中，不难看出这是一本经典之作。本书的作者拥有多年教学经验，对C、C++以及Java语言都有独到、深入的见解，以通俗易懂及小而直接的示例解释了一个个晦涩抽象的概念。本书共22章，包括操作符、控制执行流程、访问权限控制、复用类、多态、接口、通过异常处理错误、字符串、泛型、数组、容器深入研究、Java I/O系统、枚举类型、并发以及图形化用户界面等内容。这些丰富的内容，包含了Java语言基础语法以及高级特性，适合各个层次的Java程序员阅读，同时也是高等院校讲授面向对象程序设计语言以及Java语言的绝佳教材和参考书。\n第4版特点：\n适合初学者与专业人员的经典的面向对象叙述方式，为更新的Java SE5/6增加了新的示例和章节。");
	}

}
