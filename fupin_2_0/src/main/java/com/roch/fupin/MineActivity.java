package com.roch.fupin;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.User;
import com.roch.fupin.utils.Common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

@ContentView(R.layout.activity_mine)
public class MineActivity extends MainBaseActivity{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	@ViewInject(R.id.tv_phone)
	TextView tv_phone;
	@ViewInject(R.id.tv_date)
	TextView tv_data;
	@ViewInject(R.id.tv_highestdegree)
	TextView tv_highestdegree;
	@ViewInject(R.id.tv_sex)
	TextView tv_sex;
	@ViewInject(R.id.tv_zhicheng)
	TextView tv_zhicheng;
	@ViewInject(R.id.tv_name)
	TextView tv_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		ViewUtils.inject(this);
		initToolbar();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initDate();
	}

	/**
	 *
	 *
	 * 2016年8月5日
	 *
	 * ZhaoDongShao
	 *
	 */
	private void initToolbar() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

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
	 *
	 *
	 * 2016年5月11日
	 *
	 * ZhaoDongShao
	 *
	 */
	private void initDate() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		User user = (User) bundle.getSerializable(Common.BUNDEL_KEY);
		if (user != null) {
			tv_title.setText(user.getUser_name());
			
			tv_data.setText(user.getBirthday());
			tv_highestdegree.setText(user.getHighestdegreeName());
			tv_phone.setText(user.getTelnumb());
			tv_zhicheng.setText(user.getTitle());
			tv_sex.setText(user.getSexName());
			tv_name.setText(user.getUser_name());
			
		}
	}
}
