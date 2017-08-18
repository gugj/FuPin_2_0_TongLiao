package com.example.my_library_baidumap;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @ClassName: MyLocationListener
 * @Description: 地图定位监听
 * @author 王富生
 * @date 2015年3月25日 上午10:34:34
 *
 */
public class MyLocationListener implements BDLocationListener {
	public static final String TAG = MyLocationListener.class.getSimpleName();
	private MapView mMapView;
	private BaiduMap baiduMap;
	private Handler handle;
	public static Locations lo;
	static double errLat = 4.9E-324;
	static double errLng = 4.9E-324;

	public MyLocationListener(MapView mMapView, Handler handle) {
		super();
		this.mMapView = mMapView;
		this.handle = handle;
	}

	/**
	 * 61 ： GPS定位结果 62 ： 扫描整合定位依据失败。此时定位结果无效。 63 ： 网络异常，没有成功向服务器发起请求。此时定位结果无效。
	 * 65 ： 定位缓存的结果。 66 ： 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果 67 ：
	 * 离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果 68 ： 网络连接失败时，查找本地离线定位时对应的返回结果
	 * 161： 表示网络定位结果 162~167： 服务端定位失败
	 * (type为162时返回的是("Lat":4.9E-324,"Lng":4.9E-324))
	 */
	@Override
	public void onReceiveLocation(BDLocation location) {
		if (location == null || location.getLatitude() == errLat) {
			return;
		} else {
			int type = location.getLocType();
			Log.d("tag", "" + type);

			if (type < 162) {
				setLocationStr(location);
			}
			if (null != mMapView) {
				updateCenter(location);
			}
		}
	}

	/**
	 * 
	 * @Title: updateCenter
	 * @Description:定位成功，更新中心点
	 * @param @param location
	 * @return void
	 * @throws
	 */
	private void updateCenter(BDLocation location) {
		baiduMap = mMapView.getMap();
		// 构造定位数据
		MyLocationData mlocData = new MyLocationData.Builder().accuracy(location.getRadius())
		// 此处设置开发者获取到的方向信息，顺时针0-360
				.direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
		// 设置定位数据
		baiduMap.setMyLocationData(mlocData);
		LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(point, 14);
		baiduMap.animateMapStatus(msu);
	}

	private void setLocationStr(BDLocation location) {
		Log.d(TAG, "定位成功：" + location.getLatitude() + "--" + location.getLongitude() + "--" + location.getAddrStr());
		
		if(location!=null){
		try {
			lo = new Locations();
			lo.setLat(location.getLatitude());
			lo.setLon(location.getLongitude());
			lo.setProvince(location.getProvince());
			lo.setCityName(location.getCity());
			lo.setAddress(location.getAddrStr());
			lo.setTime(getTime());
			Message m = new Message();
			m.obj = lo;
			m.what = 1;
			handle.sendMessage(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		

	}

	public static String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	public void onReceivePoi(BDLocation poiLocation) {
		if (poiLocation == null) {
			return;
		}
	}
}