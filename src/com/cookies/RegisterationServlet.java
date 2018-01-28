package com.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.ServletUtils;

public class RegisterationServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		boolean isMissingValue = false;
		String formAddress = "/TestServlet/RegistrationForm";
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String emailAddress = req.getParameter("emailAddress");
		if(isMissing(firstName))
		{
			firstName = "MissingFirstName";
			isMissingValue = true;
		}
		if(isMissing(lastName))
		{
			lastName = "MissingLastName";
			isMissingValue = true;
		}
		if(isMissing(emailAddress))
		{
			emailAddress = "MissingEmailAddress";
			isMissingValue = true;
		}
		Cookie c1 = new LongLivedCookie("firstName", firstName);
		resp.addCookie(c1);
		Cookie c2 = new LongLivedCookie("lastName", lastName);
		resp.addCookie(c2);
		Cookie c3 = new LongLivedCookie("emailAddress", emailAddress);
		resp.addCookie(c3);
		
		if(isMissingValue)
		{
			resp.sendRedirect(formAddress);
		}
		else
		{
			PrintWriter out = resp.getWriter();
			String title = "Thanks for Registering";
			out.println(ServletUtils.headWithTitle(title)
					+ "<body bgcolor=\"#FDF5E6\">\n"
					+ "<h1>" + title + "</h1>\n"
					+ "<ul>\n"
					+ "<li><B>First Name</B>:"
					+ firstName + "\n"
					+ "<li><B>Last Name</B>:"
					+ lastName + "\n"
					+ "<li><B>Email address</B>:"
					+ emailAddress + "\n"
					+ "</ul>\n"
					+ "</body></html>");
		}
	}

	private boolean isMissing(String param) {
		// TODO Auto-generated method stub
		return ((param==null)||(param.trim().equals("")));
	}

}
