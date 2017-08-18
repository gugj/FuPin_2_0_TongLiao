package com.roch.fupin.utils;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSONUtil {

	/**
	 * 说明： （1）、普通对象，如User user=new User();得到type方式，user.class
	 * （2）、复杂点的对象，如List<User> users=new ArrayList<User>();得到type方式,
	 * 			new com.google.gson.reflect.TypeToken<List<User>>(){}.getType()
	 * 				    如Map<String,User> = new HashMap<String,User> 得到type方式，
	 *  		new com.google.gson.reflect.TypeToken<Map<String,User>(){}.getType()
	 */

	/**
	 * 转JSON字符串
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}

	/**
	 * 转JSON字符串
	 * @param obj
	 * @param type
	 * @return
	 */
	public static String objectToJson(Object obj, Type type) {
		Gson gson = new Gson();
		String json = gson.toJson(obj, type);
		return json;
	}

	/**
	 * 转JSON字符串
	 * @param obj
	 * @param type
	 * @param datePattern  日期格式 如：yyyy-MM-dd
	 * @return
	 */
	public static String objectToJson(Object obj, Type type, String datePattern) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(java.util.Date.class,new DateSerializerUtils())
				.setDateFormat(datePattern)
				.create();
		String json = gson.toJson(obj, type);
		return json;
	}

	/**
	 * JSON字符串转对象
	 * @param json
	 * @param type
	 * @return
	 */
	public static Object fromJson(String json, Type type) {
		Gson gson = new Gson();
		Object object = gson.fromJson(json, type);
		return object;
	}

	/**
	 * JSON字符串转对象
	 * @param json
	 * @param type
	 * @param datePattern  日期格式 如：yyyy-MM-dd
	 * @return
	 */
	public static Object fromJson(String json, Type type, String datePattern) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(java.util.Date.class,new DateDeserializerUtils())
				.setDateFormat(datePattern)
				.create();
		Object object = gson.fromJson(json, type);
		return object;
	}
}
