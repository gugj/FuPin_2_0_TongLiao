package com.roch.fupin;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
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
import com.roch.fupin.adapter.PoorVillageAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.AdlCodeListResult;
import com.roch.fupin.entity.Basic_DistrictAppModel;
import com.roch.fupin.entity.PinKunCun;
import com.roch.fupin.entity.PoorVillageBase;
import com.roch.fupin.entity.PoorVillageListResult;
import com.roch.fupin.entity.User;
import com.roch.fupin.entity.ZidianAppEntity;
import com.roch.fupin.entity.ZidianAppEntityListResult;
import com.roch.fupin.utils.AdlcdUtil;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.LogUtil;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.PingYinUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin.view.ExpandTabView;
import com.roch.fupin.view.MenuLeft;
import com.roch.fupin.view.MenuRight;
import com.roch.fupin.view.MenuRight_2;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 贫困村
 * @author Administrator
 */
@ContentView(R.layout.activity_poorhouse)
public class PoorVillageActivity extends MainBaseActivity {

	private static final String EXTS_PAGE = "page";

	@ViewInject(R.id.refresh_poorhouse)
	PullToRefreshListView listview;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	//	@ViewInject(R.id.tv_search)
	//	TextView tv_search;
	@ViewInject(R.id.expandtab_view)
	ExpandTabView expandTabView;

	String adl_cd = "";String tpqk = ""; String pkhs = "";//村、脱贫情况、贫困户数
	
	int current_page = 1; //当前页码

	String title_name,search_content = "";
	@ViewInject(R.id.tv_chaxunhushu)
	TextView tv_chaxunhushu;   //已查询到户数
	private static int FRIST = 0;

	/**
	 * 筛选条件---乡镇、村
	 */
	MenuLeft mMenuLeft;
	/**
	 * 筛选条件---脱贫情况
	 */
	MenuRight mMenuRight_1;
	/**
	 * 筛选条件---贫困户数
	 */
	MenuRight mMenuRight_2;

	/**
	 * 自定义的RelativeLayout---进行旗区县的筛选
	 */
	MenuRight mMenuRight_6;
	/**
	 * 自定义的RelativeLayout---进行乡镇的筛选
	 */
	MenuRight mMenuRight_7;
	/**
	 * 自定义的RelativeLayout---进行村的筛选
	 */
	MenuRight_2 mMenuRight_8;
	String poor_qiquxian="", poor_zhen = "", poor_cun = "";

	/**
	 * 标志为---0为旗区县、1为乡镇、2为贫困村
	 */
	int flag_zhen_cun=1;
	/**
	 * 当前选择的旗区县的adl_cd
	 */
	String adl_cd_qiquxian;
	/**
	 * 当前选择的乡镇的adl_cd
	 */
	String adl_cd_zhen;
	/**
	 * 当前选择的贫困村的adl_cd
	 */
	String adl_cd_cun;
	String select_poor_zhen="";
	String select_poor_qiquxian="";
	List<PinKunCun> poor_cun_list;

	//存储菜单
	List<View> mViewArray = new ArrayList<View>();

	PoorVillageAdapter adapter;
	//村信息
	List<Basic_DistrictAppModel> models = new ArrayList<Basic_DistrictAppModel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initToolbar();
		MyApplication.getInstance().addActivity(mActivity);
		
		expandTabView.setVisibility(View.VISIBLE);
		
		Intent intent = getIntent();
		String title_name = intent.getStringExtra(Common.INTENT_KEY);
		if (title_name != null && !title_name.equals("")) {
			tv_title.setText(title_name);
		}
		
		//添加查询条件
		mMenuLeft = new MenuLeft(mContext);
		mMenuRight_1 = new MenuRight(mContext);
		mMenuRight_2 = new MenuRight(mContext);
//
//		mViewArray.add(mMenuLeft);
//		mViewArray.add(mMenuRight_1);
//		mViewArray.add(mMenuRight_2);
//
//		ArrayList<String> mTextArray = new ArrayList<String>();
//		mTextArray.add("行政区");
//		mTextArray.add("脱贫情况");
//		mTextArray.add("贫困户数");
		//自定义的RelativeLayout---旗区县筛选
		mMenuRight_6 = new MenuRight(mContext);
		//自定义的RelativeLayout---乡镇筛选
		mMenuRight_7 = new MenuRight(mContext);
		//自定义的RelativeLayout---村筛选
		mMenuRight_8 = new MenuRight_2(mContext);

		mViewArray.add(mMenuRight_6);
		mViewArray.add(mMenuRight_7);
		mViewArray.add(mMenuRight_8);

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
//			}else if (AdlcdUtil.isVillage(user.getAdl_cd())) {
//				ad_cd = AdlcdUtil.generateTownCode(user.getAdl_cd());
//			}else {
//				AdlCode adlCode = MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(mContext, Common.LoginName);
//				ad_cd = adlCode.getAd_cd();
//			}
//		}
//		RequestParams rp = new RequestParams();
//		rp.addBodyParameter("ad_cd", ad_cd);
//		System.out.println("请求贫困村页面中的筛选条件(镇级List)的网址为：==="+URLs.TOWN+"?ad_cd="+ad_cd);
//		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
//				URLs.TOWN, rp, new MyRequestCallBack(this, MyConstans.SEVEN));

		String adl_cd = "";
		User user = MyApplication.getInstance().getLogin(Common.LoginName);
		if (user != null) {
			LogUtil.println("登陆用户的adl_cd==="+user.getAdl_cd());
			// 判断该登陆用户是否为市级或县级
			if (!AdlcdUtil.isCity(user.getAdl_cd()) || !AdlcdUtil.isCountry(user.getAdl_cd())) {
				adl_cd = AdlcdUtil.generateCountryCode(user.getAdl_cd());
			}
			LogUtil.println("判断处理过之后--登陆用户的adl_cd==="+adl_cd);
			adl_cd=user.getAdl_cd();
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("ad_cd", adl_cd);
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST, URLs.COUNTY, rp,
					new MyRequestCallBack(this, MyConstans.SIX,false));
			System.out.println("请求市县级管辖区域的服务器地址：===" + URLs.COUNTY + "?ad_cd=" + adl_cd);
		}

		//获取贫困村List数据(请求服务器网址)
		initData();
		//给各个筛选条件设置筛选的点击监听
		initListener();

		listview.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				current_page = 1;
				FRIST = 0;
				//获取贫困村List数据(请求服务器网址)
				initData();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				current_page++;
				FRIST = 1;
				//获取贫困村List数据(请求服务器网址)
				initData();
			}
		});
	}

	@Override
	protected void onNewIntent(Intent intent) {
		search_content = intent.getStringExtra(SearchManager.QUERY);
		//获取贫困村List数据(请求服务器网址)
		initData();
	};
	
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

	/**
	 * 获取贫困村List数据
	 * 2016年5月6日
	 * ZhaoDongShao
	 */
	private void initData() {
		//获取贫困村List数据
		RequestParams rp = getRequestParams();
		rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
		System.out.println("获取贫困村List数据(第一次进入页面时没有点击筛选条件)的网址为：==="+URLs.POOR_VILLAGE_LIST+"?page="+String.valueOf(current_page));
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.POOR_VILLAGE_LIST, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
	}

	/**
	 * 获取查询条件
	 * @return
	 * 2016年7月1日
	 * ZhaoDongShao
	 */
	private RequestParams getRequestParams() {
		RequestParams rp = new RequestParams();
//		if (!village.equals("")&&!village.equals("不限")) {
//			rp.addBodyParameter("villagename", village);
//		}
		if (!pkhs.equals("不限")&&!pkhs.equals("")) {
			if (pkhs != null && !pkhs.equals("")) {
				String[] a = pkhs.split("~");
				rp.addBodyParameter("littlepoorhouse", a[0]);
				if (!a[0].equals("100")) {
					rp.addBodyParameter("bigpoorhouse", a[1]);
				}
			}
		}
		if (!tpqk.equals("不限")&&!tpqk.equals("")) {
			rp.addBodyParameter("tpName", tpqk);
		}
		if (adl_cd.equals("")) {
			adl_cd = getAdl_Cd();
//			SharedPreferences sp = getSharedPreferences("loginactivty", Context.MODE_APPEND);
//			adl_cd =sp.getString("adl_cd","");
			System.out.println("adl_cd为空时获取后为=="+adl_cd);
		}
		if (!adl_cd.equals("")) {
			rp.addBodyParameter("adl_cd", adl_cd);
			System.out.println("adl_cd不为空时请求参数为==" + adl_cd);
		}
		return rp;
	}


	@OnItemClick(R.id.refresh_poorhouse)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		PoorVillageBase poorHouse = (PoorVillageBase)parent.getItemAtPosition(position);
		if (poorHouse != null) {
			Intent intent = new Intent(mContext, PoorVillageDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable(Common.BUNDEL_KEY, poorHouse);
			intent.putExtra(Common.INTENT_KEY, bundle);
			startActivity(intent);
		}
	}

	List<Basic_DistrictAppModel> list=new ArrayList<>();
	/**
	 * 给各个筛选条件设置筛选的点击监听
	 */
	private void initListener() {
//		//筛选条件---乡镇、村
//		mMenuLeft.setOnSelectListener(new MenuLeft.OnSelectListener() {
//			@Override
//			public void getValue(String showText) {
////				onRefresh(mMenuLeft, showText);
//				if(!"全部".equals(showText)){ //如果点击的不是乡镇全部
//					onRefresh(mMenuLeft, showText);
//				}else { //如果点击的是乡镇全部
//					expandTabView.onPressBack();
//					int position = getPositon(mMenuLeft);
//					if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
//						expandTabView.setTitle(showText, position);
//					}
//
//					String village = mMenuLeft.getShowText();
//					tpqk = mMenuRight_1.getShowText();//脱贫情况
//					pkhs = mMenuRight_2.getShowText();//贫困户数
//					for (Basic_DistrictAppModel appModel : models) {
//						if (appModel.getAd_nm().equals(village) && !village.equals("")) {
//							adl_cd = appModel.getAd_cd();
//						}
//					}
//
//					current_page = 1;
//					FRIST = 0;
//
//					initData();
//				}
//			}
//
//			@Override
//			public void getArray(String id,int selectPosition) {
//				System.out.println("点击乡镇的index为：==" + selectPosition);
//				String village = mAppModel.get(selectPosition).getAd_nm();
//				System.out.println("选择的乡镇是：===" + village);
//				if("全部".equals(village)){
//					expandTabView.onPressBack();
//					// 获取其在储存菜单中的位置position
//					int position = getPositon(mMenuLeft);
//					if (position >= 0 && !expandTabView.getTitle(position).equals("全部")) {
//						expandTabView.setTitle("全部", position);
//					}
//
//					String village1 = mMenuLeft.getShowText();
//					tpqk = mMenuRight_1.getShowText();//脱贫情况
//					pkhs = mMenuRight_2.getShowText();//贫困户数
//					for (Basic_DistrictAppModel appModel : mAppModel) {
//						if (appModel.getAd_nm().equals(village1) && !village1.equals("")) {
//							adl_cd = appModel.getAd_cd();
//						}
//					}
////					SharedPreferences sp = getSharedPreferences("loginactivty", Context.MODE_APPEND);
////					adl_cd =sp.getString("adl_cd","");
//					System.out.println("点了全部获取getArray中获取的adl_cd====" + adl_cd);
//
//					current_page = 1;
//					FRIST = 0;
//
//					initData();
//					mMenuLeft.setVillage(list);
//				}else {
//					RequestParams rp = new RequestParams();
//					rp.addBodyParameter("ad_cd", id);
//					MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
//							URLs.VILLAGE, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.EIGHT));
//				}
//
////				RequestParams rp = new RequestParams();
////				rp.addBodyParameter("ad_cd", id);
////				System.out.println("请求贫困村页面中的筛选条件(村级List)的网址为：==="+URLs.VILLAGE+"?ad_cd="+id);
////				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
////						URLs.VILLAGE, rp, new MyRequestCallBack((BaseActivity)mActivity, MyConstans.EIGHT));
//			}
//		});
//
//		//筛选条件---脱贫情况
//		mMenuRight_1.setOnSelectListener(new MenuRight.OnSelectListener() {
//			@Override
//			public void getValue(String distance, String showText) {
//				onRefresh(mMenuRight_1,showText);
//			}
//		});
//		//筛选条件---贫困户数
//		mMenuRight_2.setOnSelectListener(new MenuRight.OnSelectListener() {
//			@Override
//			public void getValue(String distance, String showText) {
//				onRefresh(mMenuRight_2, showText);
//			}
//		});
		// 给旗区县菜单设置点击监听
		mMenuRight_6.setOnSelectListener(new MenuRight.OnSelectListener() {
			@Override
			public void getValue(String distance, String showText) {
				current_page = 1;
				FRIST = 0;
				flag_zhen_cun = 0;
				if (!"全部".equals(showText)) { //如果点击的---不是旗区县的全部
					//点击贫困旗区县时请求服务器数据---该旗区县下的乡镇
					onRefresh0(mMenuRight_6, showText);

					if (!StringUtil.isEmpty(poor_zhen_list)) {
						poor_zhen_list.clear();
						mMenuRight_7.setStringArray(poor_zhen_list);
					}

					if (!StringUtil.isEmpty(poor_cun_list)) {
						poor_cun_list.clear();
						mMenuRight_8.setStringArray(poor_cun_list);
					}
					expandTabView.initSelectText2();

					//点击贫困旗区县flag=0或贫困乡镇flag=1或贫困村flag=2时请求服务器的贫困人口统计数据
					getData();
				} else { //如果点击的---是旗区县的全部
					expandTabView.onPressBack();
					// 获取其在储存菜单中的位置position
					int position = getPositon(mMenuRight_6);
					if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
						expandTabView.setTitle(showText, position);
					}
					poor_qiquxian = mMenuRight_6.getShowText();    //贫困旗区县
					select_poor_qiquxian = poor_qiquxian;

					if (!StringUtil.isEmpty(poor_zhen_list)) {
						poor_zhen_list.clear();
						mMenuRight_7.setStringArray(poor_zhen_list);
					}

					if (!StringUtil.isEmpty(poor_cun_list)) {
						poor_cun_list.clear();
						mMenuRight_8.setStringArray(poor_cun_list);
					}
					expandTabView.initSelectText2();

					initData();
				}
			}
		});
		// 给贫困镇菜单设置点击监听
		mMenuRight_7.setOnSelectListener(new MenuRight.OnSelectListener() {
			@Override
			public void getValue(String distance, String showText) {
				current_page = 1;
				FRIST = 0;
				flag_zhen_cun = 1;
				if (!"全部".equals(showText)) { //如果点击的---不是乡镇下的全部
					//点击贫困乡镇时请求服务器数据---该乡镇下的村
					onRefresh1(mMenuRight_7, showText);
					//点击贫困乡镇flag=1或贫困村flag=2时请求服务器的贫困人口统计数据
					getData();
				} else { //如果点击的---是乡镇下的全部
					expandTabView.onPressBack();
					// 获取其在储存菜单中的位置position
					int position = getPositon(mMenuRight_7);
					if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
						expandTabView.setTitle(showText, position);
					}
					poor_zhen = mMenuRight_7.getShowText();    //贫困镇
					select_poor_zhen = poor_zhen;

					if(StringUtil.isNotEmpty(poor_cun_list)){
						poor_cun_list.clear();
						mMenuRight_8.setStringArray(poor_cun_list);
					}
					expandTabView.initSelectText1();

					initData();
				}
			}
		});

		// 给贫困村菜单设置点击监听
		mMenuRight_8.setOnSelectListener(new MenuRight_2.OnSelectListener() {
			@Override
			public void getValue(String distance, String showText) {
				current_page = 1;
				FRIST = 0;
				flag_zhen_cun=2;
				//点击贫困村时获取选中的贫困村的adl_cd
				onRefresh2(mMenuRight_8, showText);
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

		poor_qiquxian = mMenuRight_6.getShowText();    //贫困旗区县
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
	 * 点击贫困乡镇flag=1或贫困村flag=2时请求服务器的贫困人口统计数据
	 */
	private void getData() {
		RequestParams rp = new RequestParams();
		if(flag_zhen_cun==1){
			if(StringUtil.isNotEmpty(adl_cd_zhen)){
				rp.addBodyParameter("adl_cd",adl_cd_zhen);
				rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.POOR_VILLAGE_LIST, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("获取贫困村List数据(第一次进入页面时没有点击筛选条件)的网址为：==="+URLs.POOR_VILLAGE_LIST+"?page="+String.valueOf(current_page));
			}
		}else if(flag_zhen_cun==2){
			if(StringUtil.isNotEmpty(adl_cd_cun)){
				rp.addBodyParameter("adl_cd",adl_cd_cun);
				rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.POOR_VILLAGE_LIST, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("获取贫困村List数据(第一次进入页面时没有点击筛选条件)的网址为：==="+URLs.POOR_VILLAGE_LIST+"?page="+String.valueOf(current_page));
			}
		}else if(flag_zhen_cun==0){
			if(StringUtil.isNotEmpty(adl_cd_qiquxian)){
				rp.addBodyParameter("adl_cd",adl_cd_qiquxian);
				rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.POOR_VILLAGE_LIST, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("获取贫困村List数据(第一次进入页面时没有点击筛选条件)的网址为：==="+URLs.POOR_VILLAGE_LIST+"?page="+String.valueOf(current_page));
			}
		}
	}

	/**
	 * 点击贫困乡镇时请求服务器数据---该乡镇下的村和贫困人口统计
	 * @param view
	 * @param showText
	 */
	private void onRefresh1(View view, String showText) {
		expandTabView.onPressBack();
		// 获取其在储存菜单中的位置position
		int position = getPositon(view);
		if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
			expandTabView.setTitle(showText, position);
		}

		poor_zhen = mMenuRight_7.getShowText();    //贫困镇
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

	private void onRefresh(View view, String showText) {
		current_page = 1;
		FRIST = 0;

		expandTabView.onPressBack();
		int position = getPositon(view);
		if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
			expandTabView.setTitle(showText, position);
		}

		String village = mMenuLeft.getShowText();
		tpqk = mMenuRight_1.getShowText();//脱贫情况
		pkhs = mMenuRight_2.getShowText();//贫困户数

		for (Basic_DistrictAppModel appModel : models) {
			if (appModel.getAd_nm().equals(village) && !village.equals("")) {
				adl_cd = appModel.getAd_cd();
			}
		}
//		if (village.equals("不限")) {
//			adl_cd = "";
//		}else {
//
//		}

		RequestParams rp = getRequestParams();
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST, 
				URLs.POOR_VILLAGE_LIST, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
	}

	private int getPositon(View tView) {
		for (int i = 0; i < mViewArray.size(); i++) {
			if (mViewArray.get(i) == tView) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 贫困旗区县的集合
	 */
	List<AdlCode> lAdlCodes;
	List<String> poor_zhen_list;
	List<Basic_DistrictAppModel> mAppModel;
	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		PoorVillageListResult mVillageListResult = null;
		switch (flag) {
		case MyConstans.FIRST: //贫困村List
			System.out.println("请求贫困村数据成功：==="+str);
			mVillageListResult = PoorVillageListResult.parseToT(str, PoorVillageListResult.class);
			if (!StringUtil.isEmpty(mVillageListResult)) {
				if (mVillageListResult.getSuccess()) {
					List<PoorVillageBase> lPoorHouses = mVillageListResult.getJsondata();
					tv_chaxunhushu.setText("已查询到"+mVillageListResult.getTotal()+"个贫困村");
					if (!StringUtil.isEmpty(lPoorHouses)) {
						switch (FRIST) {
						case 0:
							if (adapter != null) {
								adapter.onRefsh(lPoorHouses);
							}else {
								adapter = new PoorVillageAdapter(mContext, lPoorHouses);
								listview.setAdapter(adapter);
							}
							break;

						case 1:
							if (adapter == null) {
								adapter = new PoorVillageAdapter(mContext, lPoorHouses);
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
				} else {
					showToast("服务器网络异常");
					//如果当前网络故障，给出提示
//					ShowNoticDialog();
				}
			}
			listview.onRefreshComplete();
			break;

			case MyConstans.SIX:
				System.out.println("请求该县市级管辖的区域的服务器数据：==="+str);
				AdlCodeListResult adlCodeListResult = AdlCodeListResult.parseToT(str, AdlCodeListResult.class);
				if(StringUtil.isNotEmpty(adlCodeListResult)){
					if (adlCodeListResult.getSuccess()) {
						lAdlCodes = adlCodeListResult.getJsondata();
						List<String> poor_qiquxian = new ArrayList<>();
						if (lAdlCodes != null && lAdlCodes.size() > 0) {
							for (int i = 0; i < lAdlCodes.size(); i++) {
								lAdlCodes.get(i).setPinyi(PingYinUtil.getPingYin(lAdlCodes.get(i).getAd_nm()));
								poor_qiquxian.add(lAdlCodes.get(i).getAd_nm());
							}
						}
						mMenuRight_6.setStringArray(poor_qiquxian);
					}
				}
				break;

		case MyConstans.SEVEN: //筛选条件(镇级List)
//			System.out.println("请求贫困村页面中的筛选条件(镇级List)的数据成功：==="+str);
//			ZidianAppEntityListResult listResult = ZidianAppEntityListResult.parseToT(str, ZidianAppEntityListResult.class);
//			if (listResult != null && listResult.getSuccess()) {
//				List<ZidianAppEntity> zidianAppEntitys = listResult.getJsondata();
//				if (zidianAppEntitys != null && zidianAppEntitys.size() > 0) {
//					for (ZidianAppEntity zidianAppEntity : zidianAppEntitys) {
//						mAppModel = zidianAppEntity.getTab();
//						mMenuLeft.setStringArray(mAppModel);
//					}
//
//					String[] tpqk = ResourceUtil.getInstance().getStringArrayById(R.array.no_poor_state);
//					//脱贫情况
//					List<String> tpqks = new ArrayList<String>();
//					tpqks.add("不限");
//					for (int i = 0; i < tpqk.length; i++) {
//						tpqks.add(tpqk[i]);
//					}
//					if (tpqks != null) {
//						mMenuRight_1.setStringArray(tpqks);
//					}
//
//					String[] pkhs = ResourceUtil.getInstance().getStringArrayById(R.array.no_poor_num);
//					//贫困户数
//					List<String> pkhss = new ArrayList<String>();
//					pkhss.add("不限");
//					for (int i = 0; i < pkhs.length; i++) {
//						pkhss.add(pkhs[i]);
//					}
//					//贫困户数no_poor_num
//					if (pkhss != null) {
//						mMenuRight_2.setStringArray(pkhss);
//					}
//				}
//			}
			System.out.println("贫困人口统计页面请求筛选条件中的一级乡镇数据成功：===" + str);
			ZidianAppEntityListResult listResult = ZidianAppEntityListResult.parseToT(str, ZidianAppEntityListResult.class);
			if (listResult != null && listResult.getSuccess()) {
				List<ZidianAppEntity> zidianAppEntitys = listResult.getJsondata();
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
							mMenuRight_7.setStringArray(poor_zhen_list);
						}
					}
				}
			}
			break;

		case MyConstans.EIGHT: //筛选条件(村级List)
//			System.out.println("请求贫困村页面中的筛选条件(村级List)的数据成功：==="+str);
//			ZidianAppEntityListResult modelListResult = ZidianAppEntityListResult.parseToT(str, ZidianAppEntityListResult.class);
//			if (modelListResult.getSuccess()) {
//				List<ZidianAppEntity> mAppModels = modelListResult.getJsondata();
//				if (mAppModels.size() > 0) {
//					for (int i = 0; i < mAppModels.size(); i++) {
//						mMenuLeft.setVillage(mAppModels.get(i).getTab());
//						models.addAll(mAppModels.get(i).getTab());
//					}
//				}
//			}
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
							mMenuRight_8.setStringArray(poor_cun_list);
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
		default:
			break;
		}
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		System.out.println("请求贫困村数据失败：==="+str);
		showToast(str);
		listview.onRefreshComplete();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if (expandTabView.isShowing()) {
				expandTabView.onPressBack();
			}else {
				MyApplication.getInstance().finishActivity(mActivity);	
			}
		}
		return true;
	}
}
