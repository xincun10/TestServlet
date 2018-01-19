package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.ServletUtils;
/**
 * 根据用户使用的浏览器的类型给出不同的反应
 * 简单起见，鉴定用户使用的浏览器仅限于IE和Netscape,如果不是IE即为Netscape
 * IE11中userAgent中不再包含“MSIE”关键字，浏览器版本现在由新版本 ("rv") 令牌报告。
 *
 */
public class BrowserInsult extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String title, message;
		String userAgent = req.getHeader("user-agent");
		if((userAgent!=null) && (userAgent.indexOf("MSIE")!=-1 || userAgent.indexOf("rv:11")!=-1))
		{
			title = "Microsoft Minion";
			message = "welcome, O spineless slave to the mighty empire.";
		}
		else
		{
			title = "Hopeless Netscape Rebel";
			message = "guess your use Netscope!";
		}
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<h1 align=\"center\">"
				+ title
				+ "</h1>\n"
				+ message + "\n"
				+ "</body></html>");
	}

}
