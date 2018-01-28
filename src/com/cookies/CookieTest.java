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
 * ���ֻỰcookie�������cookie
 */
public class CookieTest extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//�½�3���Ựcookie�ͳ־���cookie
		for(int i=0; i<3; i++)
		{
			//�Ựcookie,������ر�֮���û����
			Cookie cookie = new Cookie("Session-Cookie-"+i, "Cookie-Value-S"+i);
			resp.addCookie(cookie);
			//�־���cookie
			cookie = new Cookie("Persistent-Cookie-"+i, "Cookie-Value-P"+i);
			cookie.setMaxAge(3600);
			resp.addCookie(cookie);
		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String title = "Active Cookies";
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<h1 align=\"center\">" + title + "</h1>\n"
				+ "<table border=1 align=\"center\">\n"
				+ "<tr bgcolor=\"#FFAD00\">\n"
				+ "<th>Cookie Name\n"
				+ "<th>Cookie Value\n");
		Cookie[] cookies = req.getCookies();
		if(cookies==null)
		{
			out.println("<tr><th colspan=2>No cookies");
		}
		else
		{
			Cookie cookie;
			for(int i=0; i<cookies.length; i++)
			{
				cookie = cookies[i];
				out.println("<tr>\n"
						+ "<td>" + cookie.getName() + "\n"
						+ "<td>" + cookie.getValue());
			}
		}
		out.println("</table></body></html>");
	}

	
}
