package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.BidInfo;
import com.utils.BeanUtilities;
import com.utils.ServletUtils;
/**
 * 若表单数据填写完整，返回submit；
 * 否则，在未填表单项前面显示警告
 *
 */
public class BidServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		BidInfo bid = new BidInfo();
		BeanUtilities.populateBean(bid, req);
		if(bid.isComplete())
		{
			showBid(req, res, bid);
		}
		else
		{
			showEntryForm(req, res, bid);
		}				
	}

	//全部信息
	private void showBid(HttpServletRequest req, HttpServletResponse res, BidInfo bid) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("submit!");
	}
		
	//显示部分信息
	private void showEntryForm(HttpServletRequest req, HttpServletResponse res, BidInfo bid) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String title = "Please Enter Bid";
		out.println(ServletUtils.headWithTitle(title) + 
				"<BODY BGCOLOR=\"#FDF5E6\"><CENTER>\n"
				+ warning(bid.isPartlyComplete())
				+ "<FORM>\n"
				+ inputElement("Item ID", "itemID", bid.getItemID(), bid.isPartlyComplete())
				+ inputElement("Item Name", "itemName", bid.getItemName(), bid.isPartlyComplete())
				+ inputElement("Your Name", "bidderName", bid.getBidderName(), bid.isPartlyComplete())
				+ inputElement("Your Email Address", "emailAddress", bid.getEmailAddress(), bid.isPartlyComplete())
				+ inputElement("Amount Bid", "bidPrice", bid.getBidPrice(), bid.isPartlyComplete())
				+ checkbox("Auto-increment bid to match other bidders?", "autoIncrement", bid.isAutoIncrement())
				+ "<input type=\"submit\" value=\"submit button\">\n"
				+ "</center></body></html>");
	}

	private String checkbox(String prompt, String name, boolean isChecked) {
		String result = prompt + ":"
				+ "<input type=\"checkbox\" name=\"" + name +"\" ";
		if(isChecked)
		{
			result = result + " checked";
		}
		result = result + "><br>\n";
		return result;
	}

	private String inputElement(String prompt, String name, double value, boolean partlyComplete) {
		String num;
		if(value==0.0)
		{
			num="";
		}
		else
		{
			num=String.valueOf(value);
		}
		return (inputElement(prompt, name, num, partlyComplete));
	}

	private String inputElement(String prompt, String name, String value, boolean partlyComplete) {
		String message = "";
		if(partlyComplete && ((value==null) || "".equals(value)))
		{
			message = "<B>Required field!</B>";
		}
		return (message+prompt+":"+
				"<input type=\"text\" name=\""+name+"\" "
				+ "value=\""+value+"\"><br>\n");
	}

	private String warning(boolean partlyComplete) {
		if(partlyComplete)
		{
			return "<H2>Required Data Missing!"
					+ "Enter and Resubmit.</H2>\n";
		}
		else
		{
			return "";
		}
	}

	private void submitBid(BidInfo bid) {
		//一些应用可能会将对象记录下来
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		this.doGet(req, res);
	}

}
