package com.roch.fupin.utils;

import java.util.Calendar;

/**
 * 身份证号码工具类
 * 
 * @author wf
 *
 */
public class IDCardUtil {

	/**
	 * 根据身份证号获取省份编码
	 * 
	 * @param id
	 * @return
	 */
	public static String getProvinceCode(String id) {
		if (id.length() == 18) {
			return id.substring(0, 2);
		}
		return null;
	}

	/**
	 * 根据身份证号获取市编码
	 * 
	 * @param id
	 * @return
	 */
	public static String getCityCode(String id) {
		if (id.length() == 18) {
			return id.substring(2, 4);
		}
		return null;
	}

	/**
	 * 根据身份证号获取县区编码
	 * 
	 * @param id
	 * @return
	 */
	public static String getCountryCode(String id) {
		if (id.length() == 18) {
			return id.substring(4, 6);
		}
		return null;
	}

	/**
	 * 根据身份证号获取行政区划代码
	 * 
	 * @param id
	 * @return
	 */
	public static String getDistrictCode(String id) {
		if (id.length() == 18) {
			return id.substring(0, 6);
		}
		return null;
	}

	/**
	 * 根据身份证号获取行政区划代码
	 * 
	 * @param id
	 * @return
	 */
	public static String getBirthday(String id) {
		if (id.length() == 18) {
			return id.substring(6, 14);
		}
		return null;
	}

	/**
	 * 根据身份证号码获取年龄
	 *
	 * @param id
	 *            身份证号
	 * @throws Exception
	 *             身份证号错误时发生
	 * @return int - 年龄
	 */
	public static int getAge(String id) throws Exception {
		int age = -1;
		int length = id.length();
		String birthday = "";
		if (length == 15) {
			birthday = id.substring(6, 8);
			birthday = "19" + birthday;
		} else if (length == 18) {
			birthday = id.substring(6, 10);
		} else {
			throw new Exception("错误的身份证号");
		}
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		age = currentYear - (new Integer(birthday)).intValue();
		return age;
	}

	/**
	 * 根据身份证号获取性别
	 * 
	 * @param id
	 * @return
	 */
	public static String getSex(String id) {
		if (id.length() == 18) {
			int x = Integer.parseInt(id.substring(16));
			if (x % 2 == 0) {
				return "女";
			} else {
				return "男";
			}
		}
		return null;
	}
}
