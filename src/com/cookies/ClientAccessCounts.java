package com.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.CookieUtilities;
import com.utils.ServletUtils;

/*
 * servlet����ÿ���ͻ����ض�ҳ��ķ��ʴ�����
 * ͨ��������ΪaccessCount��cookie�������������
 * servlet��Ҫ����ͬ�������ٴη���cookie,���ϵ��滻��cookie��ֵ
 */
public class ClientAccessCounts extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String countString = CookieUtilities.getCookieValue(req, "accessCount", "1");
		int count = 1;
		try
		{
			count = Integer.parseInt(countString);
		}catch(NumberFormatException nfe)
		{
			nfe.printStackTrace();
		}
		LongLivedCookie c = new LongLivedCookie("accessCount", String.valueOf(count+1));
		resp.addCookie(c);
		resp.setContentType("text/html");
		String title = "Access Count Servlet";
		PrintWriter out = resp.getWriter();
		out.println(ServletUtils.headWithTitle(title)+"<body bgcolor=\"#FDF5E6\">"
				+ "\n<h1 align=\"center\">"
				+ title + "</h1>"
				+ "<h2 align=\"center\">This is visit number "
				+ count + " by this browser.</h2>\n"
				+ "</body></html>");
	}

}
