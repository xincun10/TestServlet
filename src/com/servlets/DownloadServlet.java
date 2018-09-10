package com.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.utils.DownUtils;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
	     * ����ͷһ����
	     * 1.Content-Type
	     * 2.Content-Disposition
	     * 3.���������ļ�������
	     */
		String filename = "D:\\mp3\\�����������������\\����� - ׷���� (Live) .flac";
		String framename = DownUtils.filenameEncoding("����� - ׷���� (Live) .flac", request);
		//ͨ���ļ����ƻ�ȡMIME����
		String contentType = this.getServletContext().getMimeType(filename);
		//��ʾ�����ؿ�������ļ�����
		String contentDisposition = "attachment;filename=" + framename;
		//һ��������
		FileInputStream input = new FileInputStream(filename);
		//����ͷ
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Disposition", contentDisposition);
		//һ�������
		ServletOutputStream output = response.getOutputStream();
		//���������е�����д�뵽�������
		IOUtils.copy(input, output);
		input.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
