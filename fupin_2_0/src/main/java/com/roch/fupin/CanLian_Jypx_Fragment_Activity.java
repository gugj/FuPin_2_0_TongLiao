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
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.NoPoorProjectCanLian_JYPX_PXRY_Adapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.ProjectCanlianTrainAppModel;
import com.roch.fupin.entity.ProjectCanlianTrainAppModelListResult;
import com.roch.fupin.entity.ProjectCanlianTrainitemAppModel;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;
import java.util.List;

/**
 * 残联项目下的就业培训的列表Activity
 * @author ZhaoDongShao
 * 2016年6月2日 
 */
@ContentView(R.layout.activity_helppeople_familypeople)
public class CanLian_Jypx_Fragment_Activity extends MainBaseActivity implements OnClickListener{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.lv_poor)
	ListView listview;
	@ViewInject(R.id.tv_xiangmu_photo)
	TextView tv_xiangmu_photo;
	Context mContext;
	Activity mActivity;

	NoPoorProjectCanLian_JYPX_PXRY_Adapter adapter;
	private ProjectCanlianTrainAppModel appModel;

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
		appModel = (ProjectCanlianTrainAppModel) bundle.getSerializable(Common.BUNDEL_KEY);
		if (appModel != null) {
			tv_title.setText(appModel.getProjectname());
			tv_xiangmu_photo.setVisibility(View.VISIBLE);
			tv_xiangmu_photo.setOnClickListener(this);
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("id", appModel.getId());
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.NO_POOR_PROJECT_CANLIAN_JYPX_DETAIL, rp,
					new MyRequestCallBack(this, MyConstans.FIRST));
			System.out.println("残联项目下的就业培训的列表Activity请求服务的地址为：==="+URLs.NO_POOR_PROJECT_CANLIAN_JYPX_DETAIL+"&?id="+appModel.getId());
		}
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag) {
			case MyConstans.FIRST:
				System.out.println("残联项目下的就业培训的列表Activity请求服务的数据成功：==="+str);
				ProjectCanlianTrainAppModelListResult listResult = ProjectCanlianTrainAppModelListResult.parseToT(str, ProjectCanlianTrainAppModelListResult.class);
				if (listResult.getSuccess() && listResult != null) {
					List<ProjectCanlianTrainAppModel> list = listResult.getJsondata();
					if(null!=list && list.size()>0){
						for (int i = 0; i < list.size(); i++) {
							List<ProjectCanlianTrainitemAppModel> lAppModels = list.get(i).getPam();
							adapter = new NoPoorProjectCanLian_JYPX_PXRY_Adapter(mContext, lAppModels);
							listview.setAdapter(adapter);
						}
					}else {
						showToast("暂无数据");
					}
				}
				break;

			default:
				break;
		}
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		showToast(str);
		System.out.println("残联项目下的就业培训的列表Activity请求服务的数据失败：==="+str);
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
