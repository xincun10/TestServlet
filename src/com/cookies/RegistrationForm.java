package com.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.CookieUtilities;
import com.utils.ServletUtils;
/*
 * 如果提交的表单不完整则重新显示该表单，表单记录之前输入的数据。
 */
public class RegistrationForm extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		//在表单中，一定要使用相对ACTION URL，不要使用绝对ACTION URL
		//相对URL不含主机名
		String actionURL = "/TestServlet/RegisterationServlet";
		String firstName = CookieUtilities.getCookieValue(req, "firstName", "");
		String lastName = CookieUtilities.getCookieValue(req, "lastName", "");
		String emailAddress = CookieUtilities.getCookieValue(req, "emailAddress", "");
		String title = "Please Register";
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<h1>" + title + "</h1>\n"
				+ "<form action=\"" + actionURL + "\">\n"
				+ "First Name:\n"
				+ "<input type=\"text\" name=\"firstName\""
				+ " value=\"" + firstName + "\"><br>\n"
				+ "Last Name:\n"
				+ "<input type=\"text\" name=\"lastName\""
				+ " value=\"" + lastName + "\"><br>\n"
				+ "Email Address:\n"
				+ "<input type=\"text\" name=\"emailAddress\""
				+ " value=\"" + emailAddress + "\"><br>\n"
				+ "<input type=\"submit\" value=\"Register\">\n"
				+ "</form></body></html>");
	}

}
