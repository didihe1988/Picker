package com.didihe1988.picker.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import javax.servlet.http.Cookie;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.dao.CommentDao;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.model.display.CommentDp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentDaoTest {
	@Autowired
	private CommentDao commentDao;

	// testAddComment
	/**
	 * 
	 */
	@Test
	public void test01() {
		Comment comment = new Comment(1, 1, "henhao", Comment.COMMENT_ANSWER);
		int status = commentDao.addModel(comment);
		assertSame(1, status);
	}

	// testQueryCommentById
	@Test
	public void test02() {
		Comment comment = commentDao.queryModelById(1);
		assertNotNull(comment);
		System.out.println(comment.toString());
	}

	// testIsCommentExists
	@Test
	public void test03() {
		Comment comment = commentDao.queryModelById(1);
		boolean isCommentExists = commentDao.isCommentExists(comment);
		assertSame(true, isCommentExists);
	}

	// testIsCommentExists comment not exists
	@Test
	public void test04() {
		Comment comment = commentDao.queryModelById(20);
		boolean isCommentExists = commentDao.isCommentExists(comment);
		assertSame(false, isCommentExists);
	}

	// testUpdateComment
	@Test
	public void test05() {
		Comment comment = commentDao.queryModelById(4);
		comment.setContent("lalala");
		int status = commentDao.updateModel(comment);
		assertSame(1, status);
	}

	// testDeleteComment
	@Test
	public void test07() {
		Comment comment = commentDao.queryModelById(4);
		int status = commentDao.deleteComment(comment);
		assertSame(1, status);
	}

	// testQueryCommentByBookId
	@Test
	public void test08() {
		List<Comment> list = commentDao.queryCommentByBookId(1);
		assertNotNull(list);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(0).toString());
			}
		}
	}

	@Test
	public void test09()
	{
		List<CommentDp> list=commentDao.queryCommentDpListByCommentedId(1, 0);
		assertNotNull(list);
	}
}
