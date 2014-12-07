package com.didihe1988.picker.utils;

import org.hibernate.Query;

import com.didihe1988.picker.common.Constant;

public class DaoUtils {

	public static void setLimit(Query query, int limit) {
		// start from 0 10 20 30...
		query.setFirstResult(Constant.DEFAULT_QUERYRESULT * limit);
		// once 10 results
		query.setMaxResults(Constant.DEFAULT_QUERYRESULT);
	}
	
}
