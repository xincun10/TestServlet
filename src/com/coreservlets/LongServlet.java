package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.GzipUtilities;
import com.utils.ServletUtils;
/**
 * ʹ��gzip�����
 *
 */
public class LongServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		//����������Ƿ�֧��gzip��ʽ������PrintWriter
		PrintWriter out;
		if(GzipUtilities.isGzipSupported(req) &&
				!GzipUtilities.isGzipDisabled(req))
		{
			out = GzipUtilities.getGzipWriter(resp);
			resp.setHeader("Content-Encoding", "gzip");
		}
		else
		{
			out = resp.getWriter();
		}
		String title = "Long Page";
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<h1 align=\"CENTER\">"
				+ title
				+ "</h1>\n");
		String line = "test the time of gzip!";
		for(int i=0; i<10000; i++)
		{
			out.println(line);
		}
		out.println("</body></html>");
		out.close();//����gzip�Ǳ���ģ�����������ǿ�ѡ��
	}

	
}
