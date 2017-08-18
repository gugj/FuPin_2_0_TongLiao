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
import com.roch.fupin.adapter.PoorHouseAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.AdlCodeListResult;
import com.roch.fupin.entity.Basic_DistrictAppModel;
import com.roch.fupin.entity.PinKunCun;
import com.roch.fupin.entity.PoorFamilyBase;
import com.roch.fupin.entity.PoorFamilyListResult;
import com.roch.fupin.entity.T_SYS_DATADICTAppModel;
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
 * 贫困户
 * @author Administrator
 */
@ContentView(R.layout.activity_poorhouse)
public class PoorHouseActivity extends MainBaseActivity implements View.OnClickListener {

	public static final String EXTS_PAGE = "page";

	@ViewInject(R.id.expandtab_view)
	ExpandTabView expandTabView;
	@ViewInject(R.id.refresh_poorhouse)
	PullToRefreshListView listview;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	/**
	 * 高级搜索筛选按钮
	 */
	@ViewInject(R.id.tv_sousuo)
	TextView tv_sousuo;
	int current_page = 1; //当前页码

	String title_name,search_content = "";

	private static int FRIST = 0;
	@ViewInject(R.id.tv_chaxunhushu)
	TextView tv_chaxunhushu;   //已查询到户数
	PoorHouseAdapter adapter;
	/**
	 * 查询条件
	 */
	String adl_cd = "",zhipin_case = "",fupin_cs = "",sex = "";

	/**
	 * 筛选条件---乡镇、村
	 */
	MenuLeft mMenuLeft;
	/**
	 * 筛选条件---帮扶措施
	 */
	MenuRight mMenuRight_1;
	/**
	 * 筛选条件---致贫原因
	 */
	MenuRight mMenuRight_2;
	/**
	 * 筛选条件---性别
	 */
	MenuRight mMenuRight;
	/**
	 * 存储菜单
	 */
	List<View> mViewArray = new ArrayList<View>();

	int i = 0;

	List<Basic_DistrictAppModel> models = new ArrayList<Basic_DistrictAppModel>();

	/**
	 * 帮扶措施
	 */
	List<T_SYS_DATADICTAppModel> models_bfcs = new ArrayList<T_SYS_DATADICTAppModel>();
	/**
	 * 致贫原因
	 */
	List<T_SYS_DATADICTAppModel> models_zpyy = new ArrayList<T_SYS_DATADICTAppModel>();

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		//初始化toolbar，并显示高级查询按钮，设置点击监听
		initToolbar();

		expandTabView.setVisibility(View.VISIBLE);
		MyApplication.getInstance().addActivity(mActivity);

		Intent intent = getIntent();
		String title_name = intent.getStringExtra(Common.INTENT_KEY);
		if (title_name != null && !title_name.equals("")) {
			tv_title.setText(title_name);
		}
		
		mMenuLeft = new MenuLeft(mContext);
		mMenuRight_1 = new MenuRight(mContext);
		mMenuRight_2 = new MenuRight(mContext);
		mMenuRight = new MenuRight(mContext);
		
//		mViewArray.add(mMenuLeft);
//		mViewArray.add(mMenuRight_1);
//		mViewArray.add(mMenuRight_2);
//		mViewArray.add(mMenuRight);
//
//		ArrayList<String> mTextArray = new ArrayList<String>();
//		mTextArray.add("行政区");
//		mTextArray.add("帮扶措施");
//		mTextArray.add("致贫原因");
//		mTextArray.add("性别");

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
//		//获取登陆用户
//		User user = MyApplication.getInstance().getLogin(Common.LoginName);
//		//如果登陆用户的行政区代码不为空
//		if (user.getAdl_cd() != null && !user.getAdl_cd().equals("")) {
//			//如果登陆用户是县级或者为乡镇级
//			if (AdlcdUtil.isCountry(user.getAdl_cd()) || AdlcdUtil.isTown(user.getAdl_cd())) {
//				ad_cd = user.getAdl_cd();
//				//如果登陆用户是村级
//			}else if (AdlcdUtil.isVillage(user.getAdl_cd())) {
//				ad_cd = AdlcdUtil.generateTownCode(user.getAdl_cd());
//			}else {
//				AdlCode adlCode = MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(mContext, Common.LoginName);
//				ad_cd = adlCode.getAd_cd();
//			}
//		}
//		//请求乡镇数据
//		RequestParams rp = new RequestParams();
//		rp.addBodyParameter("ad_cd", ad_cd);
//		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST
//				, URLs.TOWN, rp, new MyRequestCallBack(this, MyConstans.SEVEN));
//		System.out.println("请求贫困户页面中的筛选条件(镇级List)的网址为：===" + URLs.TOWN + "?ad_cd=" + ad_cd);

		String adl_cd = "";
		User user = MyApplication.getInstance().getLogin(Common.LoginName);
		if (user != null) {
			LogUtil.println("登陆用户的adl_cd===" + user.getAdl_cd());
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

		//访问服务器数据---贫困户List列表（使用基本查询条件，页数为=current_page）
		initData();
		//给ExtendListView即乡镇村、帮扶措施、致贫原因、性别菜单选项设置点击监听，点击后弹出筛选条件
		initListener();

		listview.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				if(flag_gaojichaxun){ //如果当前开启了高级查询
					FRIST = 0;
					current_page = 1;
					flag_gaojichaxun=false;
					RequestParams rp = getRequestParams();
//					rp=getRequestParams_GaoJiChaXun(rp);
					rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
					MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
							URLs.POOR_HOUSE_LISE, rp,
							new MyRequestCallBack(PoorHouseActivity.this, MyConstans.FIRST));
					System.out.println("贫困户页面进行高级查询时请求服务器中的贫困户List(flag=1)的网址为：==" + URLs.POOR_HOUSE_LISE + "?&page=" + String.valueOf(current_page));
				}else {
					current_page = 1;
					search_content = "";
					FRIST = 0;
					//获取贫困户List数据(请求服务器网址)
					initData();
				}
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				if(flag_gaojichaxun){
					FRIST = 1;
					current_page++;
					RequestParams rp = getRequestParams();
					rp=getRequestParams_GaoJiChaXun(rp);
					rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
					MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
							URLs.POOR_HOUSE_LISE, rp,
							new MyRequestCallBack(PoorHouseActivity.this, MyConstans.FIRST));
					System.out.println("贫困户页面进行高级查询时请求服务器中的贫困户List(flag=1)的网址为：==" + URLs.POOR_HOUSE_LISE + "?&page=" + String.valueOf(current_page));
				}else {
					current_page++;
					FRIST = 1;
					//获取贫困户List数据(请求服务器网址)
					initData();
				}
			}
		});
	}

	@Override
	protected void onNewIntent(Intent intent) {
		search_content = intent.getStringExtra(SearchManager.QUERY);
		//获取贫困户List数据(请求服务器网址)
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
		tv_sousuo.setVisibility(View.VISIBLE);
		tv_sousuo.setOnClickListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:  
			MyApplication.getInstance().finishActivity(this);
			break;
		}
		return true;
	}
	
	/**
	 * 返回查询条件
	 * @return
	 * 2016年7月1日
	 * ZhaoDongShao
	 */
	private RequestParams getRequestParams(){
		RequestParams rp = new RequestParams();
//		if (!village.equals("不限")&&!village.equals("")) {
//			rp.addBodyParameter("villagename", village);
//		}
		if (!zhipin_case.equals("不限")&&!zhipin_case.equals("")) {
			rp.addBodyParameter("pp_poorreason", zhipin_case);
		}
		if (!fupin_cs.equals("不限")&&!fupin_cs.equals("")) {
			rp.addBodyParameter("helpplan", fupin_cs);
		}
		if (!sex.equals("不限")&&!sex.equals("")) {
			if (sex.equals("男")) {
				rp.addBodyParameter("sextext", "男");
			}else if(sex.equals("女")){
				rp.addBodyParameter("sextext", "女");
			}
		}
		if (adl_cd.equals("")) {
			adl_cd = getAdl_Cd();
			System.out.println("adl_cd为空时获取后为=="+adl_cd);
		}
		if (!adl_cd.equals("")&&!adl_cd.equals("全部")) {
			System.out.println("adl_cd不为空时请求参数为==" + adl_cd);
			rp.addBodyParameter("adl_cd", adl_cd);
		}
		if (search_content != null) {
			if (!search_content.equals("")) {
				rp.addBodyParameter("personname", search_content);
			}
		}
		return rp;
	}

	/**
	 * 访问服务器数据---贫困户List列表（使用基本查询条件，页数为=current_page）
	 * <br/>2016年5月6日
	 * ZhaoDongShao
	 */
	private void initData() {
		RequestParams rp = getRequestParams();
		rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.POOR_HOUSE_LISE, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		System.out.println("获取贫困户List数据(第一次进入页面时没有点击筛选条件)的网址为：===" + URLs.POOR_HOUSE_LISE + "?page=" + String.valueOf(current_page));
	}

	List<Basic_DistrictAppModel> list=new ArrayList<>();
	/**
	 * 给ExtendListView即乡镇村、帮扶措施、致贫原因、性别菜单选项设置点击监听，点击后弹出筛选条件
	 * 2016年11月2日
	 */
	private void initListener() {
		// 给乡镇村菜单设置点击监听
//		mMenuLeft.setOnSelectListener(new MenuLeft.OnSelectListener() {
//			@Override
//			public void getValue(String showText) {
//				current_page = 1;
//				search_content = "";
//				FRIST = 0;
//				flag_gaojichaxun=false;
//				if(!"全部".equals(showText)){ //点击的不是全部
//					onRefresh(mMenuLeft, showText);
//				}else { //点击了全部
//					expandTabView.onPressBack();
//					// 获取其在储存菜单中的位置position
//					int position = getPositon(mMenuLeft);
//					if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
//						expandTabView.setTitle(showText, position);
//					}
//
//					String village = mMenuLeft.getShowText();
//					zhipin_case = mMenuRight_2.getShowText(); //致贫原因
//					fupin_cs = mMenuRight_1.getShowText();    //扶贫措施
//					sex = mMenuRight.getShowText();           //性别
//					for (Basic_DistrictAppModel appModel : models) {
//						if (appModel.getAd_nm().equals(village) && !village.equals("")) {
//							adl_cd = appModel.getAd_cd();
//						}
//					}
//					initData();
//				}
//			}
//			@Override
//			public void getArray(String id,int selectPosition) {
//				System.out.println("点击乡镇的index为：=="+selectPosition);
//				String village = mAppModel.get(selectPosition).getAd_nm();
//				System.out.println("选择的乡镇是：==="+village);
//				if("全部".equals(village)){
//					expandTabView.onPressBack();
//					// 获取其在储存菜单中的位置position
//					int position = getPositon(mMenuLeft);
//					if (position >= 0 && !expandTabView.getTitle(position).equals("全部")) {
//						expandTabView.setTitle("全部", position);
//					}
//
//					String village1 = mMenuLeft.getShowText();
//					zhipin_case = mMenuRight_2.getShowText(); //致贫原因
//					fupin_cs = mMenuRight_1.getShowText();    //扶贫措施
//					sex = mMenuRight.getShowText();           //性别
//					for (Basic_DistrictAppModel appModel : mAppModel) {
//						if (appModel.getAd_nm().equals(village1) && !village1.equals("")) {
//							adl_cd = appModel.getAd_cd();
//						}
//					}
////					SharedPreferences sp = getSharedPreferences("loginactivty", Context.MODE_APPEND);
////					adl_cd =sp.getString("adl_cd","");
//					System.out.println("点了全部获取getArray中获取的adl_cd====" + adl_cd);
//					initData();
//					mMenuLeft.setVillage(list);
//				}else {
//					RequestParams rp = new RequestParams();
//					rp.addBodyParameter("ad_cd", id);
//					MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
//							URLs.VILLAGE, rp, new MyRequestCallBack((BaseActivity)mActivity, MyConstans.EIGHT));
//					System.out.println("贫困户页面请求筛选条件中的二级List(flag=8)即村的网址为：==="+URLs.VILLAGE+"?&ad_cd="+id);
//				}
//
////				RequestParams rp = new RequestParams();
////				rp.addBodyParameter("ad_cd", id);
////				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
////						URLs.VILLAGE, rp, new MyRequestCallBack((BaseActivity)mActivity, MyConstans.EIGHT));
//			}
//		});
//
//		// 给帮扶措施菜单设置点击监听
//		mMenuRight_1.setOnSelectListener(new MenuRight.OnSelectListener() {
//			@Override
//			public void getValue(String distance, String showText) {
//				current_page = 1;
//				search_content = "";
//				FRIST = 0;
//				flag_gaojichaxun=false;
//				onRefresh(mMenuRight_1, showText);
//			}
//		});
//
//		// 给致贫原因菜单设置点击监听
//		mMenuRight_2.setOnSelectListener(new MenuRight.OnSelectListener() {
//			@Override
//			public void getValue(String distance, String showText) {
//				current_page = 1;
//				search_content = "";
//				FRIST = 0;
//				flag_gaojichaxun=false;
//				onRefresh(mMenuRight_2, showText);
//			}
//		});
//
//		// 给性别菜单设置点击监听
//		mMenuRight.setOnSelectListener(new OnSelectListener() {
//			@Override
//			public void getValue(String distance, String showText) {
//				current_page = 1;
//				search_content = "";
//				FRIST = 0;
//				flag_gaojichaxun=false;
//				onRefresh(mMenuRight, showText);
//			}
//		});
		// 给旗区县菜单设置点击监听
		mMenuRight_6.setOnSelectListener(new MenuRight.OnSelectListener() {
			@Override
			public void getValue(String distance, String showText) {
				current_page = 1;
				search_content = "";
				FRIST = 0;
				flag_zhen_cun = 0;
				if (!"全部".equals(showText)) { //如果点击的是不是旗区县的全部
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
				} else { //如果点击的是是旗区县的全部
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
				search_content = "";
				FRIST = 0;
				flag_zhen_cun = 1;
				if (!"全部".equals(showText)) {
					//点击贫困乡镇时请求服务器数据---该乡镇下的村
					onRefresh1(mMenuRight_7, showText);
					//点击贫困乡镇flag=1或贫困村flag=2时请求服务器的贫困人口统计数据
					getData();
				} else {
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
				search_content = "";
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
						URLs.POOR_HOUSE_LISE, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("获取贫困户List数据(第一次进入页面时没有点击筛选条件)的网址为：===" + URLs.POOR_HOUSE_LISE + "?page=" + String.valueOf(current_page));
			}
		}else if(flag_zhen_cun==2){
			if(StringUtil.isNotEmpty(adl_cd_cun)){
				rp.addBodyParameter("adl_cd",adl_cd_cun);
				rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.POOR_HOUSE_LISE, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("获取贫困户List数据(第一次进入页面时没有点击筛选条件)的网址为：===" + URLs.POOR_HOUSE_LISE + "?page=" + String.valueOf(current_page));
			}
		}else if(flag_zhen_cun==0){
			if(StringUtil.isNotEmpty(adl_cd_qiquxian)){
				rp.addBodyParameter("adl_cd",adl_cd_qiquxian);
				rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.POOR_HOUSE_LISE, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("获取贫困户List数据(第一次进入页面时没有点击筛选条件)的网址为：===" + URLs.POOR_HOUSE_LISE + "?page=" + String.valueOf(current_page));
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
		expandTabView.onPressBack();
		// 获取其在储存菜单中的位置position
		int position = getPositon(view);
		if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
			expandTabView.setTitle(showText, position);
		}

		String village = mMenuLeft.getShowText();
		zhipin_case = mMenuRight_2.getShowText(); //致贫原因
		fupin_cs = mMenuRight_1.getShowText(); //扶贫措施
		sex = mMenuRight.getShowText();

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
		// 请求网络数据
		MyApplication.getInstance().getHttpUtilsInstance()
		.send(HttpMethod.POST, URLs.POOR_HOUSE_LISE, rp,
				new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
	}

	/**
	 * 根据展示的popuwindow窗口，判断其在储存菜单的位置position
	 * @param tView  展示的popuwindow窗口
	 * @return       返回其在储存菜单的位置position
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
	 * 贫困户页面的条目点击事件，点击后进入贫困户详情页面的activity，并通过Bundle把贫困户对象PoorFamilyBase传过去，这个对象包括户ID等全部基本信息
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 * 2016年10月31日
	 */
	@OnItemClick(R.id.refresh_poorhouse)
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

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		switch (flag){
			case MyConstans.FIRST:
				System.out.println("请求贫困户数据失败：===" + str);
				showToast(str);
				listview.onRefreshComplete();
				break;
		}
	}

	/**
	 * 贫困旗区县的集合
	 */
	List<AdlCode> lAdlCodes;
	List<String> poor_zhen_list;
	/**
	 * 贫困乡镇的集合
	 */
	List<Basic_DistrictAppModel> mAppModel;
	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);

		PoorFamilyListResult mHoustListResult = null;
		switch (flag) {
		case MyConstans.FIRST:
			System.out.println("请求贫困户数据成功===："+str);
			mHoustListResult = PoorFamilyListResult.parseToT(str, PoorFamilyListResult.class);
			if (!StringUtil.isEmpty(mHoustListResult)) {
				if (mHoustListResult.getSuccess()) {
					List<PoorFamilyBase> lPoorHouses = mHoustListResult.getJsondata();
					tv_chaxunhushu.setText("已查询到"+mHoustListResult.getTotal()+"户建档立卡贫困户");
					if (!StringUtil.isEmpty(lPoorHouses)) {
						switch (FRIST) {
						case 0:
							if (adapter != null) {
								adapter.onRefsh(lPoorHouses);
							}else {
								adapter = new PoorHouseAdapter(mContext, lPoorHouses);
								listview.setAdapter(adapter);
							}
							break;
							
						case 1:
							if (adapter == null) {
								adapter = new PoorHouseAdapter(mContext, lPoorHouses);
								listview.setAdapter(adapter);
							}else {
								adapter.addList(lPoorHouses);
							}
							break;
							
						default:
							break;
						}
					}else {
						showToast("没有更多数据");
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

		case MyConstans.SEVEN:
//			System.out.println("请求贫困户页面中的筛选条件(镇级List)的数据成功：==="+str);
//			ZidianAppEntityListResult listResult = ZidianAppEntityListResult.parseToT(str, ZidianAppEntityListResult.class);
//			if (listResult != null && listResult.getSuccess()) {
//				List<ZidianAppEntity> zidianAppEntitys = listResult.getJsondata();
//				if (zidianAppEntitys != null && zidianAppEntitys.size() > 0) {
//					for (ZidianAppEntity zidianAppEntity : zidianAppEntitys) {
//						mAppModel = zidianAppEntity.getTab();
//						List<T_SYS_DATADICTAppModel> mAppModels = zidianAppEntity.getTda();
//						//致贫原因
//						List<String> zpyy = new ArrayList<String>();
//						zpyy.add("不限");
//						//帮扶措施
//						List<String> bfcs = new ArrayList<String>();
//						bfcs.add("不限");
//						for (int i = 0; i < mAppModels.size(); i++) {
//							if (mAppModels.get(i).getName().equals(Common.EXTS_BFCS)) {
//								bfcs.add(mAppModels.get(i).getCode());
//								models_bfcs.add(mAppModels.get(i)); //添加帮扶措施到list
//							}else if (mAppModels.get(i).getName().equals(Common.EXTS_ZPYY)) {
//								zpyy.add(mAppModels.get(i).getCode());
//								models_zpyy.add(mAppModels.get(i)); //致贫原因
//							}
//						}
//						List<String> sexs = new ArrayList<String>();
//						sexs.add("不限");
//						sexs.add("男");
//						sexs.add("女");
//
//						if (sexs != null) {
//							mMenuRight.setStringArray(sexs);
//						}
//						if (bfcs != null) {
//							mMenuRight_1.setStringArray(bfcs);
//						}
//						if (zpyy != null) {
//							mMenuRight_2.setStringArray(zpyy);
//						}
//						if (mAppModel != null) {
//							mMenuLeft.setStringArray(mAppModel);
//						}
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
			
		case MyConstans.EIGHT:
//			System.out.println("请求贫困户页面中的筛选条件(村级List)的数据成功：==="+str);
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



	/**
	 * 点击返回键时关闭Activity
	 * @param keyCode
	 * @param event
	 * @return
	 */
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

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.tv_sousuo:
//			showToast("点击了高级查询筛选按钮");
				Intent intent=new Intent(PoorHouseActivity.this,SearchActivity.class);
				startActivityForResult(intent, 1);
				break;

			default:
				break;
		}
	}

	/**
	 * 标志位---标识当前是否为高级查询模式
	 */
	private boolean flag_gaojichaxun=false;
	//高级查询条件
	String houseName="",housePhone="",tv_renjunnianshouru_start="",tv_renjunnianshouru_end="",tv_bangfuren_xingming="",houseTPQK="",housePKUSX="",houseTouPinNianFen="",houseZaiXiaoSheng="",houseCanJiRen=""
			,suozaicun_cunshuxing="",teshu_manxingbing="",zhuyaozhipin_yuanyin="",qitazhipin_yuanyin="",changjian_manxingbing="",bangfucuoshi=""
			,youwuwgongrenyuan="",zhongda_jibing="",xiangshou_dibao="",xiangshou_wubao="",xingbie="",yutuopin_nianfen="",youwu_photo="",youwu_bangfu_jilu="";

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1){
			if(resultCode==2){
				current_page = 1;
				FRIST=0;
				flag_gaojichaxun=true;
				houseName=data.getStringExtra("houseName");
				housePhone=data.getStringExtra("housePhone");
				tv_renjunnianshouru_start=data.getStringExtra("tv_renjunnianshouru_start");
				tv_renjunnianshouru_end=data.getStringExtra("tv_renjunnianshouru_end");
				tv_bangfuren_xingming=data.getStringExtra("tv_bangfuren_xingming");
				houseTPQK=data.getStringExtra("houseTPQK");   //脱贫情况----未脱贫等
				housePKUSX=data.getStringExtra("housePKUSX");
				houseTouPinNianFen=data.getStringExtra("houseTouPinNianFen");
				bangfucuoshi=data.getStringExtra("bangfucuoshi");
				houseZaiXiaoSheng=data.getStringExtra("houseZaiXiaoSheng");
				houseCanJiRen=data.getStringExtra("houseCanJiRen");
				suozaicun_cunshuxing=data.getStringExtra("suozaicun_cunshuxing");
				teshu_manxingbing=data.getStringExtra("teshu_manxingbing");
				zhuyaozhipin_yuanyin=data.getStringExtra("zhuyaozhipin_yuanyin");
				qitazhipin_yuanyin=data.getStringExtra("qitazhipin_yuanyin");
				changjian_manxingbing=data.getStringExtra("changjian_manxingbing");
				youwuwgongrenyuan=data.getStringExtra("youwuwgongrenyuan");
				zhongda_jibing=data.getStringExtra("zhongda_jibing");
				xiangshou_dibao=data.getStringExtra("xiangshou_dibao");
				xiangshou_wubao=data.getStringExtra("xiangshou_wubao");
				xingbie=data.getStringExtra("xingbie");
				yutuopin_nianfen=data.getStringExtra("yutuopin_nianfen");
				youwu_photo=data.getStringExtra("youwu_photo");
				youwu_bangfu_jilu=data.getStringExtra("youwu_bangfu_jilu");
//        		showToast("高级查询选择后的数据为:姓名="+houseName+",电话="+housePhone+",人均年收入="+tv_renjunnianshouru_start+",帮扶人姓名="+tv_bangfuren_xingming+",脱贫情况="+houseTPQK+",贫困户属性="+housePKUSX
//						+",脱贫年份="+houseTouPinNianFen+",帮扶措施="+bangfucuoshi+",在校生="+houseZaiXiaoSheng+",残疾人="+houseCanJiRen
//						+",村属性="+suozaicun_cunshuxing+",特殊慢性病="+teshu_manxingbing+",主要致贫原因="+zhuyaozhipin_yuanyin+",其他致贫原因="+qitazhipin_yuanyin
//						+",常见慢性病="+changjian_manxingbing+",有无务工="+youwuwgongrenyuan+",有无重大疾病="+zhongda_jibing+",有无低保="+xiangshou_dibao
//						+",有无五保="+xiangshou_wubao);
				System.out.println("高级查询选择后的数据为:姓名="+houseName+",电话="+housePhone+",人均年收入-开始="+tv_renjunnianshouru_start+",人均年收入-结束="+tv_renjunnianshouru_end+",帮扶人姓名="+tv_bangfuren_xingming+",脱贫情况="+houseTPQK+",贫困户属性="+housePKUSX
						+",脱贫年份="+houseTouPinNianFen+",帮扶措施="+bangfucuoshi+",在校生="+houseZaiXiaoSheng+",残疾人="+houseCanJiRen
						+",村属性="+suozaicun_cunshuxing+",特殊慢性病="+teshu_manxingbing+",主要致贫原因="+zhuyaozhipin_yuanyin+",其他致贫原因="+qitazhipin_yuanyin
						+",常见慢性病="+changjian_manxingbing+",有无务工="+youwuwgongrenyuan+",有无重大疾病="+zhongda_jibing+",有无低保="+xiangshou_dibao
						+",有无五保="+xiangshou_wubao+",性别="+xingbie+",预脱贫年份="+yutuopin_nianfen+",有无照片="+youwu_photo+",有无帮扶记录="+youwu_bangfu_jilu);

				RequestParams rp = getRequestParams();
				rp=getRequestParams_GaoJiChaXun(rp);
				current_page=1;
				FRIST=0;
				rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.POOR_HOUSE_LISE, rp,
						new MyRequestCallBack(this, MyConstans.FIRST));
				System.out.println("贫困户页面进行高级查询时请求服务器中的贫困户List(flag=1)的网址为：==" + URLs.POOR_HOUSE_LISE + "?&page=" + String.valueOf(1));
			}
		}
	}

	private RequestParams getRequestParams_GaoJiChaXun(RequestParams rp) {
		rp.addBodyParameter("personname", houseName); //姓名
		rp.addBodyParameter("phone", housePhone); //电话
		rp.addBodyParameter("income_start", tv_renjunnianshouru_start); //人均年收入_开始
		rp.addBodyParameter("income_end", tv_renjunnianshouru_end); //人均年收入_结束
		rp.addBodyParameter("helpdutypersonnames", tv_bangfuren_xingming); //帮扶责任人姓名
		rp.addBodyParameter("povertystatustext", houseTPQK); //脱贫情况
		rp.addBodyParameter("poorfamilyproperttest", housePKUSX); //贫困户属性
		rp.addBodyParameter("tp_year", houseTouPinNianFen); //脱贫年份
		rp.addBodyParameter("helpplan", bangfucuoshi); //帮扶措施
		rp.addBodyParameter("zaixiao_info", houseZaiXiaoSheng); //有无在校生
		rp.addBodyParameter("canji_info", houseCanJiRen); //有无残疾人员
		rp.addBodyParameter("village_povertystatus_text", suozaicun_cunshuxing); //所在村的村属性
		rp.addBodyParameter("jb2_info", teshu_manxingbing); //有无特殊慢性病
		rp.addBodyParameter("pp_poorreason", zhuyaozhipin_yuanyin); //主要致贫原因
		rp.addBodyParameter("se_poorreason", qitazhipin_yuanyin); //其他致贫原因
		rp.addBodyParameter("jb1_info", changjian_manxingbing); //有无常见慢性病
		rp.addBodyParameter("job_info", youwuwgongrenyuan); //有无务工人员
		rp.addBodyParameter("jb3_info", zhongda_jibing);  //有无重大疾病
		rp.addBodyParameter("db_info", xiangshou_dibao);  //有无享受低保人员
		rp.addBodyParameter("wb_info", xiangshou_wubao);  //有无享受五保人员-
		rp.addBodyParameter("sextext", xingbie);  //性别
		rp.addBodyParameter("ytp_time", yutuopin_nianfen);  //预脱贫年份
		rp.addBodyParameter("ifhasphoto", youwu_photo);  //有无照片
		rp.addBodyParameter("ifhashelpduty", youwu_bangfu_jilu);  //有无帮扶记录
		System.out.println("高级查询中所在村的村属性的选则情况是：======"+suozaicun_cunshuxing);
		return rp;
	}

}
