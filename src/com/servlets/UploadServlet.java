package com.servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * �ϴ�����
		 * 1.�õ�����
		 * 2.ͨ����������������
		 * 3.����request���õ�FileItem����
		 * 4.����FileItem���ϣ�������API����ļ��ı���
		 */
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			FileItem file1 = fileItemList.get(0);
			FileItem file2 = fileItemList.get(1);
			
			//����ļ���Ϣ
			System.out.println("��ͨ������ʾ��" + file1.getFieldName() + "=" 
							+ file1.getString("utf-8"));
			System.out.println("�ļ�������ʾ��");
			System.out.println("Content-Type: " + file2.getContentType());
			System.out.println("size: " + file2.getSize());
			System.out.println("filename: " + file2.getName());
			
			//�����ļ�
			/*
			 * 1.�õ��ļ������·��
			 * 2.��������Ŀ¼
			 *  �õ��ļ�����
			 *  �õ�hashcode
			 *  ת��Ϊ16����
			 *  ��ȡǰ�����ַ���������Ŀ¼
			 */
			String root = this.getServletContext().getRealPath("/WEB-INF/files");
			String filename = file2.getName();
			//�����ļ����ľ���·������
			int index = filename.indexOf("/");
			if(index!=-1)
			{
				filename = filename.substring(index+1);
			}
			//�õ�hashcode
			int hcode = filename.hashCode();
			String hex = Integer.toHexString(hcode);
			//��ȡhex��ǰ������ĸ��root������һ������һ��������·��
			File dirFile = new File(root, "/"+hex.charAt(0)+"/"+hex.charAt(1));
			//����Ŀ¼��(�������򴴽�)
			dirFile.mkdirs();
			//�����ļ�
			File destFile = new File(dirFile, filename);
			file2.write(destFile);
		} catch (FileUploadException e) {
			throw new RuntimeException(e);
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
