package com.roch.fupin.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.webkit.WebView;
import android.widget.ZoomButtonsController;

/**
 * 基础工具�?
 * 
 * @author Liang 2014-08-29
 * 
 */
public class Utils {

	public static final double[] zoomDistance = { 50d, 100d, 200d, 500d, 1000d, 2000d, 5000d, 10000d, 20000d, 25000d, 50000d, 100000d, 200000d, 500000d, 1000000d, 2000000d };
	public static final int[] zoomLv = { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };

	/**
	 * 比较 手机端和服务器端的版本号
	 * 
	 * @param nativeVersion
	 *            手机端版本号
	 * @param serverVersion
	 *            服务端版本号
	 * @return 有新版本 true ; 反之 false
	 */
	public static boolean compareVersion(String nativeVersion, String serverVersion) {

		// 判断如果任意�?个�?�为null 或�?? "" 就返回false 表示不需要更�?
		if (StringUtil.isEmpty(nativeVersion) || StringUtil.isEmpty(serverVersion)) {
			return false;
		}
		String[] web_arr = serverVersion.split("\\.");
		// Web端大版本
		int web_major = Integer.parseInt(web_arr[0]);
		// Web端中版本
		int web_minor = Integer.parseInt(web_arr[1]);
		// Web端小版本
		int web_build = Integer.parseInt(web_arr[2]);

		String[] phone_arr = nativeVersion.split("\\.");
		// 手机端大版本
		int phone_major = Integer.parseInt(phone_arr[0]);
		// 手机端中版本
		int phone_minor = Integer.parseInt(phone_arr[1]);
		// 手机端小版本
		int phone_build = Integer.parseInt(phone_arr[2]);

		if (web_major - phone_major > 0) {
			return true;
		} else if (web_major - phone_major == 0) {
			if (web_minor - phone_minor > 0) {
				return true;
			} else if (web_minor - phone_minor == 0) {
				if (web_build - phone_build > 0) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 获取当前程序的版本号
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersionCode(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			String version = packInfo.versionName;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "1";
	}
	
	/**
     * @Description 判断是否是顶部activity
     * @param context
     * @param activityName
     * @return
     */
    public static boolean isTopActivy(Context context, String activityName) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cName = am.getRunningTasks(1).size() > 0 ? am
                .getRunningTasks(1).get(0).topActivity : null;

        if (null == cName)
            return false;
        return cName.getClassName().equals(activityName);
    }

	/**
	 * 判断是否为数字正�?
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		String text = str.substring(0, 1);
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(text).matches();
	}

//	/**
//	 * 经纬度转�?
//	 *
//	 * @param lat_a
//	 * @param lng_a
//	 * @param lat_b
//	 * @param lng_b
//	 * @return
//	 */
//	public static double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
//		double radLat1 = (lat_a * Math.PI / 180.0);
//		double radLat2 = (lat_b * Math.PI / 180.0);
//		double a = Math.abs(radLat1 - radLat2);
//		double b = Math.abs((lng_a - lng_b) * Math.PI / 180.0);
//		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
//		s = s * Common.EARTH_RADIUS;
//		s = Math.round(s * 10000) / 10000;
//		return s;
//	}

	// 验证邮箱格式
	public static boolean checkMailBox(String date) {
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(date);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	// dp to px
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	// px to dp
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	// 实现webview放大缩小控件隐藏
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static void setZoomControlGone(WebView webview) {
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			webview.getSettings().setDisplayZoomControls(false);
		} else {
			Class<?> classType;
			Field field;
			try {
				classType = WebView.class;
				field = classType.getDeclaredField("mZoomButtonsController");
				field.setAccessible(true);
				ZoomButtonsController mZoomButtonsController = new ZoomButtonsController(webview);
				mZoomButtonsController.getZoomControls().setVisibility(View.GONE);
				try {
					field.set(webview, mZoomButtonsController);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 安装APK文件
	 */
	public static void installApk(Context context, File file) {
		File apkfile = new File(file.getAbsolutePath());
		if (!apkfile.exists()) {
			return;
		}
		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
		context.startActivity(i);
	}

	/**
	 * 客户拜访，保留与客户的位置，精确到千�?
	 * 
	 */
	public static String getCustomVisitKm(double f) {
		float cc = (float) (f / 100); // 得到10.51==
		int d = Math.round(cc);// 四舍五入�?11
		float e = d / (float) 10;// �?10 也强转为float型的，再�?10除以�?==
		return String.valueOf(e);
	}

	public static int computeZoomDistance(double distance) {
		if (distance <= 0) {
			return 16;
		}
		if (distance > 2000000) {
			return 19;
		}
		for (int i = 0; i < zoomDistance.length; i++) {
			if (distance > zoomDistance[i] && distance < zoomDistance[i + 1]) {
				return zoomLv[zoomLv.length - 1 - i];
			}
		}
		return 16;
	}
}
