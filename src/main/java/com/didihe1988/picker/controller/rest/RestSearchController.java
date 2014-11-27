package com.didihe1988.picker.controller.rest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.SearchResult;
import com.didihe1988.picker.model.json.CircleJson;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;
import com.didihe1988.picker.utils.JsonUtils;

@RestController
public class RestSearchController {
	@Autowired
	private UserService userService;

	@Autowired
	private FeedService feedService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private BookService bookService;

	@Autowired
	private CircleService circleService;

	@RequestMapping(value = "/json/search/user/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchUser(@PathVariable String username,
			HttpServletRequest request) {
		System.out.println(username);
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (username.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		username=toUTF8(username);
		List<User> userList = userService.search(username);
		List<SearchResult> list = new ArrayList<SearchResult>();
		for (User user : userList) {
			SearchResult result = user.toSearchResult();
			list.add(result);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/questoin/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchQuestion(@PathVariable String string,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (string.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		string=toUTF8(string);
		List<Feed> questoinList = feedService
				.search(string, Feed.TYPE_QUESTION);
		List<SearchResult> list = new ArrayList<SearchResult>();
		for (Feed feed : questoinList) {
			SearchResult result = feed.toSearchResult();
			list.add(result);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/note/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchNote(@PathVariable String string,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (string.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		string=toUTF8(string);
		List<Feed> noteList = feedService.search(string, Feed.TYPE_NOTE);
		List<SearchResult> list = new ArrayList<SearchResult>();
		for (Feed feed : noteList) {
			SearchResult result = feed.toSearchResult();
			list.add(result);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/book/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchBook(@PathVariable String string,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (string.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		string=toUTF8(string);
		System.out.println(string);
		List<Book> bookList = bookService.search(string);
		List<SearchResult> list = new ArrayList<SearchResult>();
		for (Book book : bookList) {
			SearchResult result = book.toSearchResult();
			list.add(result);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/circle/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchCircle(@PathVariable String string,
			HttpServletRequest request) {
		if (!HttpUtils.isSessionUserIdExists(request)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.NULLSESSION);
		}
		if (string.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		string=toUTF8(string);
		List<Circle> circleList = circleService.search(string);
		List<SearchResult> list = new ArrayList<SearchResult>();
		for (Circle circle : circleList) {
			SearchResult result = circle.toSearchResult();
			list.add(result);
		}
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/group_search", produces = "application/json", headers = "Accept=application/json")
	public String groupSearch(HttpServletRequest request) {

		/*
		 * 如果为""怎么办
		 */
		String groupName = (String) request.getParameter("group_name");
		groupName=toUTF8(groupName);
		List<Circle> circleList = circleService.search(groupName);

		List<CircleJson> list = new ArrayList<CircleJson>();
		for (Circle circle : circleList) {
			list.add(circle.toCircleJson());
		}
		return JsonUtils.getJsonObjectString("groups", list);
	}
	
	private String toUTF8(String rawString)
	{
		try {
			rawString =new String(rawString.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rawString;
	}

}
