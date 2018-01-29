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
 * 使用简单的ArrayList来跟踪每个用户已经购置的商品
 * 输出一个项目列表，显示ArrayList中有哪些商品
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
		//尽管每个用户拥有独立的会话，竞争条件发生的唯一方式是同一用户快速地连续提交两项购买请求，因此要加上同步
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
