package com.roch.fupin.utils;

/**
 * @author ZhaoDongShao
 * 2016年5月27日
 */
public class HTMLUtils {

	/**
	 * 删除字符串中的 <>（标签）
	 * @param str
	 * @return
	 */
	public static String delTag(String str) {
		str = str + "<>";
		StringBuffer buff = new StringBuffer();
		int start = 0;
		int end = 0;

		while (str.length() > 0) {
			start = str.indexOf("<");
			end = str.indexOf(">");
			if (start > 0) {
				buff.append(str.substring(0, start));
			}
			if (end > 0 && end <= str.length()) {
				str = str.substring(end + 1, str.length());
			} else {
				str = "";
			}
		}
		String result = buff.toString();
		while (result.startsWith(" ")) {
			result = result.substring(result.indexOf(" ") + 1, result.length());
		}
		String msg = result.replaceAll("[\\t\\n\\r]", "");;
		return msg;
	}
}
