package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.ServletUtils;
/**
 * 显示请求的表头信息
 *
 */
public class ShowRequestHeaders extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String title = "Showing Headers";
		out.println(ServletUtils.headWithTitle(title) + 
				"<BODY BGCOLOR=\"#FDF5E6\">\n"
				+ "<B>Request Method:</B>"
				+ req.getMethod()
				+ "<B>Request URI</B>"
				+ req.getRequestURI()
				+ "<B>Request Protocol:</B>"
				+ req.getProtocol()
				+ "<br><br>\n");
		Enumeration headerNames = req.getHeaderNames();
		while(headerNames.hasMoreElements())
		{
			String headerName = (String) headerNames.nextElement();
			out.println("<B>"+headerName+":</B>"
					+ req.getHeader(headerName)
					+ "<br>");
		}
		out.println("\n</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
