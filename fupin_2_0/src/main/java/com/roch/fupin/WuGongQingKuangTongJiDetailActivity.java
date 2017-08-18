package com.roch.fupin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.AdlCodeListResult;
import com.roch.fupin.entity.BasicPoorpeopleModel;
import com.roch.fupin.entity.BasicPoorpeopleModelListResult;
import com.roch.fupin.entity.Basic_DistrictAppModel;
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
import com.roch.fupin.utils.Utils;
import com.roch.fupin.view.ExpandTabView;
import com.roch.fupin.view.MenuRight;
import com.roch.fupin.view.MenuRight_2;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 务工情况统计详情Activity
 * @author ZhaoDongShao
 * 2016年8月9日
 */
@ContentView(R.layout.activity_poor_people_case_detail)
public class WuGongQingKuangTongJiDetailActivity extends MainBaseActivity{

	public final static int MP = LayoutParams.MATCH_PARENT;
	public final static int WC = LayoutParams.WRAP_CONTENT;

	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tl)
	TableLayout layout;
	TableRow row;

	LX leixing;

	//务工情况类型
	enum LX{
		qxw,
		shengw,
		shiw,
		qxn,
		ydfpbq,
		stbc
	}

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
	String adl_cd_zhen;
	/**
	 * 当前选择的贫困村的adl_cd
	 */
	String adl_cd_cun;
	String select_poor_zhen="";
	String select_poor_qiquxian="";

	List<Basic_DistrictAppModel> mAppModel;

	String case_detail="";
	List<PinKunCun> poor_cun_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(this);

		Intent intent = getIntent();
		String title = intent.getStringExtra(Common.INTENT_KEY);
		if (title.equals("") || title == null) {
			tv_title.setText("详情");
		}

		//初始化标题栏
		initToolbar();

		if (title.contains("旗县外(市内)")) {
			leixing = LX.qxw;
			tv_title.setText("旗县外(市内)");
			initData("旗县外(市内)");
			case_detail="旗县外(市内)";
		}else if (title.contains("省外")) {
			leixing = LX.shengw;
			tv_title.setText("省外");
			initData("省外");
			case_detail="省外";
		}else if (title.contains("市外(省内)")) {
			leixing = LX.shiw;
			tv_title.setText("市外(省内)");
			initData("市外(省内)");
			case_detail="市外(省内)";
		}else if (title.contains("旗县内")) {
			leixing = LX.qxn;
			tv_title.setText("旗县内");
			initData("旗县内");
			case_detail="旗县内";
		}

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

		//给ExtendListView即贫困镇、贫困村菜单选项设置点击监听，点击后弹出筛选条件
		initListener();
	}

	/**
	 * 初始化标题栏
	 * 2016年8月9日
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

					initData(case_detail);
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

					initData(case_detail);
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
	 * 点击贫困乡镇flag=1或贫困村flag=2时请求服务器的贫困人口统计数据
	 */
	private void getData() {
		RequestParams rp = new RequestParams();
		if(flag_zhen_cun==1){
			if(StringUtil.isNotEmpty(adl_cd_zhen)){
				rp.addBodyParameter("adl_cd",adl_cd_zhen);
				rp.addBodyParameter("pp_poorreason", case_detail);
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.WuGongQingKuangTongJi_DETAIL, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("贫困人口统计请求服务器网址为：===" + URLs.WuGongQingKuangTongJi_DETAIL);
			}
		}else if(flag_zhen_cun==2){
			if(StringUtil.isNotEmpty(adl_cd_cun)){
				rp.addBodyParameter("adl_cd",adl_cd_cun);
				rp.addBodyParameter("pp_poorreason", case_detail);
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.WuGongQingKuangTongJi_DETAIL, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("贫困人口统计请求服务器网址为：===" + URLs.WuGongQingKuangTongJi_DETAIL);
			}
		}else if(flag_zhen_cun==0){
			if(StringUtil.isNotEmpty(adl_cd_qiquxian)){
				rp.addBodyParameter("adl_cd",adl_cd_qiquxian);
				rp.addBodyParameter("pp_poorreason", case_detail);
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.WuGongQingKuangTongJi_DETAIL, rp, new MyRequestCallBack((BaseActivity) mActivity, MyConstans.FIRST));
				System.out.println("贫困人口统计请求服务器网址为：===" + URLs.WuGongQingKuangTongJi_DETAIL);
			}
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
	 * 2016年8月9日
	 * ZhaoDongShao
	 */
	private void initData(String pp_poorreason) {
		RequestParams rp = new RequestParams();
		rp.addBodyParameter("pp_poorreason", pp_poorreason);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST, URLs.WuGongQingKuangTongJi_DETAIL, rp
				, new MyRequestCallBack(mActivity, MyConstans.FIRST));
		System.out.println("请求务工情况统计详情的网址为：" + URLs.WuGongQingKuangTongJi_DETAIL);
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
				System.out.println("请求务工情况统计详情数据成功：==" + str);
				BasicPoorpeopleModelListResult result = BasicPoorpeopleModelListResult.parseToT(str, BasicPoorpeopleModelListResult.class);
				if (result != null && result.getSuccess()) {
					List<BasicPoorpeopleModel> list = result.getJsondata();
					if (list != null && list.size()>0) {
						//先清空之前请求的数据表格
						int childCount=layout.getChildCount();
						if(childCount>0){
							layout.removeAllViews();
						}
						generateData(list);
						showToast("数据加载完成");
					}
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
		switch (flag){
			case MyConstans.FIRST:
				System.out.println("请求务工情况统计数据失败：==" + str);
				break;

			case MyConstans.SEVEN:
				System.out.println("请求行政区中的乡镇数据失败：==" + str);
				break;

			case MyConstans.EIGHT:
				System.out.println("请求行政区中贫困村数据失败：==" + str);
				break;
		}
	}

	/**
	 * 生成表格
	 * @param lEntities
	 * 2016年8月9日
	 * ZhaoDongShao
	 */
	private void generateData(List<BasicPoorpeopleModel> lEntities) {
		String[] putong = {"行政区","贫困人口数","旗县外(市内)务工人数"};//旗县外(市内)务工人数
		String[] sangshi = {"行政区","贫困人口数","省外务工人数"};//省外务工人数
		String[] wu = {"行政区","贫困人口数","市外(省内)务工人数"};//市外(省内)务工人数
		String[] jineng = {"行政区","贫困人口数","旗县内务工人数"};//旗县内务工人数

		switch (leixing) {
			case qxw:  //旗县外(市内)务工人数
			getBing(putong, lEntities);
				break;

			case shengw: //省外务工人数
			getXue(sangshi, lEntities);
				break;

			case shiw: //市外(省内)务工人数
			getCan(wu, lEntities);
				break;

			case qxn: //旗县内务工人数
			getNengli(jineng, lEntities);
				break;

		default:
			break;
		}
	}

	/**
	 * 旗县内务工人数
	 * @param case_bing_laodong_money_jishu
	 * @param lEntities
	 * 2016年8月9日
	 * ZhaoDongShao
	 */
	@SuppressLint("InflateParams")
	private void getNengli(String[] case_bing_laodong_money_jishu, List<BasicPoorpeopleModel> lEntities) {
		for (int i = 0; i < lEntities.size() + 1; i++) {
			row = new TableRow(mContext);
			if (i == 0) {
				for (int j = 0; j < case_bing_laodong_money_jishu.length; j++) {
					row.setGravity(Gravity.CENTER);
					row.setLayoutParams(getLayoutParams(1,1,1,0));
					row.addView(getLinearLayout(getTextView(case_bing_laodong_money_jishu[j])));
				}
			}else if (i > 0 && i != lEntities.size()) {
				for (int j = 0; j < case_bing_laodong_money_jishu.length; j++) {
					switch (j) {
					case 0:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.addView(getLinearLayout(getTextView(lEntities.get(i - 1).getAdl_nm())));
						break;

					case 1:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getPopulationnumber()))));
						break;

					case 2:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getGoodhealthnumber()))));
						break;

					default:
						break;
					}
				}
			}else {
				for (int j = 0; j < case_bing_laodong_money_jishu.length; j++) {
					switch (j) {
					case 0:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.addView(getLinearLayout(getTextView(lEntities.get(i -1).getAdl_nm())));
						break;

					case 1:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getPopulationnumber()))));
						break;

					case 2:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getGoodhealthnumber()))));
						break;

					default:
						break;
					}
				}
			}
			layout.addView(row);
		}
	}

	/**
	 * 省外务工人数
	 * @param case_xue
	 * @param lEntities
	 * 2016年8月9日
	 * ZhaoDongShao
	 */
	@SuppressLint("InflateParams")
	private void getXue(String[] case_xue, List<BasicPoorpeopleModel> lEntities) {
		for (int i = 0; i < lEntities.size() + 1; i++) {
			row = new TableRow(mContext);
			if (i == 0) {
				for (int j = 0; j < case_xue.length; j++) {
					row.setGravity(Gravity.CENTER);
					row.setLayoutParams(getLayoutParams(1,1,1,0));
					row.addView(getLinearLayout(getTextView(case_xue[j])));
				}
			}else if (i > 0 && i!=lEntities.size()) {
				for (int j = 0; j < case_xue.length; j++) {
					switch (j) {
					case 0:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.addView(getLinearLayout(getTextView(lEntities.get(i -1).getAdl_nm())));
						break;

					case 1:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getPopulationnumber()))));
						break;

					case 2:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getDisability()))));
						break;

					default:
						break;
					}
				}
			}else {
				for (int j = 0; j < case_xue.length; j++) {
					switch (j) {
					case 0:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.addView(getLinearLayout(getTextView(lEntities.get(i -1).getAdl_nm())));
						break;

					case 1:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getPopulationnumber()))));
						break;

					case 2:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getDisability()))));
						break;

					default:
						break;
					}
				}
			}
			layout.addView(row);
		}
	}

	/**
	 * 旗县外(市内)务工人数
	 * @param case_bing_laodong_money_jishu
	 * @param lEntities
	 * 2016年8月9日
	 * ZhaoDongShao
	 */
	@SuppressLint("InflateParams")
	private void getBing(String[] case_bing_laodong_money_jishu, List<BasicPoorpeopleModel> lEntities) {
		for (int i = 0; i < lEntities.size() + 1; i++) {
			row = new TableRow(mContext);
			if (i == 0) {
				for (int j = 0; j < case_bing_laodong_money_jishu.length; j++) {
					row.setGravity(Gravity.CENTER);
					row.setLayoutParams(getLayoutParams(1,1,1,0));
					row.addView(getLinearLayout(getTextView(case_bing_laodong_money_jishu[j])));
				}
			}else if (i > 0 && i!=lEntities.size()) {
				for (int j = 0; j < case_bing_laodong_money_jishu.length; j++) {
					switch (j) {
					case 0:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.addView(getLinearLayout(getTextView(lEntities.get(i - 1).getAdl_nm())));
						break;

					case 1:
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getPopulationnumber()))));
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.setGravity(Gravity.CENTER);
						break;

					case 2:
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getSpechronicdisnum()))));
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.setGravity(Gravity.CENTER);
						break;
					default:
						break;
					}
				}
			}else {
				for (int j = 0; j < case_bing_laodong_money_jishu.length; j++) {
					switch (j) {
					case 0:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.addView(getLinearLayout(getTextView(lEntities.get(i - 1).getAdl_nm())));
						break;

					case 1:
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getPopulationnumber()))));
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.setGravity(Gravity.CENTER);
						break;

					case 2:
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getSpechronicdisnum()))));
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.setGravity(Gravity.CENTER);
						break;

					default:
						break;
					}
				}
			}
			layout.addView(row);
		}
	}

	/**
	 * 市外(省内)务工人数
	 * @param lEntities
	 * 2016年8月9日
	 * ZhaoDongShao
	 * @param lEntities
	 */
	@SuppressLint("InflateParams")
	private void getCan(String[] case_can, List<BasicPoorpeopleModel> lEntities) {
		for (int i = 0; i < lEntities.size() + 1; i++) {
			row = new TableRow(mContext);
			if (i == 0) {
				for (int j = 0; j < case_can.length; j++) {
					row.setGravity(Gravity.CENTER);
					row.setLayoutParams(getLayoutParams(1,1,1,0));
					row.addView(getLinearLayout(getTextView(case_can[j])));
				}
			}else if (i > 0 && i != lEntities.size()) {
				for (int j = 0; j < case_can.length; j++) {
					switch (j) {
					case 0:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.addView(getLinearLayout(getTextView(lEntities.get(i - 1).getAdl_nm())));
						break;

					case 1:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getPopulationnumber()))));
						break;

					case 2:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getMajordisease()))));
						break;

					default:
						break;
					}
				}
			}else {
				for (int j = 0; j < case_can.length; j++) {
					switch (j) {
					case 0:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.addView(getLinearLayout(getTextView(lEntities.get(i - 1).getAdl_nm())));
						break;

					case 1:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getPopulationnumber()))));
						break;

					case 2:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getMajordisease()))));
						break;

					default:
						break;
					}
				}
			}
			layout.addView(row);
		}
	}

	/**
	 * 返回textview
	 * @param name
	 * @return
	 * 2016年8月10日
	 * ZhaoDongShao
	 */
	private TextView getTextView(String name){
		TextView tView = new TextView(mContext);
		tView.setTextSize(15);
		tView.setTextSize(15);
		tView.setText(name);
		tView.setBackgroundColor(Color.WHITE);
		tView.setGravity(Gravity.CENTER);
		
		int dp = Utils.dip2px(mContext, 30);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(MP, dp);  
		lp.setMargins(0, 0, 1, 0);  
		tView.setLayoutParams(lp);
		return tView;
	}

	/**
	 * 创建线性布局
	 * @param tv
	 * @return
	 * 2016年8月10日
	 * ZhaoDongShao
	 */
	private LinearLayout getLinearLayout(TextView tv){
		LinearLayout layout = new LinearLayout(mContext);
		layout.addView(tv);
		return layout;
	}

	/**
	 * 声明属性
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @return
	 * 2016年8月10日
	 * ZhaoDongShao
	 */
	private LayoutParams getLayoutParams(int left, int top, int right, int bottom){
		TableLayout.LayoutParams lParams = new TableLayout.LayoutParams(MP,MP);
		lParams.setMargins(left, top, right, bottom);
		return lParams;
	}
}
