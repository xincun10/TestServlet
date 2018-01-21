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
			//�ں�̨����
			Thread t = new Thread(this);
			//���ýϵ͵����ȼ�
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
		//����numPrimes�������
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
	
	//�ж��Ƿ��Ѿ��������
	public synchronized boolean isDone()
	{
		return (primesFound.size() == numPrimes);
	}
	//�����Ѿ�����������
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
	//�����Ѿ�����������Ĵ�С
	public synchronized int numCalculatedPrimes()
	{
		return primesFound.size();
	}
}
