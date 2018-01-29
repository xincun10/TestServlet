package com.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utils.ServletUtils;

/*
 * 显示客户访问计数的servlet
 */
public class ShowSession extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		String header;
		Integer accessCount = (Integer) session.getAttribute("accessCount");
		if(accessCount == null)
		{
			accessCount = new Integer(0);
			header = "Welcome, new comer";
		}
		else
		{
			//Integer是一种不可修改的数据结构，每个请求都必须分配新的Integer对象，
			//之后使用setAttribute来替换老的对象
			accessCount = new Integer(accessCount.intValue()+1);
			header = "Welcome back";
		}
		session.setAttribute("accessCount", accessCount);
		PrintWriter out = resp.getWriter();
		String title = "Session Tracking Example";
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<h1>" + header + "</h1>\n"
				+ "<h2>Information on Your Session</h2>\n"
				+ "<table border=1>\n"
				+ "<tr bgcolor=\"#FFAD00\">\n"
				+ "<th>Info Type<th>Value\n"
				+ "<tr>\n"
				+ "<td>ID\n"
				+ "<td>" + session.getId() + "\n"
				+ "<tr>\n"
				+ "<td>Creation Time\n"
				+ "<td>" + new Date(session.getCreationTime()) + "\n"
				+ "<tr>\n"
				+ "<td>Time of Last Access\n"
				+ "<td>" + new Date(session.getLastAccessedTime()) + "\n"
				+ "<tr>\n"
				+ "<td>Number of Previous Accesses\n"
				+ "<td>" + accessCount + "\n"
				+ "</table>\n"
				+ "</body></html>");
	}

}
