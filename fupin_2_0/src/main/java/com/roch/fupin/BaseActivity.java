package com.roch.fupin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NoticeDialog;
import com.roch.fupin.entity.User;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyRequestCallBack.SuccessResult;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;

/**
 * Activity基类，onCreate()方法中有百度地图的定位功能，定位成功后调用onLacation()方法，实现了SuccessResult接口，
 * 里面有请求网络的方法，访问网络成功就调用onSuccess();否则就调用onFail();
 * @author ZhaoDongShao
 * 2016年9月5日
 */
public class BaseActivity extends AppCompatActivity implements SuccessResult {
	
	/**
	 * progressdialog对话框
	 */
	ProgressDialog mProgressDialog;
	
	/**
	 * 百度地图定位的客户端LocationClient
	 */
	LocationClient mLocationClient;
	
	/**
	 * 接收定位信息的广播
	 */
	BroadcastReceiver mReceiver;
	
	BaseActivity mActivity;
	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		this.mActivity = this;
		this.mContext = this;
		// CommonUtil.getInstance(this).getState();

		// 如果手机版本 >=21(Android 5.0)---就设置状态栏的颜色
		setMyStatusBarColor();

		mLocationClient = ((MyApplication) getApplication()).mLocationClient;
		// 注册接收定位信息的广播
		registerBroadCastReceiver();
		// 初始化设置百度地图定位的参数信息
		initLocation();
		// 开始定位
		mLocationClient.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}

	/**
	 * 设置状态栏的颜色
	 * 2016年11月3日
	 */
	@SuppressLint({ "InlinedApi", "NewApi" })
	private void setMyStatusBarColor() {
		// 手机版本 >=21(Android 5.0)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			// 取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

			// 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			// 设置状态栏颜色
//			window.setStatusBarColor(ResourceUtil.getInstance().getColorById(R.color.color_145bba));
			window.setStatusBarColor(getResources().getColor(R.color.color_145bba));

			ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
			View mChildView = mContentView.getChildAt(0);
			if (mChildView != null) {
				// 注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView
				// 的第一个子 View . 预留出系统 View 的空间.
				ViewCompat.setFitsSystemWindows(mChildView, true);
			}
		}
		// else if (Build.VERSION_CODES.LOLLIPOP > Build.VERSION.SDK_INT &&
		// Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
		//
		// Window window = getWindow();
		// window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//
		// ViewGroup mContentView = (ViewGroup)
		// findViewById(Window.ID_ANDROID_CONTENT);
		// View statusBarView = mContentView.getChildAt(0);
		// //移除假的 View
		// if (statusBarView != null && statusBarView.getLayoutParams() != null
		// && statusBarView.getLayoutParams().height == getStatusBarHeight()) {
		// mContentView.removeView(statusBarView);
		// }
		// //不预留空间
		// if (mContentView.getChildAt(0) != null) {
		// ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), false);
		// }
		//
		// }
	}

	/**
	 * 获取当前选择的行政区代码-----公共方法，让子类去用
	 * 2016年7月1日
	 * ZhaoDongShao
	 */
	public String getAdl_Cd() {
		SharedPreferences sp = getSharedPreferences("loginactivty", Context.MODE_APPEND);
		String adl_cd=sp.getString("adl_cd", "");
		return adl_cd;
//		AdlCode adlCode = MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(this, Common.LoginName);
//		if (adlCode != null) {
//			if (adlCode.getAd_cd() != null) {
//				if (!adlCode.getAd_cd().equals("")) {
//					return adlCode.getAd_cd();
//				}
//			}
//		}
//		return "";
	}

	/**
	 * 注册接受定位信息的广播
	 */
	void registerBroadCastReceiver() {
		mReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				MyApplication.now_address = intent.getStringExtra("address");
				User user = MyApplication.getInstance().getLogin(Common.LoginName);
				if (StringUtil.isEmpty(user.getLoginname())) {
					if (!StringUtil.isEmpty(user.getAdl_nm()) && !StringUtil.isEmpty(MyApplication.now_address)) {
						if (user.getAdl_nm().length() == MyApplication.now_address.length()) {
							if (!user.getAdl_nm().equals(MyApplication.now_address)) {
								MyApplication.gx_address = user.getAdl_nm();
							} else {
								MyApplication.gx_address = MyApplication.now_address;
							}
						} else {
							if (user.getAdl_nm().length() == (MyApplication.now_address + MyApplication.location.getDistrict()).length()) {
								if (user.getAdl_nm().equals(MyApplication.now_address + MyApplication.location.getDistrict())) {
									MyApplication.gx_address = MyApplication.location.getStreet();
								} else {
									MyApplication.gx_address = user.getAdl_nm().substring(
											MyApplication.now_address.length(), MyApplication.now_address.length()
													+ MyApplication.location.getDistrict().length());
								}
							} else {
								MyApplication.gx_address = user.getAdl_nm().substring(
										MyApplication.now_address.length(), MyApplication.now_address.length()
												+ MyApplication.location.getDistrict().length());
							}
						}
					}
				}
				//当父类BaseActivity定位成功后调用此方法，让子类重写
				onLoction();
			}
		};
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction(Common.ADRESS_ACTION);
		registerReceiver(mReceiver, mFilter);
	}

	// /**
	// * 获取定位信息
	// * 2016年6月20日
	// * ZhaoDongShao
	// */
	// protected void onLoction() {
	// AdlCode address =
	// MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(this,Common.LoginName);
	// if (address.getAd_nm() != null && !address.getAd_nm().equals("")) {
	//
	// if (address.getAd_nm().equals(MyApplication.now_address)) {
	// return;
	// }else {
	//
	// MyApplication.getInstance().getSharePreferencesUtilInstance().saveNowCity(this,
	// new AdlCode("", MyApplication.now_address,
	// PingYinUtil.getPingYin(MyApplication.now_address)),Common.LoginName);
	//
	// }
	// }else {
	// MyApplication.getInstance().getSharePreferencesUtilInstance().saveNowCity(this,
	// new AdlCode("", MyApplication.now_address,
	// PingYinUtil.getPingYin(MyApplication.now_address)),Common.LoginName);
	// }
	// }

	/**
	 * 当父类BaseActivity定位成功后调用此方法，让子类重写  <br/>
	 * 这是BaseActivity的定位成功后调用的方法，子类可以重写该方法实现定位信息
	 * 2016年7月28日
	 * ZhaoDongShao
	 */
	protected void onLoction() {
	}

	/**
	 * 初始化设置百度地图定位的参数信息 <br/>
	 * 2016年10月28日
	 */
	void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(10000); // 10分钟扫描1次
		// 需要地址信息，设置为其他任何值（string类型，且不能为null）时，都表示无地址信息。
		option.setAddrType("all");
		// 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		option.setProdName("通过GPS定位我当前的位置");
		// 禁用启用缓存定位数据
		option.disableCache(true);
		option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
		option.setOpenGps(true);// 可选，默认false,设置是否使用gps
		option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIgnoreKillProcess(true);// 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
		// 设置定位方式的优先级。
		// 当gps可用，而且获取了定位结果时，不再发起网络请求，直接返回给用户坐标。这个选项适合希望得到准确坐标位置的用户。如果gps不可用，再发起网络请求，进行定位。
		option.setPriority(LocationClientOption.GpsFirst);
		mLocationClient.setLocOption(option);
	}

	/*
	 * 显示进度条--不可取消
	 */
	public void showProgressDialog(String msg) {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setCancelable(false);
			mProgressDialog.setMessage(msg);
		}
		if (!mProgressDialog.isShowing())
			mProgressDialog.show();
	}

	/*
	 * 显示进度条--可取消
	 */
	public void showProgressDialog(String msg, boolean cancleble) {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setCancelable(cancleble);
			mProgressDialog.setMessage(msg);
		}
		if (!mProgressDialog.isShowing())
			mProgressDialog.show();
	}

	/**
	 * 隐藏进度条
	 */
	public void cancelProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing())
			mProgressDialog.cancel();
	}

	/**
	 * 获取状态栏的高度
	 * @return
	 */
	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	/**
	 * 如果当前网络故障，给出提示
	 * 2016年5月19日
	 * ZhaoDongShao
	 */
	@SuppressLint("NewApi")
	public void ShowNoticDialog() {
		NoticeDialog dialog = new NoticeDialog();
		dialog.show(getFragmentManager(), "BaseActivity");
	}

	/**
	 * 显示Toast
	 * @param msg
	 */
	public void showToast(String msg) {
		MyApplication.getInstance().getToastUtilsInstance().showNormalToast(this, msg);
	}

	/**
	 * 返回EditText编辑的内容
	 * @param ed
	 * @return
	 */
	public String getEditText(EditText ed) {
		return ed.getText().toString().trim();
	}

	/**
	 * 返回TextView所显示的内容
	 * @param tv
	 * @return
	 */
	public String getTextView(TextView tv) {
		return tv.getText().toString().trim();
	}

	/**
	 * 处理事件分发
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			// 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或�?�实体案件会移动焦点�??
			View view = getCurrentFocus();
			if (isShouldHideInput(view, ev)) {
				hideSoftInput(view.getWindowToken());
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * 2016年10月27日 <br/>
	 * 隐藏软键盘输入法
	 */
	private void hideSoftInput(IBinder windowToken) {
		if (windowToken != null) {
			InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			manager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * 2016年10月27日 <br/>
	 * 是否隐藏软键盘 <br/>
	 * @param view  当前获得焦点的view对象
	 * @param ev    触摸事件
	 * @return      如果获得焦点的view是EditText，并且被触摸则返回true，否则返回false
	 */
	private boolean isShouldHideInput(View view, MotionEvent ev) {
		if (view != null && (view instanceof EditText)) {
			int[] l = { 0, 0 };
			view.getLocationInWindow(l);
			int left = l[0], top = l[1], bottom = top + view.getHeight(), right = left + view.getWidth();
			if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
				// 点击EditText的事件
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		System.out.println("BaseActivity中重写的onSuccessResult(String str, int flag)方法-------->>登录状态");
	}

	@Override
	public void onFaileResult(String str, int flag) {
	}

	// /**
	// * 软件更新弹出
	// */
	// CheckUpDialog versionUpdateDialog;
	//
	// void showProductOrderDialog(final String url, final String fileName,
	// String content, final String fileSize, String IsQiangZhi) {
	// Log.e("showProductOrderDialog", "软件更新弹出");
	// if (versionUpdateDialog != null && versionUpdateDialog.isShowing()) {
	// return;
	// }
	// versionUpdateDialog = new CheckUpDialog(BaseActivity.this,
	// R.style.popup_dialog_style);
	// Window win = versionUpdateDialog.getWindow();
	// win.setGravity(Gravity.CENTER);
	// WindowManager mWindowManager = (WindowManager)
	// BaseActivity.this.getSystemService(Context.WINDOW_SERVICE);
	// win.setWindowManager(mWindowManager, null, null);
	// versionUpdateDialog.setCancelable(false);
	// versionUpdateDialog.show();
	// versionUpdateDialog.setContent(content);
	// if (IsQiangZhi.equals("1")) {
	// // 强制更新
	// versionUpdateDialog.setCancelVisibility(View.GONE);
	// versionUpdateDialog.setCanceledOnTouchOutside(false);
	// } else {
	// versionUpdateDialog.setCancelVisibility(View.VISIBLE);
	// versionUpdateDialog.setCanceledOnTouchOutside(true);
	// }
	//
	// versionUpdateDialog.setIfBack(IsQiangZhi);
	// // 弹出框dialog 点击事件
	// versionUpdateDialog.setOnClickListener(new OnClickListener() {
	// public void onClick(View v) {
	// switch (v.getId()) {
	// case R.id.dialog_product_done:
	// // 判断SD卡是否存在，并且是否具有读写权限
	// if
	// (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
	// {
	//
	// File file = new File(Common.DOWNLOAD_DIR + "/" + fileName);
	// if (file.exists()) {
	// installApk(BaseActivity.this, file);
	// } else {
	// if (MyApplication.getInstance().isDownload()) {
	// /*
	// * Intent it = new Intent(SettingActivity.this,
	// * DownloadService.class); it.putExtra("url",
	// * Constant.SERVER_URL + url);
	// * it.putExtra("fileName", fileName);
	// * SettingActivity.this.startService(it);
	// * SettingActivity.this.bindService(it, conn,
	// * Context.BIND_AUTO_CREATE);
	// */
	//
	// Map<String, String> map = new HashMap<String, String>();
	// map.put("url", url);
	// map.put("name", "零工�??");
	// map.put("filename", fileName);
	// map.put("fileSize", fileSize);
	// UpdateManager manager = new UpdateManager(BaseActivity.this, map,
	// MyApplication.getInstance());
	// manager.showNotifiction();
	// } else {
	// MyApplication.getInstance().getToastUtilsInstance().showNormalToast(BaseActivity.this,"更新文件正在下载，稍后会自动安装");
	// }
	//
	// }
	//
	// } else {
	// MyApplication.getInstance().getToastUtilsInstance().showNormalToast(BaseActivity.this,"SD卡不存在,
	// 无法下载更新程序");
	// }
	// versionUpdateDialog.dismiss();
	// break;
	// case R.id.dialog_product_cancel:
	// // 更改标记
	// versionUpdateDialog.dismiss();
	// break;
	// }
	// }
	// });
	// }
	//
	//
	// /**
	// * 安装APK文件
	// */
	// private void installApk(Context context, File file) {
	// File apkfile = new File(file.getAbsolutePath());
	// if (!apkfile.exists()) {
	// return;
	// }
	// // 通过Intent安装APK文件
	// Intent i = new Intent(Intent.ACTION_VIEW);
	// i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	// i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
	// "application/vnd.android.package-archive");
	// context.startActivity(i);
	// }
}
