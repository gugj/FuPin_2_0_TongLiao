package com.roch.fupin.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 行政区划代码工具类，根据登陆用户的行政区划代码，判定是否为市级、县级、镇级、村级等.......
 * @author wf
 */
public class AdlcdUtil {

	public static void main(String[] args) {
		String adl_cd = "410181000100";
		System.out.println(isVillage(adl_cd));
	}

	/**
	 * 根据行政区划代码获取其行政区划代码非零部分 (主要用于sql对数据过滤)
	 * @param adl_cd
	 * @return
	 */
	public static String generateSelfCodePrefix(String adl_cd) {
		String prefixCode = "";
		if (isProvince(adl_cd)) {
			prefixCode = generateProvinceCodePrefix(adl_cd);
		} else if (isCity(adl_cd)) {
			prefixCode = generateCityCodePrefix(adl_cd);
		} else if (isCountry(adl_cd)) {
			prefixCode = generateCountryCodePrefix(adl_cd);
		} else if (isTown(adl_cd)) {
			prefixCode = generateTownCodePrefix(adl_cd);
		} else {
			prefixCode = adl_cd;
		}
		return prefixCode;
	}

	/**
	 * 根据行政区划代码获取其上一级行政区划代码 (主要用于对前台控件的数据过滤)\(可返回多级代码)
	 * @param adl_cd
	 * @return
	 */
	public static List<String> generateParentCodes(String adl_cd) {
		List<String> parentCodes = new ArrayList<String>();
		if (isCity(adl_cd)) {
			parentCodes.add(generateProvinceCodePrefix(adl_cd) + "0000000000");
		} else if (isCountry(adl_cd)) {
			parentCodes.add(generateProvinceCodePrefix(adl_cd) + "0000000000");
			parentCodes.add(generateCityCodePrefix(adl_cd) + "00000000");
		} else if (isTown(adl_cd)) {
			parentCodes.add(generateProvinceCodePrefix(adl_cd) + "0000000000");
			parentCodes.add(generateCityCodePrefix(adl_cd) + "00000000");
			parentCodes.add(generateCountryCodePrefix(adl_cd) + "000000");
		} else {
			parentCodes.add(generateProvinceCodePrefix(adl_cd) + "0000000000");
			parentCodes.add(generateCityCodePrefix(adl_cd) + "00000000");
			parentCodes.add(generateCountryCodePrefix(adl_cd) + "000000");
			parentCodes.add(generateTownCodePrefix(adl_cd) + "000");
		}
		return parentCodes;
	}

	/**
	 * 根据行政区划代码获取市级行政区划代码
	 * @param adl_cd
	 * @return
	 */
	public static String generateCityCode(String adl_cd) {
		String prefixCode = "";
//		prefixCode = generateCityCodePrefix(adl_cd) + "0000000000";
		prefixCode = generateCityCodePrefix(adl_cd) + "00000000";
		return prefixCode;
	}

	/**
	 * 根据行政区划代码获取区县级行政区划代码
	 * @param adl_cd
	 * @return
	 */
	public static String generateCountryCode(String adl_cd) {
		String prefixCode = "";
		prefixCode = generateCountryCodePrefix(adl_cd) + "000000";
		return prefixCode;
	}

	/**
	 * 根据行政区划代码获取乡镇级行政区划代码
	 * @param adl_cd
	 * @return
	 */
	public static String generateTownCode(String adl_cd) {
		String prefixCode = "";
		prefixCode = generateTownCodePrefix(adl_cd) + "000";
		return prefixCode;
	}

	/**
	 * 判断行政区划代码是否为省级
	 * @param adl_cd
	 * @return
	 */
	public static boolean isProvince(String adl_cd) {
		boolean isProvince = false;
		if (StringUtil.isNotEmpty(adl_cd)) {
			if (adl_cd.length() == 12) {
				if (adl_cd.substring(2, 12).equals("0000000000")) {
					isProvince = true;
				}
			}
		}
		return isProvince;
	}

	/**
	 * 判断行政区划代码是否为市级
	 * @param adl_cd
	 * @return
	 */
	public static boolean isCity(String adl_cd) {
		boolean isCity = false;
		if (StringUtil.isNotEmpty(adl_cd)) {
			if (adl_cd.length() == 12) {
				if (adl_cd.substring(4, 12).equals("00000000") && !adl_cd.substring(2, 4).equals("00")) {
					isCity = true;
				}
			}
		}
		return isCity;
	}

	/**
	 * 判断行政区划代码是否为县级
	 * @param adl_cd
	 * @return
	 */
	public static boolean isCountry(String adl_cd) {
		boolean isCountry = false;
		if (StringUtil.isNotEmpty(adl_cd)) {
			if (adl_cd.length() == 12) {
				if (adl_cd.substring(6, 12).equals("000000")
						&& !adl_cd.substring(2, 4).equals("00")
						&& !adl_cd.substring(4, 6).equals("00")) {
					isCountry = true;
				}
			}
		}
		return isCountry;
	}

	/**
	 * 判断行政区划代码是否为乡镇级
	 * @param adl_cd
	 * @return
	 */
	public static boolean isTown(String adl_cd) {
		boolean isTown = false;
		if (StringUtil.isNotEmpty(adl_cd)) {
			if (adl_cd.length() == 12) {
				if (adl_cd.substring(10, 12).equals("00")
						&& !adl_cd.substring(2, 4).equals("00")
						&& !adl_cd.substring(4, 6).equals("00")
						&& !adl_cd.substring(6, 10).equals("0000")) {
					isTown = true;
				}
			}
		}
		return isTown;
	}

	/**
	 * 判断行政区划代码是否为村级
	 * @param adl_cd
	 * @return
	 */
	public static boolean isVillage(String adl_cd) {
		boolean isVillage = false;
		if (StringUtil.isNotEmpty(adl_cd)) {
			if (adl_cd.length() == 12) {
				if (!adl_cd.substring(10, 12).equals("00")) {
					isVillage = true;
				}
			}
		}
		return isVillage;
	}

	/**
	 * 获取给定行政区划的省级代码(非零部分)
	 * @param adl_cd
	 * @return
	 */
	public static String generateProvinceCodePrefix(String adl_cd) {
		String prefixCode = "";
		if (StringUtil.isNotEmpty(adl_cd)) {
			if (adl_cd.length() == 12) {
				prefixCode = adl_cd.substring(0, 2);
			}
		}
		return prefixCode;
	}

	/**
	 * 获取给定行政区划的市级代码(非零部分)
	 * @param adl_cd
	 * @return
	 */
	public static String generateCityCodePrefix(String adl_cd) {
		String prefixCode = "";
		if (StringUtil.isNotEmpty(adl_cd)) {
			if (adl_cd.length() == 12) {
				prefixCode = adl_cd.substring(0, 4);
			}
		}
		return prefixCode;
	}

	/**
	 * 获取给定行政区划的区县级代码(非零部分)
	 * @param adl_cd
	 * @return
	 */
	public static String generateCountryCodePrefix(String adl_cd) {
		String prefixCode = "";
		if (StringUtil.isNotEmpty(adl_cd)) {
			if (adl_cd.length() == 12) {
				prefixCode = adl_cd.substring(0, 6);
			}
		}
		return prefixCode;
	}

	/**
	 * 获取给定行政区划的乡镇级代码(非零部分)
	 * @param adl_cd
	 * @return
	 */
	public static String generateTownCodePrefix(String adl_cd) {
		String prefixCode = "";
		if (StringUtil.isNotEmpty(adl_cd)) {
			if (adl_cd.length() == 12) {
				prefixCode = adl_cd.substring(0, 9);
			}
		}
		return prefixCode;
	}
}
