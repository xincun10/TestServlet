package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ����User-Agent����ͷ�����ݣ���IE���û�ת�͵�Netscape����ҳ��
 * �����������û�ת�͵�Microsoft����ҳ��
 * ���servletͨ��ʹ��sendRedirct���������������302״̬�����Location��Ӧ��ͷ��
 */
public class WrongDestination extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String userAgent = req.getHeader("user-agent");
		if((userAgent!=null) && (userAgent.indexOf("MSIE")!=-1 || userAgent.indexOf("rv:11")!=-1))
		{
			//IE���������ת��NetScape
			resp.sendRedirect("http://home.netscape.com");
		}
		else
		{
			resp.sendRedirect("http://www.microsoft.com");
		}

	}

}
