package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.HelpPeoplePoorFamilyListAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.HelpPeople;
import com.roch.fupin.entity.PoorFamilyBase;
import com.roch.fupin.entity.PoorFamilyListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;
import java.util.List;

/**
 * 帮扶责任人负责的贫困户
 * @author ZhaoDongShao
 * 2016年5月25日
 */
@ContentView(R.layout.activity_helppeople_familypeople)
public class HelpPeoplePoorFamilyActivity extends MainBaseActivity{

	@ViewInject(R.id.lv_poor)
	ListView listview;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	HelpPeoplePoorFamilyListAdapter adapter;

	static String uid = "id";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initToolbar();
		MyApplication.getInstance().addActivity(this);

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
		HelpPeople helpPeople = (HelpPeople) bundle.getSerializable(Common.BUNDEL_KEY);
		if (helpPeople != null) {
			tv_title.setText(helpPeople.getName()+ "——帮扶贫困户列表" );
		}

		String uid = helpPeople.getId();
		RequestParams rp = new RequestParams();
		rp.addBodyParameter(HelpPeoplePoorFamilyActivity.uid, uid);
		System.out.println("请求帮扶责任人列表的网址为：===" + URLs.HELP_PEOPLE_POOR_FAMILY_LIST+"?id="+uid);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.HELP_PEOPLE_POOR_FAMILY_LIST, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				PoorFamilyBase poorHouse = (PoorFamilyBase)parent.getItemAtPosition(position);
				if (poorHouse != null) {
					Intent intent = new Intent(mContext, PoorHouseDetailActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable(Common.BUNDEL_KEY, poorHouse);
					intent.putExtra(Common.INTENT_KEY, bundle);
					startActivity(intent);
				}
			}
		});
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		PoorFamilyListResult poorFamilyListResult = null;
		switch (flag) {
		case MyConstans.FIRST:
			System.out.println("请求帮扶责任人列表成功：==="+str);
			poorFamilyListResult = PoorFamilyListResult.parseToT(str, PoorFamilyListResult.class);
			if (!StringUtil.isEmpty(poorFamilyListResult)) {
				if (poorFamilyListResult.getSuccess()) {
					List<PoorFamilyBase> poorFamilyBases = poorFamilyListResult.getJsondata();
					if (!StringUtil.isEmpty(poorFamilyBases) && poorFamilyBases.size() > 0) {
						adapter = new HelpPeoplePoorFamilyListAdapter(mContext, poorFamilyBases);
						listview.setAdapter(adapter);
					}
				} else {
					ShowNoticDialog();
				}
			}
			break;
		}
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		System.out.println("请求帮扶责任人列表失败：===" + str);
		showToast(str);
	}
}
