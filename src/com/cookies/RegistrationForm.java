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
 * ����ύ�ı���������������ʾ�ñ�������¼֮ǰ��������ݡ�
 */
public class RegistrationForm extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		//�ڱ��У�һ��Ҫʹ�����ACTION URL����Ҫʹ�þ���ACTION URL
		//���URL����������
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
