package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.ServletUtils;
/**
 * 显示除HTTP_XXX_YYY以外所有CGI变量的值
 *
 */
public class ShowCGIVariables extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		//使用数组存储变量和值
		String[][] var = 
			{{"AUTH_TYPE", req.getAuthType()},
			 {"CONTENT_LENGTH", String.valueOf(req.getContentLength())},
			 {"DOCUMENT_ROOT", this.getServletContext().getRealPath("/")},
			 {"PATH_INFO", req.getPathInfo()},
			 {"PATH_TRANSLATED", req.getPathTranslated()},
			 {"QUERY_STRING", req.getQueryString()},
			 {"REMOTE_ADDR", req.getRemoteAddr()},
			 {"REMOTE_HOST", req.getRemoteHost()},
			 {"REMOTE_USER", req.getRemoteUser()},
			 {"REQUEST_METHOD", req.getMethod()},
			 {"SCRIPT_NAME", req.getServletPath()},
			 {"SERVER_NAME", req.getServerName()},
			 {"SERVER_PORT", String.valueOf(req.getServerPort())},
			 {"SERVER_PROTOCOL", req.getProtocol()},
			 {"SERVER_SOFTWARE", this.getServletContext().getServerInfo()}};
		String title = "Servlet Example:Showing CGI Variables";
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<center>\n"
				+ "<h1>" + title + "</h1>\n"
				+ "<table border=1>\n"
				+ "<tr bgcolor=\"#FF1D00\">\n"
				+ "<th>CGI Variable Name<th>Value");
		for(int i=0; i<var.length; i++)
		{
			String varName = var[i][0];
			String varValue = var[i][1];
			if(varValue==null)
			{
				varValue = "<I>Not specified</I>";
			}
			out.println("<tr><td>"+varName+"<td>"+varValue);
		}
		out.println("</table></center></body></html>");
	}

}
