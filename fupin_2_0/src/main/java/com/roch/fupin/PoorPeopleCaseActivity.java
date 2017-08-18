package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.PoorPeopleCaseListResult;
import com.roch.fupin.entity.PoorPeopleCause;
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
 * 主要致贫原因统计
 * @author ZhaoDongShao
 * 2016年6月27日
 */
@ContentView(R.layout.activity_poor_people_case)
public class PoorPeopleCaseActivity extends MainBaseActivity{

	public static final String TAG = "PoorPeopleCaseActivity";
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
		ViewUtils.inject(mActivity);

		initToolbar();
		MyApplication.getInstance().addActivity(mActivity);
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
	 * 初始化数据
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
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST, 
				URLs.ZhuYao_POOR_PEOPLE_CASE, rp, new MyRequestCallBack((BaseActivity)mActivity, MyConstans.FIRST));
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
			System.out.println("请求主要致贫原因统计饼图的网址为：" + URLs.ZhuYao_POOR_PEOPLE_CASE+"?adl_cd="+adl_cd);
		}
		return rp;
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag) {
		case MyConstans.FIRST:
			System.out.println("请求致贫原因统计饼图的数据成功：==="+str);
			PoorPeopleCaseListResult caseListResult = PoorPeopleCaseListResult.parseToT(str, PoorPeopleCaseListResult.class);
			if (caseListResult != null && caseListResult.getSuccess()) {
				List<PoorPeopleCause> list = caseListResult.getJsondata();
				if (list != null) {
					generateData(list);
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
		System.out.println("请求致贫原因统计饼图的数据失败：===" + str);
	}

	/**
	 * 初始化柱状图
	 * 2016年6月27日
	 * ZhaoDongShao
	 * @param lEntities
	 */
	private void generateData(List<PoorPeopleCause> lEntities){
		int numValues = lEntities.size();
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);

		int people_count = 0;
		for (int i = 0; i < lEntities.size(); i++) {
			people_count += Integer.parseInt(lEntities.get(i).getPp_poorreason_pc());
		}

		List<SliceValue> values = new ArrayList<SliceValue>();
		SliceValue sliceValue = null;
		for (int i = 0; i < numValues; ++i) {
			if (Integer.parseInt(lEntities.get(i).getPp_poorreason_pc()) <= 0) {
				continue;
			}
			if (lEntities.get(i).getPp_poorreason().equals("因病")) { // 1
				String string = numberFormat.format((Float.parseFloat(lEntities.get(i).getPp_poorreason_pc()) / people_count) * 100);
				sliceValue = new SliceValue();
				sliceValue.setValue(Float.parseFloat(string));
				sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.magenta));
				sliceValue.setLabel("因病" + lEntities.get(i).getPp_poorreason_pc() + "人("+string + "%)");
			}else if (lEntities.get(i).getPp_poorreason().equals("因残")) {// 2
				String string = numberFormat.format((Float.parseFloat(lEntities.get(i).getPp_poorreason_pc()) / people_count) * 100);
				sliceValue = new SliceValue();
				sliceValue.setValue(Float.parseFloat(string));
				sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.cornflowerblue));
				sliceValue.setLabel("因残" + lEntities.get(i).getPp_poorreason_pc() + "人("+string + "%)");
			}else if (lEntities.get(i).getPp_poorreason().equals("因灾")) { // 3
				String string = numberFormat.format((Float.parseFloat(lEntities.get(i).getPp_poorreason_pc()) / people_count) * 100);
				sliceValue = new SliceValue();
				sliceValue.setValue(Float.parseFloat(string));
				sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.peachpuff));
				sliceValue.setLabel("因灾" + lEntities.get(i).getPp_poorreason_pc() + "人("+string + "%)");
			}else if (lEntities.get(i).getPp_poorreason().equals("缺土地")) { // 4
				String string = numberFormat.format((Float.parseFloat(lEntities.get(i).getPp_poorreason_pc()) / people_count) * 100);
				sliceValue = new SliceValue();
				sliceValue.setValue(Float.parseFloat(string));
				sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.sandybrown));
				sliceValue.setLabel("缺土地" + lEntities.get(i).getPp_poorreason_pc() + "人("+string + "%)");
			}else if (lEntities.get(i).getPp_poorreason().equals("缺水")) { // 5
				String string = numberFormat.format((Float.parseFloat(lEntities.get(i).getPp_poorreason_pc()) / people_count) * 100);
				sliceValue = new SliceValue();
				sliceValue.setValue(Float.parseFloat(string));
				sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.greenyellow));
				sliceValue.setLabel("缺水" + lEntities.get(i).getPp_poorreason_pc() + "人("+string + "%)");
			}else if (lEntities.get(i).getPp_poorreason().equals("因学")) { // 6
				String string = numberFormat.format((Float.parseFloat(lEntities.get(i).getPp_poorreason_pc()) / people_count) * 100);
				sliceValue = new SliceValue();
				sliceValue.setValue(Float.parseFloat(string));
				sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.aquamarine));
				sliceValue.setLabel("因学" + lEntities.get(i).getPp_poorreason_pc() + "人("+string + "%)");
			}else if (lEntities.get(i).getPp_poorreason().equals("缺技术")) { // 7
				String string = numberFormat.format((Float.parseFloat(lEntities.get(i).getPp_poorreason_pc()) / people_count) * 100);
				sliceValue = new SliceValue();
				sliceValue.setValue(Float.parseFloat(string));
				sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.bule_01b8ec));
				sliceValue.setLabel("缺技术" + lEntities.get(i).getPp_poorreason_pc() + "人("+string + "%)");
			}else if (lEntities.get(i).getPp_poorreason().equals("缺劳动力")) { // 8
				String string = numberFormat.format((Float.parseFloat(lEntities.get(i).getPp_poorreason_pc()) / people_count) * 100);
				sliceValue = new SliceValue();
				sliceValue.setValue(Float.parseFloat(string));
				sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.color_0093f0));
				sliceValue.setLabel("缺劳动力" + lEntities.get(i).getPp_poorreason_pc() + "人("+string + "%)");
			}else if (lEntities.get(i).getPp_poorreason().equals("缺资金")) { // 9
				String string = numberFormat.format((Float.parseFloat(lEntities.get(i).getPp_poorreason_pc()) / people_count) * 100);
				sliceValue = new SliceValue();
				sliceValue.setValue(Float.parseFloat(string));
				sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.black));
				sliceValue.setLabel("缺资金" + lEntities.get(i).getPp_poorreason_pc() + "人("+string + "%)");
			}else if (lEntities.get(i).getPp_poorreason().equals("交通条件落后")) { // 10
				String string = numberFormat.format((Float.parseFloat(lEntities.get(i).getPp_poorreason_pc()) / people_count) * 100);
				sliceValue = new SliceValue();
				sliceValue.setValue(Float.parseFloat(string));
				sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.jiaotongtiaojian));
				sliceValue.setLabel("交通条件落后" + lEntities.get(i).getPp_poorreason_pc() + "人("+string + "%)");
			}else if (lEntities.get(i).getPp_poorreason().equals("自身发展动力不足")) { // 11
				String string = numberFormat.format((Float.parseFloat(lEntities.get(i).getPp_poorreason_pc()) / people_count) * 100);
				sliceValue = new SliceValue();
				sliceValue.setValue(Float.parseFloat(string));
				sliceValue.setColor(ResourceUtil.getInstance().getColorById(R.color.zishenfazhanli));
				sliceValue.setLabel("自身发展动力不足" + lEntities.get(i).getPp_poorreason_pc() + "人("+string + "%)");
			}
			values.add(sliceValue);
		}
		data = new PieChartData(values);
		data.setHasLabels(true);
		data.setHasLabelsOnlyForSelected(false);
		data.setHasLabelsOutside(false);
		chart.setOnValueTouchListener(new ValueTouchListener());
		chart.setPieChartData(data);
	}

	class ValueTouchListener implements PieChartOnValueSelectListener{
		@Override
		public void onValueDeselected() {
		}

		@Override
		public void onValueSelected(int arcIndex, SliceValue value) {
			Log.i(TAG, "*********************");
			String string_lableaschart = String.valueOf(value.getLabelAsChars());
			Intent intent = null;
			//因学和因病致贫详情单独放一个Activity中
			if (string_lableaschart.contains("因学") || string_lableaschart.contains("因病")) {
				intent = new Intent(mContext, PoorPeopleCaseYXActivity.class);
				intent.putExtra(Common.INTENT_KEY, string_lableaschart);
				startActivity(intent);
			}else { //其他9个主要致贫原因放一个Activity中
				intent = new Intent(mContext, PoorPeopleCaseDetailActivity.class);
				intent.putExtra(Common.INTENT_KEY, string_lableaschart);
				startActivity(intent);
			}
		}
	}
}
