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
 * ��ȡmessage��fontName��fontSize�����������Ǵ��ݸ�MessageImageʵ�ù�����
 * ����һ��JPEGͼ����ָ��������ʹ�С��ʾ����Ϣ��ͬʱ�����ַ��������Ի�ɫ����б��Ӱ��ʾ����Ϣ��
 * ����û������shoe font list��ť����ôservlet�Ͳ��ڹ���ͼ��
 * ������ʾ������֧�ֵ����������б�
 *
 */
public class ShadowedText extends HttpServlet{

	private static final char[] ServletUtilities = null;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String wantsList = req.getParameter("showList");
		if(wantsList != null)
		{
			//��ʾ���е������б���html��ʽ
			showFontList(resp);
		}
		else
		{
			//��ʾjpegͼ��
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
