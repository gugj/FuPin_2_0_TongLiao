/**
 * 
 */
package com.roch.fupin;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.adapter.NoPoorProjectFuPinBan_PXRY_Adapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.ProjectFupinbanTrainAppModel;
import com.roch.fupin.entity.ProjectFupinbanTrainItemAppModel;
import com.roch.fupin.utils.Common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 扶贫办培训人员
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月27日 
 *
 */
@ContentView(R.layout.fupinban_pxry_activity)
public class FuPinBanPxryActivity extends BaseActivity{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.lv_poor)
	ListView listview;

	Context mContext;
	Activity mActivity;

	NoPoorProjectFuPinBan_PXRY_Adapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(mActivity);
		initToolbar();
		initData();
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
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	private void initData() {
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		ProjectFupinbanTrainAppModel appModel = (ProjectFupinbanTrainAppModel) bundle.getSerializable(Common.BUNDEL_KEY);
		if (appModel != null) {
			tv_title.setText("培训人员");
			List<ProjectFupinbanTrainItemAppModel> jyrys = appModel.getPam(); //培训信息
			if (jyrys!=null && jyrys.size() > 0) {
				adapter = new NoPoorProjectFuPinBan_PXRY_Adapter(mContext, jyrys);
				listview.setAdapter(adapter);
			}
		}

	}
}
