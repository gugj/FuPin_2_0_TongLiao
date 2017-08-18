package com.roch.fupin.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.roch.fupin_2_0.R;

import java.text.NumberFormat;

/**
 * 2016年10月27日   <br/>
 *
 * 通用工具类，内含各种工具方法以供调用
 */
public class CommonUtil {

	private static CommonUtil commonMethod;
	private Activity activity;

	/**
	 * 2016年10月27日
	 *
	 * 获取CommonUtil工具类的对象
	 */
	public static CommonUtil getInstance(Activity activity) {
		commonMethod = new CommonUtil();
		commonMethod.activity = activity;
		return commonMethod;
	}

	/**
	 * 通过获取的android版本改变状态栏的颜色
	 */
	public void getState() {
		// 如果手机的版本为  >=19(Android 4.4)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 通过WindowManager.LayoutParams设置Window的透明状态
			setTranslucentStatus(true);
			
			// 设置通知栏颜色
			SystemBarTintManager tintManager = new SystemBarTintManager(activity);
			tintManager.setStatusBarTintEnabled(true);
			tintManager.setStatusBarTintDrawable(ResourceUtil.getInstance().getDrawableByID(R.color.bule_155bbb));// 通知栏颜色
		}
	}

	/**
	 * 2016年10月27日
	 *
	 * 通过WindowManager.LayoutParams设置Window的透明状态
	 */
	@TargetApi(19)
	private void setTranslucentStatus(boolean on) {
		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	/**
	 * 获取手机型号
	 * 
	 * @return
	 */
	public static String getPhone_Xinghao() {
		return android.os.Build.MODEL;
	}

	/**
	 * 获取手机版本
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getPhone_Ver() {
		return android.os.Build.VERSION.SDK;
	}

	/**
	 * 获取手机品牌
	 * 
	 * @return
	 */
	public static String getPhone_Pinpai() {
		return android.os.Build.BRAND;
	}

	/**
	 * 获取手机系统版本
	 * 
	 * @return
	 */
	public static String getPhone_OS_Ver() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获取手机imei号码
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = manager.getDeviceId();
		return imei;
	}

	/**
	 * 获取android设备的唯�?ID
	 * 
	 * @return
	 */
	public static String getAndroidId(Context context) {
		return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
	}

	/**
	 * 获取日期
	 *
	 * @return
	 *
	 * 		2016年7月12日
	 *
	 *         ZhaoDongShao
	 *
	 */
	public static String getSpliteDate(String date) {
		return date != null ? date.split(" ")[0] : "";
	}

	/**
	 * 获取两个数字的比例
	 *
	 * @param d
	 * @param e
	 * @return
	 *
	 * 		2016年8月24日
	 *
	 *         ZhaoDongShao
	 *
	 */
	public static String getBili(double d, double e) {

		String bili = "";

		if (StringUtil.isNotEmpty(d) && StringUtil.isNotEmpty(e)) {

			NumberFormat dFormat = NumberFormat.getInstance();
			dFormat.setMaximumFractionDigits(2);

			bili = dFormat.format(d / e);

			return bili + "%";
		}
		return bili + "%";
	}
}
