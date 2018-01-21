package com.utils;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

	//�����ַ�������
	public static final String DOCTYPE = 
			"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "+
			"Transitional//EN\">";
	public static String headWithTitle(String title)
	{
		return (DOCTYPE + "\n" + 
				"<HTML>\n" + 
				"<HEAD><TITLE>" + title + "</TITLE></HEAD>\n");
	}
	//ʵ���ַ�������
	public static String filter(String input)
	{
		if(!hasSpecialChars(input))
		{
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		//�滻�����ַ�
		for(int i=0; i<input.length(); i++)
		{
			c = input.charAt(i);
			switch(c)
			{
				case '<' : filtered.append("&lt;"); break;
				case '>' : filtered.append("&gt;"); break;
				case '"' : filtered.append("&quot;"); break;
				case '&' : filtered.append("&amp;"); break;
				default : filtered.append(c);
			}
		}
		return filtered.toString();
	}
	
	//�ж��Ƿ��������ַ�
	public static boolean hasSpecialChars(String input)
	{
		boolean flag = false;
		if(input!=null && (input.length()>0))
		{
			char c;
			for(int i=0; i<input.length(); i++)
			{
				c = input.charAt(i);
				switch(c)
				{
					case '<' : flag=true; break;
					case '>' : flag=true; break;
					case '"' : flag=true; break;
					case '&' : flag=true; break;
				}
			}
		}
		return flag;
	}
	
	//������ת��Ϊint���Ͳ�����
	public static int getIntParameter(HttpServletRequest request, String paramName, int defaultValue)
	{
		String paramString = request.getParameter(paramName);
		int paramValue;
		try
		{
			paramValue = Integer.parseInt(paramString);
		}
		catch(NumberFormatException nfe)
		{
			//���ִ���Ļ�תΪĬ��ֵ
			paramValue = defaultValue;
		}
		return paramValue;
	}
}
