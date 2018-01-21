package com.utils;

import java.math.BigInteger;
import java.util.ArrayList;

public class PrimeList implements Runnable{

	private ArrayList primesFound;
	private int numPrimes, numDigits;
	
	public PrimeList(int numPrimes, int numDigits, boolean runInBackground)
	{
		primesFound = new ArrayList(numPrimes);
		this.numPrimes = numPrimes;
		this.numDigits = numDigits;
		if(runInBackground)
		{
			//在后台运行
			Thread t = new Thread(this);
			//设置较低的优先级
			t.setPriority(Thread.MIN_PRIORITY);
//			t.setPriority(Thread.NORM_PRIORITY);
			t.start();
		}
		else
		{
			run();
		}
	}

	public void run() {
		BigInteger start = Primes.random(numDigits);
		//产生numPrimes个随机数
		for(int i=0; i<numPrimes; i++)
		{
			start = Primes.nextPrime(start);
			synchronized(this)
			{
				primesFound.add(start);
				System.out.println("Prime "+i+" = "+start);
			}
		}
	}
	
	//判断是否已经产生完成
	public synchronized boolean isDone()
	{
		return (primesFound.size() == numPrimes);
	}
	//返回已经产生的数组
	public synchronized ArrayList getPrimes()
	{
		if(isDone())
		{
			return primesFound;
		}
		else
		{
			return (ArrayList) primesFound.clone();
		}
	}
	public int numDigits()
	{
		return numDigits;
	}
	public int numPrimes()
	{
		return numPrimes;
	}
	//返回已经产生的数组的大小
	public synchronized int numCalculatedPrimes()
	{
		return primesFound.size();
	}
}
