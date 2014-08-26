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
import com.didihe1988.picker.model.Question;
import com.didihe1988.picker.service.QuestionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuestionServiceTest {
	@Autowired
	private QuestionService questionService;

	@Test
	public void test0() {
		Question question = new Question(1, 2, "fefef", "fhefheiufhef");
		int status = questionService.addQuestion(question);
		assertSame(Status.SUCCESS, status);
	}
}
