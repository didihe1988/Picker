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
	 * "cover_image_path": "/static/images/books/7.jpg", "author": "[英] 毛姆",
	 * "publisher": "上海译文出版社", "date": "2007-3", "brief":
	 * "<p>《人生的枷锁》是毛姆的代表作，具有明显的自传色彩。</p><p>小说主人公菲利普·凯里自幼父母双亡，不幸又先天残疾，在冷漠而陌生的环境中度过了童年，性格因此孤僻而敏感。在寄宿学校度过的岁月让他饱受了不合理的学校制度的摧残，而当他走入社会后，又在爱情上经历了伤痛。在坎坷的人生道路上，他每跨一步，都要付出艰辛的挣扎，但思想和个性都独立不羁的凯里，一直努力挣脱宗教和小市民意识这两条禁锢自己精神的桎梏，力图在混沌纷扰的生活漩流中，寻求人生的真谛。</p>"
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
				book.getWriter(), book.getPress(), book.getDate(), "这个真没有");
	}

}
