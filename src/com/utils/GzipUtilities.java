package com.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * gzip压缩相关工具类
 *
 */
public class GzipUtilities {

	//判断浏览器是否支持gzip
	public static boolean isGzipSupported(HttpServletRequest request)
	{
		String encoding = request.getHeader("accept-encoding");
		return (encoding!=null && encoding.indexOf("gzip")!=-1);
	}
	//自定义功能，我们可以在URL尾部加上?disableGzip来禁止压缩
	//判断浏览器是否禁用了gzip
	public static boolean isGzipDisabled(HttpServletRequest request)
	{
		String flag = request.getParameter("disableGzip");
		return ((flag!=null) && (!flag.equalsIgnoreCase("false")));
	}
	//返回gzip格式的输出流
	public static PrintWriter getGzipWriter(HttpServletResponse response) throws IOException
	{
		return (new PrintWriter
				(new GZIPOutputStream
						(response.getOutputStream())));
	}
}
