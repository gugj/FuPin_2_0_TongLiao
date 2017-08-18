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
import com.roch.fupin.adapter.NoPoorProjectFuPinBanAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NoPoorProjectFilterPopWindow;
import com.roch.fupin.dialog.NoPoorProjectFilterPopWindow.ShowMessageListener;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ProjectFupinbanTrainAppModel;
import com.roch.fupin.entity.ProjectFupinbanTrainAppModelListResult;
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
 * @author ZhaoDongShao
 *
 * 2016年6月2日 
 *
 */
@ContentView(R.layout.activity_poorhouse)
public class NoPoorProjectFuPinBanActivity extends MainBaseActivity implements ShowMessageListener{

	@ViewInject(R.id.refresh_poorhouse)
	PullToRefreshListView listview;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;


	String xmjd = "",xmlx = "";

	String title_name,search_content = "";
	int current_page = 1; //当前页码
	private static int FRIST = 0;//状态

	NoPoorProjectFuPinBanAdapter adapter;
	NoPoorProjectFilterPopWindow filterPopWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(mActivity);

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
				current_page = 1;
				FRIST = 0;
				search_content = "";
				initData();

			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				current_page++;
				FRIST = 1;
				initData();
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
					filterPopWindow.setShowMessageListener(NoPoorProjectFuPinBanActivity.this);
					filterPopWindow.showXmlxGridView(true);
					filterPopWindow.showXmjdGridView(true);
					filterPopWindow.setSelectionAdapter(NoPoorProjectFuPinBanActivity.class, maps);
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
		ComponentName name = new ComponentName(mActivity,NoPoorProjectFuPinBanActivity.class);
		SearchableInfo info = searchManager.getSearchableInfo(name);
		searchView.setSearchableInfo(info);

		edt.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				if (actionId == EditorInfo.IME_ACTION_SEND) {

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
	//			filterPopWindow.setShowMessageListener(this);
	//			filterPopWindow.showXmlxGridView(true);
	//			filterPopWindow.showXmjdGridView(true);
	//			filterPopWindow.setSelectionAdapter(NoPoorProjectFuPinBanActivity.class, maps);
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
		//		if (!xmlx.equals("")) {
		//			rp.addBodyParameter("projectcategoryidcall", xmlx);
		//		}
		//		if (!xmjd.equals("")) {
		//			rp.addBodyParameter("projectscheduleidcall", xmjd);
		//		}
		rp.addBodyParameter(Common.EXTS_PAGE, String.valueOf(current_page));
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NO_POOR_PROJECT_FUPINBAN, rp,
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
		if (!xmlx.equals("")) {
			rp.addBodyParameter("projectcategoryidcall", xmlx);
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
		ProjectFupinbanTrainAppModel pfb = (ProjectFupinbanTrainAppModel)parent.getItemAtPosition(position);

		if (pfb != null) {
			//产业化项目
			if (pfb.getProjectcategoryid().equals("1101")
					||pfb.getProjectcategoryid().equals("1102")
					||pfb.getProjectcategoryid().equals("1103")
					||pfb.getProjectcategoryid().equals("1105")
					||pfb.getProjectcategoryid().equals("1111")) {
				
				Intent intent = new Intent(mContext, FuPinBanXmxxActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, pfb);
				intent.putExtra(Common.INTENT_KEY, bundle);
				startActivity(intent);
				
			}else {
				//培训项目
				Intent intent = new Intent(mContext, FuPinBanPxryActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, pfb);
				intent.putExtra(Common.INTENT_KEY, bundle);
				startActivity(intent);
			}
			
		}
	}


	/* (non-Javadoc)
	 * @see com.roch.fupin.BaseActivity#onSuccessResult(java.lang.String, int)
	 */
	@Override
	public void onSuccessResult(String str, int flag) {
		// TODO Auto-generated method stub
		super.onSuccessResult(str, flag);

		ProjectFupinbanTrainAppModelListResult fupinbanTrainAppModelListResult = null;

		switch (flag) {
		case MyConstans.FIRST:

			fupinbanTrainAppModelListResult = ProjectFupinbanTrainAppModelListResult.parseToT(str, ProjectFupinbanTrainAppModelListResult.class);
			if (!StringUtil.isEmpty(fupinbanTrainAppModelListResult)) {
				if (fupinbanTrainAppModelListResult.getSuccess()) {
					List<ProjectFupinbanTrainAppModel> lPoorHouses = fupinbanTrainAppModelListResult.getJsondata();

					if (!StringUtil.isEmpty(lPoorHouses)) {

						switch (FRIST) {
						case 0:
							if (adapter != null) {
								adapter.onRefsh(lPoorHouses);
							}else {
								adapter = new NoPoorProjectFuPinBanAdapter(mContext, lPoorHouses);
								listview.setAdapter(adapter);
							}
							break;

						case 1:
							if (adapter == null) {
								adapter = new NoPoorProjectFuPinBanAdapter(mContext, lPoorHouses);
								listview.setAdapter(adapter);
							}else {
								adapter.addList(lPoorHouses);
							}
							break;
						default:
							break;
						}
					}
					else {
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


	List<MapEntity> maps = new ArrayList<MapEntity>();
	@Override
	public void Message(String string_xmjd, String string_xmlx, String a, String time) {
		// TODO Auto-generated method stub
		FRIST = 0;
		RequestParams rp = new RequestParams();

		maps.clear();
		if (!string_xmlx.equals("")) {
			rp.addBodyParameter("projectcategoryidcall", string_xmlx);
			xmlx = string_xmlx;
			maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmlx), string_xmlx));
		}else {
			xmlx = "";
			maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmlx), string_xmlx.replace("", "不限")));
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
				URLs.NO_POOR_PROJECT_FUPINBAN, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		filterPopWindow.dismiss();
	}

}
