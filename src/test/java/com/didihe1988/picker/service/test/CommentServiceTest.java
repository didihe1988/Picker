package com.didihe1988.picker.service.test;

import static org.junit.Assert.assertSame;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Comment;
import com.didihe1988.picker.service.CommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentServiceTest {
	@Autowired
	private CommentService commentService;

	/*
	 * commentNum++
	 */
	@Test
	public void test0() {
		Comment comment = new Comment(1, 2, "Comment Question",
				Comment.COMMENT_QUESTION);
		int status = commentService.addComment(comment);
		assertSame(Status.SUCCESS, status);
	}

	@Test
	public void test1() {
		Comment comment = new Comment(1, 2, "Comment Answer",
				Comment.COMMENT_ANSWER);
		int status = commentService.addComment(comment);
		assertSame(Status.SUCCESS, status);
	}
}
