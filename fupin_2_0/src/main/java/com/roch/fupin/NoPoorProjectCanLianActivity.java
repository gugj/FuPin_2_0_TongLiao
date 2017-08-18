package com.roch.fupin;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.ViewPagerAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NoPoorProjectFilterPopWindow;
import com.roch.fupin.dialog.NoPoorProjectFilterPopWindow.ShowOneMessageListener;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ProjectCanlianRebuildAppModel;
import com.roch.fupin.entity.ProjectCanlianRebuildAppModelListResult;
import com.roch.fupin.entity.ProjectCanlianTrainAppModel;
import com.roch.fupin.entity.ProjectCanlianTrainAppModelListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.KeyBoardUtils;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 残联Activity
 * @author ZhaoDongShao
 * 2016年6月3日 
 */
@ContentView(R.layout.activity_poorhouse_detail)
public class NoPoorProjectCanLianActivity extends MainBaseActivity implements ShowOneMessageListener{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	@ViewInject(R.id.ll_navibar)
	private LinearLayout layout_title_name;
	@ViewInject(R.id.vp_pager)
	ViewPager viewPager;

	List<Fragment> list = new ArrayList<Fragment>();
	ViewPagerAdapter viewPagerAdapter;

	/**
	 * 当前fragment索引
	 */
	private int currPage = 0;
	/**
	 * table页标题
	 */
	private String[] title_names;
	/**
	 * 所有标题
	 */
	List<View> listviews = new ArrayList<View>();
	//改造原因，培训类别
	public static String gzyy = "",pxlb = "";

	String title_name,search_content = "";
	int current_page = 1; //当前页码

	NoPoorProjectFilterPopWindow filterPopWindow;

	List<ProjectCanlianTrainAppModel> trainAppModel;
	List<ProjectCanlianRebuildAppModel> rebuildAppModel;
	boolean isloading_first = false; //第一次加载
	boolean isloading_second = false; //第二次加载

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
						filterPopWindow.setShowOneMessageListener(NoPoorProjectCanLianActivity.this);
						if (currPage == 1) {
							filterPopWindow.showPxlbGridView(true);
						}else {
							filterPopWindow.showGzfsGridView(true);
						}
						filterPopWindow.setSelectionAdapter(NoPoorProjectCanLianActivity.class, maps);
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
		ComponentName name = new ComponentName(mActivity,NoPoorProjectCanLianActivity.class);
		SearchableInfo info = searchManager.getSearchableInfo(name);
		searchView.setSearchableInfo(info);

		edt.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					/*隐藏软键盘*/
					searchView.clearFocus();
					KeyBoardUtils.closeKeyBoard(mContext);
					search_content = v.getText().toString();
					current_page = 1;
					initData();
					return true;
				}
				return false;
			}
		});
		return true;
	}

	/**
	 * 2016年5月9日
	 * ZhaoDongShao
	 */
	private void initData() {
		//危房改造
		RequestParams rp = getWFGZRequestParams();
		rp.addBodyParameter(Common.EXTS_PAGE, Common.PAGR);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NO_POOR_PROJECT_CANLIAN_WFGZ, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		System.out.println("残联项目下的危房改造请求服务器的地址为：==="+URLs.NO_POOR_PROJECT_CANLIAN_WFGZ);
		//就业培训
		RequestParams rp1 = getJYPXRequestParams();
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NO_POOR_PROJECT_CANLIAN_JYPX, rp1,
				new MyRequestCallBack(this, MyConstans.SECOND));
		System.out.println("残联项目下的就业培训请求服务器的地址为：==="+URLs.NO_POOR_PROJECT_CANLIAN_JYPX);

		title_names = ResourceUtil.getInstance().getStringArrayById(R.array.canlian_title);
		listviews.clear();
		layout_title_name.removeAllViews();
		for (int i = 0; i < title_names.length; i++) {
			final TextView tv_title_name = new TextView(mContext);
			tv_title_name.setGravity(Gravity.CENTER);
			tv_title_name.setPadding(5, 5, 5, 5);
			tv_title_name.setTextSize(Common.TEXT_SIZE);
			tv_title_name.setId(i);
			tv_title_name.setText(title_names[i]);
			layout_title_name.addView(tv_title_name, Common.Width / 4,LinearLayout.LayoutParams.WRAP_CONTENT);
			listviews.add(tv_title_name);

			tv_title_name.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (currPage == v.getId()){
					}else {
						viewPager.setCurrentItem(v.getId());
					}
				}
			});
		}
	}

	/**
	 * @return
	 * 2016年7月1日
	 * ZhaoDongShao
	 */
	private RequestParams getJYPXRequestParams() {
		RequestParams rp = new RequestParams();
		if (pxlb != null && !pxlb.equals("")) {
			rp.addBodyParameter("rebuildmodeidcall", pxlb);
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

	/**
	 * @return
	 * 2016年7月1日
	 * ZhaoDongShao
	 */
	private RequestParams getWFGZRequestParams() {
		RequestParams rp = new RequestParams();
		if (gzyy != null && !gzyy.equals("")) {
			rp.addBodyParameter("rebuildmodeidcall", gzyy);
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

//	@OnClick({R.id.tv_back,R.id.tv_filter})
//	public void onClick(View v)
//	{
//		if (R.id.tv_back == v.getId()) {
//			MyApplication.getInstance().finishActivity(mActivity);
//		}else if (v.getId() == R.id.tv_filter) {
//
//			int xPox = (int)(Common.Width * 0.9);
//			filterPopWindow = new NoPoorProjectFilterPopWindow(mContext);
//			filterPopWindow.setShowOneMessageListener(this);
//
//			if (currPage == 1) {
//				filterPopWindow.showPxlbGridView(true);
//			}else {
//				filterPopWindow.showGzfsGridView(true);
//			}
//			filterPopWindow.setSelectionAdapter(NoPoorProjectCanLianActivity.class, maps);
//			filterPopWindow.showPopupWindow(v,xPox);
//		}
//	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag) {
			case MyConstans.FIRST:
				System.out.println("残联项目下的危房改造请求服务器的数据成功：==="+str);
				ProjectCanlianRebuildAppModelListResult lAppModelListResult = ProjectCanlianRebuildAppModelListResult.parseToT(str, ProjectCanlianRebuildAppModelListResult.class);
				if (lAppModelListResult != null && lAppModelListResult.getSuccess()) {
					this.rebuildAppModel = lAppModelListResult.getJsondata();
					isloading_first = true;
				}
				break;

			case MyConstans.SECOND:
				System.out.println("残联项目下的就业培训请求服务器的数据成功：==="+str);
				ProjectCanlianTrainAppModelListResult listResult = ProjectCanlianTrainAppModelListResult.parseToT(str, ProjectCanlianTrainAppModelListResult.class);
				if (listResult != null && listResult.getSuccess()) {
					this.trainAppModel = listResult.getJsondata();
					isloading_second = true;
				}
				break;

			default:
				break;
		}
		if (isloading_second && isloading_first) {
			initPage(rebuildAppModel,trainAppModel);
		}
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		switch (flag) {
			case MyConstans.FIRST:
				System.out.println("残联项目下的危房改造请求服务器的数据失败：==="+str);
				break;

			case MyConstans.SECOND:
				System.out.println("残联项目下的就业培训请求服务器的数据失败：==="+str);
				break;

			default:
				break;
		}
	}

	/**
	 * @param rebuildAppModel
	 * @param trainAppModel
	 * 2016年6月3日
	 * ZhaoDongShao
	 */
	@SuppressWarnings("deprecation")
	private void initPage(List<ProjectCanlianRebuildAppModel> rebuildAppModel, List<ProjectCanlianTrainAppModel> trainAppModel) {
		CanLian_Wfgz_Fragment wfgzFragment = new CanLian_Wfgz_Fragment();
		Bundle bundle_wfgz = new Bundle();
		bundle_wfgz.putSerializable(Common.BUNDEL_KEY, (Serializable) rebuildAppModel);
		wfgzFragment.setArguments(bundle_wfgz);

		CanLian_Jypx_Fragment jypxFragment = new CanLian_Jypx_Fragment();
		Bundle bundle_jypx = new Bundle();
		bundle_jypx.putSerializable(Common.BUNDEL_KEY, (Serializable) trainAppModel);
		jypxFragment.setArguments(bundle_jypx);

		list.clear();
		list.add(wfgzFragment);
		list.add(jypxFragment);

		viewPagerAdapter = new ViewPagerAdapter(list, this);
		viewPagerAdapter.setTitle(title_names);
		//默认显示第一页
		viewPager.setOffscreenPageLimit(0);
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				for (int i = 0; i < listviews.size(); i++) {
					if (arg0 == i) {
						listviews.get(arg0).setSelected(true);
						TextView textView = (TextView)listviews.get(arg0);
						textView.setTextColor(ResourceUtil.getInstance().getColorById(R.color.bule_155bbb));
						Drawable drawable = ResourceUtil.getInstance().getDrawableByID(R.drawable.blueyes_03);
						textView.setBackgroundDrawable(drawable);
						currPage = arg0;
					}else {
						listviews.get(i).setSelected(false);
						TextView textView = (TextView)listviews.get(i);
						textView.setTextColor(ResourceUtil.getInstance().getColorById(R.color.black));
						Drawable drawable = ResourceUtil.getInstance().getDrawableByID(R.drawable.blusno_03);
						textView.setBackgroundDrawable(drawable);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		for (int i = 0; i < listviews.size(); i++) {
			if (currPage == i) {
				listviews.get(currPage).setSelected(true);
				TextView textView = (TextView)listviews.get(currPage);
				textView.setTextColor(ResourceUtil.getInstance().getColorById(R.color.bule_155bbb));
				Drawable drawable = ResourceUtil.getInstance().getDrawableByID(R.drawable.blueyes_03);
				textView.setBackgroundDrawable(drawable);
				viewPager.setCurrentItem(currPage);
			}else {
				listviews.get(i).setSelected(false);
				TextView textView = (TextView)listviews.get(i);
				textView.setTextColor(ResourceUtil.getInstance().getColorById(R.color.black));
				Drawable drawable = ResourceUtil.getInstance().getDrawableByID(R.drawable.blusno_03);
				textView.setBackgroundDrawable(drawable);
			}
		}
	}

	List<MapEntity> maps = new ArrayList<MapEntity>();
	@Override
	public void Message(String string_pxlb, String time) {
		RequestParams rp = new RequestParams();
		if (currPage==0) {
			for (MapEntity i : maps) {
				if (i.getKey().equals(ResourceUtil.getInstance().getStringById(R.string.gzyy))) {
					maps.remove(i);
				}
			}
			if (!string_pxlb.equals("")) {
				rp.addBodyParameter("rebuildmodeidcall", string_pxlb);
				gzyy = string_pxlb;
				maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.gzyy), string_pxlb));
			}else {
				gzyy = "";
				maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.gzyy), string_pxlb.replace("", "不限")));
			}
			if (!time.equals("")) { //立项时间
				rp.addBodyParameter("lixiangdate", time);
			}
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.NO_POOR_PROJECT_CANLIAN_WFGZ, rp,
					new MyRequestCallBack(this, MyConstans.FIRST));
		}else if (currPage == 1) {
			for (MapEntity i : maps) {
				if (i.getKey().equals(ResourceUtil.getInstance().getStringById(R.string.pxlb))) {
					maps.remove(i);
				}
			}
			if (!string_pxlb.equals("")) {
				pxlb = string_pxlb;
				rp.addBodyParameter("traintypeidcall", string_pxlb);
				maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.pxlb), string_pxlb));
			}else {
				pxlb = "";
				maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.pxlb), string_pxlb.replace("", "不限")));
			}
			if (!time.equals("")) { //培训时间
				rp.addBodyParameter("traindate", time);
			}
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.NO_POOR_PROJECT_CANLIAN_JYPX, rp,
					new MyRequestCallBack(this, MyConstans.SECOND));
		}
		filterPopWindow.dismiss();
	}

}
