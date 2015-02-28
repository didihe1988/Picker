package com.didihe1988.picker.service.test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.display.AttachmentFeedDp;
import com.didihe1988.picker.model.display.FeedDp;
import com.didihe1988.picker.model.json.NoteJson;
import com.didihe1988.picker.service.BookService;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.UserService;
import com.didihe1988.picker.utils.HttpUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeedServiceTest {
	@Autowired
	private FeedService feedService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;

	@Test
	public void test0() {
		int bookId = 2;
		int userId = 1;
		int page = 3;
		Feed feed = new Feed(bookId, userId, "question title",
				"question content", "question brief", page, Feed.TYPE_QUESTION);
		int status = feedService.addFeed(feed);
		assertSame(Status.SUCCESS, status);
	}

	@Test
	public void test1() {
		int bookId = 2;
		int userId = 1;
		int page = 3;
		Feed feed = new Feed(bookId, userId, "note title", "note content",
				"note brief", page, Feed.TYPE_NOTE);
		int status = feedService.addFeed(feed);
		assertSame(Status.SUCCESS, status);
	}

	@Test
	public void test2() {
		List<Feed> list = feedService.getFeedListForBrowse(1);
		assertNotNull(list);
	}

	@Test
	public void test3() {
		Feed feed = feedService.getFeedById(8);
		assertNotNull(feed);
	}

	@Test
	public void test4() {
		List<NoteJson> list = feedService.getNoteJsons(1, 1);
		assertNotNull(list);
	}

	@Test
	public void test5() {
		Feed feed = feedService.getFeedById(4);
		assertSame(feed.getType(), Feed.TYPE_QUESTION);
	}

	@Test
	public void test6() {
		/*
		int id = 1;
		int userId = 1;
		List<FeedDp> list = feedService.getFeedDpListByBookId(id,
				Feed.TYPE_QUESTION, userId, DaoConstant.FeedPageOrder);
		System.out.println(list);
		assertNotNull(list);*/
	}
	
	@Test
	public void test7() {
		int bookId=1035;
		List<AttachmentFeedDp> list=feedService.getAttFeedDpsByBookId(bookId);
		System.out.println(list);
		assertNotNull(list);
	}
	
	@Test
	public void test8()
	{
		AttachmentFeedDp attFeedDp=feedService.getAttFeedDpByFeedId(23);
		assertNotNull(attFeedDp);
		System.out.println(attFeedDp);
	}
	
	
	@Test
	public void testRelatedQuestionNum()
	{
		int bookId=1;
		int userId=1;
		int bookQueNum=bookService.getBookById(bookId).getQuestionNum();
		int userQueNum=userService.getUserById(userId).getQuestionNum();
		Feed feed=new Feed(bookId, userId, "test related num","test test","test test",5, Feed.TYPE_QUESTION);
		feedService.addFeed(feed);
		assertSame(++bookQueNum, bookService.getBookById(bookId).getQuestionNum());
		assertSame(++userQueNum, userService.getUserById(userId).getQuestionNum());
	}
	
	@Test
	public void testRelatedNoteNum()
	{
		int bookId=1;
		int userId=1;
		int bookNoteNum=bookService.getBookById(bookId).getNoteNum();
		int userNoteNum=userService.getUserById(userId).getNoteNum();
		Feed feed=new Feed(bookId, userId, "test related note num","test test","test test",5, Feed.TYPE_NOTE);
		feedService.addFeed(feed);
		assertSame(++bookNoteNum, bookService.getBookById(bookId).getNoteNum());
		assertSame(++userNoteNum, userService.getUserById(userId).getNoteNum());
	}
	
	@Test
	public void testRelatedAttNum()
	{
		int bookId=1;
		int userId=1;
		int attNum=bookService.getBookById(bookId).getAttachmentNum();
		Feed feed=new Feed(bookId, userId, "test related att num","test test","test test",5, Feed.TYPE_ATTACHMENT_FEED);
		feedService.addFeed(feed);
		assertSame(++attNum, bookService.getBookById(bookId).getAttachmentNum());
	}
	
	

}
