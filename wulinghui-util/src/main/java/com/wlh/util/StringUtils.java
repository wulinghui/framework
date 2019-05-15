package com.wlh.util;


public class StringUtils extends org.apache.commons.lang3.StringUtils{
	public static int[] splitToInts(Object str,String regex){
		if(str == null) return null;
		String[] split = str.toString().split(regex);
		int[] is = new int[split.length];
		for (int i = 0; i < is.length; i++) {
			is[i] = Integer.parseInt(split[i]);
		}
		return is;
	}
}
