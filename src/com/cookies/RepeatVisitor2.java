package com.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.CookieUtilities;
import com.utils.ServletUtils;

/*
 * ���ڵ�һ����������ʾwelcome aboard,��������������ʾwelcome back
 * ����ͨ��cookies�����Ƿ�Ϊ���ж��Ƿ�Ϊ�µ������ߣ�����Ҫ���Ψһ������cookie�Ƿ����
 */
public class RepeatVisitor2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean newbie = true;
		String value = CookieUtilities.getCookieValue(req, "repeatVisitor2", "no");
		if(value.equals("yes"))
		{
			newbie = false;
		}
		
		String title;
		if(newbie)
		{
			LongLivedCookie returnVisitorCookie = new LongLivedCookie("repeatVisitor2", "yes");
			resp.addCookie(returnVisitorCookie);
			title = "Welcome Aboard";
		}
		else
		{
			title = "Welcome back";
		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println(ServletUtils.headWithTitle(title)+"<body bgcolor=\"#FDF5E6\">"
				+ "\n<h1 align=\"center\">"
				+ title + "</h1>"
				+ "</body></html>");
	}
	
}
