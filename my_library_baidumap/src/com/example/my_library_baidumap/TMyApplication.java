package com.example.my_library_baidumap;

import com.baidu.mapapi.SDKInitializer;

import android.app.Application;

public class TMyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(this);
	}
}
