package com.itc.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Date now = new Date();
		String strNow = sdf.format(now);
		System.out.println(strNow);
	}

}
