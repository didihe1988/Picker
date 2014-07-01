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

import com.didihe1988.picker.dao.BoughtDao;
import com.didihe1988.picker.model.Bought;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoughtDaoTest {
	@Autowired
	BoughtDao boughtDao;

	// TestAddBought
	@Test
	public void test01() {
		Bought bought = new Bought(1, 5);
		int status = boughtDao.addBought(bought);
		assertSame(1, status);
	}

	// TestQueryBoughtByUserId
	@Test
	public void test02() {
		List<Bought> list = boughtDao.queryBoughtByUserId(1);
		assertNotNull(list);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).toString());
			}
		}
	}

	// TestqueryBoughtByUserIdAndBookId
	@Test
	public void test03() {
		Bought bought = boughtDao.queryBoughtByUserIdAndBookId(1, 4);
		assertNotNull(bought);
	}

	// TestqueryBoughtByUserIdAndBookId bought not exists
	@Test
	public void test04() {
		Bought bought = boughtDao.queryBoughtByUserIdAndBookId(2, 4);
		assertSame(null, bought);
	}

	// TestIsBoughtExists
	@Test
	public void test05() {
		Bought bought = new Bought(1, 4);
		boolean isBoughtExists = boughtDao.isBoughtExists(bought);
		assertSame(true, isBoughtExists);
	}

	// TestIsBoughtExists bought not exists
	@Test
	public void test06() {
		Bought bought = new Bought(2, 4);
		boolean isBoughtExists = boughtDao.isBoughtExists(bought);
		assertSame(false, isBoughtExists);
	}
}
