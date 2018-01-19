package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.ServletUtils;

/**
 * 根据不同的referer报头信息显示不同的内容
 * 有三个不同的html页面可以链接到这个servlet
 * 例如从http://localhost:8080/TestServlet/JRun-Referer.html引用的页面内容显示为jrun-powered.gif
 *
 */
public class CustomizeImage extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		//获取Referer信息
		String referer = req.getHeader("Referer");
		if(referer==null)
		{
			referer = "<I>NONE</I>";
		}
		String title = "Referering page:" + referer;
		String imageName;
		if(contains(referer, "JRun"))
		{
			imageName = "jrun-powered.gif";
		}
		else if(contains(referer, "Resin"))
		{
			imageName = "resin-powered.gif";
		}
		else
		{
			imageName = "tomcat-powered.gif";
		}
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<center><h2>" + title + "</h2>\n"
				+ "<I>" + imageName +"</I>"
				+ "</center></body></html>");
	}

	private boolean contains(String referer, String string) {
		return (referer.indexOf(string)!=-1);
	}

}
