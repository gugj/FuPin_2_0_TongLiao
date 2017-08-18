package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.BasicPoorpeopleModel;
import com.roch.fupin.entity.BasicPoorpeopleModelListResult;
import com.roch.fupin.entity.LiuGeYiPiTongJi;
import com.roch.fupin.entity.LiuGeYiPiTongJi_ResultList;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * 六个一批统计Activity饼图
 * @author ZhaoDongShao
 * 2016年6月27日
 */
//@ContentView(R.layout.activity_poor_people_case)
public class LiuGeYiPiTongJiActivity extends MainBaseActivity{

	public static final String TAG = "LiuGeYiPiTongJiActivity";
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.pie_chart)
	PieChartView chart;

	PieChartData data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String title = intent.getStringExtra(Common.INTENT_KEY);
		if(title.equals("六个一批统计")){
			setContentView(R.layout.activity_poor_people_case);
		}else if(title.equals("劳动能力类型统计")){
			setContentView(R.layout.activity_laodongnenglileixing_tongji);
		}else if(title.equals("贫困户属性统计")){
			setContentView(R.layout.activity_pinkunhushuxing_tongji);
		}else if(title.equals("贫困村属性统计")){
			setContentView(R.layout.activity_pinkuncunshuxing_tongji);
		}else if(title.equals("务工情况统计")){
			setContentView(R.layout.activity_wugongqingkuang_tongji);
		}
		ViewUtils.inject(mActivity);
		chart= (PieChartView) findViewById(R.id.pie_chart);
		MyApplication.getInstance().addActivity(mActivity);

		//初始化toolbar
		initToolbar();
		//设置title标题并初始化请求饼图数据
		initData();
	}

	/**
	 * 初始化toolbar
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
	 * 设置title标题并初始化请求饼图数据
	 * 2016年6月25日
	 * ZhaoDongShao
	 */
	private void initData() {
		Intent intent = getIntent();
		String title = intent.getStringExtra(Common.INTENT_KEY);

		AdlCode adlCode = MyApplication.getInstance().getSharePreferencesUtilInstance().getNowCity(mContext,Common.LoginName);
		if (adlCode != null && adlCode.getAd_nm() != null) {
			tv_title.setText(adlCode.getAd_nm() + title);
		}else {
			tv_title.setText(title);
		}

		RequestParams rp = getRequestParams();
		if(title.equals("六个一批统计")){ //六个一批统计
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.LiuGeYiPiTongJi_BingTu, rp, new MyRequestCallBack((BaseActivity)mActivity, MyConstans.FIRST));
		}else if(title.equals("劳动能力类型统计")){ //劳动能力类型统计
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.LaoDongNengLiLeiXingTongJi_BingTu, rp, new MyRequestCallBack((BaseActivity)mActivity, MyConstans.SECOND));
		}else if(title.equals("贫困户属性统计")){ //贫困户属性统计
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.PinKunHuShuXingTongJi_BingTu, rp, new MyRequestCallBack((BaseActivity)mActivity, MyConstans.THIRD));
		}else if(title.equals("贫困村属性统计")){ //贫困村属性统计
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.PinKunCunShuXingTongJi_BingTu, rp, new MyRequestCallBack((BaseActivity)mActivity, MyConstans.FORTH));
		}else if(title.equals("务工情况统计")){ //务工情况统计
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
					URLs.WuGongQingKuangTongJi_BingTu, rp, new MyRequestCallBack((BaseActivity)mActivity, MyConstans.FIFTH));
		}
	}

	/**
	 * 增加查询条件
	 * @return
	 * 2016年7月2日
	 * ZhaoDongShao
	 */
	private RequestParams getRequestParams() {
		RequestParams rp = new RequestParams();
		String adl_cd = getAdl_Cd();
		if (!adl_cd.equals("")) {
			rp.addBodyParameter("adl_cd", adl_cd);
		}
//		System.out.println("请求六个一批饼图的网址为："+URLs.LiuGeYiPiTongJi_BingTu+"?adl_cd="+adl_cd);
		return rp;
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag) {
		case MyConstans.FIRST: //六个一批饼图
			System.out.println("请求六个一批饼图的数据成功：==="+str);
			LiuGeYiPiTongJi_ResultList caseListResult = LiuGeYiPiTongJi_ResultList.parseToT(str, LiuGeYiPiTongJi_ResultList.class);
			if (caseListResult != null && caseListResult.getSuccess()) {
				List<LiuGeYiPiTongJi> list = caseListResult.getJsondata();
				if (list != null) {
					generateData(list,null,1);
				}
			}
			break;

			case MyConstans.SECOND: //劳动能力类型统计饼图
				BasicPoorpeopleModelListResult result = BasicPoorpeopleModelListResult.parseToT(str, BasicPoorpeopleModelListResult.class);
				if (result != null && result.getSuccess()) {
					List<BasicPoorpeopleModel> list = result.getJsondata();
					if (list != null && list.size()>0) {
						generateData(null,list,2);
					}
				}
				break;

			case MyConstans.THIRD: //贫困户属性统计饼图
				BasicPoorpeopleModelListResult result3 = BasicPoorpeopleModelListResult.parseToT(str, BasicPoorpeopleModelListResult.class);
				if (result3 != null && result3.getSuccess()) {
					List<BasicPoorpeopleModel> list = result3.getJsondata();
					if (list != null && list.size()>0) {
						generateData(null,list,3);
					}
				}
				break;

			case MyConstans.FORTH: //贫困村属性统计饼图
				BasicPoorpeopleModelListResult result4 = BasicPoorpeopleModelListResult.parseToT(str, BasicPoorpeopleModelListResult.class);
				if (result4 != null && result4.getSuccess()) {
					List<BasicPoorpeopleModel> list = result4.getJsondata();
					if (list != null && list.size()>0) {
						generateData(null,list,4);
					}
				}
				break;

			case MyConstans.FIFTH: //务工情况统计
				System.out.println("请求务工情况统计饼图的数据成功：==="+str);
				BasicPoorpeopleModelListResult result5 = BasicPoorpeopleModelListResult.parseToT(str, BasicPoorpeopleModelListResult.class);
				if (result5 != null && result5.getSuccess()) {
					List<BasicPoorpeopleModel> list = result5.getJsondata();
					if (list != null && list.size()>0) {
						generateData(null,list,5);
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
		System.out.println("请求六个一批饼图的数据失败：===" + str);
	}

	/**
	 * 初始化饼状图
	 * 2016年6月27日
	 * ZhaoDongShao
	 * @param lEntities
	 */
	private void generateData(List<LiuGeYiPiTongJi> lEntities,List<BasicPoorpeopleModel> lEntities2,int type){

		List<SliceValue> values = new ArrayList<SliceValue>();
		if(type==1){ //1.六个一批统计饼图
			int numValues = lEntities.size();
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);

			int people_count = 0;
			for (int i = 0; i < lEntities.size(); i++) {
				people_count += Integer.parseInt(((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm());
			}

			System.out.println("总人数："+people_count);

			SliceValue sliceValue = null;
			for (int i = 0; i < numValues; i++) {
				if (Integer.parseInt(((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm()) <= 0) {
					continue;
				}
				if (((LiuGeYiPiTongJi) lEntities.get(i)).getPp_helpplan().equals("大病救治")) {
					String string = numberFormat.format((Float.parseFloat(((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.magenta));
					sliceValue.setLabel("大病救治" + ((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm() + "人("+string + "%)");
				}else if (((LiuGeYiPiTongJi) lEntities.get(i)).getPp_helpplan().equals("社会保障兜底")) {
					String string = numberFormat.format((Float.parseFloat(((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.cornflowerblue));
					sliceValue.setLabel("社会保障兜底" + ((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm() + "人("+string + "%)");
				}else if (((LiuGeYiPiTongJi) lEntities.get(i)).getPp_helpplan().equals("教育扶贫")) {
					String string = numberFormat.format((Float.parseFloat(((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm()) / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.peachpuff));
					sliceValue.setLabel("教育扶贫" + ((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm() + "人("+string + "%)");
				}else if (((LiuGeYiPiTongJi) lEntities.get(i)).getPp_helpplan().equals("产业发展和转移就业")) {
					String string = numberFormat.format((Float.parseFloat(((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.sandybrown));
					sliceValue.setLabel("产业发展和转移就业" + ((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm() + "人("+string + "%)");
				}else if (((LiuGeYiPiTongJi) lEntities.get(i)).getPp_helpplan().equals("异地扶贫搬迁")) {
					String string = numberFormat.format((Float.parseFloat(((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.greenyellow));
					sliceValue.setLabel("异地扶贫搬迁" + ((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm() + "人("+string + "%)");
				}else if (((LiuGeYiPiTongJi) lEntities.get(i)).getPp_helpplan().equals("生态补偿")) {
					String string = numberFormat.format((Float.parseFloat(((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.aquamarine));
					sliceValue.setLabel("生态补偿" + ((LiuGeYiPiTongJi) lEntities.get(i)).getHelpplannm() + "人("+string + "%)");
				}
				values.add(sliceValue);
			}
		}else if(type==2){ //2.劳动能力类型统计饼图
			int numValues = lEntities2.size();
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);

			int people_count = 0;
			for (int i = 0; i < lEntities2.size(); i++) {
				people_count += Integer.parseInt(lEntities2.get(i).getHealthcount());
			}

			System.out.println("总人数："+people_count);

			SliceValue sliceValue = null;
			for (int i = 0; i < numValues; i++) {
				if (Integer.parseInt(lEntities2.get(i).getHealthcount()) <= 0) {
					continue;
				}
				if (lEntities2.get(i).getHealthname().equals("普通劳动力")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.magenta));
					sliceValue.setLabel("普通劳动力" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}else if (lEntities2.get(i).getHealthname().equals("丧失劳动力")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.cornflowerblue));
					sliceValue.setLabel("丧失劳动力" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}else if (lEntities2.get(i).getHealthname().equals("无劳动力")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount()) / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.aquamarine));
					sliceValue.setLabel("无劳动力" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}else if (lEntities2.get(i).getHealthname().equals("技能劳动力")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.sandybrown));
					sliceValue.setLabel("技能劳动力" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}
				values.add(sliceValue);
			}
		}else if(type==3){ //3.贫困户属性统计饼图
			int numValues = lEntities2.size();
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);

			int people_count = 0;
			for (int i = 0; i < lEntities2.size(); i++) {
				people_count += Integer.parseInt(lEntities2.get(i).getHealthcount());
			}

			System.out.println("总人数："+people_count);

			SliceValue sliceValue = null;
			for (int i = 0; i < numValues; i++) {
				if (Integer.parseInt(lEntities2.get(i).getHealthcount()) <= 0) {
					continue;
				}
				if (lEntities2.get(i).getHealthname().equals("五保贫困户")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.magenta));
					sliceValue.setLabel("五保贫困户" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}else if (lEntities2.get(i).getHealthname().equals("一般农户")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.cornflowerblue));
					sliceValue.setLabel("一般农户" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}else if (lEntities2.get(i).getHealthname().equals("一般贫困户")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount()) / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.aquamarine));
					sliceValue.setLabel("一般贫困户" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}else if (lEntities2.get(i).getHealthname().equals("低保贫困户")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.sandybrown));
					sliceValue.setLabel("低保贫困户" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}
				values.add(sliceValue);
			}
		}else if(type==4){ //4.贫困村属性统计饼图
			int numValues = lEntities2.size();
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);

			int people_count = 0;
			for (int i = 0; i < lEntities2.size(); i++) {
				people_count += Integer.parseInt((lEntities2.get(i)).getHealthcount());
			}

			System.out.println("总人数："+people_count);

			SliceValue sliceValue = null;
			for (int i = 0; i < numValues; i++) {
				if (Integer.parseInt(( lEntities2.get(i)).getHealthcount()) <= 0) {
					continue;
				}
				if ((lEntities2.get(i)).getHealthname().equals("非贫困村")) {
					String string = numberFormat.format((Float.parseFloat(( lEntities2.get(i)).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.magenta));
					sliceValue.setLabel("非贫困村" + (lEntities2.get(i)).getHealthcount() + "("+string + "%)");
				}else if ((lEntities2.get(i)).getHealthname().equals("三到村三到户项目村")) {
					String string = numberFormat.format((Float.parseFloat((lEntities2.get(i)).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.cornflowerblue));
					sliceValue.setLabel("三到村三到户项目村" + (lEntities2.get(i)).getHealthcount() + "("+string + "%)");
				}else if (( lEntities2.get(i)).getHealthname().equals("贫困村")) {
					String string = numberFormat.format((Float.parseFloat((lEntities2.get(i)).getHealthcount()) / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.aquamarine));
					sliceValue.setLabel("贫困村" + (lEntities2.get(i)).getHealthcount() + "("+string + "%)");
				}else if ((lEntities2.get(i)).getHealthname().equals("经济薄弱村")) {
					String string = numberFormat.format((Float.parseFloat((lEntities2.get(i)).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.sandybrown));
					sliceValue.setLabel("经济薄弱村" + (lEntities2.get(i)).getHealthcount() + "("+string + "%)");
				}else if ((lEntities2.get(i)).getHealthname().equals("十二五贫困村")) {
					String string = numberFormat.format((Float.parseFloat((lEntities2.get(i)).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.greenyellow));
					sliceValue.setLabel("十二五贫困村" + (lEntities2.get(i)).getHealthcount() + "("+string + "%)");
				}else if ((lEntities2.get(i)).getHealthname().equals("十三五贫困村")) {
					String string = numberFormat.format((Float.parseFloat(( lEntities2.get(i)).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.peachpuff));
					sliceValue.setLabel("十三五贫困村" + (lEntities2.get(i)).getHealthcount() + "("+string + "%)");
				}
				values.add(sliceValue);
			}
		}else if(type==5){ //5.务工情况统计饼图
			int numValues = lEntities2.size();
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);

			int people_count = 0;
			for (int i = 0; i < lEntities2.size(); i++) {
				people_count += Integer.parseInt(lEntities2.get(i).getHealthcount());
			}

			System.out.println("务工情况统计总人数："+people_count);

			SliceValue sliceValue = null;
			for (int i = 0; i < numValues; i++) {
				if (Integer.parseInt(lEntities2.get(i).getHealthcount()) <= 0) {
					continue;
				}
				if (lEntities2.get(i).getHealthname().equals("旗县外(市内)")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.magenta));
					sliceValue.setLabel("旗县外(市内)" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}else if (lEntities2.get(i).getHealthname().equals("省外")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.cornflowerblue));
					sliceValue.setLabel("省外" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}else if (lEntities2.get(i).getHealthname().contains("市外(省内)")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount()) / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.aquamarine));
					sliceValue.setLabel("市外(省内)" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}else if (lEntities2.get(i).getHealthname().equals("旗县内")) {
					String string = numberFormat.format((Float.parseFloat(lEntities2.get(i).getHealthcount())  / people_count) * 100);
					sliceValue = new SliceValue();
					sliceValue.setValue(Float.parseFloat(string));
					sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.sandybrown));
					sliceValue.setLabel("旗县内" + lEntities2.get(i).getHealthcount() + "人("+string + "%)");
				}
				values.add(sliceValue);
			}
		}

		System.out.println("饼状图总数据："+values.size());

		data = new PieChartData(values);
		data.setHasLabels(true);
		data.setHasLabelsOnlyForSelected(false);
		data.setHasLabelsOutside(false);
		chart.setOnValueTouchListener(new ValueTouchListener());
		System.out.println("饼状图data总数据：" + data);
		chart.setPieChartData(data);
	}

	class ValueTouchListener implements PieChartOnValueSelectListener{
		@Override
		public void onValueDeselected() {
		}

		@Override
		public void onValueSelected(int arcIndex, SliceValue value) {
			String string_lableaschart = String.valueOf(value.getLabelAsChars());
			System.out.println("饼状图点击区域的名字："+string_lableaschart);
			Intent intent = null;
			if (string_lableaschart.contains("因学")) {
				intent = new Intent(mContext, PoorPeopleCaseYXActivity.class);
				intent.putExtra(Common.INTENT_KEY, string_lableaschart);
				startActivity(intent);
			}else if(string_lableaschart.contains("劳动力")){
				intent = new Intent(mContext, LaoDongNengLiLeiXingDetailActivity.class);
				intent.putExtra(Common.INTENT_KEY, string_lableaschart);
				startActivity(intent);
			}else if(string_lableaschart.contains("贫困户")
					||string_lableaschart.contains("农户")){
				intent = new Intent(mContext, PinKunHuShuXingDetailActivity.class);
				intent.putExtra(Common.INTENT_KEY, string_lableaschart);
				startActivity(intent);
			}else if(string_lableaschart.contains("村")){
				intent = new Intent(mContext, PinKunCunShuXingDetailActivity.class);
				intent.putExtra(Common.INTENT_KEY, string_lableaschart);
				startActivity(intent);
			}else if(string_lableaschart.contains("旗县外(市内)")
					||string_lableaschart.contains("省外")
					||string_lableaschart.contains("市外(省内)")
					||string_lableaschart.contains("旗县内")){
				intent = new Intent(mContext, WuGongQingKuangTongJiDetailActivity.class);
				intent.putExtra(Common.INTENT_KEY, string_lableaschart);
				startActivity(intent);
			}else{
				intent = new Intent(mContext, LiuGeYiPiTongJiDetailActivity.class);
				intent.putExtra(Common.INTENT_KEY, string_lableaschart);
				startActivity(intent);
			}
		}
	}

	/**
	 * 点击返回关闭页面
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
}
