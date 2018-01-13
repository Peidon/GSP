package com.test;

import com.Apriori_SP.Apriori_SP;


public class Test {
	public static void main(String[] args) {
		 String filePath = "E:\\test.txt";  
	     Long t = System.currentTimeMillis();
	     int minSupportCount = 100; //最小支持度阈值 
	     Apriori_SP test = new Apriori_SP(filePath,minSupportCount);
	     test.generate();
	     System.out.println("It spent " + (System.currentTimeMillis() - t) + " Miller Seconds!");
	}
}
