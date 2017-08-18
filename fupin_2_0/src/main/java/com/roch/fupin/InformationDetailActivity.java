package com.roch.fupin;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.NoticBoard;
import com.roch.fupin.utils.Common;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * 信息宣传详情页activity
 * @author ZhaoDongShao
 * 2016年5月27日 
 */
@ContentView(R.layout.activity_infomation_detail)
public class InformationDetailActivity extends MainBaseActivity{

	@ViewInject(R.id.wv_notic)
	WebView wv_notic;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_msg_title)
	TextView tv_msg_title;
	@ViewInject(R.id.tv_date)
	TextView tv_data;
	@ViewInject(R.id.tv_name)
	TextView tv_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initToolbar();
		MyApplication.getInstance().addActivity(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initData();
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

		default:
			break;
		}
		return true;
	}
	
	/**
	 * 2016年5月27日
	 * ZhaoDongShao
	 */
	private void initData() {
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		if (bundle != null) {
			NoticBoard noticBoard = (NoticBoard) bundle.getSerializable(Common.BUNDEL_KEY);
			String title = bundle.getString(Common.TITLE_KEY);
			if (noticBoard != null) {
				tv_title.setText(title);
				tv_msg_title.setText(noticBoard.getTitle());
				tv_name.setText(noticBoard.getUsername());
				tv_data.setText(noticBoard.getCreatetime());
				System.out.print("信息公告栏webview的数据为：-------"+noticBoard.getContent());
				wv_notic.loadDataWithBaseURL(null, noticBoard.getContent(), "text/html", "utf-8", null);
			}
		}
	}	
}
