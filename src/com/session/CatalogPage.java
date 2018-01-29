package com.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.ServletUtils;
/*
 * 抽象基类，显示待售商品的servlet将它用作起点。
 * 分类图书页面都继承这个基类
 */
public abstract class CatalogPage extends HttpServlet {

	private CatalogItem[] items;
	private String[] itemIDs;
	private String title;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(items==null)
		{
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Missing items.");
			return;
		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<h1 align=\"center\">" + title + "</h1>\n");
		CatalogItem item;
		for(int i=0; i<items.length; i++)
		{
			out.println("<hr>");
			item = items[i];
			if(item == null)
			{
				out.println("<font color=\"red\">"
						+ "Unknown item ID" + itemIDs[i]
						+ "</font>");
			}
			else
			{
				String formURL = "/TestServlet/OrderPage";
				formURL = resp.encodeURL(formURL);
				out.println("<form action=\""+formURL+"\">\n"
						+ "<input type=\"hidden\" name=\"itemID\""
						+ " value=\"" + item.getItemID() + "\">\n"
						+ "<h2>" + item.getShortDescription()
						+ " ($" + item.getCost() + ")</h2>\n"
						+ item.getLongDescription() + "\n"
						+ "<p><center><input type=\"submit\" value="
						+ "\"Add to Shopping Cart\"></center></form>");
			}
		}
		out.println("<hr></body></html>");
	}

	protected void setItems(String[] itemIDs)
	{
		this.itemIDs = itemIDs;
		items = new CatalogItem[itemIDs.length];
		for(int i=0; i<items.length; i++)
		{
			items[i] = Catalog.getItem(itemIDs[i]);
		}
	}
	
	protected void setTitle(String title)
	{
		this.title = title;
	}
}
