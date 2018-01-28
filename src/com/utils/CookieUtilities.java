package com.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtilities {

	//获取cookie对应的值,如果没有对应cookie，返回默认值
	public static String getCookieValue(HttpServletRequest request,
			String cookieName, String defaultValue)
	{
		Cookie[] cookies = request.getCookies();
		if(cookies!=null)
		{
			for(int i=0; i<cookies.length; i++)
			{
				Cookie cookie = cookies[i];
				if(cookieName.equals(cookie.getName()))
				{
					return cookie.getValue();
				}
			}
		}
		return defaultValue;
	}
	
	//获取cookie,没有返回null
	public static Cookie getCookie(HttpServletRequest request, String cookieName)
	{
		Cookie[] cookies = request.getCookies();
		if(cookies!=null)
		{
			for(int i=0; i<cookies.length; i++)
			{
				Cookie cookie = cookies[i];
				if(cookieName.equals(cookie.getName()))
				{
					return cookie;
				}				
			}
		}
		return null;
	}
}
