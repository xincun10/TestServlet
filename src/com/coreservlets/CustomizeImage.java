package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.ServletUtils;

/**
 * ���ݲ�ͬ��referer��ͷ��Ϣ��ʾ��ͬ������
 * ��������ͬ��htmlҳ��������ӵ����servlet
 * �����http://localhost:8080/TestServlet/JRun-Referer.html���õ�ҳ��������ʾΪjrun-powered.gif
 *
 */
public class CustomizeImage extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		//��ȡReferer��Ϣ
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
