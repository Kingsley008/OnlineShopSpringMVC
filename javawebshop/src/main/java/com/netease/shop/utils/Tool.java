package com.netease.shop.utils;

public class Tool {
	
	public static String getNewString(String input){
		String result="";
		try {
			result = new String(input.getBytes("iso-8859-1"),"UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	        
}
