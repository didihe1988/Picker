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
import com.didihe1988.picker.model.Book;
import com.didihe1988.picker.model.ChapterRange;
import com.didihe1988.picker.model.Circle;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Section;
import com.didihe1988.picker.model.User;
import com.didihe1988.picker.model.display.SearchResult;
import com.didihe1988.picker.model.interfaces.SearchModel;
import com.didihe1988.picker.model.json.CircleJson;
import com.didihe1988.picker.service.AnswerService;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.CircleService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.SectionService;
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
	private SectionService sectionService;

	@Autowired
	private CircleService circleService;

	private SearchModelListGenerator generator = new SearchModelListGenerator();

	@RequestMapping(value = "/json/search/user/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchUser(@PathVariable String username,
			HttpServletRequest request) {
		if (username.equals("")) {
			return Constant.STATUS_INVALID;
		}
		username = StringUtils.toUTF8(username);
		List<User> userList = userService.search(username, false);
		List<SearchResult> list = generator.toSearchResults(userList);
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/question/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchQuestion(@PathVariable String string,
			HttpServletRequest request) {
		if (string.equals("")) {
			return Constant.STATUS_INVALID;
		}
		string = StringUtils.toUTF8(string);
		List<Feed> questoinList = feedService.search(string,
				Feed.TYPE_QUESTION, false);
		List<SearchResult> list = generator.toSearchResults(questoinList);
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/note/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchNote(@PathVariable String string,
			HttpServletRequest request) {
		if (string.equals("")) {
			return Constant.STATUS_INVALID;
		}
		string = StringUtils.toUTF8(string);
		List<Feed> noteList = feedService.search(string, Feed.TYPE_NOTE, false);
		List<SearchResult> list = generator.toSearchResults(noteList);
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/book/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchBook(@PathVariable String string,
			HttpServletRequest request) {
		if (string.equals("")) {
			return Constant.STATUS_INVALID;
		}
		string = StringUtils.toUTF8(string);
		System.out.println(string);
		List<Book> bookList = bookService.search(string, false);
		List<SearchResult> list = generator.toSearchResults(bookList);
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	@RequestMapping(value = "/json/search/circle/{string}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String searchCircle(@PathVariable String string,
			HttpServletRequest request) {
		if (string.equals("")) {
			return Constant.STATUS_INVALID;
		}
		string = StringUtils.toUTF8(string);
		List<Circle> circleList = circleService.search(string, false);
		List<SearchResult> list = generator.toSearchResults(circleList);
		return JsonUtils.getJsonObjectString(Constant.KEY_SEARCHRESULT_LIST,
				list);
	}

	// 用于前端圈子的搜索
	@RequestMapping(value = "/group_search", produces = "application/json", headers = "Accept=application/json")
	public String groupSearch(HttpServletRequest request) {
		// 如果为""怎么办
		String groupName = (String) request.getParameter("group_name");
		groupName = StringUtils.toUTF8(groupName);
		List<Circle> circleList = circleService.search(groupName, false);

		List<CircleJson> list = new ArrayList<CircleJson>();
		for (Circle circle : circleList) {
			list.add(circle.toCircleJson());
		}
		return JsonUtils.getJsonObjectString("groups", list);
		
		
	}

	@RequestMapping(value = "/json/search/book/{bookId}/{page}")
	public String pageSearch(@PathVariable int bookId, @PathVariable int page,HttpServletRequest request) {
		if ((bookId < 1) || (page < 0)) {
			return Constant.STATUS_INVALID;
		}
		// checkPageValidation
		Book book = bookService.getBookById(bookId);
		if ((book == null) || (book.getPages() < page)) {
			return Constant.STATUS_INVALID;
		}
		int secType=Section.CHAPTER;
		String type = (String) request.getParameter("type");
		if ((type != null) && (type.equals("section"))) {
			secType=Section.SECTION;
		}
		// get ChapterRange
		ChapterRange chapterRange = sectionService.getChapterRangeByPage(book,
				secType, page);
		List<Feed> feedList = feedService.getFeedListByChapterRange(bookId,
				chapterRange);
		List<SearchResult> list = generator.toSearchResults(feedList);
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
