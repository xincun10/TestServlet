package com.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * gzipѹ����ع�����
 *
 */
public class GzipUtilities {

	//�ж�������Ƿ�֧��gzip
	public static boolean isGzipSupported(HttpServletRequest request)
	{
		String encoding = request.getHeader("accept-encoding");
		return (encoding!=null && encoding.indexOf("gzip")!=-1);
	}
	//�Զ��幦�ܣ����ǿ�����URLβ������?disableGzip����ֹѹ��
	//�ж�������Ƿ������gzip
	public static boolean isGzipDisabled(HttpServletRequest request)
	{
		String flag = request.getParameter("disableGzip");
		return ((flag!=null) && (!flag.equalsIgnoreCase("false")));
	}
	//����gzip��ʽ�������
	public static PrintWriter getGzipWriter(HttpServletResponse response) throws IOException
	{
		return (new PrintWriter
				(new GZIPOutputStream
						(response.getOutputStream())));
	}
}
