package com.roch.fupin;

import java.util.List;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.PoorcityLedgerModel;
import com.roch.fupin.entity.PoorcityLedgerModelListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 贫困人口统计
 * @author ZhaoDongShao
 * 2016年6月25日 
 */
@ContentView(R.layout.activity_poor_people_statistics)
public class PoorPeopleStatisticsActivity extends MainBaseActivity{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	
	/**
	 * 贫困人口统计的表格tablayout
	 */
	@ViewInject(R.id.ll)
	TableLayout layout;
	TableRow row;
	/**
	 * 贫困人口统计的表格tablayout的标题
	 */
	String[] column = {"行政区","总人口数","贫困户数","贫困人数","贫困比例"};
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
				URLs.POOR_PEOPLE_STATISTIC, rp, new MyRequestCallBack((BaseActivity)mActivity, MyConstans.FIRST));
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
		return rp;
	}


	@SuppressLint("InflateParams")
	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag) {
		case MyConstans.FIRST:
			PoorcityLedgerModelListResult modelListResult = PoorcityLedgerModelListResult.parseToT(str, PoorcityLedgerModelListResult.class);
			if (modelListResult != null && modelListResult.getSuccess()) {

				List<PoorcityLedgerModel> models = modelListResult.getJsondata();

				List<PoorcityLedgerModel> ledgerModels = getPoorcityLedgerModels(models);
				
				if (ledgerModels != null) {
					for (int i = 0; i < ledgerModels.size(); i++) {
						LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						View view = inflater.inflate(R.layout.tab_horizontal_line, null);
						layout.addView(view);
						row = new TableRow(mContext);
						
						if (i==0) {
							for (int j = 0; j < column.length; j++) {
								LayoutInflater inflater1 = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
								View view1 = inflater1.inflate(R.layout.tab_vertical_line, null);
								row.addView(view1);
								TextView tView = new TextView(mContext);
								tView.setTextSize(Common.TEXT_SIZE);
								tView.setText(column[j]);
								tView.setGravity(Gravity.CENTER);
								row.setGravity(Gravity.CENTER);
								row.addView(tView);
								if (j == column.length - 1) {
									LayoutInflater inflater2 = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
									View view2 = inflater2.inflate(R.layout.tab_vertical_line, null);
									row.addView(view2);
								}
							}
						}else if (i>0) {
							for (int j = 0; j < column.length; j++) {
								TextView tView = null;
								LayoutInflater inflater1 = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
								View view1 = inflater1.inflate(R.layout.tab_vertical_line, null);
								switch (j) {
								case 0:
									row.addView(view1);
									tView = new TextView(mContext);
									tView.setTextSize(Common.TEXT_SIZE);
									tView.setText(ledgerModels.get(i - 1).getAdl_nm());
									tView.setGravity(Gravity.CENTER);
									row.setGravity(Gravity.CENTER);
									row.addView(tView);
									break;
									
								case 1:
									row.addView(view1);
									tView = new TextView(mContext);
									tView.setTextSize(Common.TEXT_SIZE);
									tView.setText(String.valueOf(ledgerModels.get(i - 1).getPopulationnumber()));
									tView.setGravity(Gravity.CENTER);
									row.addView(tView);
									row.setGravity(Gravity.CENTER);
									break;
									
								case 2:
									row.addView(view1);
									tView = new TextView(mContext);
									tView.setTextSize(Common.TEXT_SIZE);
									tView.setText(String.valueOf(ledgerModels.get(i - 1).getPoorhousenm()));
									tView.setGravity(Gravity.CENTER);
									row.addView(tView);
									row.setGravity(Gravity.CENTER);
									break;
									
								case 3:
									row.addView(view1);
									tView = new TextView(mContext);
									tView.setTextSize(Common.TEXT_SIZE);
									tView.setText(String.valueOf(ledgerModels.get(i - 1).getPoorpeoplenm()));
									tView.setGravity(Gravity.CENTER);
									row.addView(tView);
									row.setGravity(Gravity.CENTER);
									break;
									
								case 4:
									row.addView(view1);
									tView = new TextView(mContext);
									tView.setTextSize(Common.TEXT_SIZE);
//									double a = (float)ledgerModels.get(i - 1).getPoorpeoplenm() / ((float)ledgerModels.get(i).getPopulationnumber() == 0 ? 1.00 : (float)ledgerModels.get(i).getPopulationnumber()) * 100;
//									BigDecimal bd = new BigDecimal(a);
//									bd = bd.setScale(2,RoundingMode.HALF_UP);
									
									// ****************************************贫困人口统计百分比*****************************************************
									tView.setText(ledgerModels.get(i - 1).getPoorpercent()+"%");
//									tView.setText(bd.toString() + "%");
									tView.setGravity(Gravity.CENTER);
									row.addView(tView);
									row.setGravity(Gravity.CENTER);	
									LayoutInflater inflater2 = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
									View view2 = inflater2.inflate(R.layout.tab_vertical_line, null);
									row.addView(view2);
									break;
									
								default:
									break;
								}
							}
						}
						layout.addView(row);

						if (i == ledgerModels.size() - 1) {
							LayoutInflater inflater0 = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
							View view0 = inflater0.inflate(R.layout.tab_horizontal_line, null);
							layout.addView(view0);
							row = new TableRow(mContext);
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
	 * @param models
	 * @return
	 * 2016年8月4日
	 * ZhaoDongShao
	 */
	private List<PoorcityLedgerModel> getPoorcityLedgerModels(List<PoorcityLedgerModel> models) {
		
		int populationnumber = 0;
		int poorhousenm = 0;
		int poorpeoplenm = 0;
		
		for (int i = 0; i < models.size(); i++) {
			populationnumber += models.get(i).getPopulationnumber();
			poorhousenm += models.get(i).getPoorhousenm();
			poorpeoplenm += models.get(i).getPoorpeoplenm();
		}
		
		PoorcityLedgerModel model = new PoorcityLedgerModel();
		model.setAdl_nm("合计");
		model.setPopulationnumber(populationnumber);
		model.setPoorhousenm(poorhousenm);
		model.setPoorpeoplenm(poorpeoplenm);
		models.add(model);
		return models;
	}
}
