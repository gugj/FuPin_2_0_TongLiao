package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.GongZuoRiZhi_GV_Adapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.GongZuoRiZhi;
import com.roch.fupin.entity.GongZuoRiZhi_ResultList;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 工作日志的activity
 * @author ZhaoDongShao
 * 2016年5月26日 
 */
@ContentView(R.layout.acitivity_gongzuorizhi)
public class GongZuoRiZhiActivity extends MainBaseActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;

	@ViewInject(R.id.tv_title)
	TextView tv_title;

	/**
	 * gridview的容器
	 */
//	@ViewInject(R.id.rl_gongzuorizhi_contaimer)
//	RelativeLayout rl_gongzuorizhi_contaimer;

	/**
	 * 系统的gridview
	 */
	@ViewInject(R.id.gv_gongzuorizhi)
	GridView mDragMoreGrid;

	/**
	 * 自定义的gridview的适配器
	 */
	GongZuoRiZhi_GV_Adapter gongZuoRiZhi_gv_adapter;
	private int current_page = 1; //当前页码

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(this);
		
		Intent intent = getIntent();
		String title_name = intent.getStringExtra(Common.INTENT_KEY);
		if (title_name != null && !title_name.equals("")) {
			tv_title.setText(title_name + "列表");
		}
		
		initToolbar();
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
	
	/**
	 * 2016年5月25日
	 * ZhaoDongShao
	 */
	private void initData() {
		RequestParams rp = new RequestParams();
		rp.addBodyParameter("page",String.valueOf(current_page));
		System.out.println("请求查看工作日志的网址为："+URLs.GongZuoRiZhi_LIST);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.GongZuoRiZhi_LIST, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		GongZuoRiZhi gongZuoRiZhi = (GongZuoRiZhi)parent.getItemAtPosition(position);
		if (gongZuoRiZhi != null) {
			Intent intent = new Intent(mContext, GongZuoRiZhiDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable(Common.BUNDEL_KEY, gongZuoRiZhi);
			intent.putExtra(Common.INTENT_KEY, bundle);
			
			startActivity(intent);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			MyApplication.getInstance().finishActivity(mActivity);
			break;
		}
		return true;
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		GongZuoRiZhi_ResultList gongZuoRiZhi_resultList = null;
		switch (flag) {
		case MyConstans.FIRST:
			System.out.println("获取工作日志的数据成功：==="+str);
			gongZuoRiZhi_resultList = GongZuoRiZhi_ResultList.parseToT(str, GongZuoRiZhi_ResultList.class);
			if (!StringUtil.isEmpty(gongZuoRiZhi_resultList)) {
				if (gongZuoRiZhi_resultList.getSuccess()) {
					List<GongZuoRiZhi> gongZuoRiZhis = gongZuoRiZhi_resultList.getJsondata();
					if (!StringUtil.isEmpty(gongZuoRiZhis) && gongZuoRiZhis.size()>0) {
						if(null!=gongZuoRiZhi_gv_adapter){
							gongZuoRiZhi_gv_adapter.addList(gongZuoRiZhis);
						}else {
							gongZuoRiZhi_gv_adapter = new GongZuoRiZhi_GV_Adapter(mContext, gongZuoRiZhis);
							mDragMoreGrid.setAdapter(gongZuoRiZhi_gv_adapter);
						}
						mDragMoreGrid.setOnItemClickListener(this);
						mDragMoreGrid.setOnScrollListener(this);
					}else {
						showToast("暂无更多数据");
					}
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
		System.out.println("获取工作日志的数据失败：===" + str);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		int lastVisiblePosition = mDragMoreGrid.getLastVisiblePosition();
//		System.out.println("GridView的总条目：===" + totalItemCount);
//		System.out.println("GridView的最后一个可见条目位置：===" + lastVisiblePosition);
		if(lastVisiblePosition==totalItemCount-1){ //滑动到最后一个可见条目时
			current_page++;
			initData();
		}
	}

}
