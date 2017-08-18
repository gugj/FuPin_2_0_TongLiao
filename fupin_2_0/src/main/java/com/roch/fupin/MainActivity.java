package com.roch.fupin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.roch.fupin.adapter.MenuListAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NormalDailog;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.AdlCodeListResult;
import com.roch.fupin.entity.Menu;
import com.roch.fupin.entity.QianDaoJiLu;
import com.roch.fupin.entity.QianDaoJiLu_ResultList;
import com.roch.fupin.entity.User;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.LogUtil;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin.view.NumberProgressBar;
import com.roch.fupin.view.OnProgressBarListener;
import com.roch.fupin_2_0.R;
import com.zxing.activity.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@ContentView(R.layout.mian)
public class MainActivity extends BaseActivity implements OnClickListener, OnProgressBarListener {

	/**
	 * 没有网络时的网络异常的容器布局
	 */
	@ViewInject(R.id.rl_no_internet)
	RelativeLayout rl_no_internet;
	
	@ViewInject(R.id.toolbar)
	Toolbar Toolbar;
	
	/**
	 * 快捷菜单的容器RelativeLayout
	 */
	@ViewInject(R.id.rl_kjcd)
	RelativeLayout rl_kjcd;
	/**
	 * 快捷菜单的图标
	 */
	@ViewInject(R.id.iv_icon)
	ImageView iv_icon;
	/**
	 * 侧滑菜单中的ListView
	 */
	@ViewInject(R.id.id_lv)
	ListView lv;
	/**
	 * 整个Main布局文件的容器DrawerLayout，包含了要显示的布局和侧滑菜单
	 */
	@ViewInject(R.id.id_drawerlayout)
	DrawerLayout mDrawerLayout;
	/**
	 * toolbar右侧选择城市按钮
	 */
	@ViewInject(R.id.tv_city)
	TextView tv_city;
	/**
	 * 侧滑菜单中的设置按钮
	 */
	@ViewInject(R.id.tv_setting)
	TextView tv_setting;
	/**
	 * 扫一扫
	 */
	@ViewInject(R.id.iv_saoyisao)
	ImageView iv_saoyisao;
	/**
	 * 签到
	 */
	@ViewInject(R.id.iv_qiandao)
	ImageView iv_qiandao;
	/**
	 * 侧滑菜单中登录用户的名字
	 */
	@ViewInject(R.id.tv_name)
	TextView tv_name;

	/**
	 * 存有Menu对象的List集合---侧滑菜单
	 */
	List<Menu> listmenu;
	/**
	 * 侧滑菜单中登录用户、头像、图标等的容器
	 */
	@ViewInject(R.id.ll_mine)
	LinearLayout ll_mine;
	/**
	 * toolbar的中间的标题title
	 */
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	/**
	 *  顶部导航的高度
	 */
	public static int StruesHeight;
	
	/**
	 * 侧滑菜单的开关ActionBarDrawerToggle
	 */
	ActionBarDrawerToggle mActionBarDrawerToggle;
	/**
	 * 自定义的Menu菜单的适配器类，继承自BaseAdapter
	 */
	MenuListAdapter adapter;

	/**
	 * 自定义的状态栏，是一个LinearLayout布局，在toolbar的上面
	 */
	@ViewInject(R.id.ll)
	LinearLayout layout;
	/**
	 * 登陆用户的id
	 */
	String user_id;
	/**
	 * 登陆用户的名称
	 */
	String rolename_app;
	/**
	 * 定位时的位置信息
	 */
	String location_address;

	int keyCodeCount = 0; // 按下返回键的次数
	
	/**
	 * 存放6个侧滑菜单中fragment的数组
	 */
	Fragment[] fragments;
	FragmentManager manager;
	FragmentTransaction transaction;
	private Handler mHandler;
	
	/**
	 * 自定义的判断网络连接状态的广播
	 */
	InternetBroadcast mBroadcast;
	User user = null;
	AdlCode address = null;
	boolean loction = false;
	
	/**
	 * AlertDialog的Builder
	 */
	AlertDialog.Builder builder = null;

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mian);

		initFindView();
//		ViewUtils.inject(this);
		// 设置顶部状态栏的颜色
		setStatusBarColor();

		initToolbar();
		MyApplication.getInstance().addActivity(this);

		//获取本地APP的版本号
		getVersionCode();
		//请求服务器中APP的版本号
		requestNetVersionCode();

		// 判断登录用户权限---如果是1，就让选择城市，如果是0，不让选择城市
//		Sroles sroles = MyApplication.getInstance().getSroles();
//		if (sroles.getPdatafilter() == 1) {
//			tv_city.setVisibility(View.VISIBLE);
//		} else if (sroles.getPdatafilter() == 0) {
//			tv_city.setVisibility(View.GONE);
//		}
		tv_city.setVisibility(View.GONE);

		// 获取手机的屏幕密度DPI
		initDensityDpi();
		mHandler = new Handler();

		// 初始化6个fragment，并将他们都隐藏，只显示HomeFragment即快捷菜单的fragment
		initView();
		// 初始化侧滑菜单栏的数据源，和登陆用户栏的信息，并给ListView和登陆用户一栏的容器设置点击监听事件
		initDate();
		// 注册广播--对网络连接状态判断，如果没网就显示layout布局，否则不显示
		registerMessageReceiver();
	}

	/**
	 * 本地APP的版本号
	 */
	private int versionCode;
	/**
	 * 本地APP的版本名称
	 */
	private String versionName;
	/**
	 * 获取本地APP的版本号
	 */
	private void getVersionCode() {
		// 获取自己的版本信息
		PackageManager pm = getPackageManager();
		try {
			PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
			// 版本号
			versionCode = packageInfo.versionCode;
			// 版本名
			versionName = packageInfo.versionName;
			// 设置textview
//			tv_versionName.setText(versionName);
		} catch (Exception e) {
			System.out.println("获取本地APP的版本号失败");
		}
	}

	/**
	 * 请求服务器中APP的版本号
	 */
	private void requestNetVersionCode() {
		RequestParams rp = new RequestParams();
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.APP_Update, rp,
				new MyRequestCallBack(this, MyConstans.SECOND));
		System.out.println("请求服务器中APP的版本号的网址为：===" + URLs.APP_Update);
	}

	/**
	 * 检查网络状态，无网络时显示无网络布局
	 */
	@Override
	protected void onResume() {
		super.onResume();
		if (!MyApplication.getInstance().getNetConnectInstance().ischeackNet(mActivity)) {
			rl_no_internet.setVisibility(View.VISIBLE);
		} else {
			rl_no_internet.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mBroadcast);
		MyApplication.getInstance().ColseDbutil();
	}

	private void initFindView() {
		rl_no_internet= (RelativeLayout) findViewById(R.id.rl_no_internet);
		Toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
		rl_kjcd= (RelativeLayout) findViewById(R.id.rl_kjcd);
		iv_icon= (ImageView) findViewById(R.id.iv_icon);
		lv= (ListView) findViewById(R.id.id_lv);
		mDrawerLayout= (DrawerLayout) findViewById(R.id.id_drawerlayout);
		tv_city= (TextView) findViewById(R.id.tv_city);
		tv_setting= (TextView) findViewById(R.id.tv_setting);
		tv_name= (TextView) findViewById(R.id.tv_name);
		ll_mine= (LinearLayout) findViewById(R.id.ll_mine);
		tv_title= (TextView) findViewById(R.id.tv_title);
		layout= (LinearLayout) findViewById(R.id.ll);
		iv_saoyisao= (ImageView) findViewById(R.id.iv_saoyisao);
		iv_qiandao= (ImageView) findViewById(R.id.iv_qiandao);

		SharedPreferences sp = getSharedPreferences("loginactivty", Context.MODE_APPEND);
		rolename_app=sp.getString("rolename_app", "");
		user_id=sp.getString("user_id","");
//		location_address=sp.getString("location_str", "");
		if (rolename_app.equals("帮扶责任人") ||rolename_app.equals("第一书记") ||rolename_app.equals("驻村工作队队长") ||rolename_app.equals("驻村工作队队员")) {// 特殊角色
			iv_qiandao.setOnClickListener(this);
			iv_qiandao.setVisibility(View.VISIBLE);
		}
//		iv_qiandao.setOnClickListener(this);
//		iv_qiandao.setVisibility(View.VISIBLE);

		tv_city.setOnClickListener(this);
		tv_setting.setOnClickListener(this);
		iv_saoyisao.setOnClickListener(this);
		iv_saoyisao.setVisibility(View.VISIBLE);
	}

	/**
	 * 注册广播--对网络连接状态判断，如果没网就显示layout布局，否则不显示 <br/>
	 * 2016年5月12日
	 * ZhaoDongShao
	 */
	public void registerMessageReceiver() {
		mBroadcast = new InternetBroadcast();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(Common.MESSAGE_RECEIVED_ACTION);
		filter.addAction(Common.ADRESS_ACTION);
		registerReceiver(mBroadcast, filter);
	}

	/**
	 * 设置顶部状态栏的颜色
	 * 2016年11月3日
	 */
	private void setStatusBarColor() {
		// 如果版本 >=21即Android 5.0
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

			ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
			View statusBarView = mContentView.getChildAt(0);
			// 移除假的 View
			if (statusBarView != null && statusBarView.getLayoutParams() != null
					&& statusBarView.getLayoutParams().height == getStatusBarHeight()) {
				mContentView.removeView(statusBarView);
			}
			// 不预留空间
			if (mContentView.getChildAt(0) != null) {
				ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), false);
			}
			LinearLayout.LayoutParams lParams = (android.widget.LinearLayout.LayoutParams) layout.getLayoutParams();
			int height = getStatusBarHeight();
			lParams.height = height;

			layout.setLayoutParams(lParams);
			layout.setBackgroundColor(ResourceUtil.getInstance().getColorById(R.color.color_145bba));
		}

		// 如果版本 >=19即Android 4.4，<21即Android 5.0
		if (Build.VERSION_CODES.LOLLIPOP > Build.VERSION.SDK_INT
				&& Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
			ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
			// 首先使 ChildView 不预留空间
			View mChildView = mContentView.getChildAt(0);
			if (mChildView != null) {
				ViewCompat.setFitsSystemWindows(mChildView, false);
			}

			int statusBarHeight = getStatusBarHeight();
			// 需要设置这个 flag 才能设置状态栏
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 避免多次调用该方法时,多次移除了 View
			if (mChildView != null && mChildView.getLayoutParams() != null
					&& mChildView.getLayoutParams().height == statusBarHeight) {
				// 移除假的 View.
				mContentView.removeView(mChildView);
				mChildView = mContentView.getChildAt(0);
			}
			if (mChildView != null) {
				FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
				// 清除 ChildView 的 marginTop 属性
				if (lp != null && lp.topMargin >= statusBarHeight) {
					lp.topMargin -= statusBarHeight;
					mChildView.setLayoutParams(lp);
				}
			}
			LinearLayout.LayoutParams lParams = (android.widget.LinearLayout.LayoutParams) layout.getLayoutParams();
			int height = getStatusBarHeight();
			lParams.height = height;
			layout.setLayoutParams(lParams);
			layout.setBackgroundColor(ResourceUtil.getInstance().getColorById(R.color.color_145bba));
		}
		
		// 如果版本小于19，即小于Android 4.4
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
			layout.setVisibility(View.GONE);
		}
	}

	/**
	 * 2016年8月5日
	 * ZhaoDongShao
	 */
	@SuppressWarnings("deprecation")
	private void initToolbar() {
		tv_city.setVisibility(View.VISIBLE);

		AdlCode code = MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(mActivity, Common.LoginName);
		if (code.getAd_nm() != null) {
			tv_city.setText(code.getAd_nm());
		}
		LayoutParams iLayout = Toolbar.getLayoutParams();
		MainActivity.StruesHeight = iLayout.height;
		Toolbar.setTitle("");
		Toolbar.setNavigationIcon(R.drawable.tubiao_03);
		setSupportActionBar(Toolbar);

		mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, Toolbar, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}
		};
		mActionBarDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
//		mDrawerLayout.setFadingEdgeLength(200);
	}

	/**
	 * 这是重写的父类BaseActivity的定位成功后调用的方法，子类可以重写该方法实现定位信息
	 * 2016年7月28日
	 * ZhaoDongShao
	 */
	@Override
	protected void onLoction() {
		super.onLoction();
//		showToast("当前定位城市："+MyApplication.now_address);
		// 获取现在保存的地区
		address = MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(mActivity, Common.LoginName);
		// 判断是否定位
		loction = MyApplication.getInstance().getSharePreferencesUtilInstance().getisLoction(mContext);
		if (address != null) {
			// 显示保存的城市信息
			final String string = address.getAd_nm();
			if (string != null) {
				if (!StringUtil.isEmpty(MyApplication.now_address)) {
					if (!string.equals(MyApplication.now_address)) {
						if (loction) {
							return;
						}
						if (builder != null) {
							return;
						}

						// ***********************以下是定位信息，取消不要**************************************
//						builder = new Builder(mActivity);
//						builder.setTitle("提示");
//						builder.setMessage("检测到当前位置为：" + MyApplication.now_address + "，是否切换地区？");
//						builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//								ResuestAdlCode(MyApplication.now_address);
//								mLocationClient.stop();
//								// RequestParams rp = new RequestParams();
//								// rp.addBodyParameter("ad_nm",
//								// MyApplication.now_address);
//								// MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
//								// URLs.CITY_CODE, rp, new
//								// MyRequestCallBack((BaseActivity)MainActivity.this,
//								// MyConstans.FIRST));
//							}
//						});
//						builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//								// tv_city.setText(address.getAd_nm());
//								// ResuestAdlCode(MyApplication.gx_address);
//								// tv_city.setText(MyApplication.gx_address);
//								MyApplication.getInstance().getSharePreferencesUtilInstance().saveLoction(mContext,
//										true);
//								mLocationClient.stop();
//							}
//						});
//						builder.create();
//						builder.show();
					}
				}
			} else {
//				if (!loction) {
//					ResuestAdlCode(MyApplication.now_address);
//				}
			}
		} else {
			ResuestAdlCode(MyApplication.now_address);
		}
	}

	/**
	 * 根据地名，通过xUtils的POST请求方式，将地名作为参数请求网络，获取行政区代码
	 * @param address 全局的一个存储地名的变量
	 * 2016年8月8日
	 * ZhaoDongShao
	 */
	private void ResuestAdlCode(String address) {
		RequestParams rp = new RequestParams();
		rp.addBodyParameter("ad_nm", address);
		System.out.println("MainActivity获取行政区划的网址为：" + URLs.CITY_CODE+"?ad_nm="+address);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST, URLs.CITY_CODE, rp,
				new MyRequestCallBack((BaseActivity) MainActivity.this, MyConstans.FIRST));
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag) {
		case MyConstans.FIRST:
			System.out.println("MainActivity获取行政层级代码数据成功：==="+str);
			AdlCodeListResult listResult = AdlCodeListResult.parseToT(str, AdlCodeListResult.class);
			if (!StringUtil.isEmpty(listResult)) {
				if (listResult.getSuccess()) {
					List<AdlCode> list = listResult.getJsondata();
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							AdlCode adlCode = list.get(i);
							if (adlCode == null || adlCode.getAd_nm() == null || adlCode.getAd_nm().equals("")) {
								showToast("获取数据失败");
							} else {
								tv_city.setText(adlCode.getAd_nm());
								MyApplication.getInstance().getSharePreferencesUtilInstance().saveNowCity(this, adlCode, Common.LoginName);
								MyApplication.getInstance().getSharePreferencesUtilInstance().saveLoction(mContext, true);
							}
						}
					}
				} else {
					showToast("获取数据失败");
				}
			} else {
				AdlCode adlCode = new AdlCode();
				adlCode.setAd_cd("00000000000");
				adlCode.setAd_nm("通辽市");
				tv_city.setText(adlCode.getAd_nm());
				MyApplication.getInstance().getSharePreferencesUtilInstance().saveNowCity(this, adlCode, Common.LoginName);
			}
			break;

		case MyConstans.SECOND:
			System.out.println("请求服务器中APP的版本号成功：===" + str);
			try {
				JSONObject jsonObject=new JSONObject(str);
				String netVersion=jsonObject.getString("bbgxID");
				if(StringUtil.isNotEmpty(netVersion)){
					//判断服务器中的版本号和本地的版本号是否一致，如果不一致就更新APP
					isNewVersion(netVersion);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;

			case MyConstans.THIRD:
				LogUtil.println("查询签到次数成功：==="+str);
				QianDaoJiLu_ResultList qianDaoJiLu_resultList=QianDaoJiLu_ResultList.parseToT(str,QianDaoJiLu_ResultList.class);
				if(qianDaoJiLu_resultList.getSuccess()){
					QianDaoJiLu qianDaoJiLu = qianDaoJiLu_resultList.getData();
					if(null!=qianDaoJiLu){
						//显示确认签到对话框
						showCommitDialog(qianDaoJiLu);
					}
				}else {
					showToast(qianDaoJiLu_resultList.getMsg());
				}
				break;

			case MyConstans.FORTH:
				showToast("签到成功");
				LogUtil.println("签到成功：==="+str);
				break;
		}
	}

	/**
	 * 判断服务器中的版本号和本地的版本号是否一致，如果不一致就更新APP
	 * @param NetVersion 服务器中的版本号
	 */
	private void isNewVersion(String NetVersion){
		int netVersion=Integer.parseInt(NetVersion);
		LogUtil.println("服务器中APP的版本号为：==="+NetVersion);
		LogUtil.println("当前APP的版本号为：==="+versionCode);
		if(netVersion>versionCode){
			//显示更新APP版本的对话框
			showUpdateDialog();
		}else {
//			showToast("已经是最新版本");
		}
	}

	/**
	 * 下载最新版本APP的弹出对话框
	 */
	private AlertDialog dialog;
	/**
	 * 下载最新版本APP的进度条
	 */
	private NumberProgressBar pb_download;
	private NumberProgressBar pb_download2;
	private RelativeLayout rl_notification;
	/**
	 * 显示更新APP版本的对话框
	 */
	private void showUpdateDialog() {
		dialog = new AlertDialog.Builder(this).create();
		dialog.setCancelable(false);
		dialog.show();
		Window window = dialog.getWindow();
		//更新弹出对话框自定义布局
		window.setContentView(R.layout.prompt_alertdialog);
		//版本更新的标题内容的布局容器
		LinearLayout ll_title = (LinearLayout) window.findViewById(R.id.ll_title);
		ll_title.setVisibility(View.VISIBLE);
		//版本更新的标题内容
		TextView tv_title = (TextView) window.findViewById(R.id.tv_title);
		//自定义的更新进度条
		pb_download = (NumberProgressBar) window.findViewById(R.id.pb_splash_download);
		// 隐藏进度条
		pb_download.setVisibility(View.GONE);
		pb_download.setOnProgressBarListener(this);
		tv_title.setText("版本更新");
		//版本更新的提示内容
		TextView tv_content = (TextView) window.findViewById(R.id.tv_content);
		tv_content.setMovementMethod(new ScrollingMovementMethod());
		tv_content.setText("有新版本，是否更新当前版本？");
		final TextView tv_sure = (TextView) window.findViewById(R.id.tv_sure);
		final TextView tv_cancle = (TextView) window.findViewById(R.id.tv_cancle);
		tv_cancle.setText("取消");
		tv_cancle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.cancel();
			}
		});
		tv_sure.setText("更新");
		tv_sure.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//下载安装最新版本的APP
				downLoadNewApk();
//				createFloatView();
				tv_cancle.setEnabled(false);
				tv_sure.setEnabled(false);
			}
		});
	}

	/**
	 * 下载安装最新版本的APP
	 */
	protected void downLoadNewApk() {
		HttpUtils utils = new HttpUtils();
		File file = new File(Common.DOWNLOAD_DIR + "/fupin_tongliao.apk" );
		//如果此文件已存在先删除
		file.delete();// 删除文件

		//本机tomcat服务器地址(debug包)：http://192.168.1.151:8080/demo/fupin_2_0-debug.apk
		//本机tomcat服务器地址(正式包)：http://192.168.1.151:8080/demo/fupin_2_0-release.apk
		//阿里云服务器地址：    http://101.200.190.254/app/fp_tongliao/
		//阿里云服务器地址：    http://www.rochsoft.com/app/fp_tongliao/fupin_2_0-release.apk
		utils.download(
				"http://47.93.120.179:9005/poverty/apk/fupin_2_0-release.apk",
				Common.DOWNLOAD_DIR + "/fupin_tongliao.apk", new RequestCallBack<File>() {
					@Override
					public void onLoading(final long total, final long current, boolean isUploading) {
						pb_download.setVisibility(View.VISIBLE);// 设置进度的显示
						int max = (int) total;
						int progress = (int) current;
						pb_download.setMax(max);// 设置进度条的最大值
						pb_download.setProgress(progress);// 设置当前进度
//						pb_download2.setMax(max);
//						pb_download2.setProgress(progress);

						//显示通知栏下载进度
//						showNotifi(max, progress);
						super.onLoading(total, current, isUploading);
					}

					@Override
					public void onSuccess(ResponseInfo<File> arg0) {
						// 下载成功
						// 在主线程中执行
						Toast.makeText(getApplicationContext(), "下载新版本成功", Toast.LENGTH_SHORT).show();
						// 隐藏进度条
						pb_download.setVisibility(View.GONE);
						dialog.cancel();
						// 安装apk
						installApk();
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// 下载失败
						Toast.makeText(getApplicationContext(), "下载新版本失败", Toast.LENGTH_SHORT).show();
						pb_download.setVisibility(View.GONE);// 隐藏进度条
						dialog.cancel();
					}
				});
	}

	/**
	 * 安装下载的新版本
	 */
	protected void installApk() {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //如果不加，最后安装完成，点打开，无法打开新版本应用
		intent.addCategory("android.intent.category.DEFAULT");
		String type = "application/vnd.android.package-archive";
		Uri data = Uri.fromFile(new File(Common.DOWNLOAD_DIR + "/fupin_tongliao.apk"));
		intent.setDataAndType(data, type);
		startActivityForResult(intent, 0);
		android.os.Process.killProcess(android.os.Process.myPid()); //如果不加，最后不会提示完成、打开
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		switch (flag){
			case MyConstans.FIRST:
				LogUtil.println("MainActivity获取行政层级代码数据失败：===" + str);
				break;

			case MyConstans.SECOND:
				System.out.println("请求服务器中APP的版本号失败：===" + str);
				break;

			case MyConstans.THIRD:
				LogUtil.println("查询签到次数失败：==="+str);
				showToast("请求签到服务器异常");
				break;

			case MyConstans.FORTH:
				showToast("签到失败");
				LogUtil.println("签到失败：==="+str);
				break;
		}
	}

	/**
	 * 获取手机的屏幕密度DPI 
	 */
	private void initDensityDpi() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		Common.densityDpi = metrics.densityDpi;
		Common.Width = metrics.widthPixels;
		Common.Hight = metrics.heightPixels;
	}

	/**
	 * 快捷菜单、网络异常布局、设置按钮、筛选城市按钮的点击事件
	 * @param v
	 * 2016年11月3日
	 */
	@OnClick({ R.id.rl_kjcd, R.id.rl_no_internet, R.id.tv_setting, R.id.tv_city,R.id.iv_saoyisao,R.id.iv_qiandao })
	public void onClick(View v) {
		if (v.getId() == R.id.rl_kjcd) { // 0.点击了快捷菜单
			mHandler.postDelayed(new Runnable() {
				public void run() {
					mDrawerLayout.closeDrawer(Gravity.LEFT);
				}
			}, 300);
			transaction = manager.beginTransaction()
					.hide(fragments[0])
					.hide(fragments[1])
					.hide(fragments[2])
					.hide(fragments[3])
					.hide(fragments[4])
					.hide(fragments[5])
					.hide(fragments[6])
					.hide(fragments[7])
					.hide(fragments[8])
					.hide(fragments[9])
					.hide(fragments[10])
					.hide(fragments[11]);
			tv_title.setText("快捷菜单");
			transaction.show(fragments[0]).commit();
		} else if (v.getId() == R.id.rl_no_internet) { // 点击了没有网络状态时的布局容器
			Intent intent = new Intent(mActivity, NoInternetActivity.class);
			intent.putExtra("name", ResourceUtil.getInstance().getStringById(R.string.no_internet_title));
			startActivity(intent);
		} else if (v.getId() == R.id.tv_setting) { // 点击了侧滑菜单中的设置按钮
			Intent intent = new Intent(mActivity, SettingActivity.class);
			// intent.putExtra(Common.INTENT_KEY, getTextView(tv_setting));
			startActivity(intent);
		} else if (v.getId() == R.id.tv_city) { // 点击了toolbar上的城市筛选按钮
//			showToast("选择城市");
			Intent intent = new Intent(mActivity, CityChooesActivity1.class);
			Bundle bundle = new Bundle();
			bundle.putString(Common.TITLE_KEY, "选择城市");
			bundle.putSerializable(Common.BUNDEL_KEY, user);
			intent.putExtra(Common.TITLE_KEY, bundle);
			startActivityForResult(intent, 0);
		}else if (v.getId() == R.id.iv_saoyisao) { // 点击了toolbar上的扫一扫按钮
//			showToast("扫一扫");
			//	扫描二维码
			Intent openCameraIntent = new Intent(MainActivity.this, CaptureActivity.class);
			startActivityForResult(openCameraIntent, 1);
		}else if (v.getId() == R.id.iv_qiandao) { // 点击了toolbar上的签到按钮
			if(StringUtil.isNotEmpty(location_address)){
				LogUtil.println("查询签到次数的网址为：==="+URLs.QianDao_JiLu+"&userid="+user_id);
				RequestParams rp = new RequestParams();
				rp.addBodyParameter("userid", user_id);
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.QianDao_JiLu, rp,
						new MyRequestCallBack(this, MyConstans.THIRD));
			}else {
				showToast("请先打开GPS或设置定位功能");
			}
			//	签到
//			OkHttpUtils.post()
//					.url(URLs.LOGIN)
////					.addParams("userid", "8A24FCE8-C2F7-4C39-93BD-4417073A4BBE")
//					.addParams("loginname", "admin")
//					.addParams("password", "admin123")
//					.build()
//			        .execute(new StringCallback() {
//						@Override
//						public void onError(Call call, Exception e, int id) {
//							LogUtil.println("查询签到次数失败：==="+e.toString());
//						}
//
//						@Override
//						public void onResponse(String response, int id) {
//							LogUtil.println("查询签到次数成功：==="+response);
//							//显示确认签到对话框
////							showCommitDialog(response);
//						}
//					});
		}
	}

	/**
	 * 确认签到对话框
	 * @param qianDaoJiLu
	 */
	private void showCommitDialog(QianDaoJiLu qianDaoJiLu) {
		final NormalDailog dailog = new NormalDailog(mContext,R.style.popup_dialog_style,4);
		dailog.show();
		dailog.setLocationText(location_address); //当前签到位置：
		dailog.setNetLocationText(qianDaoJiLu.getSignlocation()==null ? "":qianDaoJiLu.getSignlocation()); //上次签到位置：
		dailog.setQiaoDaoCount(qianDaoJiLu.getCount()==null ? "":qianDaoJiLu.getCount()); //已签到次数：
		dailog.setContentText(qianDaoJiLu.getSigndatetext()== null ? "" : qianDaoJiLu.getSigndatetext());//上次签到时间：
		dailog.setDoneBtnText("签到");
		dailog.setOnClickLinener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
					case R.id.normal_dialog_done:
						dailog.dismiss();
						LogUtil.println("提交保存签到的网址为：=="+URLs.QianDao_Commit+"&signlocation="+location_address);
						RequestParams rp = new RequestParams();
						rp.addBodyParameter("signlocation", location_address);
						MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
								URLs.QianDao_Commit, rp,
								new MyRequestCallBack(MainActivity.this, MyConstans.FORTH));
//						OkHttpUtils.post()
//								.url(URLs.QianDao_Commit)
////								.addParams("userid",user_id)
//								.addParams("signlocation", location_address)
//								.build()
//								.execute(new StringCallback() {
//									@Override
//									public void onBefore(Request request, int id) {
//										super.onBefore(request, id);
//										showProgressDialog("签到中......");
//									}
//
//									@Override
//									public void onError(Call call, Exception e, int id) {
//										cancelProgressDialog();
//										showToast("签到失败");
//										LogUtil.println("签到失败：=="+e.toString());
//									}
//
//									@Override
//									public void onResponse(String response, int id) {
//										cancelProgressDialog();
//										showToast("签到成功");
//										LogUtil.println("签到成功：==" + response);
//									}
//								});
						break;

					case R.id.normal_dialog_cancel:
						dailog.dismiss();
						break;
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == Activity.RESULT_OK) { //选择城市后返回的结果
			AdlCode adlCode = (AdlCode) intent.getSerializableExtra(Common.INTENT_KEY);
			if (adlCode != null) {
				if (adlCode.getAd_cd().equals("")) {
					ResuestAdlCode(adlCode.getAd_nm());
				} else {
					tv_city.setText(adlCode.getAd_nm());
					System.out.println("选择城市后的ad_cd为：-----"+adlCode.getAd_cd());
					MyApplication.getInstance().getSharePreferencesUtilInstance().saveNowCity(this, adlCode, Common.LoginName);
					MyApplication.getInstance().getSharePreferencesUtilInstance().saveLoction(mContext, true);
				}
			}
		}else if(resultCode == 1){ //扫描二维码后返回的结果
			String resultStr=intent.getStringExtra("resultStr");
//			showToast(resultStr);  ---跳转到扫一扫后的贫困户详情界面
			Intent intent2 = new Intent(mActivity, PoorHouseDetailActivity_SaoYiSao.class);
			Bundle bundle = new Bundle();
			bundle.putString(Common.BUNDEL_KEY, resultStr);
			intent2.putExtra(Common.INTENT_KEY, bundle);
			startActivity(intent2);
		}
	}

	@Override
	public void onProgressChange(int current, int max) {
		pb_download.setProgress(current);
//		pb_download2.setProgress(current);
	}

	/**
	 * 自定义的判断网络连接状态的广播----如果网络中断，就显示无网络连接布局
	 * @author ZhaoDongShao
	 * 2016年5月12日
	 */
	class InternetBroadcast extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (Common.MESSAGE_RECEIVED_ACTION == intent.getAction()) {
				boolean isConntion = intent.getBooleanExtra(Common.KEY_MESSAGE, true);
				if (!isConntion) {
					rl_no_internet.setVisibility(View.VISIBLE);
				} else {
					rl_no_internet.setVisibility(View.GONE);
				}
			}else if(Common.ADRESS_ACTION==intent.getAction()){
				location_address=intent.getStringExtra("locationStr");
			}
		}
	}

	/**
	 * 初始化侧滑菜单栏的数据源，和登陆用户栏的信息，并给ListView和登陆用户一栏的容器设置点击监听事件
	 * 2016年11月3日
	 */
	private void initDate() {
		// 快捷菜单设置点击监听：隐藏其他5个fragment，只显示快捷菜单的fragment
		rl_kjcd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { // 0.点击了快捷菜单
				mHandler.postDelayed(new Runnable() {
					public void run() {
						mDrawerLayout.closeDrawer(Gravity.LEFT);
					}
				}, 300);
				transaction = manager.beginTransaction()
						.hide(fragments[0])
						.hide(fragments[1])
						.hide(fragments[2])
						.hide(fragments[3])
						.hide(fragments[4])
						.hide(fragments[5])
						.hide(fragments[6])
						.hide(fragments[7])
						.hide(fragments[8])
						.hide(fragments[9])
						.hide(fragments[10])
						.hide(fragments[11]);
				tv_title.setText("快捷菜单");
				transaction.show(fragments[0]).commit();
			}
		});

		//初始化侧滑菜单栏的数据源
		listmenu = new ArrayList<Menu>();
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.MENU_INTENT_KEY);
		user = (User) bundle.getSerializable(Common.USER_BUNDEL_KEY);
		if (user != null) {
			tv_name.setText(user.getUser_name());
		}
		@SuppressWarnings("unchecked")
		List<Menu> list = (List<Menu>) bundle.getSerializable(Common.MENU_BUNDEL_KEY);
		if (list != null && list.size() > 0) {
			for (Menu menu : list) {
				listmenu.add(menu);
			}
		}

		//给侧滑菜单栏设置适配器adapter
		adapter = new MenuListAdapter(listmenu, mContext);
		lv.setAdapter(adapter);
		//给侧滑菜单栏设置条目点击监听
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				final Menu item = (Menu) parent.getItemAtPosition(position);
				if (item.getName().equals("基础信息")) { // 1.点击了基础信息
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							transaction = manager.beginTransaction()
									.hide(fragments[0])
									.hide(fragments[1])
									.hide(fragments[2])
									.hide(fragments[3])
									.hide(fragments[4])
									.hide(fragments[5])
									.hide(fragments[6])
									.hide(fragments[7])
									.hide(fragments[8])
									.hide(fragments[9])
									.hide(fragments[10])
									.hide(fragments[11]);
							tv_title.setText(item.getName());
							transaction.show(fragments[1]).commit();
						}
					}, 300);
				} else if (item.getName().equals(Common.EXTS_NOTIC_NAME)) { // 3.点击了通知公告菜单
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							transaction = manager.beginTransaction()
									.hide(fragments[0])
									.hide(fragments[1])
									.hide(fragments[2])
									.hide(fragments[3])
									.hide(fragments[4])
									.hide(fragments[5])
									.hide(fragments[6])
									.hide(fragments[7])
									.hide(fragments[8])
									.hide(fragments[9])
									.hide(fragments[10])
									.hide(fragments[11]);
							tv_title.setText(item.getName());
							transaction.show(fragments[3]).commit();
						}
					}, 300);
				} else if (item.getName().equals(Common.EXTS_HELP_SUBJECT_NAME)) { //2.点击了帮扶主题菜单--去掉不要
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							transaction = manager.beginTransaction()
									.hide(fragments[0])
									.hide(fragments[1])
									.hide(fragments[2])
									.hide(fragments[3])
									.hide(fragments[4])
									.hide(fragments[5])
									.hide(fragments[6])
									.hide(fragments[7])
									.hide(fragments[8])
									.hide(fragments[9])
									.hide(fragments[10])
									.hide(fragments[11]);
							tv_title.setText(item.getName());
							transaction.show(fragments[2]).commit();
						}
					}, 300);
				} else if (item.getName().equals(Common.EXTS_NO_POOR_PROJECT_NAME)) { // 4.点击了脱贫攻坚项目--已改为项目跟踪
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							transaction = manager.beginTransaction()
									.hide(fragments[0])
									.hide(fragments[1])
									.hide(fragments[2])
									.hide(fragments[3])
									.hide(fragments[4])
									.hide(fragments[5])
									.hide(fragments[6])
									.hide(fragments[7])
									.hide(fragments[8])
									.hide(fragments[9])
									.hide(fragments[10])
									.hide(fragments[11]);
							tv_title.setText(item.getName());
							transaction.show(fragments[4]).commit();
						}
					}, 300);
				} else if (item.getName().equals(Common.EXTS_STATISTIC)) { // 5.点击了数据分析
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							transaction = manager.beginTransaction()
									.hide(fragments[0])
									.hide(fragments[1])
									.hide(fragments[2])
									.hide(fragments[3])
									.hide(fragments[4])
									.hide(fragments[5])
									.hide(fragments[6])
									.hide(fragments[7])
									.hide(fragments[8])
									.hide(fragments[9])
									.hide(fragments[10])
									.hide(fragments[11]);
							tv_title.setText(item.getName());
							transaction.show(fragments[5]).commit();
						}
					}, 300);
				}else if (item.getName().equals("项目跟踪")) { // 4.点击了项目跟踪
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							transaction = manager.beginTransaction()
									.hide(fragments[0])
									.hide(fragments[1])
									.hide(fragments[2])
									.hide(fragments[3])
									.hide(fragments[4])
									.hide(fragments[5])
									.hide(fragments[6])
									.hide(fragments[7])
									.hide(fragments[8])
									.hide(fragments[9])
									.hide(fragments[10])
									.hide(fragments[11]);
							tv_title.setText(item.getName());
							transaction.show(fragments[4]).commit();
						}
					}, 300);
				}else if (item.getName().equals("扶贫大事记")) { // 7.点击了扶贫大事记
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							transaction = manager.beginTransaction()
									.hide(fragments[0])
									.hide(fragments[1])
									.hide(fragments[2])
									.hide(fragments[3])
									.hide(fragments[4])
									.hide(fragments[5])
									.hide(fragments[6])
									.hide(fragments[7])
									.hide(fragments[8])
									.hide(fragments[9])
									.hide(fragments[10])
									.hide(fragments[11]);
							tv_title.setText(item.getName());
							transaction.show(fragments[7]).commit();
						}
					}, 300);
				}else if (item.getName().equals("信息服务")) { // 8.点击了信息服务
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							transaction = manager.beginTransaction()
									.hide(fragments[0])
									.hide(fragments[1])
									.hide(fragments[2])
									.hide(fragments[3])
									.hide(fragments[4])
									.hide(fragments[5])
									.hide(fragments[6])
									.hide(fragments[7])
									.hide(fragments[8])
									.hide(fragments[9])
									.hide(fragments[10])
									.hide(fragments[11]);
							tv_title.setText(item.getName());
							transaction.show(fragments[8]).commit();
						}
					}, 300);
				}else if (item.getName().equals("资金监管")) { // 9.点击了资金监管
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							transaction = manager.beginTransaction()
									.hide(fragments[0])
									.hide(fragments[1])
									.hide(fragments[2])
									.hide(fragments[3])
									.hide(fragments[4])
									.hide(fragments[5])
									.hide(fragments[6])
									.hide(fragments[7])
									.hide(fragments[8])
									.hide(fragments[9])
									.hide(fragments[10])
									.hide(fragments[11]);
							tv_title.setText(item.getName());
							transaction.show(fragments[9]).commit();
						}
					}, 300);
				}else if (item.getName().equals("贫困户互动")) { // 10.点击了贫困户互动
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							transaction = manager.beginTransaction()
									.hide(fragments[0])
									.hide(fragments[1])
									.hide(fragments[2])
									.hide(fragments[3])
									.hide(fragments[4])
									.hide(fragments[5])
									.hide(fragments[6])
									.hide(fragments[7])
									.hide(fragments[8])
									.hide(fragments[9])
									.hide(fragments[10])
									.hide(fragments[11]);
							tv_title.setText(item.getName());
							transaction.show(fragments[10]).commit();
						}
					}, 300);
				}else if (item.getName().equals("干部包联")) { // 11.点击了干部包联
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							transaction = manager.beginTransaction()
									.hide(fragments[0])
									.hide(fragments[1])
									.hide(fragments[2])
									.hide(fragments[3])
									.hide(fragments[4])
									.hide(fragments[5])
									.hide(fragments[6])
									.hide(fragments[7])
									.hide(fragments[8])
									.hide(fragments[9])
									.hide(fragments[10])
									.hide(fragments[11]);
							tv_title.setText(item.getName());
							transaction.show(fragments[11]).commit();
						}
					}, 300);
				}
			}
		});

		//给登陆用户一栏的容器设置点击监听
		ll_mine.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mActivity, MineActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, (Serializable) user);
				intent.putExtra(Common.INTENT_KEY, bundle);
				startActivity(intent);
			}
		});
	}

	/**
	 * 初始化6个fragment，并将他们都隐藏，只显示HomeFragment即快捷菜单的fragment
	 */
	private void initView() {
		fragments = new Fragment[12];
		manager = getSupportFragmentManager();
		fragments[0] = manager.findFragmentById(R.id.fragment_home); // 0.首页即快捷菜单
		fragments[1] = manager.findFragmentById(R.id.fragment_helpingobject); // 1.帮扶对象----改为基础信息
		fragments[2] = manager.findFragmentById(R.id.fragment_helpingsubject); // 2.帮扶主体 ******去掉不要*****
		fragments[3] = manager.findFragmentById(R.id.fragment_notic); // 3.通知公告
		fragments[4] = manager.findFragmentById(R.id.fragment_no_poor_project); // 4.脱贫攻坚项目--改为项目跟踪
		fragments[5] = manager.findFragmentById(R.id.fragment_poor_people_statistic); // 5.数据分析
		fragments[6] = manager.findFragmentById(R.id.fragment_zhuanxiang); // 6.数据分析 ******去掉不要****
		fragments[7] = manager.findFragmentById(R.id.fragment_fupindashiji); // 7.扶贫大事记
		fragments[8] = manager.findFragmentById(R.id.fragment_xinxifuwu); // 8.信息服务
		fragments[9] = manager.findFragmentById(R.id.fragment_zijinjianguan); // 9.资金监管
		fragments[10] = manager.findFragmentById(R.id.fragment_pinkunhuhudong); // 10.贫困户互动
		fragments[11] = manager.findFragmentById(R.id.fragment_ganbubaolian); // 11.干部包联
		transaction = manager.beginTransaction()
				.hide(fragments[0])
				.hide(fragments[1])
				.hide(fragments[2])
				.hide(fragments[3])
				.hide(fragments[4])
				.hide(fragments[5])
				.hide(fragments[6])
				.hide(fragments[7])
				.hide(fragments[8])
				.hide(fragments[9])
				.hide(fragments[10])
				.hide(fragments[11]);
		transaction.show(fragments[0]).commit();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
			mDrawerLayout.closeDrawer(Gravity.LEFT);
		} else {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				switch (keyCodeCount++) {
				case 0:
					MyApplication.getInstance().getToastUtilsInstance().showNormalToast(this, "再按一次，退出程序");
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							keyCodeCount = 0;
						}
					}, 3000);
					break;
					
				case 1:
					MyApplication.getInstance().finishAllActivity();
					break;
					
				default:
					break;
				}
			}
		}
		return true;
	}

	/**
	 * 监听onTouch事件
	 * @author Administrator
	 */
	public interface MyTouchListener {
		void onTouchEvent(MotionEvent event);
	}

	/**
	 * 存有MyTouchListener接口的列表集合
	 */
	private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MainActivity.MyTouchListener>();

	/**
	 * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
	 * @param listener
	 */
	public void registerMyTouchListener(MyTouchListener listener) {
		myTouchListeners.add(listener);
	}

	/**
	 * 分发触摸事件给所有注册了MyTouchListener的接口
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		for (MyTouchListener listener : myTouchListeners) {
			listener.onTouchEvent(ev);
		}
		return super.dispatchTouchEvent(ev);
	}

}
