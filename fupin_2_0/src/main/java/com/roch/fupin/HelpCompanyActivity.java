package com.roch.fupin;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.roch.fupin.adapter.HelpCompanyAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.HelpCompany;
import com.roch.fupin.entity.HelpCompanyListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.KeyBoardUtils;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;
import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年5月26日
 */
@ContentView(R.layout.activity_poorhouse)
public class HelpCompanyActivity extends MainBaseActivity{

	@ViewInject(R.id.refresh_poorhouse)
	PullToRefreshListView listview;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	int current_page = 1; //当前页码

	String title_name,search_content = "";

	private static int FRIST = 0;

	HelpCompanyAdapter adapter;

	static String uid = "helpdutypersonid";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initToolbar();
		MyApplication.getInstance().addActivity(this);

		Intent intent = getIntent();
		title_name = intent.getStringExtra(Common.INTENT_KEY);
		if (title_name != null && !title_name.equals("")) {
			tv_title.setText(title_name + "列表");
		}

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

	@Override
	protected void onNewIntent(Intent intent) {
		search_content = intent.getStringExtra(SearchManager.QUERY);
		initData();
	};

	/**
	 * 2016年5月25日
	 * ZhaoDongShao
	 */
	private void initData() {
		RequestParams rp = getRequestParams();
		rp.addBodyParameter(PoorHouseActivity.EXTS_PAGE, String.valueOf(current_page));
		System.out.println("请求帮扶单位List数据的网址为：=="+URLs.HELP_COMPANY_LIST);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.HELP_COMPANY_LIST, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
	}

	/**
	 * @return
	 * 2016年7月2日
	 * ZhaoDongShao
	 */
	private RequestParams getRequestParams() {
		RequestParams rp = new RequestParams();
		String adl_cd = getAdl_Cd();
//		if (!adl_cd.equals("")) {
//			rp.addBodyParameter("adl_cd", adl_cd);
//		}
		if (search_content != null) {
			if (!search_content.equals("")) {
				rp.addBodyParameter("name", search_content);
			}
		}
		return rp;
	}

	@OnItemClick(R.id.refresh_poorhouse)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		HelpCompany helpCompany = (HelpCompany)parent.getItemAtPosition(position);
		if (helpCompany != null) {
			Intent intent = new Intent(mContext, HelpCompanyPoorVillageActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable(Common.BUNDEL_KEY, helpCompany);
			intent.putExtra(Common.INTENT_KEY, bundle);
			startActivity(intent);
		}
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

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.help_company_menu, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
		SearchView.SearchAutoComplete edt = (SearchView.SearchAutoComplete)searchView.findViewById(R.id.search_src_text);
		edt.setTextColor(Color.WHITE);
		edt.setHint(ResourceUtil.getInstance().getStringById(R.string.edt_hini_companyname));
		edt.setHintTextColor(Color.WHITE);
		ComponentName name = new ComponentName(mActivity,HelpCompanyActivity.class);
		SearchableInfo info = searchManager.getSearchableInfo(name);
		searchView.setSearchableInfo(info);

		edt.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					/*隐藏软键盘*/
					searchView.clearFocus();
					KeyBoardUtils.closeKeyBoard(mContext);
					//清空
					search_content = v.getText().toString();
					current_page = 1;
					FRIST = 0;
					initData();
					return true;
				}
				return false;
			}
		});
		return true;
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		System.out.println("请求帮扶单位List数据成功：==" + str);
		HelpCompanyListResult helpCompanyListResult = null;
		switch (flag) {
		case MyConstans.FIRST:
			helpCompanyListResult = HelpCompanyListResult.parseToT(str, HelpCompanyListResult.class);
			if (!StringUtil.isEmpty(helpCompanyListResult)) {
				if (helpCompanyListResult.getSuccess()) {
					List<HelpCompany> helpCompanies = helpCompanyListResult.getJsondata();
					if (!StringUtil.isEmpty(helpCompanies)) {
						switch (FRIST) {
						case 0:
							if (adapter != null) {
								adapter.onRefsh(helpCompanies);
							}else {
								adapter = new HelpCompanyAdapter(mContext, helpCompanies);
								listview.setAdapter(adapter);
							}
							break;
						case 1:
							if (adapter == null) {
								adapter = new HelpCompanyAdapter(mContext, helpCompanies);
								listview.setAdapter(adapter);
							}else {
								adapter.addList(helpCompanies);
							}
							listview.onRefreshComplete();
							break;

						default:
							break;
						}
						listview.onRefreshComplete();
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
		System.out.println("请求帮扶单位List数据成功：==" + str);
		showToast(str);
		listview.onRefreshComplete();
	}
}
