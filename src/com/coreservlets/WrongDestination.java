package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 根据User-Agent请求报头的内容，将IE的用户转送到Netscape的主页，
 * 将所有其他用户转送到Microsoft的主页。
 * 这个servlet通过使用sendRedirct方法向浏览器发送302状态代码和Location响应报头。
 */
public class WrongDestination extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String userAgent = req.getHeader("user-agent");
		if((userAgent!=null) && (userAgent.indexOf("MSIE")!=-1 || userAgent.indexOf("rv:11")!=-1))
		{
			//IE浏览器，跳转到NetScape
			resp.sendRedirect("http://home.netscape.com");
		}
		else
		{
			resp.sendRedirect("http://www.microsoft.com");
		}

	}

}
