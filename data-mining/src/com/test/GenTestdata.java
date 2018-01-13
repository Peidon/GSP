package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class GenTestdata {
	public static void main(String[] args) {
		StringBuffer s = new StringBuffer();
		Random rand = new Random();
		HashSet<Integer> hs = null;
		int r = 10000; //序列行数
		for(;r > 0; r--){
			int n = rand.nextInt(8) + 2; //序列个数
			hs = new HashSet<>();
			for(; n > 0; n--){
				hs.add(rand.nextInt(99) + 1);//序列取值范围
			}
			Iterator<Integer> i = hs.iterator();
			while(i.hasNext()){
				s.append(i.next() + " ");
			}
			s.append("\n");
		}
		
		String fileName = "E:\\test.txt";
		File file = new File(fileName);
		Writer out;
		try {
			out = new OutputStreamWriter(new FileOutputStream(file));
			out.write(s.toString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
