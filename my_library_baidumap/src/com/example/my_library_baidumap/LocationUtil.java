package com.example.my_library_baidumap;
import android.content.Context;
import android.os.Handler;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.MapView;

/**
 * @author wfs
 * @created 2013-11-15
 * @version 1.0
 */
public class LocationUtil {
	/**
	 * 初始化定位信息并设置位置
	 */
	Context context;
	static LocationUtil lu;
	public LocationUtil(Context context) {
		super();
		this.context = context;
	}
	public static LocationUtil getInstance(Context context){
		return lu==null? new LocationUtil(context):lu;
	}

	public LocationClient createLocationClient(Handler handler, MapView mv) {
		LocationClient mLocationClient = new LocationClient(context);
		mLocationClient.registerLocationListener(new MyLocationListener(mv,
				handler));
		mLocationClient.setLocOption(createLocationClientOption());
		mLocationClient.requestLocation();
		mLocationClient.start();
		return mLocationClient;
	}

	private LocationClientOption createLocationClientOption() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//定位模式为高精度
		option.setOpenGps(true);
		option.setNeedDeviceDirect(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.setTimeOut(50000);
		return option;
	}
}
