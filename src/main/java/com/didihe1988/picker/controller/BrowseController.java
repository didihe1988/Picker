package com.didihe1988.picker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Duck;
import com.didihe1988.picker.model.dp.FeedDp;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

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
	
	@RequestMapping(value = "/json/test", method = RequestMethod.POST, headers = "Accept=*")
	public String test(@RequestParam("name") String name,@RequestParam("age") int age,@RequestParam("weight") int weight ) {
		System.out.println("post test");
		System.out.println(new Duck(name, age, weight).toString());
		return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,Status.SUCCESS);
	}
}
