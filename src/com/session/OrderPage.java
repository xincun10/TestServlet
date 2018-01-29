package com.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utils.ServletUtils;

public class OrderPage extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ShoppingCart cart;
		synchronized(session)
		{
			cart = (ShoppingCart) session.getAttribute("shoppingCart");
			if(cart==null)
			{
				cart = new ShoppingCart();
				session.setAttribute("shoppingCart", cart);
			}
			String itemID = req.getParameter("itemID");
			if(itemID!=null)
			{
				String numItemsString = req.getParameter("numItems");
				if(numItemsString == null)
				{
					cart.addItem(itemID);
				}
				else
				{
					int numItems;
					try
					{
						numItems = Integer.parseInt(numItemsString);
					}
					catch(NumberFormatException e)
					{
						numItems = 1;
					}
					cart.setNumOrdered(itemID, numItems);
				}
			}
		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String title = "Status of Your Order";
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<h1 align=\"center\">" + title + "</h1>");
		synchronized(session)
		{
			List itemsOrdered = cart.getItemsOrdered();
			if(itemsOrdered.size()==0)
			{
				out.println("<h2><I>No items in your cart...</I></h2>");
			}
			else
			{
				out.println("<table border=1 align=\"center\">\n"
						+ "<tr bgcolor=\"#FFAD00\">\n"
						+ "<th>Item ID<th>Description\n"
						+ "<th>Unit Cost<th>Number<th>Total Cost");
				ItemOrder order;
				//返回指定语言环境的货币格式
				NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
				for(int i=0; i<itemsOrdered.size(); i++)
				{
					order = (ItemOrder) itemsOrdered.get(i);
					out.println("<tr>\n"
							+ " <td>" + order.getItemID() + "\n"
							+ " <td>" + order.getShortDescription() + "\n"
							+ " <td>" + formatter.format(order.getUnitCost()) + "\n"
							+ " <td>"
							+ "<form>\n"
							+ "<input type=\"hidden\" name=\"itemID\"\n"
							+ " value=\"" + order.getItemID() + "\">\n"
							+ "<input type=\"text\" name=\"numItems\"\n"
							+ " size=3 value=\"" + order.getNumItems() + "\">\n"
							+ "<small>\n"
							+ "<input type=\"submit\" value=\"Update Order\">\n"
							+ "</small>\n"
							+ "</form>\n"
							+ " <td>"
							+ formatter.format(order.getTotalCost()));
				}
				String checkoutURL = resp.encodeURL("Checkout.html");
				out.println("</table>\n"
						+ "<form action=\"" + checkoutURL + "\">\n"
						+ "<big><center>\n"
						+ "<input type=\"submit\" value=\"Procceed to Checkout\">\n"
						+ "</center></big></form>");
			}
			out.println("</body></html>");
		}
	}

}
