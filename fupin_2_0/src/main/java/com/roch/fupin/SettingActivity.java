/**
 * 
 */
package com.roch.fupin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NormalDailog;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.DataCleanManager;
import com.roch.fupin.view.UpdatePassWordActivity;
import com.roch.fupin_2_0.R;

import java.io.File;

/**
 * @author ZhaoDongShao
 * 2016年6月17日
 */
@ContentView(R.layout.activity_setting)
public class SettingActivity extends MainBaseActivity{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.rl_ver)
	RelativeLayout rl_ver;
	@ViewInject(R.id.rl_update_pwd)
	RelativeLayout rl_update_pwd;
	@ViewInject(R.id.rl_clear)
	RelativeLayout rl_clear;
	@ViewInject(R.id.tv_size)
	TextView tv_size;
	@ViewInject(R.id.btn_login_out)
	Button btn_login_out;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(mActivity);
		initToolbar();
		MyApplication.getInstance().addActivity(mActivity);

		initDate();
	}

	/**
	 * 2016年8月5日
	 * ZhaoDongShao
	 */
	private void initToolbar() {
		toolbar.setTitle("");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			MyApplication.getInstance().finishActivity(this);
			break;
		}
		return true;
	}

	/**
	 * 2016年6月17日
	 * ZhaoDongShao
	 */
	private void initDate() {
		Intent intent = getIntent();
		String title = intent.getStringExtra(Common.INTENT_KEY);
		tv_title.setText("设置");

		initCacheSize();
	}

	/**
	 * 清除缓存
	 * 2016年6月17日
	 * ZhaoDongShao
	 */
	private void initCacheSize() {
		String path = Environment.getExternalStorageDirectory().getPath() + Common.CACHE_PATHE;
		File file = new File(path);
		try {
			String size = DataCleanManager.getCacheSize(file);
			tv_size.setText(size);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnClick({R.id.rl_clear,R.id.rl_ver,R.id.btn_login_out,R.id.rl_update_pwd})
	public void onClick(View v) {
		if (R.id.rl_clear == v.getId()) {
			final NormalDailog dailog = new NormalDailog(mContext,R.style.popup_dialog_style);
			dailog.show();
			dailog.setContentText("确定清除缓存数据吗？");
			dailog.setOnClickLinener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					switch (v.getId()) {
					case R.id.normal_dialog_done:
						showProgressDialog("正在清除...");
						DataCleanManager.cleanExternalCache(mContext);
						DataCleanManager.cleanSharedPreference(mContext);
						DataCleanManager.cleanInternalCache(mContext);
						DataCleanManager.cleanFiles(mContext);
						initCacheSize();
						cancelProgressDialog();
						dailog.dismiss();
						break;

					case R.id.normal_dialog_cancel:
						dailog.dismiss();
						break;
					default:
						break;
					}
				}
			});
		}else if(v.getId()==R.id.rl_update_pwd){ //点击了修改密码
			Intent intent=new Intent(mActivity, UpdatePassWordActivity.class);
			startActivity(intent);
		}else if (v.getId() == R.id.rl_ver) {
			
		}else if (v.getId() == R.id.btn_login_out) {
			AlertDialog.Builder dialog = new Builder(mContext);
			dialog.setTitle("提示");
			dialog.setMessage("是否退出当前登录用户？");
			dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					MyApplication.getInstance().finishAllActivity();
					Intent intent = new Intent(mContext, LoginActivity.class);
					startActivity(intent);
				}
			});
			dialog.setNegativeButton("取消", null);
			dialog.create().show();
		}
	}

}
