package com.cookies;

import javax.servlet.http.Cookie;

/*
 * �����������ڵ�cookie
 * cookie��������Ϊ1��
 */
public class LongLivedCookie extends Cookie{

	public static final int SECONDS_PER_YEAR = 60*60*24*365;
	public LongLivedCookie(String name, String value) {
		super(name, value);
		this.setMaxAge(SECONDS_PER_YEAR);
	}

}
