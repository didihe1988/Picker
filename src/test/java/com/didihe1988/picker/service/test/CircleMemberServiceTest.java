package com.didihe1988.picker.service.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.model.CircleMember;
import com.didihe1988.picker.service.CircleMemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CircleMemberServiceTest {
	@Autowired
	private CircleMemberService circleMemberService;

	@Test
	public void test01() {
		CircleMember circleMember = new CircleMember(1, 1);
		circleMemberService.addCircleMember(circleMember);
	}
}
