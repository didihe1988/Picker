package com.didihe1988.picker.controller.rest;

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
import com.didihe1988.picker.model.AttachmentFeed;
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.dp.SearchResult;
import com.didihe1988.picker.model.interfaces.SearchModel;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.AttachmentFeedService;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.JsonUtils;
import com.didihe1988.picker.utils.StringUtils;

@RestController
public class RestSearchController {
	/*
	 * SearchService
	 */
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

	@Autowired
	private AttachmentFeedService aFeedService;

	private SearchModelListGenerator generator = new SearchModelListGenerator();

	@RequestMapping(value = "/json/search/user/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchUser(@PathVariable String username,
			HttpServletRequest request) {
		if (username.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		username = StringUtils.toUTF8(username);
		List<User> userList = userService.search(username);
		List<SearchResult> list = generator.toSearchResults(userList);
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/question/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchQuestion(@PathVariable String string,
			HttpServletRequest request) {
		if (string.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		string = StringUtils.toUTF8(string);
		List<Feed> questoinList = feedService
				.search(string, Feed.TYPE_QUESTION);
		List<SearchResult> list = generator.toSearchResults(questoinList);
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/note/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchNote(@PathVariable String string,
			HttpServletRequest request) {
		if (string.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		string = StringUtils.toUTF8(string);
		List<Feed> noteList = feedService.search(string, Feed.TYPE_NOTE);
		List<SearchResult> list = generator.toSearchResults(noteList);
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/book/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchBook(@PathVariable String string,
			HttpServletRequest request) {
		if (string.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		string = StringUtils.toUTF8(string);
		System.out.println(string);
		List<Book> bookList = bookService.search(string);
		List<SearchResult> list = generator.toSearchResults(bookList);
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/circle/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchCircle(@PathVariable String string,
			HttpServletRequest request) {
		if (string.equals("")) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		string = StringUtils.toUTF8(string);
		List<Circle> circleList = circleService.search(string);
		List<SearchResult> list = generator.toSearchResults(circleList);
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	/*
	 * @RequestMapping(value = "/group_search", produces = "application/json",
	 * headers = "Accept=application/json") public String
	 * groupSearch(HttpServletRequest request) {
	 * 
	 * // 如果为""怎么办
	 * 
	 * String groupName = (String) request.getParameter("group_name");
	 * groupName=toUTF8(groupName); List<Circle> circleList =
	 * circleService.search(groupName);
	 * 
	 * List<CircleJson> list = new ArrayList<CircleJson>(); for (Circle circle :
	 * circleList) { list.add(circle.toCircleJson()); } return
	 * JsonUtils.getJsonObjectString("groups", list); }
	 */

	@RequestMapping(value = "/json/search/book/{bookId}/{page}")
	public String pageSearch(@PathVariable int bookId, @PathVariable int page) {
		if ((bookId < 1) || (page < 0)) {
			return JsonUtils.getJsonObjectString(Constant.KEY_STATUS,
					Status.INVALID);
		}
		List<AttachmentFeed> aFeedList = aFeedService
				.getAttachmentFeedListByPage(bookId, page);
		List<Feed> feedList = feedService.getFeedListByPage(bookId, page);
		List<SearchResult> list = generator.toSearchResults(aFeedList);
		list = generator.addSearchResults(list, feedList);
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	private class SearchModelListGenerator {

		public List<SearchResult> toSearchResults(
				List<? extends SearchModel> rawList) {
			List<SearchResult> results = new ArrayList<SearchResult>(
					rawList.size());
			for (SearchModel model : rawList) {
				results.add(model.toSearchResult());
			}
			return results;
		}

		public List<SearchResult> addSearchResults(List<SearchResult> results,
				List<? extends SearchModel> rawList) {
			if (results == null) {
				results = new ArrayList<SearchResult>();
			}
			results.addAll(toSearchResults(rawList));
			return results;
		}
	}

}
