package com.roch.fupin.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/***
 * 手机连接网络状态的实体类
 */
public class NetConnect {

	public NetConnect() {
	}

	/**
	 * 检查当前手机连接网络的状态--WiFi、网络...
	 *
	 * @param ctx 
	 * @return     如果网络可用返回true，否则返回false <br/>
	 *
	 * 2016年10月28日
	 */
	public boolean ischeackNet(Context ctx) {
		ConnectivityManager conManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
		NetworkInfo mobileInfo = conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifiInfo = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if ((networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable())
				|| (null != wifiInfo && wifiInfo.isAvailable() && wifiInfo.isConnected())
				|| (null != mobileInfo && mobileInfo.isAvailable() && mobileInfo.isConnected())) {
			return true;
		}
		return false;
	}
}
