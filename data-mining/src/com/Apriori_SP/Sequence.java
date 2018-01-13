package com.Apriori_SP;

import java.util.Arrays;

public class Sequence {
	private String[] elements;// 序列包含的元素
	private int length;// 序列的长度
	private int count_sup; // 支持度阈值
	public Sequence(String[] e){
		super();
		elements = Arrays.copyOfRange(e, 0, e.length);
		length = e.length;
	}
	
	public String get(int i){
		return elements[i];
	}
	
	public String[] toArray(){
		return elements;
	}
	/**
	 * 判断s是否是当前序列的子序列
	 * @param s
	 * @return
	 */
	public boolean isContains(Sequence s){
		if(s.length > this.length){
			return false;
		}
		int i = 0;
		String[] sa = s.toArray();
		for(; i < this.length; i++){
			if(s.length >= this.length - i){
				return false;
			}
			if(elements[i].equals(s.get(0))) {
				if(s.length == 1){
					return true;
				}
				break;
			}
		}
		String[] newEle = Arrays.copyOfRange(elements, i + 1, this.length);
		String[] newS = Arrays.copyOfRange(sa, 1, sa.length);
		Sequence newThis = new Sequence(newEle);
		Sequence newSeq = new Sequence(newS);
		boolean j = newThis.isContains(newSeq);
		return j;
	}

	public int getCount_sup() {
		return count_sup;
	}

	public void setCount_sup(int count_sup) {
		this.count_sup = count_sup;
	}
}
