package com.roch.fupin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.KaoQinXiangQing;
import com.roch.fupin.entity.KaoQinXinXi;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.Utils;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 考勤信息详情
 * @author ZhaoDongShao
 * 2016年6月25日 
 */
@ContentView(R.layout.activity_poor_people_statistics)
public class KaoQinXinXiDetailActivity extends MainBaseActivity {

	public final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
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
	 * 贫困人口统计的表格tablayout的标题--表头
	 */
	String[] column = {"月\\日","6日","13日","20日","27日"};

	private String sixth;
	private String thirteenth;
	private String twenty;
	private String twenty_seven;
	private KaoQinXiangQing kaoQinXiangQing;
	private List<KaoQinXiangQing> kaoQinXiangQings=new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(mActivity);
		initToolbar();
		MyApplication.getInstance().addActivity(mActivity);

		//初始化考勤信息表的数据集合
		initTableData();
		//生成考勤信息表格
		getKaoQin(column,kaoQinXiangQings);
//		initData();
	}

	/**
	 * 初始化考勤信息表的数据集合
	 */
	private void initTableData() {
		Intent intent = getIntent();
		KaoQinXinXi kaoQinXinXi= (KaoQinXinXi) intent.getSerializableExtra("kaoqin");

		//一月份
		sixth = kaoQinXinXi.getJanuary_sixth();
		thirteenth = kaoQinXinXi.getJanuary_thirteenth();
		twenty = kaoQinXinXi.getJanuary_twenty();
		twenty_seven = kaoQinXinXi.getJanuary_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("1月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);

		//二月份
		sixth = kaoQinXinXi.getFebruary_sixth();
		thirteenth = kaoQinXinXi.getFebruary_thirteenth();
		twenty = kaoQinXinXi.getFebruary_twenty();
		twenty_seven = kaoQinXinXi.getFebruary_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("2月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);

		//三月份
		sixth = kaoQinXinXi.getMarch_sixth();
		thirteenth = kaoQinXinXi.getMarch_thirteenth();
		twenty = kaoQinXinXi.getMarch_twenty();
		twenty_seven = kaoQinXinXi.getMarch_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("3月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);

		//四月份
		sixth = kaoQinXinXi.getApril_sixth();
		thirteenth = kaoQinXinXi.getApril_thirteenth();
		twenty = kaoQinXinXi.getApril_twenty();
		twenty_seven = kaoQinXinXi.getApril_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("4月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);

		//五月份
		sixth = kaoQinXinXi.getMay_sixth();
		thirteenth = kaoQinXinXi.getMarch_thirteenth();
		twenty = kaoQinXinXi.getMay_twenty();
		twenty_seven = kaoQinXinXi.getMay_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("5月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);

		//六月份
		sixth = kaoQinXinXi.getJune_sixth();
		thirteenth = kaoQinXinXi.getJune_thirteenth();
		twenty = kaoQinXinXi.getJune_twenty();
		twenty_seven = kaoQinXinXi.getJune_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("6月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);

		//七月份
		sixth = kaoQinXinXi.getJuly_sixth();
		thirteenth = kaoQinXinXi.getJuly_thirteenth();
		twenty = kaoQinXinXi.getJune_twenty();
		twenty_seven = kaoQinXinXi.getJuly_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("7月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);

		//八月份
		sixth = kaoQinXinXi.getAguest_sixth();
		thirteenth = kaoQinXinXi.getAguest_thirteenth();
		twenty = kaoQinXinXi.getAguest_twenty();
		twenty_seven = kaoQinXinXi.getAguest_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("8月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);

		//九月份
		sixth = kaoQinXinXi.getSeptember_sixth();
		thirteenth = kaoQinXinXi.getSeptember_thirteenth();
		twenty = kaoQinXinXi.getSeptember_twenty();
		twenty_seven = kaoQinXinXi.getSeptember_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("9月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);

		//十月份
		sixth = kaoQinXinXi.getOctober_sixth();
		thirteenth = kaoQinXinXi.getOctober_thirteenth();
		twenty = kaoQinXinXi.getOctober_twenty();
		twenty_seven = kaoQinXinXi.getOctober_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("10月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);

		//十一月份
		sixth = kaoQinXinXi.getNovember_sixth();
		thirteenth = kaoQinXinXi.getNovember_thirteenth();
		twenty = kaoQinXinXi.getNovember_twenty();
		twenty_seven = kaoQinXinXi.getNovember_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("11月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);

		//十二月份
		sixth = kaoQinXinXi.getDecember_sixth();
		thirteenth = kaoQinXinXi.getDecember_thirteenth();
		twenty = kaoQinXinXi.getDecember_twenty();
		twenty_seven = kaoQinXinXi.getDecember_twenty_seven();
		kaoQinXiangQing = new KaoQinXiangQing("12月",sixth, thirteenth, twenty, twenty_seven);
		kaoQinXiangQings.add(kaoQinXiangQing);
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

		tv_title.setText("考勤详情");
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
	 * 生成考勤信息表格
	 * @param lEntities
	 * 2016年8月9日
	 * ZhaoDongShao
	 * @param lEntities
	 */
	@SuppressLint("InflateParams")
	private void getKaoQin(String[] kaoqin, List<KaoQinXiangQing> lEntities) {
		for (int i = 0; i < lEntities.size() + 1; i++) { //总共多少行
			row = new TableRow(mContext);
			if (i == 0) { //第一行表头--标题
				for (int j = 0; j < kaoqin.length; j++) {
					row.setGravity(Gravity.CENTER);
					row.setLayoutParams(getLayoutParams(1,1,1,0));
					row.addView(getLinearLayout(getTextView(kaoqin[j])));
				}
			}else if (i > 0 && i != lEntities.size()) { //第二行至倒数第二行
				for (int j = 0; j < kaoqin.length; j++) { //总共有多少列
					switch (j) {
						case 0: //第一列
							row.setGravity(Gravity.CENTER);
							row.setLayoutParams(getLayoutParams(1,1,1,0));
							row.addView(getLinearLayout(getTextView(lEntities.get(i - 1).getName())));
							break;

						case 1: //第二列
							row.setGravity(Gravity.CENTER);
							row.setLayoutParams(getLayoutParams(1,1,1,0));
							row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getFirst()))));
							break;

						case 2: //第三列
							row.setGravity(Gravity.CENTER);
							row.setLayoutParams(getLayoutParams(1,1,1,0));
							row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getSecond()))));
							break;

						case 3: //第四列
							row.setGravity(Gravity.CENTER);
							row.setLayoutParams(getLayoutParams(1,1,1,0));
							row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getThird()))));
							break;

						case 4://第五列
							row.setGravity(Gravity.CENTER);
							row.setLayoutParams(getLayoutParams(1,1,1,0));
							row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getForth()))));
							break;

						default:
							break;
					}
				}
			}else { //最后一行
				for (int j = 0; j < kaoqin.length; j++) {
					switch (j) {
						case 0:
							row.setGravity(Gravity.CENTER);
							row.setLayoutParams(getLayoutParams(1,1,1,1));
							row.addView(getLinearLayout(getTextView(lEntities.get(i - 1).getName())));
							break;

						case 1:
							row.setGravity(Gravity.CENTER);
							row.setLayoutParams(getLayoutParams(1,1,1,1));
							row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getFirst()))));
							break;

						case 2:
							row.setGravity(Gravity.CENTER);
							row.setLayoutParams(getLayoutParams(1,1,1,1));
							row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getSecond()))));
							break;

						case 3:
							row.setGravity(Gravity.CENTER);
							row.setLayoutParams(getLayoutParams(1,1,1,1));
							row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getThird()))));
							break;

						case 4:
							row.setGravity(Gravity.CENTER);
							row.setLayoutParams(getLayoutParams(1,1,1,1));
							row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).getForth()))));
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
		if(null != name){
			if(name.equals("0")){
				tView.setText("X");
				tView.setTextColor(Color.RED);
			}else if(name.equals("1")){
				tView.setText("√");
				tView.setTextColor(Color.GREEN);
			}else {
				tView.setText(name);
			}
		}
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
	private ViewGroup.LayoutParams getLayoutParams(int left, int top, int right, int bottom){
		TableLayout.LayoutParams lParams = new TableLayout.LayoutParams(MP,MP);
		lParams.setMargins(left, top, right, bottom);
		return lParams;
	}

	/**
	 * 初始化数据
	 * 2016年6月25日
	 * ZhaoDongShao
	 */
	private void initData() {
		Intent intent = getIntent();
		KaoQinXinXi kaoQinXinXi= (KaoQinXinXi) intent.getSerializableExtra("kaoqin");

		if (kaoQinXinXi != null) {
			for (int i = 0; i < 3; i++) { //共3条横线---2个行格
				//先画一条横线，添加到TableRow中
				LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View view = inflater.inflate(R.layout.tab_horizontal_line, null);
				layout.addView(view);
				row = new TableRow(mContext);
				if (i == 0) { //第1条横线--往下画
					for (int j = 0; j < column.length; j++) { //4列数据---日期
						LayoutInflater inflater1 = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						View view1 = inflater1.inflate(R.layout.tab_vertical_line, null);
						row.addView(view1);
						TextView tView = new TextView(mContext);
						tView.setTextSize(Common.TEXT_SIZE);
						tView.setText(column[j]);
						tView.setGravity(Gravity.CENTER);
						row.setGravity(Gravity.CENTER);
						row.addView(tView);
						if (j == column.length - 1) { //最后一列数据--日期
							LayoutInflater inflater2 = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
							View view2 = inflater2.inflate(R.layout.tab_vertical_line, null);
							row.addView(view2);
						}
					}
				} else if (i > 0) { //第2条横线--往下画
					for (int j = 0; j < column.length; j++) {
						TextView tView = null;
						LayoutInflater inflater1 = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						View view1 = inflater1.inflate(R.layout.tab_vertical_line, null);
						row.addView(view1);
						switch (j) {
							case 0:
								tView = new TextView(mContext);
								tView.setTextSize(Common.TEXT_SIZE);
								tView.setText(kaoQinXinXi.getApril_sixth());
								tView.setGravity(Gravity.CENTER);
								row.setGravity(Gravity.CENTER);
								row.addView(tView);
								break;

							case 1:
								tView = new TextView(mContext);
								tView.setTextSize(Common.TEXT_SIZE);
								tView.setText(String.valueOf(kaoQinXinXi.getAguest_twenty()));
								tView.setGravity(Gravity.CENTER);
								row.addView(tView);
								row.setGravity(Gravity.CENTER);
								break;

							case 2:
								tView = new TextView(mContext);
								tView.setTextSize(Common.TEXT_SIZE);
								tView.setText(String.valueOf(kaoQinXinXi.getApril_sixth()));
								tView.setGravity(Gravity.CENTER);
								row.addView(tView);
								row.setGravity(Gravity.CENTER);
								break;

							case 3:
								tView = new TextView(mContext);
								tView.setTextSize(Common.TEXT_SIZE);
								tView.setText(String.valueOf(kaoQinXinXi.getApril_twenty()));
								tView.setGravity(Gravity.CENTER);
								row.addView(tView);
								row.setGravity(Gravity.CENTER);

								LayoutInflater inflater2 = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
								View view2 = inflater2.inflate(R.layout.tab_vertical_line, null);
								row.addView(view2);
								break;

							default:
								break;
						}
					}
				}
				if (i == 2) { //第3条横线
					LayoutInflater inflater0 = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View view0 = inflater0.inflate(R.layout.tab_horizontal_line, null);
					layout.addView(view0);
					row = new TableRow(mContext);
				}
				layout.addView(row);
			}
		}
	}
}
