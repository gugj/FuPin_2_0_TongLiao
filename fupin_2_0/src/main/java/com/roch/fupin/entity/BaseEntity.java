package com.roch.fupin.entity;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 基本的实体类对象，里面仅只有如下一个方法：
 * 把json字符串通过gson转换成实体类，实现了Serializable序列化接口
 * @author 赵东绍
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 将json字符串通过gson转换成实体类
	 * @param str  json字符串
	 * @param cla  转换为的实体类
	 * @return
	 */
	public static<T> T parseToT(String str,Class<T> cla){
		try {
			System.err.println(cla.getName());
			Gson gson = new Gson();
			return gson.fromJson(str, cla);
//			return new Gson().fromJson(str, cla);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
