package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.SearchSpec;
import com.utils.SearchUtilities;
import com.utils.ServletUtils;
/*
 * ËÑË÷ÒýÇæÒ³Ãæ
 */
public class SearchEngineForm extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String title = "One-Stop Web Search!";
		String actionURL = "/TestServlet/SearchEngines";
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">"
				+ "<h1>"+title+"</h1><br>"
				+ "<form action=\""+actionURL+"\">\n"
				+ "Search keywords:"
				+ "<input type=\"text\" name=\"searchString\" /><br>");
		SearchSpec[] specs = SearchUtilities.getCommonSpecs();
		for(int i=0; i<specs.length; i++)
		{
			String searchEngineName = specs[i].getName();
			out.println("<input type=\"RADIO\""
					+ "name=\"searchEngine\""
					+ "value=\""+searchEngineName+"\" />\n");
			out.println(searchEngineName + "<br>\n");
		}
		out.println("<br> <input type=\"submit\" />\n"
				+ "</form>\n"
				+ "</body></html>");
	}

}
