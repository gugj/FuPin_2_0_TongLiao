package com.roch.fupin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.util.LogUtils;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.CheckPhoneDialog;
import com.roch.fupin.dialog.NoticeDialog;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.CommonUtil;
import com.roch.fupin.utils.MyRequestCallBack.SuccessResult;
import com.roch.fupin_2_0.R;

/**
 * Fragment基类
 * 
 * @author ZhaoDongShao
 * 2016年9月5日
 */
public class BaseFragment extends Fragment implements SuccessResult {

	ProgressDialog mProgressDialog;
	LocationClient mLocationClient;
	BroadcastReceiver mReceiver;
	Activity mActivity;
	Context mContext;
	DbUtils dbUtil;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.mActivity = getActivity();
		this.mContext = getContext();
		
		// 通过获取的android版本改变状态栏的颜色
		CommonUtil.getInstance(mActivity).getState();
		// 获取手机的屏幕密度DPI、屏幕的宽度和高度
		initDensityDpi();
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	/**
	 * 获取手机的屏幕密度DPI、屏幕的宽度和高度
	 */
	private void initDensityDpi() {
		DisplayMetrics metrics = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		Common.densityDpi = metrics.densityDpi;
		Common.Width = metrics.widthPixels;
		Common.Hight = metrics.heightPixels;
		LogUtils.i(Common.densityDpi + "");
	}

	/*
	 * 显示进度条对话框--不可取消
	 */
	public void showProgressDialog(String msg) {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(mActivity);
			mProgressDialog.setCancelable(false);
			mProgressDialog.setMessage(msg);
		}
		if (!mProgressDialog.isShowing())
			mProgressDialog.show();
	}

	/*
	 * 显示进度条对话框--可取消
	 */
	public void showProgressDialog(String msg, boolean cancleble) {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(mActivity);
			mProgressDialog.setCancelable(cancleble);
			mProgressDialog.setMessage(msg);
		}
		if (!mProgressDialog.isShowing())
			mProgressDialog.show();
	}

	/**
	 * 隐藏进度条对话框
	 */
	public void cancelProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing())
			mProgressDialog.cancel();
	}

	/**
	 * 2016年10月27日
	 * 
	 * 显示一般时间的吐司
	 */
	public void ShowToast(String msg) {
		MyApplication.getInstance().getToastUtilsInstance().showNormalToast(getActivity(), msg);
	}

	/**
	 * 如果当前连接网络时出现故障，给出提示：服务器验证失败，请重新登陆！
	 * 2016年5月19日
	 * ZhaoDongShao
	 */
	@SuppressLint("NewApi")
	public void ShowNoticDialog() {
		NoticeDialog dialog = new NoticeDialog();
		dialog.show(getActivity().getFragmentManager(), "BaseActivity");
	}

	/**
	 * 返回EditText所编辑的内容
	 * @param ed
	 * @return
	 */
	public String getEditText(EditText ed) {
		return ed.getText().toString().trim();
	}

	/**
	 * 返回TextView所显示的内容
	 * @param
	 * @return
	 */
	public String getTextView(TextView tv) {
		return tv.getText().toString().trim();
	}

	/**
	 * 获取当前选择的行政区代码
	 * 2016年7月1日
	 * ZhaoDongShao
	 */
	public String getAdl_Cd() {
		AdlCode adlCode = MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(getActivity(),
				Common.LoginName);
		if (adlCode != null) {
			if (adlCode.getAd_cd() != null) {
				if (!adlCode.getAd_cd().equals("")) {
					return adlCode.getAd_cd();
				}
			}
		}
		return "";
	}

	@Override
	public void onSuccessResult(String str, int flag) {
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
	// versionUpdateDialog = new CheckUpDialog(activity,
	// R.style.popup_dialog_style);
	// Window win = versionUpdateDialog.getWindow();
	// win.setGravity(Gravity.CENTER);
	// WindowManager mWindowManager = (WindowManager)
	// activity.getSystemService(Context.WINDOW_SERVICE);
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
	// installApk(activity, file);
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
	// UpdateManager manager = new UpdateManager(activity, map,
	// MyApplication.getInstance());
	// manager.showNotifiction();
	// } else {
	// MyApplication.getInstance().getToastUtilsInstance().showNormalToast(activity,"更新文件正在下载，稍后会自动安装");
	// }
	//
	// }
	//
	// } else {
	// MyApplication.getInstance().getToastUtilsInstance().showNormalToast(activity,"SD卡不存在,
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

	/**
	 * 显示自定义的对电话号码进行拨打、保存操作的dialog对话框 <br/>
	 * 2016年7月15日  <br/>
	 * ZhaoDongShao <br/>
	 * @param phone 电话号码
	 */
	public void showPhoneDialog(final String phone) {

		final CheckPhoneDialog dialog = new CheckPhoneDialog(getActivity(), R.style.popup_dialog_style);
		Window window = dialog.getWindow();

		window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
		WindowManager windowManager = (WindowManager) this.getActivity().getSystemService(Context.WINDOW_SERVICE);
		window.setWindowManager(windowManager, null, null);
		dialog.setCanceledOnTouchOutside(true);
		window.setWindowAnimations(R.style.ContactAnimationPreview);
		dialog.show();
		dialog.setLable(phone);
		dialog.setOnClickListener(new OnClickListener() {

			Intent intent = null;
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.check_phone_dialog_cancel:
					dialog.dismiss();
					break;

				case R.id.check_save_phone_dialog:
					intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
					intent.setType("vnd.android.cursor.item/person");
					intent.setType("vnd.android.cursor.item/contact");
					intent.setType("vnd.android.cursor.item/raw_contact");
					intent.putExtra(android.provider.ContactsContract.Intents.Insert.PHONE, phone);
					intent.putExtra(android.provider.ContactsContract.Intents.Insert.PHONE_TYPE, 3);
					startActivity(intent);
					break;

				case R.id.check_call_phone_dialog:
					intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});
	}
}
