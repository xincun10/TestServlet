package com.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utils.ServletUtils;
/*
 * ʹ�ü򵥵�ArrayList������ÿ���û��Ѿ����õ���Ʒ
 * ���һ����Ŀ�б���ʾArrayList������Щ��Ʒ
 */
public class ShowItems extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ArrayList previousItems = (ArrayList) session.getAttribute("previousItems");
		if(previousItems == null)
		{
			previousItems = new ArrayList();
			session.setAttribute("previousItems", previousItems);
		}
		String newItem = req.getParameter("newItem");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String title = "Items Purchased";
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<h1>" + title + "</h1>");
		//����ÿ���û�ӵ�ж����ĻỰ����������������Ψһ��ʽ��ͬһ�û����ٵ������ύ������������Ҫ����ͬ��
		synchronized(previousItems)
		{
			if((newItem!=null) && (!newItem.trim().equals("")))
			{
				previousItems.add(newItem);
			}
			if(previousItems.size()==0)
			{
				out.println("<I>No items</I>");
			}
			else
			{
				out.println("<ul>");
				for(int i=0; i<previousItems.size(); i++)
				{
					out.println("<li>" + previousItems.get(i).toString());
				}
				out.println("</ul>");
			}
		}
		out.println("</body></html>");
	}
}
