/**
 * 
 */
package com.roch.fupin;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.adapter.HelpCompanyPoorVillageListAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.HelpCompany;
import com.roch.fupin.entity.HelpCompanyPoorVillageListResult;
import com.roch.fupin.entity.PoorVillageBase;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;

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

/**
 * @author ZhaoDongShao
 *
 * 2016年5月26日 
 *
 */
@ContentView(R.layout.activity_helppeople_familypeople)
public class HelpCompanyPoorVillageActivity extends MainBaseActivity{

	
	@ViewInject(R.id.lv_poor)
	ListView listview;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	
	HelpCompanyPoorVillageListAdapter adapter;
	
	static String uid = "id";
	
	/* (non-Javadoc)
	 * @see com.roch.fupin.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initToolbar();
		MyApplication.getInstance().addActivity(this);
		
		initData();

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
		HelpCompany helpCompany = (HelpCompany) bundle.getSerializable(Common.BUNDEL_KEY);
		if (helpCompany != null) {
			
			tv_title.setText("帮扶贫困村列表");
		
		}

		String uid = helpCompany.id;
		RequestParams rp = new RequestParams();
		rp.addBodyParameter(HelpPeoplePoorFamilyActivity.uid, uid);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.HELP_COMPANY_POOR_VILLAGE_LIST, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				PoorVillageBase poorHouse = (PoorVillageBase)parent.getItemAtPosition(position);
				if (poorHouse != null) {
					Intent intent = new Intent(mContext, PoorVillageDetailActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable(Common.BUNDEL_KEY, poorHouse);
					intent.putExtra(Common.INTENT_KEY, bundle);
					startActivity(intent);
				}
			}
		});
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
			
			MyApplication.getInstance().finishActivity(mActivity);
			
			break;

		default:
			break;
		}

		return true;
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		// TODO Auto-generated method stub
		super.onSuccessResult(str, flag);

		HelpCompanyPoorVillageListResult helpCompanyPoorVillageListResult = null;

		switch (flag) {
		case MyConstans.FIRST:

			helpCompanyPoorVillageListResult = HelpCompanyPoorVillageListResult.parseToT(str, HelpCompanyPoorVillageListResult.class);
			if (!StringUtil.isEmpty(helpCompanyPoorVillageListResult)) {
				if (helpCompanyPoorVillageListResult.getSuccess()) {
					List<PoorVillageBase> poorVillageBases = helpCompanyPoorVillageListResult.getJsondata();

					if (!StringUtil.isEmpty(poorVillageBases) && poorVillageBases.size() > 0) {
						adapter = new HelpCompanyPoorVillageListAdapter(mContext, poorVillageBases);
						listview.setAdapter(adapter);
					}else {
						showToast("当前没有更多数据");
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
		// TODO Auto-generated method stub
		super.onFaileResult(str, flag);
	}
	
}
