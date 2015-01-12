package com.didihe1988.picker.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");

	public static String toDate(Date inputDate) {
		Calendar curCalendar = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		if (curCalendar.get(Calendar.DAY_OF_MONTH) != calendar
				.get(Calendar.DAY_OF_MONTH)) {
			return dateFormat.format(inputDate);
		} else if (curCalendar.get(Calendar.HOUR_OF_DAY) != calendar
				.get(Calendar.HOUR_OF_DAY)) {
			return curCalendar.get(Calendar.HOUR_OF_DAY)
					- calendar.get(Calendar.HOUR_OF_DAY) + "小时前";
		} else if (curCalendar.get(Calendar.MINUTE) != calendar
				.get(Calendar.MINUTE)) {
			return curCalendar.get(Calendar.MINUTE)
					- calendar.get(Calendar.MINUTE) + "分钟前";
		} else {
			return "刚刚";
		}
	}
}
