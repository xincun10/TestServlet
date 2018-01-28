package com.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.ServletUtils;

/*
 * 对于第一次来访者显示welcome aboard,对于老来访者显示welcome back
 * 不能通过cookies数组是否为空判断是否为新的来访者，而是要检查唯一命名的cookie是否存在
 */
public class RepeatVisitor extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean newbie = true;
		Cookie[] cookies = req.getCookies();
		if(cookies!=null)
		{
			for(int i=0; i<cookies.length; i++)
			{
				Cookie c = cookies[i];
				if(c.getName().equals("repeatVisitor") && c.getValue().equals("yes"))
				{
					newbie = false;
					break;
				}
			}
		}
		
		String title;
		if(newbie)
		{
			Cookie returnVisitorCookie = new Cookie("repeatVisitor", "yes");
			returnVisitorCookie.setMaxAge(60*60*24*365);//1年
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
