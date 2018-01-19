package com.utils;

public class ServletUtils {

	//定义字符串常量
	public static final String DOCTYPE = 
			"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "+
			"Transitional//EN\">";
	public static String headWithTitle(String title)
	{
		return (DOCTYPE + "\n" + 
				"<HTML>\n" + 
				"<HEAD><TITLE>" + title + "</TITLE></HEAD>\n");
	}
	//实现字符串过滤
	public static String filter(String input)
	{
		if(!hasSpecialChars(input))
		{
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		//替换特殊字符
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
	
	//判断是否含有特殊字符
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
}
