package com.example.demo.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 字符处理类
 * @author Administrator
 *
 */
public class StringUtil {
	
	
	/**
	 * 判断是否空字符
	 * @param str
	 * @return
	 */
	public static boolean isEmptyStr(String str) {

		return StringUtils.isEmpty(str) || "undefined".equals(str) || "null".equals(str);
	}
	
	/**
	 * 判断是否空list
	 * @param list
	 * @return
	 */
	public static <T> boolean isEmptyList(List<T> list) {
		if(list.isEmpty()) {
			return true;
		}
		
		if(list.size() == 1 && list.get(0) == null) {
			return true;
		}
		
		return false;
	}

}
