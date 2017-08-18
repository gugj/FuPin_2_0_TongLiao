package com.roch.fupin.utils;

import java.io.InputStream;

import com.roch.fupin.app.MyApplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * 资源工具类——单例模式
 */
public class ResourceUtil {
	
	private  ResourceUtil(){
	}

	private static ResourceUtil getResourceUtil;
	
	/**
	 * @return 获取ResourceUtil的实例
	 */
	public static ResourceUtil getInstance(){
		if (getResourceUtil == null) {
			getResourceUtil = new ResourceUtil();
		}
		return getResourceUtil;
	}


	/***
	 * 得到View
	 * 
	 * @param resId
	 * @return
	 */
	public  String getTextViewById(Activity ctx, int resId) {
		String str = ((TextView) ctx.findViewById(resId)).getText().toString();
		return str;
	}

	/***
	 * SetView
	 * 
	 * @param resId
	 * @return
	 */
	public  void setTextViewById(Activity ctx, int resId, String str) {
		((TextView) ctx.findViewById(resId)).setText(str);
	}
	
	/**
	 * 获取资源文件
	 */
	public  String getStringById(int resId) {
		return MyApplication.getInstance().getResources().getString(resId);
	}

	public  int getColorById(int resId) {
		return MyApplication.getInstance().getResources().getColor(resId);
	}

	/**
	 * 2016年10月27日
	 *
	 * 通过资源ID获取Drawable对象
	 */
	public  Drawable getDrawableByID(int resId) {
		return MyApplication.getInstance().getResources().getDrawable(resId);
	}

	public  float getDensity() {
		return MyApplication.getInstance().getResources().getDisplayMetrics().density;
	}

	public  Bitmap getBitmapById(int resId) {
		InputStream is = MyApplication.getInstance().getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, null);
	}

	public  float getDeminVal(int redId) {
		return MyApplication.getInstance().getResources().getDimension(redId);
	}

	public static int getResIdentifier(String id, String type) {
		return MyApplication.getInstance().getResources().getIdentifier(id, type, MyApplication.getInstance().getPackageName());
	}
	
	/**
	 * 
	 *
	 * @param id
	 * @return
	 *
	 * 2016年5月9日
	 *
	 * ZhaoDongShao
	 *
	 */
	public String[] getStringArrayById(int id){
		return MyApplication.getInstance().getResources().getStringArray(id);
	}
}
