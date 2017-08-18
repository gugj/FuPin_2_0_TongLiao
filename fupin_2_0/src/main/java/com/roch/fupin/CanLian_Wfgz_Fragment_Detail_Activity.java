package com.roch.fupin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.NoPoorProjectCanLian_WFGZ_DetailAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ProjectCanlianRebuildAppModel;
import com.roch.fupin.utils.Common;
import com.roch.fupin_2_0.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 残联项目下的危房改造详情Activity
 * @author ZhaoDongShao
 * 2016年5月27日 
 */
@ContentView(R.layout.activity_helppeople_familypeople)
public class CanLian_Wfgz_Fragment_Detail_Activity extends MainBaseActivity implements OnClickListener{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.lv_poor)
	ListView listview;
	@ViewInject(R.id.tv_xiangmu_photo)
	TextView tv_xiangmu_photo;
	@ViewInject(R.id.lv_poor)
	Context mContext;
	Activity mActivity;

	NoPoorProjectCanLian_WFGZ_DetailAdapter adapter;
	private ProjectCanlianRebuildAppModel appModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		this.mContext = this;
		this.mActivity = this;
		initToolbar();
		MyApplication.getInstance().addActivity(mActivity);
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
				MyApplication.getInstance().finishActivity(mActivity);
				break;

			default:
				break;
		}
		return true;
	}

	/**
	 * 2016年5月25日
	 * ZhaoDongShao
	 */
	private void initData() {
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.INTENT_KEY);
		appModel = (ProjectCanlianRebuildAppModel) bundle.get(Common.BUNDEL_KEY);
		if (appModel != null) {
			tv_title.setText(appModel.getProjectname());
			tv_xiangmu_photo.setVisibility(View.VISIBLE);
			tv_xiangmu_photo.setOnClickListener(this);
			List<MapEntity> list = new ArrayList<MapEntity>();
//			list.add(new MapEntity("项目名称", appModel.getProjectname()));
			list.add(new MapEntity("户主姓名", appModel.getPersonname()));
			list.add(new MapEntity("年均收入", String.valueOf(appModel.getYearincome())));
			list.add(new MapEntity("家庭类型", appModel.getFamilytypeidcall()));
			list.add(new MapEntity("家庭住址", appModel.getAddress()));
			list.add(new MapEntity("申请状态", appModel.getApprovestatusidcall()));
			list.add(new MapEntity("申请日期", appModel.getApprovedate()));
			list.add(new MapEntity("旧房房屋面积", String.valueOf(appModel.getOldarea())));
			list.add(new MapEntity("旧房房屋结构", appModel.getOldstruct()));
			list.add(new MapEntity("改造方式", appModel.getRebuildmodeidcall()));
			list.add(new MapEntity("房屋结构", appModel.getRebuildstructidcall()));
			list.add(new MapEntity("改造面积", String.valueOf(appModel.getRebuildarea())));
			list.add(new MapEntity("动工时间", appModel.getStartdate().split(" ")[0]));
			list.add(new MapEntity("简要说明", appModel.getRemark()));
			adapter = new NoPoorProjectCanLian_WFGZ_DetailAdapter(mContext, list);
			listview.setAdapter(adapter);
		}else {
			return;
		}
//		if (appModel != null) {
//			List<MapEntity> list = new ArrayList<MapEntity>();
//			list.add(new MapEntity("项目名称", appModel.getProjectname()));
//			list.add(new MapEntity("户主姓名", appModel.getPersonname()));
//			list.add(new MapEntity("年均收入", String.valueOf(appModel.getYearincome())));
//			list.add(new MapEntity("家庭类型", appModel.getFamilytypeidcall()));
//			list.add(new MapEntity("家庭住址", appModel.getAddress()));
//			list.add(new MapEntity("申请状态", appModel.getApprovestatusidcall()));
//			list.add(new MapEntity("申请日期", appModel.getApprovedate()));
//			list.add(new MapEntity("旧房房屋面积", String.valueOf(appModel.getOldarea())));
//			list.add(new MapEntity("旧房房屋结构", appModel.getOldstruct()));
//			list.add(new MapEntity("改造方式", appModel.getRebuildmodeidcall()));
//			list.add(new MapEntity("房屋结构", appModel.getRebuildstructidcall()));
//			list.add(new MapEntity("改造面积", String.valueOf(appModel.getRebuildarea())));
//			list.add(new MapEntity("动工时间", appModel.getStartdate().split(" ")[0]));
//			list.add(new MapEntity("简要说明", appModel.getRemark()));
//			adapter = new NoPoorProjectCanLian_WFGZ_Adapter(mContext, list);
//			listview.setAdapter(adapter);
//		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			case R.id.tv_xiangmu_photo:
//			showToast("点击了项目照片");
				Intent intent=new Intent(this,XiangMuZhaoPianActivity.class);
				intent.putExtra("xiangmu_id", appModel.getProjectid());
				startActivity(intent);
				break;

			default:
				break;
		}
	}

}
