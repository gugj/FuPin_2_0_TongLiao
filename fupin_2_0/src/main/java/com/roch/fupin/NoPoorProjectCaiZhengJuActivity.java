/**
 * 
 */
package com.roch.fupin;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.roch.fupin_2_0.R;
import com.roch.fupin.adapter.NoPoorProjectCaiZhengJuAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NoPoorProjectFilterPopWindow;
import com.roch.fupin.dialog.NoPoorProjectFilterPopWindow.ShowMessageListener;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ProjectCaizhengjuAppModel;
import com.roch.fupin.entity.ProjectCaizhengjuAppModelListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.KeyBoardUtils;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;

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
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
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

/**
 * 财政局
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月2日 
 *
 */
@ContentView(R.layout.activity_poorhouse)
public class NoPoorProjectCaiZhengJuActivity extends MainBaseActivity implements ShowMessageListener{

	@ViewInject(R.id.refresh_poorhouse)
	PullToRefreshListView listview;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	NoPoorProjectCaiZhengJuAdapter adapter;

	NoPoorProjectFilterPopWindow filterPopWindow;
	//项目进度、相关职能部门全局变量，用来提供筛选
	String xmjd = "",xgznbm = "";

	String title_name,search_content = "";
	int current_page = 1; //当前页码
	private static int FRIST = 0;//状态

	List<MapEntity> maps = new ArrayList<MapEntity>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(this);

		Intent intent = getIntent();
		String title_name = intent.getStringExtra(Common.INTENT_KEY);
		if (title_name != null && !title_name.equals("")) {
			tv_title.setText(title_name);
		}

		initToolbar();
		initData();


		listview.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				xmjd = "";
				xgznbm = "";
				current_page = 1;
				FRIST = 0;
				initData();

			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				current_page++;
				FRIST = 1;
				initData();
				//				Common.PAGR = String.valueOf(Integer.valueOf(Common.PAGR) + 1 );
				//				RequestParams rp = getRequestParams();
				//				if (!xgznbm.equals("")) {
				//					rp.addBodyParameter("juweicall", xgznbm);
				//				}
				//				if (!xmjd.equals("")) {
				//					rp.addBodyParameter("projectscheduleidcall", xmjd);
				//				}
				//				rp.addBodyParameter(Common.EXTS_PAGE, Common.PAGR);
				//				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				//						URLs.NO_POOR_PROJECT_CAIZHENGJU , rp,
				//						new MyRequestCallBack((BaseActivity) mActivity, MyConstans.REFERSH));
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
		toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				// TODO Auto-generated method stub

				switch (menuItem.getItemId()) {
				case R.id.select:

					int xPox = (int)(Common.Width * 0.9);
					filterPopWindow = new NoPoorProjectFilterPopWindow(mContext);

					filterPopWindow.setShowMessageListener(NoPoorProjectCaiZhengJuActivity.this);
					filterPopWindow.showXmjdGridView(true);
					filterPopWindow.showXgznbmGridView(true);
					filterPopWindow.setSelectionAdapter(NoPoorProjectCaiZhengJuActivity.class, maps);
					filterPopWindow.showPopupWindow(toolbar,xPox);

					break;

				default:
					break;
				}

				return false;
			}
		});
	}

	@Override
	protected void onNewIntent(Intent intent) {

		search_content = intent.getStringExtra(SearchManager.QUERY);
		initData();

	};


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


	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
		SearchView.SearchAutoComplete edt = (SearchView.SearchAutoComplete)searchView.findViewById(R.id.search_src_text);
		edt.setTextColor(Color.WHITE);
		edt.setHint(ResourceUtil.getInstance().getStringById(R.string.edt_hini_projectname));
		edt.setHintTextColor(Color.WHITE);
		ComponentName name = new ComponentName(mActivity,NoPoorProjectCaiZhengJuActivity.class);
		SearchableInfo info = searchManager.getSearchableInfo(name);
		searchView.setSearchableInfo(info);

		edt.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
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


	//	@OnClick({R.id.tv_back,R.id.tv_filter,R.id.tv_search})
	//	public void onClick(View v)
	//	{
	//		if (v.getId() == R.id.tv_back) {
	//
	//			MyApplication.getInstance().finishActivity(mActivity);
	//
	//		}else if (v.getId() == R.id.tv_filter) {
	//
	//			int xPox = (int)(Common.Width * 0.9);
	//			filterPopWindow = new NoPoorProjectFilterPopWindow(mContext);
	//
	//			filterPopWindow.setShowMessageListener(this);
	//			filterPopWindow.showXmjdGridView(true);
	//			filterPopWindow.showXgznbmGridView(true);
	//			filterPopWindow.setSelectionAdapter(NoPoorProjectCaiZhengJuActivity.class, maps);
	//			filterPopWindow.showPopupWindow(v,xPox);
	//
	//		}else if (v.getId() == R.id.tv_search) {
	//			Intent intent = new Intent(mContext, SearchActivity1.class);
	//			intent.putExtra(Common.INTENT_KEY, getTextView(tv_title));
	//			intent.putExtra(Common.TEXT_HINI, ResourceUtil.getInstance().getStringById(R.string.edt_hini_projectname));
	//			startActivity(intent);
	//		}
	//	}

	/**
	 *
	 *
	 * 2016年5月6日
	 *
	 * ZhaoDongShao
	 *
	 */
	private void initData() {

		RequestParams rp = getRequestParams();
		//		if (!xgznbm.equals("")) {
		//			rp.addBodyParameter("juweicall", xgznbm);
		//		}
		//		if (!xmjd.equals("")) {
		//			rp.addBodyParameter("projectscheduleidcall", xmjd);
		//		}
		rp.addBodyParameter(Common.EXTS_PAGE, String.valueOf(current_page));
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NO_POOR_PROJECT_CAIZHENGJU, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));

	}

	/**
	 *
	 * @return
	 *
	 * 2016年7月1日
	 *
	 * ZhaoDongShao
	 *
	 */
	private RequestParams getRequestParams() {
		// TODO Auto-generated method stub
		RequestParams rp = new RequestParams();
		if (!xgznbm.equals("")) {
			rp.addBodyParameter("juweicall", xgznbm);
		}
		if (!xmjd.equals("")) {
			rp.addBodyParameter("projectscheduleidcall", xmjd);
		}

		String adl_cd = getAdl_Cd();
		if (!adl_cd.equals("")) {
			rp.addBodyParameter("adl_cd", adl_cd);
		}
		if (search_content != null) {
			if (!search_content.equals("")) {
				rp.addBodyParameter("projectname", search_content);
			}

		}
		return rp;
	}


	@OnItemClick(R.id.refresh_poorhouse)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		ProjectCaizhengjuAppModel appEntity = (ProjectCaizhengjuAppModel)parent.getItemAtPosition(position);
		if (appEntity != null) {
			Intent intent = new Intent(mContext, NoPoorProjectCaiZhengJuDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable(Common.BUNDEL_KEY, appEntity);
			intent.putExtra(Common.INTENT_KEY, bundle);
			startActivity(intent);
		}
	}


	/* (non-Javadoc)
	 * @see com.roch.fupin.BaseActivity#onSuccessResult(java.lang.String, int)
	 */
	@Override
	public void onSuccessResult(String str, int flag) {
		// TODO Auto-generated method stub
		super.onSuccessResult(str, flag);

		ProjectCaizhengjuAppModelListResult caizhengjuAppModelListResult = null;

		switch (flag) {
		case MyConstans.FIRST:

			caizhengjuAppModelListResult = ProjectCaizhengjuAppModelListResult.parseToT(str, ProjectCaizhengjuAppModelListResult.class);
			if (!StringUtil.isEmpty(caizhengjuAppModelListResult)) {
				if (caizhengjuAppModelListResult.getSuccess()) {
					List<ProjectCaizhengjuAppModel> lPoorHouses = caizhengjuAppModelListResult.getJsondata();

					if (!StringUtil.isEmpty(lPoorHouses)) {

						switch (FRIST) {
						case 0:
							if (adapter != null) {
								adapter.onRefsh(lPoorHouses);
							}else {
								adapter = new NoPoorProjectCaiZhengJuAdapter(mContext, lPoorHouses);
								listview.setAdapter(adapter);
							}
							break;
						case 1:
							if (adapter == null) {
								adapter = new NoPoorProjectCaiZhengJuAdapter(mContext, lPoorHouses);
								listview.setAdapter(adapter);
							}else {
								adapter.addList(lPoorHouses);
							}
							break;
						default:
							break;
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
			listview.onRefreshComplete();
			break;

		default:
			break;
		}
	}


	@Override
	public void onFaileResult(String str, int flag) {
		// TODO Auto-generated method stub
		super.onFaileResult(str, flag);
		showToast(str);
		listview.onRefreshComplete();
	}


	@Override
	public void Message(String string_xmjd, String string_xgznbm, String string_yzpz,String time) {
		// TODO Auto-generated method stub
		FRIST = 0;
		RequestParams rp = new RequestParams();
		maps.clear();
		if (!string_xgznbm.equals("")) {
			rp.addBodyParameter("juweicall", string_xgznbm);
			xgznbm = string_xgznbm;
			maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xgznbm), string_xgznbm));
		}else {
			xgznbm = "";
			maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xgznbm), string_xgznbm.replace("", "不限")));
		}
		if (!string_xmjd.equals("")) {
			rp.addBodyParameter("projectscheduleidcall", string_xmjd);
			xmjd = string_xmjd;
			maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmjd), string_xmjd));
		}else {
			xmjd = "";
			maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmjd), string_xmjd.replace("", "不限")));
		}
		if (!time.equals("")) { //立项时间
			rp.addBodyParameter("lixiangdate", time);
		}
		
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NO_POOR_PROJECT_CAIZHENGJU, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		filterPopWindow.dismiss();

	}

}
