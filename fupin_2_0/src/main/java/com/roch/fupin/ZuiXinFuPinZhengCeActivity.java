package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.roch.fupin.adapter.FuPinXinWenAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.FuPinXinWen;
import com.roch.fupin.entity.FuPinXinWen_ResultList;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 最近扶贫政策的activity
 * @author ZhaoDongShao
 * 2016年5月26日 
 */
@ContentView(R.layout.activity_poorhouse)
public class ZuiXinFuPinZhengCeActivity extends MainBaseActivity{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.refresh_poorhouse)
	PullToRefreshListView listview;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	int current_page = 1; //当前页码
	private static int FRIST = 0;
	
	FuPinXinWenAdapter adapter;

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
		
		listview.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				current_page = 1;
				FRIST = 0;
				initData();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				current_page++;
				FRIST = 1;
				initData();
			}
		});
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
		rp.addBodyParameter(PoorHouseActivity.EXTS_PAGE, String.valueOf(current_page));
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.ZuiXinFuPinZhengCe_LIST, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));

	}

	@OnItemClick(R.id.refresh_poorhouse)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		FuPinXinWen fuPinXinWen = (FuPinXinWen)parent.getItemAtPosition(position);
		if (fuPinXinWen != null) {
			Intent intent = new Intent(mContext, FuPinXinWenDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable(Common.BUNDEL_KEY, fuPinXinWen);
			bundle.putString(Common.TITLE_KEY, fuPinXinWen.getTitle());
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

		default:
			break;
		}
		return true;
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		FuPinXinWen_ResultList fuPinXinWen_resultList = null;
		switch (flag) {
		case MyConstans.FIRST:
			fuPinXinWen_resultList = FuPinXinWen_ResultList.parseToT(str, FuPinXinWen_ResultList.class);
			if (!StringUtil.isEmpty(fuPinXinWen_resultList)) {
				if (fuPinXinWen_resultList.getSuccess()) {
					List<FuPinXinWen> fuPinXinWens = fuPinXinWen_resultList.getJsondata();
					if (!StringUtil.isEmpty(fuPinXinWens)) {
						if(fuPinXinWens.size()>0){
							switch (FRIST) {
								case 0:
									if (adapter !=null) {
										adapter.onRefsh(fuPinXinWens);
									}else {
										adapter = new FuPinXinWenAdapter(mContext, fuPinXinWens);
										listview.setAdapter(adapter);
									}
									break;

								case 1:
									if (adapter != null) {
										adapter.addList(fuPinXinWens);
									}else {
										adapter = new FuPinXinWenAdapter(mContext, fuPinXinWens);
										listview.setAdapter(adapter);
									}
									break;

								default:
									break;
							}
							listview.onRefreshComplete();
						}else {
							showToast("暂无数据");
						}
					}else {
						showToast("当前没有更多数据");
						listview.onRefreshComplete();
					}
				}
				else {
					ShowNoticDialog();
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
		listview.onRefreshComplete();
	}
}
