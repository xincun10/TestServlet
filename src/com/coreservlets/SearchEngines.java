package com.coreservlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.SearchUtilities;
/*
 * 302和404状态码
 * 用户输入查询，选择搜索引擎，然后将用户转送到搜索引擎的结果页面。
 * 如果用户省略搜索关键字，或者没有选择搜索引擎，此时就不知道应该将用户重定向到哪个网站，
 * 此时，显示一个错误页面，告知用户这个情况。
 */
public class SearchEngines extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException
	{
		String searchString = req.getParameter("searchString");
		String searchEngineName = req.getParameter("searchEngine");
		//搜索字段为空
		if((searchString == null) || (searchString.trim().length()==0))
		{
			reportProblem(resp, "Missing search string");
			return;
		}
		String searchURL = SearchUtilities.makeURL(searchEngineName, searchString);
		if(searchURL!=null)
		{
			resp.sendRedirect(searchURL);
		}
		else
		{
			reportProblem(resp, "Unrecognized search engine");
		}
	}

	private void reportProblem(HttpServletResponse resp, String message) throws IOException {
		resp.sendError(HttpServletResponse.SC_NOT_FOUND, message);
	}
}
