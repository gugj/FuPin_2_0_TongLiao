package com.roch.fupin.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {
	// 单例模式, 构�?�函数私有化
	private Toast toast = null;

	/**
	 * 在屏幕中间显示Toast的方�?
	 * 
	 * @param context
	 *            上下文对�?
	 * @param content
	 *            显示的内�?
	 */
	public void showMiddleToast(Context context, String content) {
		if (null == toast) {
			toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(content);
		}
		toast.show();
	}

	/**
	 * 显示Toast方法 -- 长时�?
	 * 
	 * @param context
	 * @param content
	 */
	public void showMiddleLengthToast(Context context, String content) {
		if (null == toast) {
			toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(content);
		}
		toast.show();
	}

	/**
	 * 共用的显示Toast的方法--一般时间
	 * 
	 * @param context 上下文对象
	 * @param content 显示的内容
	 */
	public void showNormalToast(Context context, String content) {
		if (null == toast) {
			toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
		} else {
			toast.setText(content);
		}
		toast.show();
	}

	/**
	 * 共用的显示Toast的方--长时间
	 * 
	 * @param context
	 *            上下文对象
	 * @param content
	 *            显示的内容
	 */
	public void showLongToast(Context context, String content) {
		if (null == toast) {
			toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
		} else {
			toast.setText(content);
		}
		toast.show();
	}

	/**
	 * 共用的显示Toast的方�?--�?般时�?
	 * 
	 * @param context
	 *            上下文对�?
	 * @param content
	 *            显示的内�?
	 */
	public void showBottomToast(Context context, String content) {
		if (null == toast) {
			toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		} else {
			toast.setText(content);
		}
		toast.show();
	}
}
