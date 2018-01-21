package com.coreservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.PrimeList;
import com.utils.ServletUtils;

public class PrimeNumberServlet extends HttpServlet {
	private ArrayList primeListCollection = new ArrayList();
	private int maxPrimeLists = 30;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int numPrimes = ServletUtils.getIntParameter(req, "numPrimes", 50);
		int numDigits = ServletUtils.getIntParameter(req, "numDigits", 120);
		PrimeList primeList = 
				findPrimeList(primeListCollection, numPrimes, numDigits);
		if(primeList == null)
		{
			//允许在后台运行
			primeList = new PrimeList(numPrimes, numDigits, true);
			//新建一个primeList，不允许在后台运行
//			primeList = new PrimeList(numPrimes, numDigits, false);
			synchronized(primeListCollection)
			{
				if(primeListCollection.size()>=maxPrimeLists)
				{
					primeListCollection.remove(0);
				}
				primeListCollection.add(primeList);
			}
		}
		ArrayList currentPrimes = primeList.getPrimes();
		int numCurrentPrimes = currentPrimes.size();
		int numPrimesRemaining = (numPrimes-numCurrentPrimes);
		boolean isLastResult = (numPrimesRemaining==0);
		if(!isLastResult)
		{
			//如果素数生成没有完成，浏览器每隔5秒钟就刷新一次
			resp.setIntHeader("Refresh", 5);
		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String title = "some " + numDigits + "-Digit Prime Numbers";
		out.println(ServletUtils.headWithTitle(title)
				+ "<body bgcolor=\"#FDF5E6\">\n"
				+ "<h2 align=center>" + title + "</h2>\n"
				+ "<h3>Primes found with" + numDigits
				+ " or more digits:" + numCurrentPrimes
				+ ".</h3>");
		if(isLastResult)
		{
			out.println("<b>Done searching.</b>");
		}
		else
		{
			out.println("<b>Still looking for " + numPrimesRemaining
					+ " more <blink>...</blink></b>");
		}
		out.println("<OL>");
		for(int i=0; i<numCurrentPrimes; i++)
		{
			out.println(" <LI>"+currentPrimes.get(i));
		}
		out.println("</OL>");
		out.println("</body></html>");
	}

	private PrimeList findPrimeList(ArrayList primeListCollection, 
			int numPrimes, int numDigits) {
		synchronized(primeListCollection)
		{
			for(int i=0; i<primeListCollection.size(); i++)
			{
				PrimeList primes = (PrimeList) primeListCollection.get(i);
				if((numPrimes == primes.numPrimes()) && 
						(numDigits == primes.numDigits()))
				{
					return primes;
				}
			}
			return null;
		}
	}

}
