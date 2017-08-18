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
import com.roch.fupin.dialog.NoPoorProjectFilterPopWindow.ShowMessageListener;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ProjectInfoAppEntity;
import com.roch.fupin.entity.ProjectInfoAppEntityListResult;
import com.roch.fupin.entity.ProjectLinyeEconomyAppModel;
import com.roch.fupin.entity.ProjectLinyeEconomyAppModelListResult;
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
 * 林业局Activity
 * @author ZhaoDongShao
 * 2016年6月3日 
 */
@ContentView(R.layout.activity_poorhouse_detail)
public class NoPoorProjectLinYeJuActivity extends MainBaseActivity implements ShowMessageListener{
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

	String xmjd = "",xmzt = "",yzpz = ""; //项目进度、项目状态、养殖品种

	String title_name,search_content = "";
	int current_page = 1; //当前页码
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

	NoPoorProjectFilterPopWindow filterPopWindow;

	List<ProjectLinyeEconomyAppModel> trainAppModel;
	List<ProjectInfoAppEntity> rebuildAppModel;
	boolean isloading_first = false; //第一次加载
	boolean isloading_second = false; //第二次加载
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(mActivity);
		initToolbar();
		initView();
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
						filterPopWindow.setShowMessageListener(NoPoorProjectLinYeJuActivity.this);
						if (currPage == 1) {
							filterPopWindow.showYzpzGridView(true);
						}else {
							filterPopWindow.showXmjdGridView(true);
							filterPopWindow.showXmztGridView(true);
						}
						filterPopWindow.setSelectionAdapter(NoPoorProjectLinYeJuActivity.class, maps);
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
		ComponentName name = new ComponentName(mActivity,NoPoorProjectLinYeJuActivity.class);
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
					if (currPage == 0) {
						//道路绿化
						RequestParams rp = getDLLHRequestParams();
						rp.addBodyParameter(Common.EXTS_PAGE, String.valueOf(current_page));
						MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
								URLs.NO_POOR_PROJECT_LINYEJU_DLLH, rp,
								new MyRequestCallBack(mActivity, MyConstans.FIRST));
					}else if (currPage == 1) {
						//林下经济
						RequestParams rp1 = getLXJJRequestParams();
						rp1.addBodyParameter(Common.EXTS_PAGE, String.valueOf(current_page));
						MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
								URLs.NO_POOR_PROJECT_LINYEJU_LXJJ, rp1,
								new MyRequestCallBack(mActivity, MyConstans.SECOND));
					}
					return true;
				}
				return false;
			}
		});
		return true;
	}

	/**
	 * 加载数据
	 * 2016年8月6日
	 * ZhaoDongShao
	 */
	private void initData(){
		//道路绿化
		RequestParams rp = getDLLHRequestParams();
		rp.addBodyParameter(Common.EXTS_PAGE, String.valueOf(current_page));
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NO_POOR_PROJECT_LINYEJU_DLLH, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		System.out.println("请求道路绿化的服务器地址为：==="+URLs.NO_POOR_PROJECT_LINYEJU_DLLH);
		//林下经济
		RequestParams rp1 = getLXJJRequestParams();
		rp.addBodyParameter(Common.EXTS_PAGE, String.valueOf(current_page));
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NO_POOR_PROJECT_LINYEJU_LXJJ, rp1,
				new MyRequestCallBack(this, MyConstans.SECOND));
		System.out.println("请求林下经济的服务器地址为：==="+URLs.NO_POOR_PROJECT_LINYEJU_LXJJ);
	}

	/**
	 * 2016年5月9日
	 * ZhaoDongShao
	 */
	private void initView() {
		Intent intent = getIntent();
		String title_name = intent.getStringExtra(Common.INTENT_KEY);
		if (title_name != null && !title_name.equals("")) {
			tv_title.setText(title_name);
		}

		title_names = ResourceUtil.getInstance().getStringArrayById(R.array.linyeju_title);
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
	private RequestParams getLXJJRequestParams() {
		RequestParams rp = new RequestParams();
		if (yzpz != null && !yzpz.equals("")) {
			rp.addBodyParameter("breedtypeidcall", yzpz);
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
	private RequestParams getDLLHRequestParams() {
		RequestParams rp = new RequestParams();
		if (xmjd != null && !xmjd.equals("")) {
			rp.addBodyParameter("projectscheduleidcall", xmjd);
		}
		if (xmzt != null && !xmzt.equals("")) {
			rp.addBodyParameter("projectstatusidcall", xmzt);
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
//			int xPox = (int)(Common.Width * 0.9);
//			filterPopWindow = new NoPoorProjectFilterPopWindow(mContext);
//			filterPopWindow.setShowMessageListener(this);
//			if (currPage == 1) {
//				filterPopWindow.showYzpzGridView(true);
//			}else {
//				filterPopWindow.showXmjdGridView(true);
//				filterPopWindow.showXmztGridView(true);
//			}
//			filterPopWindow.setSelectionAdapter(NoPoorProjectLinYeJuActivity.class, maps);
//			filterPopWindow.showPopupWindow(v,xPox);
//		}
//	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag) {
			case MyConstans.FIRST:
				System.out.println("请求道路绿化服务器数据成功：==="+str);
				ProjectInfoAppEntityListResult lAppModelListResult = ProjectInfoAppEntityListResult.parseToT(str, ProjectInfoAppEntityListResult.class);
				if (lAppModelListResult != null && lAppModelListResult.getSuccess()) {
					this.rebuildAppModel = lAppModelListResult.getJsondata();
					isloading_first = true;
				}
				break;

			case MyConstans.SECOND:
				System.out.println("请求林下经济服务器数据成功：==="+str);
				ProjectLinyeEconomyAppModelListResult listResult = ProjectLinyeEconomyAppModelListResult.parseToT(str, ProjectLinyeEconomyAppModelListResult.class);
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
				showToast("请求道路绿化数据失败");
				System.out.println("请求道路绿化服务器数据失败：==="+str);
				break;

			case MyConstans.SECOND:
				showToast("请求林下经济数据失败");
				System.out.println("请求林下经济服务器数据失败：==="+str);
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
	private void initPage(List<ProjectInfoAppEntity> rebuildAppModel, List<ProjectLinyeEconomyAppModel> trainAppModel) {
		LinYeJu_Dllh_Fragment dllh_Fragment = new LinYeJu_Dllh_Fragment();
		Bundle bundle_dllh = new Bundle();
		bundle_dllh.putSerializable(Common.BUNDEL_KEY, (Serializable) rebuildAppModel);
		bundle_dllh.putString(Common.BUNDEL_FILTER_KEY_1, xmjd);
		bundle_dllh.putString(Common.BUNDEL_FILTER_KEY_2, xmzt);
		dllh_Fragment.setArguments(bundle_dllh);

		LinYeJu_Lxjj_Fragment lxjj_Fragment = new LinYeJu_Lxjj_Fragment();
		Bundle bundle_lxjj = new Bundle();
		bundle_lxjj.putSerializable(Common.BUNDEL_KEY, (Serializable) trainAppModel);
		bundle_lxjj.putString(Common.BUNDEL_FILTER_KEY_1, yzpz);
		lxjj_Fragment.setArguments(bundle_lxjj);
		list.clear();
		list.add(dllh_Fragment);
		list.add(lxjj_Fragment);

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
	public void Message(String string_xmjd, String string_xmzt, String string_yzpz, String time) {
		RequestParams rp = new RequestParams();
		maps.clear();
		if (currPage==0) {
			if (!string_xmjd.equals("")) {
				rp.addBodyParameter("projectscheduleidcall", string_xmjd);
				xmjd = string_xmjd;
				maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmjd), string_xmjd));
			}
			else {
				xmjd = "";
				maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmjd), string_xmjd.replace("", "不限")));
			}
			if (!string_xmzt.equals("")) {
				xmzt = string_xmzt;
				maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmzt), string_xmzt));
				rp.addBodyParameter("projectstatusidcall", string_xmzt);
			}
			else {
				xmzt = "";
				maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmzt), string_xmzt.replace("", "不限")));
			}
			if (!time.equals("")) { //立项时间
				rp.addBodyParameter("lixiangdate", time);
			}
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.NO_POOR_PROJECT_LINYEJU_DLLH, rp,
					new MyRequestCallBack(this, MyConstans.FIRST));
		}else if (currPage == 1) {
			if (!string_yzpz.equals("")) {
				yzpz = string_yzpz;
				maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.yzpz), string_yzpz));
				rp.addBodyParameter("breedtypeidcall", string_yzpz);
			}
			else {
				yzpz = "";
				maps.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmzt), string_yzpz.replace("", "不限")));
			}
			if (!time.equals("")) { //立项时间
				rp.addBodyParameter("lixiangdate", time);
			}
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.NO_POOR_PROJECT_LINYEJU_LXJJ, rp,
					new MyRequestCallBack(this, MyConstans.SECOND));
		}
		filterPopWindow.dismiss();
	}

}
