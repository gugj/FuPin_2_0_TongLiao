package com.roch.fupin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.roch.fupin.dialog.NoPoorProject_weijiwei_FilterPopWindow;
import com.roch.fupin.dialog.NoPoorProject_weijiwei_FilterPopWindow.ShowMessageListener;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ProjectWeijiweiAppModel;
import com.roch.fupin.entity.ProjectWeijiweiAppModelListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin.utils.Utils;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 卫计委
 * @author ZhaoDongShao
 * 2016年6月2日
 */
@ContentView(R.layout.activity_poor_people_weijiwei)
public class NoPoorProjectWeiJiWeiActivity extends MainBaseActivity implements ShowMessageListener{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.tv_time)
	TextView tv_time;
	@ViewInject(R.id.tl)
	TableLayout layout;
	TableRow row;

	String[] weijiwei = {"乡镇","人数","报销金额(元)"};//卫计委

	NoPoorProject_weijiwei_FilterPopWindow filterPopWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(mActivity);

		Intent intent = getIntent();
		String title_name = intent.getStringExtra(Common.INTENT_KEY);
		if (title_name != null && !title_name.equals("")) {
			tv_title.setText(title_name);
		}
		initToolbar();
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
					filterPopWindow = new NoPoorProject_weijiwei_FilterPopWindow(mContext);
					filterPopWindow.setShowMessageListener(NoPoorProjectWeiJiWeiActivity.this);
					filterPopWindow.setAdapter(maps);
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
		inflater.inflate(R.menu.select_menu, menu);
		return true;
	}

	/**
	 * 2016年5月6日
	 * ZhaoDongShao
	 */
	private void initData() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		String time = year + "年" + month + "月份";
		tv_time.setText(time + "报销汇总统计");

		RequestParams rp = getRequestParams();
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.WEIJIWEI_STISTIC, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		System.out.println("卫计委请求服务器的地址为：==="+URLs.WEIJIWEI_STISTIC);
	}

	/**
	 * @return
	 * 2016年7月1日
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

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		ProjectWeijiweiAppModelListResult weijiweiItemAppModelListResult = null;
		switch (flag) {
		case MyConstans.FIRST:
			System.out.println("卫计委请求服务器的数据成功：==="+str);
			weijiweiItemAppModelListResult = ProjectWeijiweiAppModelListResult.parseToT(str, ProjectWeijiweiAppModelListResult.class);
			if (!StringUtil.isEmpty(weijiweiItemAppModelListResult)) {
				if (weijiweiItemAppModelListResult.getSuccess()) {
					List<ProjectWeijiweiAppModel> lPoorHouses = weijiweiItemAppModelListResult.getJsondata();
					if (!StringUtil.isEmpty(lPoorHouses)) {
						if (lPoorHouses.size()>0){
							generateData(lPoorHouses);
							showToast("数据加载完成");
						}else {
							showToast("暂无数据");
						}
					}else {
						showToast("当前没有更多数据");
					}
				} else {
					ShowNoticDialog();
				}
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 显示统计表
	 * @param lEntities
	 * 2016年8月20日
	 * ZhaoDongShao
	 */
	private void generateData(List<ProjectWeijiweiAppModel> lEntities) {
		layout.removeAllViews();
		for (int i = 0; i < lEntities.size() + 1; i++) {
			row = new TableRow(mContext);
			if (i == 0) {
				for (int j = 0; j < weijiwei.length; j++) {
					row.setGravity(Gravity.CENTER);
					row.setLayoutParams(getLayoutParams(1,1,1,0));
					row.addView(getLinearLayout(getTextView(weijiwei[j])));
				}
			}else if (i > 0 && i!=lEntities.size()) {
				for (int j = 0; j < weijiwei.length; j++) {
					switch (j) {
					case 0:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.addView(getLinearLayout(getTextView(lEntities.get(i - 1).ad_nm)));
						break;

					case 1:
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).pkrs))));
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.setGravity(Gravity.CENTER);
						break;

					case 2:
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).baoxiaojine))));
						row.setLayoutParams(getLayoutParams(1,1,1,0));
						row.setGravity(Gravity.CENTER);
						break;

					default:
						break;
					}
				}
			}else {
				for (int j = 0; j < weijiwei.length; j++) {
					switch (j) {
					case 0:
						row.setGravity(Gravity.CENTER);
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.addView(getLinearLayout(getTextView(lEntities.get(i - 1).ad_nm)));
						break;

					case 1:
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).pkrs))));
						row.setLayoutParams(getLayoutParams(1,1,1,1));
						row.setGravity(Gravity.CENTER);
						break;

					case 2:
						row.addView(getLinearLayout(getTextView(String.valueOf(lEntities.get(i - 1).baoxiaojine))));
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

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(PoorPeopleCaseDetailActivity.MP, dp);  
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
		TableLayout.LayoutParams lParams = new TableLayout.LayoutParams(
				PoorPeopleCaseDetailActivity.MP,PoorPeopleCaseDetailActivity.MP);
		lParams.setMargins(left, top, right, bottom);
		return lParams;
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		showToast(str);
		System.out.println("卫计委请求服务器的数据失败：===" + str);
	}


	List<MapEntity> maps = new ArrayList<MapEntity>();
	@Override
	public void Message(String time_start, String time_end) {
		RequestParams rp = new RequestParams();
		String times = "";
		maps.clear();
		if (!StringUtil.isEmpty(time_start)) {
			int year = Integer.parseInt(time_start.split("-")[0]);
			int months = Integer.parseInt(time_start.split("-")[1]);
			Calendar calendar = Calendar.getInstance();
			if (year > calendar.get(Calendar.YEAR)) {
				showToast("请选择正确的查询日期");
				return;
			}else if (year == calendar.get(Calendar.YEAR)) {
				if (months > calendar.get(Calendar.MONTH) + 1) {
					showToast("请选择正确的查询日期");
					return;
				}else {
					int start_year = Integer.parseInt(time_start.split("-")[0]);
					int start_months = Integer.parseInt(time_start.split("-")[1]);
					if (start_year > year) {
						showToast("开始日期不能在结束日期之后");
						return;
					}else {
						if (start_months > months) {
							showToast("开始日期不能在结束日期之后");
							return;
						}else {
							times += year + "年" + months + "月份";
							rp.addBodyParameter("startbaoxiao", time_start);
							maps.add(new MapEntity("startbaoxiao", time_start));
						}
					}
				}
			}else {
				int start_year = Integer.parseInt(time_start.split("-")[0]);
				int start_months = Integer.parseInt(time_start.split("-")[1]);
				if (start_year > year) {
					showToast("开始日期不能在结束日期之后");
					return;
				}else {
					if (start_months > months) {
						showToast("开始日期不能在结束日期之后");
						return;
					}else {
						times += year + "年" + months + "月份";
						rp.addBodyParameter("startbaoxiao", time_start);
						maps.add(new MapEntity("startbaoxiao", time_start));
					}
				}
			}
		}else {
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			String a = year + "年" + month + "月份";
			times += a;
		}
		if (!StringUtil.isEmpty(time_end)) {
			int year = Integer.parseInt(time_end.split("-")[0]);
			int months = Integer.parseInt(time_end.split("-")[1]);

			Calendar calendar = Calendar.getInstance();
			if (year > calendar.get(Calendar.YEAR)) {
				showToast("请选择正确的查询日期");
				return;
			}else if (year <= calendar.get(Calendar.YEAR)) {
				if (months > calendar.get(Calendar.MONTH) + 1) {
					showToast("请选择正确的查询日期");
					return;
				}else {
					times += "~" + year + "年" + months + "月份";
					rp.addBodyParameter("endbaoxiao", time_end);
					maps.add(new MapEntity("endbaoxiao", time_end));
				}
			}
		}else {
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;

			String a = year + "年" + month+ "月份";
			times += "~" + a;
		}
		tv_time.setText(times + "报销汇总统计");
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.WEIJIWEI_STISTIC, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));

		filterPopWindow.dismiss();
	}

}
