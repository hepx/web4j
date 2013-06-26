package com.xixi.web4j.util;

public class ToolUtils {

	/**
	 * 判断字符串是否为空，null "" " " 将返回true
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s){
		return s!=null?"".equals(s.trim()):true;
	}
	
}
