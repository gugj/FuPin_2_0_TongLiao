package com.roch.fupin.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DaoConfig;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.HelpObjectMenu;
import com.roch.fupin.entity.HelpSubjectMenu;
import com.roch.fupin.entity.HomeMenu;
import com.roch.fupin.entity.MoreMenu;
import com.roch.fupin.entity.NoPoorProjectMenu;
import com.roch.fupin.entity.NoticeMenu;
import com.roch.fupin.entity.Sroles;
import com.roch.fupin.entity.StatisticMenu;
import com.roch.fupin.entity.User;
import com.roch.fupin.entity.Whcd;
import com.roch.fupin.entity.WhcdAndYhzgxList;
import com.roch.fupin.entity.Yhzgx;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.CommonUtil;
import com.roch.fupin.utils.FileUtil;
import com.roch.fupin.utils.LogUtil;
import com.roch.fupin.utils.NetConnect;
import com.roch.fupin.utils.SharePreferencesUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.ToastUtils;
import com.roch.fupin_2_0.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Stack;

public class MyApplication extends Application {

	private static MyApplication myapp;
	Stack<Activity> activityStack;

	private static HttpUtils httpUtils;
	private static BitmapUtils bitmapUtils;
	private static NetConnect netConnect;
	private static DbUtils dbUtils;
	private static SharePreferencesUtil sharePreferencesUtil;
	private static CommonUtil commonUtil;
	private static ToastUtils toastUtil;

	// 是否下载完成
	private boolean isDownload = true;
	/**
	 * 全局的一个存储地变的变量
	 */
	public static String now_address, gx_address = null;

	/**
	 * 获取当前的定位location
	 */
	public static BDLocation location = null;
	/**
	 * 百度地图定位的客户端LocationClient
	 */
	public LocationClient mLocationClient = null;
	public MyLocationListener myListener;
	public Vibrator mVibrator;
	boolean isFirstLoc = true;// 是否首次定位
	private String TAG = "MyApplication";

	/**
	 * 文化程度list
	 */
	public static List<Whcd> lWhcd = null;
	
	/**
	 * 与户主关系list
	 */
	public static List<Yhzgx> lYhzgx = null;

	@Override
	public void onCreate() {
		super.onCreate();

//		SDKInitializer.initialize(getApplicationContext());
		SDKInitializer.initialize(this);
		mLocationClient = new LocationClient(getApplicationContext());
		//LocationClientOption 该类用来设置定位SDK的定位方式。
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); //打开GPRS
		option.setAddrType("all");//返回的定位结果包含地址信息
		option.setCoorType("bd09ll");//返回的定位结果是百度经纬度,默认值gcj02
		option.setPriority(LocationClientOption.GpsOnly); // 设置GPS优先
		option.setScanSpan(1000); //设置发起定位请求的间隔时间为5000ms
		option.disableCache(false);//禁止启用缓存定位
//      option.setPoiNumber(5);    //最多返回POI个数
//      option.setPoiDistance(1000); //poi查询距离
//      option.setPoiExtraInfo(true);  //是否需要POI的电话和地址等详细信息
		LocationClientOption option2 = new LocationClientOption(option);
		mLocationClient.setLocOption(option2);  //设置定位参数
		myListener = new MyLocationListener();
		mLocationClient.registerLocationListener(myListener);

		myapp = this;
		try {
			if (!FileUtil.isFileExist(Common.CACHE_DIR)) {
				FileUtil.creatSDDir(Common.CACHE_DIR);
			}
			if (!FileUtil.isFileExist(Common.DOWNLOAD_DIR)) {
				FileUtil.creatSDDir(Common.DOWNLOAD_DIR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		httpUtils = new HttpUtils();
		httpUtils.configCurrentHttpCacheExpiry(1000 * 10);// 超时时间 Long配置当前Http缓存到期
		// 设置超时时间
		httpUtils.configTimeout(10 * 1000);// 连接超时  //指的是连接一个url的连接等待时间。
		httpUtils.configSoTimeout(60 * 1000);// 获取数据超时  //指的是连接上一个url，获取response的返回等待时间

		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.empty_photo) //设置图片在下载期间显示的图片
				.showImageForEmptyUri(R.drawable.empty_photo) //设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.empty_photo) //设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true) //设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) //设置下载的图片是否缓存在SD卡中
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT) //设置图片的缩放方式
				.bitmapConfig(Bitmap.Config.RGB_565) //设置图片的解码类型
				.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions)
				.discCacheSize(50 * 1024 * 1024)//
				.discCacheFileCount(100)// 缓存一百张图片
				.writeDebugLogs()
				.build();
		ImageLoader.getInstance().init(config);
		
		// 开启线程
		new Thread(new MyThread()).start();
		// 获取资产目录json.txt
		findJson();
	}

	/**
	 * 获取application的实例
	 */
	public static MyApplication getInstance() {
		return myapp;
	}

	/**
	 * 每隔10s发送广播判断网络连接状态
	 */
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Intent intent = null;
			if (msg.what == 1) {
				boolean isConntion = getNetConnectInstance().ischeackNet(myapp);
				intent = new Intent(Common.MESSAGE_RECEIVED_ACTION);
				intent.putExtra(Common.KEY_MESSAGE, isConntion);
			}
			sendBroadcast(intent);
			super.handleMessage(msg);
		}
	};

	/**
	 * 自定义的判断网络连接状态的线程类
	 * @author ZhaoDongShao
	 * 2016年5月12日
	 */
	public class MyThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(5000);// 线程暂停10秒，单位毫秒
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);// 发送消息
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 自定义的地图定位监听类，继承自百度地图的监听类BDLocationListener <br/>
	 * 2016年10月28日
	 */
	class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null) {
				sendBroadCast("定位失败!","");
				return;
			}
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
			}
			String city = location.getCity(); //获取城市
			String addStr=location.getAddrStr(); //获取详细地址
			String street=location.getStreet();
			if (!StringUtil.isEmpty(city)) {
				MyApplication.location = location;
				sendBroadCast(city,addStr);
			}
			LogUtil.println("MyApplacation定位成功：==="+addStr);
			SharedPreferences sp = getSharedPreferences("loginactivty", Context.MODE_APPEND);
			SharedPreferences.Editor ed = sp.edit();
			ed.putString("location_str",addStr);
			ed.putString("street",street);
			ed.commit();
		}
	}

	/**
	 * 发送定位的广播---城市位置和详细位置
	 * @param city 百度地图定位返回的信息---城市
	 * @param locationStr 百度地图定位返回的信息---详细信息
	 */
	public void sendBroadCast(String city,String locationStr) {
		Intent intent = new Intent();
		intent.setAction(Common.ADRESS_ACTION);
		intent.putExtra("address", city);
		intent.putExtra("locationStr", locationStr);
		sendBroadcast(intent);
	}

	// /**
	// * 显示请求地址
	// * @param address
	// */
	// public void logMsg(String address) {
	// try {
	//
	// AdlCode addressString =
	// getSharePreferencesUtilInstance().getNowCity(this, Common.LoginName);
	//
	// if (addressString != null && !addressString.getAd_nm().equals("")) {
	// getSharePreferencesUtilInstance().saveNowCity(this, new AdlCode("",
	// MyApplication.now_address,
	// PingYinUtil.getPingYin(MyApplication.now_address)), Common.LoginName);
	// sendBroadCast(address);
	//
	// }
	// else {
	//
	// if (addressString!=null&&addressString.getAd_nm().equals(address)) {
	// return;
	// }
	// else {
	// getSharePreferencesUtilInstance().saveNowCity(this, new AdlCode("",
	// MyApplication.now_address,
	// PingYinUtil.getPingYin(MyApplication.now_address)), Common.LoginName);
	// sendBroadCast(address);
	// }
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * @return 获取手机连接网络状态的实体类对象NetConnect
	 */
	public NetConnect getNetConnectInstance() {
		if (netConnect == null) {
			netConnect = new NetConnect();
		}
		return netConnect;
	}

	/**
	 * 获取当前Toast对象的实例
	 * @return 实例
	 */
	public ToastUtils getToastUtilsInstance() {
		if (toastUtil == null) {
			toastUtil = new ToastUtils();
		}
		return toastUtil;
	}

	/**
	 * BitmapUtils不是单例， 根据需要要重载多个获取实例的方法
	 * @return
	 */
	public BitmapUtils getBitmapUtilsInstance() {
		if (bitmapUtils == null) {
			bitmapUtils = new BitmapUtils(myapp);
		}
		return bitmapUtils;
	}

	/**
	 * 根据登陆用户名(admin),创建xUtils数据库对象，数据库名为:admin_fupin_2_0_DB,
	 * 并创建数据库表table首页、扶贫对象、通知公告、城市、统计表...等
	 * @return  返回xUtils数据库对象
	 */
	public DbUtils getDbUtilsInstance(String loginname) {
		DaoConfig config = new DaoConfig(myapp);
		config.setDbName(loginname + "_" + Common.DB_NAME); //设置数据库名——admin_fupin_2_0_DB
		config.setDbVersion(Common.DB_VERSION); //设置数据库版本——3
		if (dbUtils == null) {
			dbUtils = DbUtils.create(config);
		}
		try {
			// 首页
			dbUtils.createTableIfNotExist(HomeMenu.class);
			// 扶贫对象
			dbUtils.createTableIfNotExist(HelpSubjectMenu.class);
			// 帮扶主体
			dbUtils.createTableIfNotExist(HelpObjectMenu.class);
			// 更多
			dbUtils.createTableIfNotExist(MoreMenu.class);
			// 通知公告
			dbUtils.createTableIfNotExist(NoticeMenu.class);
			// 脱贫攻坚项目
			dbUtils.createTableIfNotExist(NoPoorProjectMenu.class);
			// 城市
			dbUtils.createTableIfNotExist(AdlCode.class);
			// 统计报表
			dbUtils.createTableIfNotExist(StatisticMenu.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return dbUtils;
	}

	public void ColseDbutil() {
		if (dbUtils != null) {
			dbUtils.close();
			dbUtils = null;
		}
	}

	/**
	 * 获取本地asset资产目录中的json.txt，并将其赋值给文化程度和与户主关系两个实体类
	 * 2016年5月19日
	 * ZhaoDongShao
	 */
	public void findJson() {
		try {
			InputStream in = getAssets().open("json.txt");
			// 得到数据流的大小
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			String text = new String(buffer);
			if (text != null && !text.equals("")) {
				WhcdAndYhzgxList whcdAndYhzgxList = WhcdAndYhzgxList.parseToT(text, WhcdAndYhzgxList.class);
				if (whcdAndYhzgxList != null) {
					lWhcd = whcdAndYhzgxList.getWordbook_whcd();
					lYhzgx = whcdAndYhzgxList.getWordbook_yhzgx();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取SharePreferences工具类实例
	 * @return
	 */
	public SharePreferencesUtil getSharePreferencesUtilInstance() {
		if (sharePreferencesUtil == null) {
			sharePreferencesUtil = new SharePreferencesUtil();
		}
		return sharePreferencesUtil;
	}

	/**
	 * CommonUtil实例
	 * @return
	 */
	public CommonUtil getCommonUtilInstance() {
		if (commonUtil == null) {
			commonUtil = new CommonUtil();
		}
		return commonUtil;
	}

	/**
	 * 返回xUtils访问网络的实例
	 * @return
	 */
	public HttpUtils getHttpUtilsInstance() {
		if (httpUtils != null) {
			return httpUtils;
		} else {
			httpUtils = new HttpUtils();
		}
		httpUtils.configCurrentHttpCacheExpiry(1000 * 10);// 超时时间 Long配置当前Http缓存到期
		// 设置超时时间
		httpUtils.configTimeout(10 * 1000);// 连接超时  //指的是连接一个url的连接等待时间。
		httpUtils.configSoTimeout(60 * 1000);// 获取数据超时  //指的是连接上一个url，获取response的返回等待时间
		return httpUtils;
	}

	/**
	 * 清除用户信息
	 */
	public void clearLoginUser(String loginname) {
		getSharePreferencesUtilInstance().clearLoginUser(this, loginname);
	}

	/**
	 * 保存登陆用户---根据登陆用户名获取登陆用户信息，并存到sp中
	 * @param login
	 */
	public void saveLogin(User login) {
		if (login != null) {
			getSharePreferencesUtilInstance().saveLoginUser(this, login);
		}
	}

	/**
	 * 将登陆用户的权限保存到sp中
	 * @param sroles
	 * 2016年7月2日 ZhaoDongShao
	 */
	public void saveSroles(Sroles sroles) {
		if (sroles != null) {
			getSharePreferencesUtilInstance().saveSroles(this, sroles);
		}
	}

	/**
	 * 获取当前登录用户权限的实体类对象
	 * @return
	 * 2016年7月2日  ZhaoDongShao
	 */
	public Sroles getSroles() {
		return getSharePreferencesUtilInstance().getSroles(this);
	}

	public boolean isDownload() {
		return isDownload;
	}

	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}

	/**
	 * add Activity 添加Activity到栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * get current Activity 获取当前Activity（栈中最后一个压入的�?
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前Activity（栈中最后一个压入的�?
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束�?有Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * �?出应用程�?
	 */
	public void AppExit() {
		try {
			finishAllActivity();
		} catch (Exception e) {
		}
	}

	// /**
	// * 用户是否登陆
	// * @return
	// */
	// public boolean isLogin() {
	// if (getLogin() != null && !TextUtils.isEmpty(getLogin().getId())) {
	// return true;
	// }
	// return false;
	// }

	/**
	 * 通过登陆名称获取用户实例
	 * @return
	 */
	public User getLogin(String loginname) {
		return getSharePreferencesUtilInstance().getLoginUser(this, loginname);
	}
}
