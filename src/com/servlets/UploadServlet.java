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
		 * 上传三步
		 * 1.得到工厂
		 * 2.通过工厂创建解析器
		 * 3.解析request，得到FileItem集合
		 * 4.遍历FileItem集合，调用其API完成文件的保存
		 */
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			FileItem file1 = fileItemList.get(0);
			FileItem file2 = fileItemList.get(1);
			
			//输出文件信息
			System.out.println("普通表单项演示：" + file1.getFieldName() + "=" 
							+ file1.getString("utf-8"));
			System.out.println("文件表单项演示：");
			System.out.println("Content-Type: " + file2.getContentType());
			System.out.println("size: " + file2.getSize());
			System.out.println("filename: " + file2.getName());
			
			//保存文件
			/*
			 * 1.得到文件保存的路径
			 * 2.生成两层目录
			 *  得到文件名称
			 *  得到hashcode
			 *  转化为16进制
			 *  获取前两个字符用来生成目录
			 */
			String root = this.getServletContext().getRealPath("/WEB-INF/files");
			String filename = file2.getName();
			//处理文件名的绝对路径问题
			int index = filename.indexOf("/");
			if(index!=-1)
			{
				filename = filename.substring(index+1);
			}
			//得到hashcode
			int hcode = filename.hashCode();
			String hex = Integer.toHexString(hcode);
			//获取hex的前两个字母与root连接在一起生成一个完整的路径
			File dirFile = new File(root, "/"+hex.charAt(0)+"/"+hex.charAt(1));
			//创建目录链(不存在则创建)
			dirFile.mkdirs();
			//创建文件
			File destFile = new File(dirFile, filename);
			file2.write(destFile);
		} catch (FileUploadException e) {
			throw new RuntimeException(e);
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
