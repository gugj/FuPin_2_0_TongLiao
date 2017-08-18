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

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.roch.fupin.adapter.NoPoorProjectRenLaoJuAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NoPoorProjectFilterPopWindow;
import com.roch.fupin.dialog.NoPoorProjectFilterPopWindow.ShowOneMessageListener;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ProjectRenlaoAppModel;
import com.roch.fupin.entity.ProjectRenlaoAppModelListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.KeyBoardUtils;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 人社局Activity
 * @author ZhaoDongShao
 * 2016年6月2日 
 */
@ContentView(R.layout.activity_poorhouse)
public class NoPoorProjectRenLaoJuActivity extends MainBaseActivity implements ShowOneMessageListener{

	private static final String EXTS_PAGE = "page";

	@ViewInject(R.id.refresh_poorhouse)
	PullToRefreshListView listview;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	String pxlb = ""; //培训类别

	String title_name,search_content = "";
	int current_page = 1; //当前页码
	private static int FRIST = 0;//状态

	NoPoorProjectRenLaoJuAdapter adapter;
	NoPoorProjectFilterPopWindow filterPopWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
				current_page = 1;
				FRIST = 0;
				search_content = "";
				initData();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				current_page++;
				FRIST = 1;
				initData();
				//				Common.PAGR = String.valueOf(Integer.valueOf(Common.PAGR) + 1 );
				//				RequestParams rp = getRequestParams();
				//				if (!pxlb.equals("")) {
				//					rp.addBodyParameter("traintypeidcall", pxlb); //项目进度
				//				}
				//				rp.addBodyParameter(EXTS_PAGE, Common.PAGR);
				//				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				//						URLs.NO_POOR_PROJECT_RENLAOJU , rp,
				//						new MyRequestCallBack((BaseActivity) mActivity, MyConstans.REFERSH));
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
		toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				switch (menuItem.getItemId()) {
					case R.id.select:
						int xPox = (int)(Common.Width * 0.9);
						filterPopWindow = new NoPoorProjectFilterPopWindow(mContext);
						filterPopWindow.setShowOneMessageListener(NoPoorProjectRenLaoJuActivity.this);
						filterPopWindow.showPxlbGridView(true);
						filterPopWindow.setSelectionAdapter(NoPoorProjectRenLaoJuActivity.class, maps);
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
		inflater.inflate(R.menu.main, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
		SearchView.SearchAutoComplete edt = (SearchView.SearchAutoComplete)searchView.findViewById(R.id.search_src_text);
		edt.setTextColor(Color.WHITE);
		edt.setHint(ResourceUtil.getInstance().getStringById(R.string.edt_hini_projectname));
		edt.setHintTextColor(Color.WHITE);
		ComponentName name = new ComponentName(mActivity,NoPoorProjectRenLaoJuActivity.class);
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
	//			filterPopWindow.setShowOneMessageListener(this);
	//			filterPopWindow.showPxlbGridView(true);
	//			filterPopWindow.setSelectionAdapter(NoPoorProjectRenLaoJuActivity.class, maps);
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
	 * 2016年5月6日
	 * ZhaoDongShao
	 */
	private void initData() {
		RequestParams rp = getRequestParams();
		//		if (!pxlb.equals("")) {
		//			rp.addBodyParameter("traintypeidcall", pxlb);
		//		}
		//		String adl_cd = getAdl_Cd();
		//		if (!adl_cd.equals("")) {
		//			rp.addBodyParameter("adl_cd", adl_cd);
		//		}
		rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NO_POOR_PROJECT_RENLAOJU, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
	}

	/**
	 * @return
	 * 2016年7月1日
	 * ZhaoDongShao
	 */
	private RequestParams getRequestParams() {
		RequestParams rp = new RequestParams();
		if (!pxlb.equals("")) {
			rp.addBodyParameter("traintypeidcall", pxlb);
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
		ProjectRenlaoAppModel appEntity = (ProjectRenlaoAppModel)parent.getItemAtPosition(position);
		if (appEntity != null) {
			Intent intent = new Intent(mContext, NoPoorProjectRenLaoJuDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable(Common.BUNDEL_KEY, appEntity);
			intent.putExtra(Common.INTENT_KEY, bundle);
			startActivity(intent);
		}
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		ProjectRenlaoAppModelListResult renlaoAppModelListResult = null;
		switch (flag) {
			case MyConstans.FIRST:
				System.out.println("人社局Activity请求服务器的数据成功：==="+str);
				renlaoAppModelListResult = ProjectRenlaoAppModelListResult.parseToT(str, ProjectRenlaoAppModelListResult.class);
				if (!StringUtil.isEmpty(renlaoAppModelListResult)) {
					if (renlaoAppModelListResult.getSuccess()) {
						List<ProjectRenlaoAppModel> lPoorHouses = renlaoAppModelListResult.getJsondata();
						if (!StringUtil.isEmpty(lPoorHouses)) {
							switch (FRIST) {
								case 0:
									if (adapter != null) {
										adapter.onRefsh(lPoorHouses);
									}else {
										adapter = new NoPoorProjectRenLaoJuAdapter(mContext, lPoorHouses);
										listview.setAdapter(adapter);
									}
									break;

								case 1:
									if (adapter == null) {
										adapter = new NoPoorProjectRenLaoJuAdapter(mContext, lPoorHouses);
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
		super.onFaileResult(str, flag);
		System.out.println("人社局Activity请求服务器的数据失败：==="+str);
		showToast(str);
		listview.onRefreshComplete();
	}

	List<MapEntity> maps = new ArrayList<MapEntity>();
	@Override
	public void Message(String string_pxlb, String time) {
		FRIST = 0;
		RequestParams rp = new RequestParams();

		maps.clear();

		if (!string_pxlb.equals("")) {
			rp.addBodyParameter("traintypeidcall", string_pxlb);
			pxlb = string_pxlb;
			maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.pxlb), string_pxlb));
		}else {
			pxlb = "";
			maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.pxlb), string_pxlb.replace("", "不限")));
		}
		if (!time.equals("")) { //立项时间
			rp.addBodyParameter("lixiangdate", time);
		}
		//		rp.addBodyParameter("traintypeidcall", string_pxlb);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NO_POOR_PROJECT_RENLAOJU, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		filterPopWindow.dismiss();
	}

}
