package com.roch.fupin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NoPoorProject_tpqk_FilterPopWindow;
import com.roch.fupin.dialog.NoPoorProject_tpqk_FilterPopWindow.ShowMessageListener;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.AdlCodeListResult;
import com.roch.fupin.entity.Basic_DistrictAppModel;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.NoPoorSituation;
import com.roch.fupin.entity.NoPoorSituationListResult;
import com.roch.fupin.entity.PinKunCun;
import com.roch.fupin.entity.User;
import com.roch.fupin.entity.ZidianAppEntity;
import com.roch.fupin.entity.ZidianAppEntityListResult;
import com.roch.fupin.utils.AdlcdUtil;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.PingYinUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin.view.ExpandTabView;
import com.roch.fupin.view.MenuRight;
import com.roch.fupin.view.MenuRight_2;
import com.roch.fupin.view.TPQKTableScrollView;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 脱贫情况统计
 * @author ZhaoDongShao
 * 2016年8月11日
 */
@ContentView(R.layout.activity_poor_people_tpqk_statistic)
public class PoorPeopleTPQKActivity extends MainBaseActivity implements ShowMessageListener{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.tv_time)
	TextView tv_time;

	NoPoorProject_tpqk_FilterPopWindow filterPopWindow;

	private ListView mListView;
	protected List<TPQKTableScrollView> mHScrollViews =new ArrayList<TPQKTableScrollView>();

	public TPQKTableScrollView mTouchView;

	//创建数组保存表头
	private String[] cols = {"title","户数","人数","户数1","人数1"};

	private  ScrollAdapter mAdapter;

	/**
	 * 自定义的LinearLayout布局---筛选条件的容器
	 */
	@ViewInject(R.id.expandtab_view)
	ExpandTabView expandTabView;
	/**
	 * 自定义的RelativeLayout---进行旗区县的筛选
	 */
	MenuRight mMenuRight_0;
	/**
	 * 自定义的RelativeLayout---进行乡镇的筛选
	 */
	MenuRight mMenuRight_1;
	/**
	 * 自定义的RelativeLayout---进行村的筛选
	 */
	MenuRight_2 mMenuRight_2;
	/**
	 * 存储自定义的筛选条件布局（即RelativeLayout）的集合
	 */
	List<View> mViewArray = new ArrayList<View>();
	/**
	 * 查询条件
	 */
	String adl_cd = "", poor_qiquxian="", poor_zhen = "", poor_cun = "";
	List<Basic_DistrictAppModel> models = new ArrayList<Basic_DistrictAppModel>();

	/**
	 * 标志为---1为乡镇、2为贫困村
	 */
	int flag_zhen_cun=1;
	/**
	 * 当前选择的旗区县的adl_cd
	 */
	String adl_cd_qiquxian;
	/**
	 * 当前选择的乡镇的adl_cd
	 */
	String adl_cd_zhen="";
	/**
	 * 当前选择的贫困村的adl_cd
	 */
	String adl_cd_cun="";
	String select_poor_zhen="";
	String select_poor_qiquxian="";
	List<Basic_DistrictAppModel> mAppModel;
	List<PinKunCun> poor_cun_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(mActivity);

		//初始化toolbar，并设置点击后的监听弹出PopupWindow
		initToolbar();

		//将自定义的LinearLayout布局---筛选条件的容器，显示出来
		expandTabView.setVisibility(View.VISIBLE);
		MyApplication.getInstance().addActivity(mActivity);
		//自定义的RelativeLayout---旗区县筛选
		mMenuRight_0 = new MenuRight(mContext);
		//自定义的RelativeLayout---乡镇筛选
		mMenuRight_1 = new MenuRight(mContext);
		//自定义的RelativeLayout---村筛选
		mMenuRight_2 = new MenuRight_2(mContext);

		mViewArray.add(mMenuRight_0);
		mViewArray.add(mMenuRight_1);
		mViewArray.add(mMenuRight_2);

		ArrayList<String> mTextArray = new ArrayList<String>();
		mTextArray.add("旗区县");
		mTextArray.add("苏木乡");
		mTextArray.add("嘎查村");

		expandTabView.setValue(mTextArray, mViewArray);

//		String ad_cd = "";
//		User user = MyApplication.getInstance().getLogin(Common.LoginName);
//		if (user.getAdl_cd() != null && user.getAdl_cd() != null && !user.getAdl_cd().equals("")) {
//			if (AdlcdUtil.isCountry(user.getAdl_cd()) || AdlcdUtil.isTown(user.getAdl_cd())) {
//				ad_cd = user.getAdl_cd();
//			} else if (AdlcdUtil.isVillage(user.getAdl_cd())) {
//				ad_cd = AdlcdUtil.generateTownCode(user.getAdl_cd());
//			} else {
//				AdlCode adlCode = MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(mContext, Common.LoginName);
//				ad_cd = adlCode.getAd_cd();
//			}
//		}
//		// 请求服务器---乡镇数据
//		RequestParams rp = new RequestParams();
//		rp.addBodyParameter("ad_cd", ad_cd);
//		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
//				URLs.TOWN, rp, new MyRequestCallBack(this, MyConstans.SEVEN));
//		System.out.println("贫困人口统计页面一进来初始化时请求服务器中筛选条件的乡镇(flag=7)网址为：==" + URLs.TOWN + "?&ad_cd=" + ad_cd);
		String adl_cd = "";
		User user = MyApplication.getInstance().getLogin(Common.LoginName);
		if (user != null) {
			// 判断该登陆用户是否为市级或县级
			if (!AdlcdUtil.isCity(user.getAdl_cd()) || !AdlcdUtil.isCountry(user.getAdl_cd())) {
				adl_cd = AdlcdUtil.generateCountryCode(user.getAdl_cd());
			}
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("ad_cd", adl_cd);
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST, URLs.COUNTY, rp,
					new MyRequestCallBack(this, MyConstans.SIX));
			System.out.println("请求市县级管辖区域的服务器地址：===" + URLs.COUNTY + "?ad_cd=" + adl_cd);
		}

		//初始化Activity标题和表格表头，并请求网络数据
		initViews();

		//给ExtendListView即贫困镇、贫困村菜单选项设置点击监听，点击后弹出筛选条件
		initListener();
	}

	/**
	 * 初始化toolbar，并设置点击后的监听弹出PopupWindow
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
						int xPox = (int) (Common.Width * 0.9);
						filterPopWindow = new NoPoorProject_tpqk_FilterPopWindow(mContext);
						//监听者模式，将监听者传过来
						filterPopWindow.setShowMessageListener(PoorPeopleTPQKActivity.this);
						//设置筛选过之后的时间
						filterPopWindow.setSelectionAdapter(maps,selectShouRu);
						//显示popupWindow
						filterPopWindow.showPopupWindow(toolbar, xPox);
						break;

					default:
						break;
				}
				return false;
			}
		});
	}

	/**
	 * 给ExtendListView即乡镇、村菜单选项设置点击监听，点击后弹出筛选条件
	 * 2016年11月2日
	 */
	private void initListener() {
		// 给旗区县菜单设置点击监听
		mMenuRight_0.setOnSelectListener(new MenuRight.OnSelectListener() {
			@Override
			public void getValue(String distance, String showText) {
				flag_zhen_cun = 0;
				if (!"全部".equals(showText)) { //如果点击的是不是旗区县的全部
					//点击贫困旗区县时请求服务器数据---该旗区县下的乡镇
					onRefresh0(mMenuRight_0, showText);

					if (!StringUtil.isEmpty(poor_zhen_list)) {
						poor_zhen_list.clear();
						mMenuRight_1.setStringArray(poor_zhen_list);
					}

					if (!StringUtil.isEmpty(poor_cun_list)) {
						poor_cun_list.clear();
						mMenuRight_2.setStringArray(poor_cun_list);
					}
					expandTabView.initSelectText2();

					//点击贫困旗区县flag=0或贫困乡镇flag=1或贫困村flag=2时请求服务器的贫困人口统计数据
					getData();
				} else { //如果点击的是是旗区县的全部
					expandTabView.onPressBack();
					// 获取其在储存菜单中的位置position
					int position = getPositon(mMenuRight_0);
					if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
						expandTabView.setTitle(showText, position);
					}
					poor_qiquxian = mMenuRight_0.getShowText();    //贫困旗区县
					select_poor_qiquxian = poor_qiquxian;

					if (!StringUtil.isEmpty(poor_zhen_list)) {
						poor_zhen_list.clear();
						mMenuRight_1.setStringArray(poor_zhen_list);
					}

					if (!StringUtil.isEmpty(poor_cun_list)) {
						poor_cun_list.clear();
						mMenuRight_2.setStringArray(poor_cun_list);
					}
					expandTabView.initSelectText2();

					initData();
				}
			}
		});
		// 给贫困镇菜单设置点击监听
		mMenuRight_1.setOnSelectListener(new MenuRight.OnSelectListener() {
			@Override
			public void getValue(String distance, String showText) {
				flag_zhen_cun = 1;
				if (!"全部".equals(showText)) {
					//点击贫困乡镇时请求服务器数据---该乡镇下的村
					onRefresh(mMenuRight_1, showText);
					//点击贫困乡镇flag=1或贫困村flag=2时请求服务器的贫困人口统计数据
					getData();
				} else {
					expandTabView.onPressBack();
					// 获取其在储存菜单中的位置position
					int position = getPositon(mMenuRight_1);
					if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
						expandTabView.setTitle(showText, position);
					}
					poor_zhen = mMenuRight_1.getShowText();    //贫困镇
					select_poor_zhen = poor_zhen;

					poor_cun_list.clear();
					mMenuRight_2.setStringArray(poor_cun_list);
					expandTabView.initSelectText1();

					initData();
				}
			}
		});

		// 给贫困村菜单设置点击监听
		mMenuRight_2.setOnSelectListener(new MenuRight_2.OnSelectListener() {
			@Override
			public void getValue(String distance, String showText) {
				flag_zhen_cun = 2;
//				if(!"全部".equals(showText)){
//					//点击贫困村时获取选中的贫困村的adl_cd
//					onRefresh2(mMenuRight_2, showText);
//				}else {
//
//				}
				//点击贫困村时获取选中的贫困村的adl_cd
				onRefresh2(mMenuRight_2, showText);
				//点击贫困乡镇flag=1或贫困村flag=2时请求服务器的贫困人口统计数据
				getData();
			}
		});
	}

	/**
	 * 点击贫困旗区县时请求服务器数据---该旗区县下的乡镇和贫困人口统计
	 * @param view
	 * @param showText
	 */
	private void onRefresh0(View view, String showText) {
		expandTabView.onPressBack();
		// 获取其在储存菜单中的位置position
		int position = getPositon(view);
		if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
			expandTabView.setTitle(showText, position);
		}

		poor_qiquxian = mMenuRight_0.getShowText();    //贫困旗区县
		select_poor_qiquxian=poor_qiquxian;

		if(null!=lAdlCodes &&lAdlCodes.size()>0){
			for (int i = 0; i < lAdlCodes.size(); i++) {
				if(null!=poor_qiquxian && poor_qiquxian.equals(lAdlCodes.get(i).getAd_nm())){
					adl_cd_qiquxian=lAdlCodes.get(i).getAd_cd();
					RequestParams rp = new RequestParams();
					rp.addBodyParameter("ad_cd", adl_cd_qiquxian);
					// 请求网络数据---贫困户List列表
					MyApplication.getInstance().getHttpUtilsInstance()
							.send(HttpMethod.POST, URLs.TOWN, rp,
									new MyRequestCallBack((BaseActivity) mActivity, MyConstans.SEVEN));
					System.out.println("筛选条件设置好以后请求服务器中的贫困乡镇List(flag=7)网址为：===" + URLs.TOWN+"?&ad_cd="+adl_cd_qiquxian);
				}
			}
		}
	}


	/**
	 * 初始化数据
	 * 2016年6月25日
	 * ZhaoDongShao
	 */
	private void initData() {
		Intent intent = getIntent();
		String title = intent.getStringExtra(Common.INTENT_KEY);
		AdlCode adlCode = MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(mContext, Common.LoginName);

		RequestParams rp = getRequestParams();
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.POOR_PROPLE_TPQK, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
		System.out.println("贫困人口统计请求服务器网址为：===" + URLs.POOR_PROPLE_TPQK);
	}

	/**
	 * 增加查询条件
	 *
	 * @return 2016年7月2日
	 * ZhaoDongShao
	 */
	private RequestParams getRequestParams() {
		RequestParams rp = new RequestParams();
//		String adl_cd = getAdl_Cd();
		if(adl_cd.equals("")){
			SharedPreferences sp = getSharedPreferences("loginactivty", Context.MODE_APPEND);
			adl_cd =sp.getString("adl_cd","");
		}
		if (!adl_cd.equals("")) {
			rp.addBodyParameter("adl_cd", adl_cd);
		}
		return rp;
	}

	/**
	 * 点击贫困村时请求服务器数据---该贫困村的贫困人口统计
	 * @param mMenuRight_2
	 * @param showText
	 */
	private void onRefresh2(MenuRight_2 mMenuRight_2, String showText) {
		expandTabView.onPressBack();
		// 获取其在储存菜单中的位置position
		int position = getPositon(mMenuRight_2);
		if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
			expandTabView.setTitle(showText, position);
		}
		poor_cun = mMenuRight_2.getShowText(); //贫困村
		if(null!=models &&models.size()>0){
			for (int i = 0; i < models.size(); i++) {
				if(null!=poor_cun && poor_cun.equals(models.get(i).getAd_nm())){
					adl_cd_cun=models.get(i).getAd_cd();
				}
			}
		}
	}

	/**
	 * 点击贫困乡镇flag=1或贫困村flag=2时请求服务器的贫困人口统计数据
	 */
	private void getData() {
		RequestParams rp = new RequestParams();
		if(flag_zhen_cun==1){
			if(StringUtil.isNotEmpty(adl_cd_zhen)){
				rp.addBodyParameter("adl_cd",adl_cd_zhen);
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.POOR_PROPLE_TPQK, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("贫困人口统计请求服务器网址为：===" + URLs.POOR_PROPLE_TPQK);
			}
		}else if(flag_zhen_cun==2){
			if(StringUtil.isNotEmpty(adl_cd_cun)){
				rp.addBodyParameter("adl_cd",adl_cd_cun);
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.POOR_PROPLE_TPQK, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("贫困人口统计请求服务器网址为：===" + URLs.POOR_PROPLE_TPQK);
			}
		}else if(flag_zhen_cun==0){
			if(StringUtil.isNotEmpty(adl_cd_qiquxian)){
				rp.addBodyParameter("adl_cd",adl_cd_qiquxian);
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.POOR_PROPLE_TPQK, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("贫困人口统计请求服务器网址为：===" + URLs.POOR_PROPLE_TPQK);
			}
		}
	}

	/**
	 * 点击贫困乡镇时请求服务器数据---该乡镇下的村和贫困人口统计
	 * @param view
	 * @param showText
	 */
	private void onRefresh(View view, String showText) {
		expandTabView.onPressBack();
		// 获取其在储存菜单中的位置position
		int position = getPositon(view);
		if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
			expandTabView.setTitle(showText, position);
		}

		poor_zhen = mMenuRight_1.getShowText();    //贫困镇
		select_poor_zhen=poor_zhen;

		if(null!=mAppModel &&mAppModel.size()>0){
			for (int i = 0; i < mAppModel.size(); i++) {
				if(null!=poor_zhen && poor_zhen.equals(mAppModel.get(i).getAd_nm())){
					adl_cd_zhen=mAppModel.get(i).getAd_cd();
					RequestParams rp = new RequestParams();
					rp.addBodyParameter("ad_cd", adl_cd_zhen);
					// 请求网络数据---贫困户List列表
					MyApplication.getInstance().getHttpUtilsInstance()
							.send(HttpMethod.POST, URLs.VILLAGE, rp,
									new MyRequestCallBack((BaseActivity) mActivity, MyConstans.EIGHT));
					System.out.println("筛选条件设置好以后请求服务器中的贫困村List(flag=1)网址为：===" + URLs.POOR_HOUSE_LISE+"?&ad_cd="+adl_cd_zhen);
				}
			}
		}
	}

	/**
	 * 根据展示的popuwindow窗口，判断其在储存菜单的位置position
	 *
	 * @param tView 展示的popuwindow窗口
	 * @return 返回其在储存菜单的位置position
	 * 2016年11月2日
	 */
	private int getPositon(View tView) {
		for (int i = 0; i < mViewArray.size(); i++) {
			if (mViewArray.get(i) == tView) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 当点击菜单选项时调用此方法
	 * @param menu
	 * @return
	 */
	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.select_menu, menu);
		return true;
	}

	/**
	 * 当点击返回时关闭Activity
	 * @param item
	 * @return
	 */
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

	/**
	 * 初始化Activity标题和表格表头，并请求网络数据
	 * 2016年8月11日
	 * ZhaoDongShao
	 */
	private void initViews() {
		Intent intent = getIntent();
		String name = intent.getStringExtra(Common.INTENT_KEY);

		if (name != null && !name.equals("")) {
			tv_title.setText(name);
		}

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
//		int month = calendar.get(Calendar.MONTH) + 1;   不显示月份了

		String time = year + "年";
		tv_time.setText(time + "脱贫情况汇总统计");

		RequestParams rp = new RequestParams();
		rp.addBodyParameter("endDate","2016");
		rp.addBodyParameter("income_sandards","3178");
		MyApplication.getInstance().getHttpUtilsInstance().send(
				HttpMethod.POST, URLs.POOR_PROPLE_TPQK, rp,
				new MyRequestCallBack(mActivity, MyConstans.FIRST));
	}

	/**
	 * 贫困旗区县的集合
	 */
	List<AdlCode> lAdlCodes;
	List<String> poor_zhen_list;
	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag){
			case MyConstans.FIRST:
				System.out.println("请求脱贫情况统计成功：==" + str);
				List<NoPoorSituation> list = new ArrayList<NoPoorSituation>();
				NoPoorSituationListResult listResult = NoPoorSituationListResult.parseToT(str, NoPoorSituationListResult.class);
				if (listResult != null ) {
					if (listResult.getSuccess()) {
						list.addAll(listResult.jsondata);
						if (StringUtil.isNotEmpty(list)) {
							List<Map<String, String>> datas = new ArrayList<Map<String,String>>();
							Map<String, String> data = null;
							TPQKTableScrollView headerScroll = (TPQKTableScrollView) findViewById(R.id.item_scroll_title);
							mHScrollViews.add(headerScroll);
							mListView = (ListView) findViewById(R.id.hlistview_scroll_list);
							for(int i = 0; i < list.size(); i++) {
								data = new HashMap<String, String>();
								data.put("title", list.get(i).adl_nm);
								for (int j = 1; j < 9; j++) {
									switch (j) {
										case 1:
											data.put("户数", String.valueOf(list.get(i).pass_f_c));
											break;

										case 2:
											data.put("人数", String.valueOf(list.get(i).pass_p_c));
											break;

										case 3:
											data.put("户数1", String.valueOf(list.get(i).unpass_f_c));
											break;

										case 4:
											data.put("人数1", String.valueOf(list.get(i).unpass_p_c));
											break;

										default:
											break;
									}
								}
								datas.add(data);
							}
							mAdapter = new ScrollAdapter(this, datas, R.layout.listitem_poor_people_tpqk_statistic_listitem //R.layout.item
									, cols
									, new int[] { R.id.item_titlev
									, R.id.item_datav1
									, R.id.item_datav2
									, R.id.item_datav3
									, R.id.item_datav4
							});
							mListView.setAdapter(mAdapter);
							showToast("数据加载完成");
						}else {
							showToast("当前没有更多数据");
						}
					}else {
						showToast("数据加载失败");
					}
				}else {
					ShowNoticDialog();
				}
				break;

			case MyConstans.SIX:
				System.out.println("请求该县市级管辖的区域的服务器数据：==="+str);
				AdlCodeListResult adlCodeListResult = AdlCodeListResult.parseToT(str, AdlCodeListResult.class);
				if (adlCodeListResult.getSuccess()) {
					lAdlCodes = adlCodeListResult.getJsondata();
					List<String> poor_qiquxian = new ArrayList<>();
					if (lAdlCodes != null && lAdlCodes.size() > 0) {
						for (int i = 0; i < lAdlCodes.size(); i++) {
							lAdlCodes.get(i).setPinyi(PingYinUtil.getPingYin(lAdlCodes.get(i).getAd_nm()));
							poor_qiquxian.add(lAdlCodes.get(i).getAd_nm());
						}
					}
					mMenuRight_0.setStringArray(poor_qiquxian);
				}
				break;

			case MyConstans.SEVEN:
				System.out.println("贫困人口统计页面请求筛选条件中的一级乡镇数据成功：===" + str);
				ZidianAppEntityListResult listResult2 = ZidianAppEntityListResult.parseToT(str, ZidianAppEntityListResult.class);
				if (listResult2 != null && listResult2.getSuccess()) {
					List<ZidianAppEntity> zidianAppEntitys = listResult2.getJsondata();
					if (zidianAppEntitys != null && zidianAppEntitys.size() > 0) {
						for (ZidianAppEntity zidianAppEntity : zidianAppEntitys) {
							mAppModel = zidianAppEntity.getTab();
//							List<T_SYS_DATADICTAppModel> mAppModels = zidianAppEntity.getTda();
							//贫困镇
							poor_zhen_list = new ArrayList<String>();
							if(null!=mAppModel){
								for (int i = 0; i < mAppModel.size(); i++) {
									poor_zhen_list.add(mAppModel.get(i).getAd_nm());
								}
							}
							if (poor_zhen != null) {
								mMenuRight_1.setStringArray(poor_zhen_list);
							}
						}
					}
				}
				break;

			case MyConstans.EIGHT:
				System.out.println("贫困人口统计页面请求筛选条件中的二级村数据成功：===" + str);
				ZidianAppEntityListResult modelListResult1 = ZidianAppEntityListResult.parseToT(str, ZidianAppEntityListResult.class);
				if (modelListResult1 != null && modelListResult1.getSuccess()) {
					List<ZidianAppEntity> mAppModels = modelListResult1.getJsondata();
					if(null!=mAppModels && mAppModels.size()>0){
						for (int i = 0; i < mAppModels.size(); i++) {
							if(models.size()>0){
								models.clear();
							}
							models.addAll(mAppModels.get(i).getTab());
							//贫困村
							poor_cun_list = new ArrayList<>();
							for (int j = 0;j < models.size(); j++) {
								PinKunCun pinKunCun=new PinKunCun();
								pinKunCun.setName(models.get(j).getAd_nm());
								pinKunCun.setPoor_type(models.get(j).getPovertystatusid());
								poor_cun_list.add(pinKunCun);
							}
							if (poor_zhen != null) {
								mMenuRight_2.setStringArray(poor_cun_list);
//								ArrayList<String> mTextArray = new ArrayList<String>();
//								mTextArray.add(select_poor_zhen);
//								mTextArray.add("村");
//								//将贫困村显示文字初始化
//								expandTabView.setValue(mTextArray, mViewArray);
								expandTabView.initSecetText(1);
							}
						}
					}
				}
				break;
		}
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		System.out.println("请求脱贫情况统计失败：=="+str);
	}

	public void addHViews(final TPQKTableScrollView hScrollView) {
		if(!mHScrollViews.isEmpty()) {
			int size = mHScrollViews.size();
			TPQKTableScrollView scrollView = mHScrollViews.get(size - 1);
			final int scrollX = scrollView.getScrollX();
			if(scrollX != 0) {
				mListView.post(new Runnable() {
					@Override
					public void run() {
						hScrollView.scrollTo(scrollX, 0);
					}
				});
			}
		}
		mHScrollViews.add(hScrollView);
	}

	public void onScrollChanged(int l, int t, int oldl, int oldt){
		for (TPQKTableScrollView tableScrollView : mHScrollViews) {
			if (mTouchView != tableScrollView) {
				tableScrollView.smoothScrollTo(l, t);
			}
		}
	}

	class ScrollAdapter extends SimpleAdapter{
		private List<? extends Map<String, ?>> datas;
		private int res;
		private String[] from;
		private int[] to;
		private Context context;
		public ScrollAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
						String[] from, int[] to) {
			super(context, data, resource, from, to);
			this.context = context;
			this.datas = data;
			this.res = resource;
			this.from = from;
			this.to = to;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if(v == null) {
				v = LayoutInflater.from(context).inflate(res, null);
				addHViews((TPQKTableScrollView) v.findViewById(R.id.item_chscroll_scroll));
				View[] views = new View[to.length];
				for(int i = 0; i < to.length; i++) {
					View tv = v.findViewById(to[i]);
					views[i] = tv;
				}
				v.setTag(views);
			}
			View[] holders = (View[]) v.getTag();
			int len = holders.length;

			for (int i = 0; i < len; i++) {
				Map<String, ?> map = this.datas.get(position);
				String name = (String)map.get(from[i]);
				TextView textView = (TextView)holders[i];
				if (name != null) {
					textView.setText(name);
				}else {
					textView.setText("");
				}
			}
			return v;
		}
	}

	List<MapEntity> maps = new ArrayList<MapEntity>();
	String selectShouRu="";
	@Override
	public void Message(String time_end,String shouru) {
		maps.clear();
		String times = "";
		RequestParams rp = new RequestParams();
		if (!time_end.equals("")) {
			int year = Integer.parseInt(time_end.split("-")[0]);
//			int months = Integer.parseInt(time_end.split("-")[1]);
			Calendar calendar = Calendar.getInstance();
			if (year > calendar.get(Calendar.YEAR)) {
				showToast("请选择正确的查询日期");
				return;
			}else if (year == calendar.get(Calendar.YEAR)) {
//				if (months > calendar.get(Calendar.MONTH) + 1) {
//					showToast("请选择正确的查询日期");
//					return;
//				}else {
//					times += year + "年" + months + "月份";
					times += year + "年";
					maps.add(new MapEntity("wejend", time_end));
//					if (months > 0 && months < 10) {
//						time_end = year + "-0" + months;
//						time_end = year+"";
//					}
//					rp.addBodyParameter("lastMonth", time_end);
//					rp.addBodyParameter("endDate", time_end);
//				}
				time_end = year+"";
				rp.addBodyParameter("endDate", time_end);
			}else {
//				times += year + "年" + months + "月份";
				times += year + "年";
				maps.add(new MapEntity("wejend", time_end));
//				if (months > 0 && months < 10) {
//					time_end = year + "-0" + months;
//					time_end = year + "";
//				}
//				rp.addBodyParameter("lastMonth", time_end);
				time_end = year + "";
				rp.addBodyParameter("endDate", time_end);
			}
		}else {
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
//			String a = year + "年" + month + "月份";
			String a = year + "年";
			times += a;
		}
		tv_time.setText(times + "脱贫情况汇总统计");
		if (StringUtil.isNotEmpty(shouru)){
			rp.addBodyParameter("income_sandards", shouru);
		}
		selectShouRu=shouru;
		System.out.println("筛选时请求的网址为："+URLs.POOR_PROPLE_TPQK+"?endDate="+time_end+"&income_sandards="+shouru);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.POOR_PROPLE_TPQK, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		filterPopWindow.dismiss();
	}
}
