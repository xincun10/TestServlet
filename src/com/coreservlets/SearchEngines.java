package com.coreservlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.SearchUtilities;
/*
 * 302��404״̬��
 * �û������ѯ��ѡ���������棬Ȼ���û�ת�͵���������Ľ��ҳ�档
 * ����û�ʡ�������ؼ��֣�����û��ѡ���������棬��ʱ�Ͳ�֪��Ӧ�ý��û��ض����ĸ���վ��
 * ��ʱ����ʾһ������ҳ�棬��֪�û���������
 */
public class SearchEngines extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException
	{
		String searchString = req.getParameter("searchString");
		String searchEngineName = req.getParameter("searchEngine");
		//�����ֶ�Ϊ��
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
