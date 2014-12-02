package com.didihe1988.picker.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.model.Answer;
import com.didihe1988.picker.model.dp.AnswerDp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnswerDaoTest {
	@Autowired
	private AnswerDao answerDao;
	
	/*
	 * add answer that not exists
	 */
	@Test
	public void test01() {
		Answer answer = new Answer(3, 3, "Answer Content", "Answer brief");
		int status = answerDao.addModel(answer);
		assertSame(1, status);
	}
	
	/*
	 * add answer that already exists
	 */
	@Test
	public void test02() {
		Answer answer = new Answer(3, 3, "Answer Content", "Answer brief");
		int status = answerDao.addModel(answer);
		assertSame(-1, status);
	}

	// test queryLatestAnswerIdByQuestionId
	@Test
	public void test03() {
		int num = answerDao.queryLatestAnswerIdByQuestionId(2);
		assertSame(true, num>0);
	}

	/*
	 * test queryAnswerDp
	 */
	@Test
	public void test04() {
		List<AnswerDp> list = answerDao.queryAnswerDpByQuestionId(2);
		assertNotNull(list);
		for (AnswerDp answerDp : list) {
			System.out.println(answerDp);
		}
	}
}
