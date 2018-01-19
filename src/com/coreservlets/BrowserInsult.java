package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.ServletUtils;
/**
 * �����û�ʹ�õ�����������͸�����ͬ�ķ�Ӧ
 * ������������û�ʹ�õ������������IE��Netscape,�������IE��ΪNetscape
 * IE11��userAgent�в��ٰ�����MSIE���ؼ��֣�������汾�������°汾 ("rv") ���Ʊ��档
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
