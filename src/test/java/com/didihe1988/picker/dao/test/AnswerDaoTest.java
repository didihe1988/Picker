package com.didihe1988.picker.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.dao.AnswerDao;
import com.didihe1988.picker.model.Answer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnswerDaoTest {
	@Autowired
	private AnswerDao answerDao;

	@Test
	public void test01() {
		Answer answer = new Answer(2, 3, "lalala");
		int status = answerDao.addAnswer(answer);
		assertSame(1, status);
	}

	// test queryLatestAnswerIdByQuestionId
	@Test
	public void test02() {
		int num=answerDao.queryLatestAnswerIdByQuestionId(2);
		assertSame(3, num);
	}
}
