package com.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public class DownUtils {
	public static String filenameEncoding(String filename, HttpServletRequest request)
	{
		String userAgent = request.getHeader("User-Agent"); 
		try
		{
			//针对IE或者以IE为内核的浏览器：
			if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
				filename = java.net.URLEncoder.encode(filename, "UTF-8");
			} else {
			//非IE浏览器的处理：
				filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
			}
			return filename;
		}catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
		
	}
}
