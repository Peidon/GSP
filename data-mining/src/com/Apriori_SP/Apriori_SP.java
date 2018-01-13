package com.Apriori_SP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Apriori_SP {
	// 输入数据文件的路径
	private String filePath;
	// 最小支持度阈值
	private int minSupportCount;
	// 初始的id序列
	ArrayList<String[]> initSequences;

	public Apriori_SP(String path, int sc) {
		filePath = path;
		minSupportCount = sc;
		readFile();
	}

	private void readFile() {
		initSequences = new ArrayList<String[]>();
		File file = new File(filePath);
		String str;
		String[] array;// 每一行数组
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while ((str = in.readLine()) != null) {
				array = str.split("\t");//根据文本格式，需修改此处
				initSequences.add(array);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 产生频繁一项序列
	 */
	private ArrayList<Sequence> getFreqSingl() {
		int count = 0;
		ArrayList<Sequence> results = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		for (String[] ss : initSequences) {
			for (String s : ss) {
				if (!map.containsKey(s)) {
					map.put(s, 1);
				} else {
					count = map.get(s) + 1;
					map.put(s, count);
				}
				map.put(s, count);
			}
		}

		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String s = it.next();
			String[] key = new String[] { s };
			count = map.get(s);
			if (count >= minSupportCount) {
				System.out.println("{" + s + " :" + count + "}");
				results.add(new Sequence(key));
			}
		}
		return results;
	}

	/**
	 * 根据k频繁产生k+1频繁序列
	 * 
	 * @param freqSingle
	 *            频繁单项序列
	 * @return
	 */
	private ArrayList<Sequence> generateFrequency(ArrayList<Sequence> candidate) {
		ArrayList<Sequence> results = new ArrayList<>();
		
		for (int i = 0; i < candidate.size(); i++) {
			for (int j = 0; j < candidate.size(); j++) {
				if (i == j)
					continue;
				String[] array1 = candidate.get(i).toArray();
				String[] array2 = candidate.get(j).toArray();
				List<String> list1 = new ArrayList<>(Arrays.asList(Arrays
						.copyOfRange(array1, 1, array1.length)));
				List<String> list2 = new ArrayList<>(Arrays.asList(Arrays
						.copyOfRange(array2, 0, array2.length - 1)));
				if (list1.equals(list2)) {
					String [] merge = new String[array1.length + 1];
					System.arraycopy(array1, 0, merge, 0, array1.length);
					System.arraycopy(array2, array2.length - 1, merge, merge.length - 1, 1);
					Sequence seq = new Sequence(merge);
					if (countSupport(seq)) {
						results.add(seq);
					}
				}
			}
		}
		return results;
	}

	private boolean countSupport(Sequence seq) {
		int count = 0;
		boolean j = false;
		for(String[] ss: initSequences){
			Sequence init = new Sequence(ss);
			if(init.isContains(seq)){
				count ++ ;
			}
		}
		if(count >= minSupportCount){
			seq.setCount_sup(count);
			j = true;
		}
		return j;
	}
	
	public void generate(){
		ArrayList<Sequence> candidate = getFreqSingl();
		do{
			candidate = generateFrequency(candidate);
			for(Sequence ss: candidate){
				for(String s: ss.toArray()){
					System.out.print(s+"  ");
				}
				System.out.println(":" + ss.getCount_sup());
			}
			System.out.println(">>>>");
		}while(candidate.size() > 0);
	}
}
