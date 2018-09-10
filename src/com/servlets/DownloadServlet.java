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
	     * 两个头一个流
	     * 1.Content-Type
	     * 2.Content-Disposition
	     * 3.流：下载文件的数据
	     */
		String filename = "D:\\mp3\\梦想的声音无损音乐\\田馥甄 - 追梦人 (Live) .flac";
		String framename = DownUtils.filenameEncoding("田馥甄 - 追梦人 (Live) .flac", request);
		//通过文件名称获取MIME类型
		String contentType = this.getServletContext().getMimeType(filename);
		//显示在下载框里面的文件名称
		String contentDisposition = "attachment;filename=" + framename;
		//一个输入流
		FileInputStream input = new FileInputStream(filename);
		//设置头
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Disposition", contentDisposition);
		//一个输出流
		ServletOutputStream output = response.getOutputStream();
		//将输入流中的数据写入到输出流中
		IOUtils.copy(input, output);
		input.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
