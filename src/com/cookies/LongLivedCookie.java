package com.cookies;

import javax.servlet.http.Cookie;

/*
 * 创建长生存期的cookie
 * cookie的周期设为1年
 */
public class LongLivedCookie extends Cookie{

	public static final int SECONDS_PER_YEAR = 60*60*24*365;
	public LongLivedCookie(String name, String value) {
		super(name, value);
		this.setMaxAge(SECONDS_PER_YEAR);
	}

}
