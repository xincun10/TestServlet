package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.MessageImage;
import com.utils.ServletUtils;
/**
 * 读取message，fontName和fontSize参数，将它们传递给MessageImage实用工具类
 * 创建一个JPEG图像，以指定的字体和大小显示该消息，同时在主字符串后面以灰色、倾斜阴影显示该消息。
 * 如果用户点击了shoe font list按钮，那么servlet就不在构建图像，
 * 而是显示服务器支持的字体名称列表。
 *
 */
public class ShadowedText extends HttpServlet{

	private static final char[] ServletUtilities = null;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String wantsList = req.getParameter("showList");
		if(wantsList != null)
		{
			//显示所有的字体列表，以html形式
			showFontList(resp);
		}
		else
		{
			//显示jpeg图像
			String message = req.getParameter("message");
			if((message==null) || (message.length()==0))
			{
				message = "Missing 'message' parameter";
			}
			String fontName = req.getParameter("fontName");
			if(fontName==null || fontName.length()==0)
			{
				fontName = "Serif";
			}
			String fontSizeString = req.getParameter("fontSize");
			int fontSize;
			try{
				fontSize = Integer.parseInt(fontSizeString);
			}catch(NumberFormatException nfe)
			{
				fontSize = 90;
			}
			resp.setContentType("image/jpeg");
			MessageImage.writeJPEG(MessageImage.makeMessageImage(message, fontName, fontSize), 
					resp.getOutputStream());
		}
	}

	private void showFontList(HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		String title = "Fonts Available on Server";
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<h1 align=center>" + title + "</h1>\n"
				+ "<ul>");
		String[] fontNames = MessageImage.getFontNames();
		for(int i=0; i<fontNames.length; i++)
		{
			out.println(" <li>" + fontNames[i]);
		}
		out.println("</ul>\n"+"</body></html>");
	}

}
