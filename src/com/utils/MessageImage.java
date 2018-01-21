package com.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class MessageImage {

	/**
	 * 创建一张图，内容是字符串及其阴影。
	 */
	public static BufferedImage makeMessageImage(String message, 
			String fontName, int fontSize)
	{
		//表示字体
		Font font = new Font(fontName, Font.PLAIN, fontSize);
		//定义字体规格对象
		FontMetrics metrics = getFontMetrics(font);
		//返回此 Font 中指定 String 的总 advance width
		int messageWidth = metrics.stringWidth(message);
		int baselineX = messageWidth/10;
		int width = messageWidth+2*(baselineX+fontSize);
		int height = fontSize*7/2;
		int baselineY = height*8/10;
		BufferedImage messageImage = 
				new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) messageImage.getGraphics();
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, width, height);
		g2d.setFont(font);
		g2d.translate(baselineX, baselineY);
		g2d.setPaint(Color.lightGray);
		AffineTransform origTransform = g2d.getTransform();
		g2d.shear(-0.95, 0);
		g2d.scale(1, 3);
		g2d.drawString(message, 0, 0);
		g2d.setTransform(origTransform);
		g2d.setPaint(Color.black);
		g2d.drawString(message, 0, 0);
		
		return messageImage;
	}

	public static void writeJPEG(BufferedImage image, OutputStream out)
	{
		try {
			ImageIO.write(image, "jpg", out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeJPEG(BufferedImage image, File file)
	{
		try {
			ImageIO.write(image, "jpg", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String[] getFontNames()
	{
		GraphicsEnvironment env = 
				GraphicsEnvironment.getLocalGraphicsEnvironment();
		return env.getAvailableFontFamilyNames();
	}
	
	private static FontMetrics getFontMetrics(Font font) {
		BufferedImage tempImage =
				new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) tempImage.getGraphics();
		return g2d.getFontMetrics(font);
	}
}
