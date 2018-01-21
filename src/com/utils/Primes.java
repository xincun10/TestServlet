package com.utils;

import java.math.BigInteger;

public class Primes {

	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger ONE = BigInteger.ONE;
	private static final BigInteger TWO = new BigInteger("2");
	//指定错误值为100，从而指定发生错误的概率
	private static final int ERR_VAL = 100;
	//产生一个素数
	public static BigInteger nextPrime(BigInteger start)
	{
		if(isEven(start))
		{
			//start是合数
			start = start.add(ONE);
		}
		else
		{
			start = start.add(TWO);
		}
		if(start.isProbablePrime(ERR_VAL))//是否可能是素数
		{
			return start;
		}
		else
		{
			return nextPrime(start);
		}
		
	}

	//判断是否是合数
	private static boolean isEven(BigInteger n) {
		return (n.mod(TWO).equals(ZERO));
	}
	
	private static StringBuffer[] digits = 
		{new StringBuffer("0"), new StringBuffer("1"),
		 new StringBuffer("2"), new StringBuffer("3"),
		 new StringBuffer("4"), new StringBuffer("5"),
		 new StringBuffer("6"), new StringBuffer("7"),
		 new StringBuffer("8"), new StringBuffer("9")};
	
	//随机产生一个StringBuffer类型的数字字符串
	private static StringBuffer randomDigit(boolean isZeroOK)
	{
		int index;
		if(isZeroOK)
		{
			index = (int) Math.floor(Math.random()*10);
		}
		else
		{
			index = 1 + (int) Math.floor(Math.random()*9);
		}
		return digits[index];
	}
	
	//产生指定位数的BigInteger
	public static BigInteger random(int numDigits)
	{
		StringBuffer s = new StringBuffer("");
		for(int i=0; i<numDigits; i++)
		{
			if(i==0)
			{
				s.append(randomDigit(false));
			}
			else
			{
				s.append(randomDigit(true));
			}
		}
		return (new BigInteger(s.toString()));
	}
	
//	public static void main(String[] args)
//	{
//		//测试primeList
//		PrimeList primeList = new PrimeList(25, 150, true);
//		Thread t = new Thread(primeList);
//		t.start();
//		int numDigits;
//		try{
//			numDigits = Integer.parseInt(args[0]);
//		}catch(Exception e)
//		{
//			numDigits = 150;
//		}
//		BigInteger start = random(numDigits);
//		for(int i =0; i<50; i++)
//		{
//			start = nextPrime(start);
//			System.out.println("Prime "+i+" = "+start);
//		}
//	}
}
