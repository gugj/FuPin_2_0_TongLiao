package com.roch.fupin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.AdlCodeListResult;
import com.roch.fupin.entity.Basic_DistrictAppModel;
import com.roch.fupin.entity.PinKunCun;
import com.roch.fupin.entity.PoorPeopleCause;
import com.roch.fupin.entity.PoorPeopleCausel_ResultList;
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
import com.roch.fupin.view.CaseXueTableScrollView;
import com.roch.fupin.view.ExpandTabView;
import com.roch.fupin.view.MenuRight;
import com.roch.fupin.view.MenuRight_2;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 致贫原因（因学、病致贫）统计
 * @author ZhaoDongShao
 * 2016年8月11日
 */
//@ContentView(R.layout.activity_poor_people_case_xue_statistic)
public class PoorPeopleCaseYXActivity extends MainBaseActivity{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	
	private ListView mListView;
	/**
	 * 存放自定义的HorizontalScrollView的List集合
	 */
	protected List<CaseXueTableScrollView> mHScrollViews =new ArrayList<CaseXueTableScrollView>();

	/**
	 * 自定义的HorizonTalScrollView
	 */
	public CaseXueTableScrollView mTouchView;

	//创建数组保存表头
	private String[] cols = {"title","户数","人数","旗县内","旗县外(市内)","市外(省内)","省外","幼儿园","小学生","初中生","高中生","职专","大学生"};//因学
	String[] case_bing = {"title","户数","人数","常见慢性病","特殊慢性病","重大疾病"};//因病

	private  ScrollAdapter mAdapter;
	private String title_name;

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
	String adl_cd = "", poor_qiquxian="",poor_zhen = "", poor_cun = "";
	List<Basic_DistrictAppModel> models = new ArrayList<Basic_DistrictAppModel>();
	List<Basic_DistrictAppModel> mAppModel;

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
		Intent intent = getIntent();
		title_name = intent.getStringExtra(Common.INTENT_KEY);
		if(title_name.contains("因学")){
			setContentView(R.layout.activity_poor_people_case_xue_statistic);
		}else if(title_name.contains("因病")){
			setContentView(R.layout.activity_poor_people_case_bing_statistic);
		}
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(mActivity);

		//初始化toogbar
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
					new MyRequestCallBack(this, MyConstans.SIX,false));
			System.out.println("请求市县级管辖区域的服务器地址：===" + URLs.COUNTY + "?ad_cd=" + adl_cd);
		}
		// 请求服务器---旗区县数据
//		RequestParams rp = new RequestParams();
//		rp.addBodyParameter("ad_cd", ad_cd);
//		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
//				URLs.TOWN, rp, new MyRequestCallBack(this, MyConstans.SEVEN));
//		System.out.println("贫困人口统计页面一进来初始化时请求服务器中筛选条件的乡镇(flag=7)网址为：==" + URLs.TOWN + "?&ad_cd=" + ad_cd);

		//初始化Activity的标题，然后请求致贫详情的数据
		initViews();

		//给ExtendListView即贫困镇、贫困村菜单选项设置点击监听，点击后弹出筛选条件
		initListener();
	}

	
	/**
	 * 初始化toogbar
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

					if(!StringUtil.isEmpty(poor_zhen_list)){
						poor_zhen_list.clear();
						mMenuRight_1.setStringArray(poor_zhen_list);
					}

					if(!StringUtil.isEmpty(poor_cun_list)){
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

					if(!StringUtil.isEmpty(poor_zhen_list)){
						poor_zhen_list.clear();
						mMenuRight_1.setStringArray(poor_zhen_list);
					}

					if(!StringUtil.isEmpty(poor_cun_list)){
						poor_cun_list.clear();
						mMenuRight_2.setStringArray(poor_cun_list);
					}
					expandTabView.initSelectText2();

					if (title_name.contains("因学")) {
						initData("因学");
					} else if (title_name.contains("因病")) {
						initData("因病");
					}
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
					onRefresh1(mMenuRight_1, showText);
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

					if (title_name.contains("因学")) {
						initData("因学");
					} else if (title_name.contains("因病")) {
						initData("因病");
					}
				}
			}
		});

		// 给贫困村菜单设置点击监听
		mMenuRight_2.setOnSelectListener(new MenuRight_2.OnSelectListener() {
			@Override
			public void getValue(String distance, String showText) {
				flag_zhen_cun=2;
				//点击贫困村时获取选中的贫困村的adl_cd
				onRefresh2(mMenuRight_2, showText);
				//点击贫困乡镇flag=1或贫困村flag=2时请求服务器的贫困人口统计数据
				getData();
			}
		});
	}

	private void initData(String cause) {
		RequestParams rp = new RequestParams();
		rp.addBodyParameter("pp_poorreason", cause);
		MyApplication.getInstance().getHttpUtilsInstance().send(
				HttpMethod.POST, URLs.CASE_DETAIL, rp,
				new MyRequestCallBack(mActivity, MyConstans.FIRST));
		System.out.println("因学致贫原因统计页面请求服务器详情网址为：===" + URLs.CASE_DETAIL + "&pp_poorreason=" + cause);
	}

	/**
	 * 点击贫困乡镇flag=1或贫困村flag=2时请求服务器的贫困人口统计数据
	 */
	private void getData() {
		RequestParams rp = new RequestParams();
		if(flag_zhen_cun==1){
			if(StringUtil.isNotEmpty(adl_cd_zhen)){
				rp.addBodyParameter("adl_cd",adl_cd_zhen);
				rp.addBodyParameter("pp_poorreason","因学");
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.CASE_DETAIL, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("贫困人口统计请求服务器网址为：===" + URLs.CASE_DETAIL);
			}
		}else if(flag_zhen_cun==2){
			if(StringUtil.isNotEmpty(adl_cd_cun)){
				rp.addBodyParameter("adl_cd",adl_cd_cun);
				rp.addBodyParameter("pp_poorreason","因学");
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.CASE_DETAIL, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("贫困人口统计请求服务器网址为：===" + URLs.CASE_DETAIL);
			}
		}else if(flag_zhen_cun==0){
			if(StringUtil.isNotEmpty(adl_cd_qiquxian)){
				rp.addBodyParameter("adl_cd",adl_cd_qiquxian);
				rp.addBodyParameter("pp_poorreason","因学");
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.CASE_DETAIL, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("贫困人口统计请求服务器网址为：===" + URLs.CASE_DETAIL);
			}
		}
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
									new MyRequestCallBack((BaseActivity) mActivity, MyConstans.SEVEN,false));
					System.out.println("筛选条件设置好以后请求服务器中的贫困乡镇List(flag=7)网址为：===" + URLs.TOWN+"?&ad_cd="+adl_cd_qiquxian);
				}
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
									new MyRequestCallBack((BaseActivity) mActivity, MyConstans.EIGHT,false));
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
	 * 点击返回时推出Activity
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
	 * 初始化Activity的标题，然后请求致贫详情的数据
	 * 2016年8月11日
	 * ZhaoDongShao
	 */
	private void initViews() {
		if (title_name != null && !title_name.equals("")) {
			tv_title.setText(title_name.substring(0, 2)+"致贫");
		}
		System.out.println("请求因病或因学致贫的网址为："+ URLs.CASE_DETAIL);
		MyApplication.getInstance().getHttpUtilsInstance().send(
				HttpMethod.POST, URLs.CASE_DETAIL,
				new MyRequestCallBack(mActivity, MyConstans.FIRST,true));
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
				System.out.println("请求因病或因学致贫的数据成功：==="+str);
				//jsondata数据List集合
				List<PoorPeopleCause> list = new ArrayList<PoorPeopleCause>();
				PoorPeopleCausel_ResultList listResult = PoorPeopleCausel_ResultList.parseToT(str, PoorPeopleCausel_ResultList.class);
				if (listResult != null ) {
					if (listResult.getSuccess()) {
						list.addAll(listResult.getJsondata());
					}
				}

				//存放map集合的List集合
				List<Map<String, String>> datas = new ArrayList<Map<String,String>>();
				//map集合
				Map<String, String> data = null;
				//存放表头的自定义的HorizontalScrollView
				CaseXueTableScrollView headerScroll = (CaseXueTableScrollView) findViewById(R.id.item_scroll_title);
				mHScrollViews.add(headerScroll);
				//ListView数据行
				mListView = (ListView) findViewById(R.id.hlistview_scroll_list);

				if(title_name.contains("因学")){ //----因学致贫-列表详情
					for(int i = 0; i < list.size(); i++) {
						data = new HashMap<String, String>();
						data.put("title", list.get(i).getAdl_nm());
						for (int j = 1; j < 13; j++) {
							switch (j) {
								case 1:
									data.put("户数", String.valueOf(list.get(i).getPoorreson_x_fc()));
									break;

								case 2:
									data.put("人数", String.valueOf(list.get(i).getPoorreson_x_pc()));
									break;

								case 3:
									data.put("旗县内", String.valueOf(list.get(i).getPoorreson_qixiannei()));
									break;
								case 4:
									data.put("旗县外(市内)", String.valueOf(list.get(i).getPoorreson_qixianwai()));
									break;

								case 5:
									data.put("市外(省内)", String.valueOf(list.get(i).getPoorreson_shiwaisn()));
									break;

								case 6:
									data.put("省外", String.valueOf(list.get(i).getPoorreson_shengwai()));
									break;

								case 7:
									data.put("幼儿园", String.valueOf(list.get(i).getPoorreson_x_pc_yey()));
									break;

								case 8:
									data.put("小学生", String.valueOf(list.get(i).getPoorreson_x_pc_xxs()));
									break;

								case 9:
									data.put("初中生", String.valueOf(list.get(i).getPoorreson_x_pc_czs()));
									break;

								case 10:
									data.put("高中生", String.valueOf(list.get(i).getPoorreson_x_pc_gzs()));
									break;

								case 11:
									data.put("职专", String.valueOf(list.get(i).getPoorreson_x_pc_zz()));
									break;

								case 12:
									data.put("大学生", String.valueOf(list.get(i).getPoorreson_x_pc_dx()));
									break;

								default:
									break;
							}
						}
						datas.add(data);
					}
					mAdapter = new ScrollAdapter(this, datas, R.layout.listitem_poor_people_case_xue_statistic_listitem //R.layout.item
							, cols
							, new int[] { R.id.item_titlev
							, R.id.item_datav1
							, R.id.item_datav2
							, R.id.item_datav3
							, R.id.item_datav4
							, R.id.item_datav5
							, R.id.item_datav6
							, R.id.item_datav7
							, R.id.item_datav8
							, R.id.item_datav9
							, R.id.item_datav10
							, R.id.item_datav11
							, R.id.item_datav12 });
				}else if(title_name.contains("因病")){ //----因病致贫-列表详情
					for(int i = 0; i < list.size(); i++) {
						data = new HashMap<String, String>();
						data.put("title", list.get(i).getAdl_nm());
						if(i>=0 && i!=list.size()-1){
							for (int j = 1; j < 6; j++) {
								switch (j) {
									case 1:
										data.put("户数", String.valueOf(list.get(i).getPoorreson_b_fc()));
										break;

									case 2:
										data.put("人数", String.valueOf(list.get(i).getPoorreson_b_pc()));
										break;

									case 3:
										data.put("常见慢性病", String.valueOf(list.get(i).getPoorreson_b_cjmxb_pc()));
										break;

									case 4:
										data.put("特殊慢性病", String.valueOf(list.get(i).getPoorreson_b_tsmxb_pc()));
										break;

									case 5:
										data.put("重大疾病", String.valueOf(list.get(i).getPoorreson_b_zdjb_pc()));
										break;

									default:
										break;
								}
							}
						}else if(i==list.size()-1){ //最后一行-----------
							for (int j = 1; j < 6; j++) {
								switch (j) {
									case 1:
										data.put("户数", String.valueOf(list.get(i).getPoorreson_b_fc()));
										break;

									case 2:
										data.put("人数", String.valueOf(list.get(i).getPoorreson_b_pc()));
										break;

									case 3:
										int changjian=0;
										for (int k = 0; k <list.size()-1; k++) {
											changjian+=Integer.parseInt(list.get(k).getPoorreson_b_cjmxb_pc());
										}
										data.put("常见慢性病", String.valueOf(changjian));
										break;

									case 4:
										int teshu=0;
										for (int k = 0; k <list.size()-1; k++) {
											teshu+=Integer.parseInt(list.get(k).getPoorreson_b_tsmxb_pc());
										}
										System.out.println("****************"+teshu);
										data.put("特殊慢性病", String.valueOf(teshu));
										break;

									case 5:
										int zhongda=0;
										for (int k = 0; k <list.size()-1; k++) {
											zhongda+=Integer.valueOf(list.get(k).getPoorreson_b_zdjb_pc());
										}
										System.out.println("****************"+zhongda);
										data.put("重大疾病", String.valueOf(zhongda));
										break;

									default:
										break;
								}
							}
						}
						datas.add(data);
					}
					mAdapter = new ScrollAdapter(this, datas, R.layout.listitem_poor_people_case_bing_statistic_listitem //R.layout.item
							, case_bing
							, new int[] { R.id.item_titlev
							, R.id.item_datav1
							, R.id.item_datav2
							, R.id.item_datav3
							, R.id.item_datav4
							, R.id.item_datav5
					});
				}

				mListView.setAdapter(mAdapter);
				showToast("数据加载完成");
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
				ZidianAppEntityListResult listResult1 = ZidianAppEntityListResult.parseToT(str, ZidianAppEntityListResult.class);
				if (listResult1 != null && listResult1.getSuccess()) {
					List<ZidianAppEntity> zidianAppEntitys = listResult1.getJsondata();
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
		switch (flag){
			case MyConstans.FIRST:
				System.out.println("请求因病或因学致贫的数据失败：===" + str);
				break;

			case MyConstans.SEVEN:
				System.out.println("请求行政区中的乡镇数据失败：===" + str);
				break;

			case MyConstans.EIGHT:
				System.out.println("请求行政区中的贫困村数据失败：===" + str);
				break;
		}
		showToast(str);
	}

	/**
	 * 将存数据的HorizonTalScrollView添加的List集合中
	 * @param hScrollView
	 */
	public void addHViews(final CaseXueTableScrollView hScrollView) {
		if(!mHScrollViews.isEmpty()) {
			int size = mHScrollViews.size();
			//取出Activity中作为表头的HorizonTalScrollView
			CaseXueTableScrollView scrollView = mHScrollViews.get(size - 1);
			final int scrollX = scrollView.getScrollX();
			//表头滑动时，存数据的HorizonTalScrollView也跟着滑动
			if(scrollX != 0) {
				mListView.post(new Runnable() {
					@Override
					public void run() {
						hScrollView.scrollTo(scrollX, 0);
					}
				});
			}
		}
		//将存数据的HorizonTalScrollView添加的List集合中
		mHScrollViews.add(hScrollView);
	}

	/**
	 * 当自定义的HorizonTalScrollView滑动时调用此方法
	 * @param l
	 * @param t
	 * @param oldl
	 * @param oldt
	 */
	public void onScrollChanged(int l, int t, int oldl, int oldt){
		for (CaseXueTableScrollView tableScrollView : mHScrollViews) {
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

		public ScrollAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
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
				addHViews((CaseXueTableScrollView) v.findViewById(R.id.item_chscroll_scroll));
				//将要被填充数据的View数组
				View[] views = new View[to.length];
				for(int i = 0; i < to.length; i++) {
					View tv = v.findViewById(to[i]);
					views[i] = tv;
				}
				v.setTag(views);
			}
			View[] holders = (View[]) v.getTag();

			//赋值
			int len = holders.length;
			for (int i = 0; i < len; i++) {
				((TextView) holders[i]).setText(this.datas.get(position).get(from[i]).toString());
			}
			return v;
		}
	}

}
