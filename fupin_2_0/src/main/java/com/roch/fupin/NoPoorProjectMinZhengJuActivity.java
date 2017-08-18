/**
 * 
 */
package com.roch.fupin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.time.JudgeDate;
import com.roch.time.ScreenInfo;
import com.roch.time.WheelMain;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.ProjectMinzhengAppModel;
import com.roch.fupin.entity.ProjectMinzhengAppModelListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin.view.MinZhengJuTableScrollView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * 民政局
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月2日 
 *
 */
@ContentView(R.layout.activity_poor_people_minzhengju_statistic)
public class NoPoorProjectMinZhengJuActivity extends MainBaseActivity{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.tv_time)
	TextView tv_time;

	private View timeView;
	private WheelMain wheelMain; // TimePicker
	private ScreenInfo screenInfo;
	private String srBirthDay = "";
	private SimpleDateFormat dateFormat;
	private TextView timeCancle;
	private TextView timeSure;
	private PopupWindow timePop;// 时间选择器popup

	private ListView mListView;
	protected List<MinZhengJuTableScrollView> mHScrollViews =new ArrayList<MinZhengJuTableScrollView>();

	public MinZhengJuTableScrollView mTouchView;

	//创建数组保存表头
	private String[] cols = {"title","低保户数","低保人数","低保金额(元)","五保户数","五保人数","五保金额(元)","兜底户数","兜底人数","兜底金额(元)"};

	private  ScrollAdapter mAdapter;

	String time = ""; //显示时间

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		MyApplication.getInstance().addActivity(this);

		Intent intent = getIntent();
		String title_name = intent.getStringExtra(Common.INTENT_KEY);
		if (title_name != null && !title_name.equals("")) {
			tv_title.setText(title_name);
		}

		initToolbar();
		initData();

	}


	/**
	 *
	 *
	 * 2016年8月5日
	 *
	 * ZhaoDongShao
	 *
	 */
	private void initToolbar() {
		// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub

				switch (menuItem.getItemId()) {
				case R.id.select:

					//初始化时间选择器
					initTimePicker(timeView);
					initTimePop();

					break;

				default:
					break;
				}

				return false;
			}
		});
	}

	/**
	 * 选择日期
	 */
	private void initTimePop() {
		timePop.setFocusable(true);
		timePop.setOutsideTouchable(false);
		timePop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		timePop.setAnimationStyle(R.style.PopupWindowTimerAnimation);
		timePop.showAtLocation(timeView,Gravity.BOTTOM, 0, LayoutParams.WRAP_CONTENT);
		timeCancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != timePop && timePop.isShowing()) {
					timePop.setFocusable(false);
					timePop.dismiss();
				}
			}
		});
		timeSure.setOnClickListener(new OnClickListener() {// 要求到货时间
			@Override
			public void onClick(View v) {

				String times = wheelMain.getYearAndQuarter();
				if (!StringUtil.isEmpty(times)) {
					String month = times.split("-")[1];
					
					int months = Integer.parseInt(month) + 1;
					int year = Integer.parseInt(times.split("-")[0]);
					
					time = year + "-" + months;
					
					Calendar calendar = Calendar.getInstance();
					if (year > calendar.get(Calendar.YEAR)) {
						showToast("请选择正确的查询日期");
						return;
					}else if (year <= calendar.get(Calendar.YEAR)) {
						
						if (months > calendar.get(Calendar.MONTH)) {
							
							showToast("请选择正确的查询日期");
							return;
							
						}else {
							
							getData(time);
							
						}
						
					}
					
					
					
				}
				
				//				tv_time.setText(wheelMain.getTime());
				//				srBirthDay = wheelMain.getTime();
				timePop.setFocusable(false);
				timePop.dismiss();
			}
		});
	}

	/**
	 * 初始化时间选择器
	 * @param v
	 */
	private void initTimePicker(View v) {
		// TODO Auto-generated method stub
		wheelMain = new WheelMain(v, false, false, true);
		int height = screenInfo.getHeight();
		wheelMain.screenheight = height;
		Calendar calendar = Calendar.getInstance();
		WheelMain.setSTART_YEAR(calendar.get(Calendar.YEAR)-60);
		WheelMain.setEND_YEAR(calendar.get(Calendar.YEAR));
		if (JudgeDate.isDate(srBirthDay, "yyyy-MM-dd")) {
			try {
				calendar.setTime(dateFormat.parse(srBirthDay));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		wheelMain.initBirthdayPicker(year,month,day);
	}




	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		menu.findItem(R.id.select).setTitle(time);	
		return super.onPrepareOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.select_menu, menu);
		return true;
	}

	/**
	 *
	 *
	 * 2016年5月6日
	 *
	 * ZhaoDongShao
	 *
	 */
	@SuppressLint("InflateParams")
	private void initData() {


		dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		screenInfo = new ScreenInfo((Activity)mContext);
		timeView = LayoutInflater.from(mContext).inflate(R.layout.item_odertime, null);
		timeCancle = (TextView) timeView.findViewById(R.id.time_cancle);
		timeSure = (TextView) timeView.findViewById(R.id.time_sure);
		timePop = new PopupWindow(timeView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		time = year + "-" + month;
		
		tv_time.setText(year + "年" + month + "月份兜底汇总统计");
		//		url = OpenActivityUtil.getUrlString(title_name);

		RequestParams rp = getRequestParams();
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.MINZHENGJU_STISTIC, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));

	}


	public void addHViews(final MinZhengJuTableScrollView hScrollView) {
		if(!mHScrollViews.isEmpty()) {
			int size = mHScrollViews.size();
			MinZhengJuTableScrollView scrollView = mHScrollViews.get(size - 1);
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

		for (MinZhengJuTableScrollView tableScrollView : mHScrollViews) {

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
				addHViews((MinZhengJuTableScrollView) v.findViewById(R.id.item_chscroll_scroll));
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
				//				
				TextView textView = (TextView)holders[i];
				//				
				if (name != null) {
					textView.setText(name);
				}else {
					textView.setText("");
				}

			}

			return v;
		}

	}

	/**
	 *
	 * @return
	 *
	 * 2016年7月1日
	 *
	 * ZhaoDongShao
	 *
	 */
	private RequestParams getRequestParams() {
		// TODO Auto-generated method stub
		RequestParams rp = new RequestParams();
		String adl_cd = getAdl_Cd();
		if (!adl_cd.equals("")) {
			rp.addBodyParameter("adl_cd", adl_cd);
		}
		return rp;
	}



	@Override
	public void onSuccessResult(String str, int flag) {
		// TODO Auto-generated method stub
		super.onSuccessResult(str, flag);

		ProjectMinzhengAppModelListResult minzhengAppModelListResult = null;

		switch (flag) {
		case MyConstans.FIRST:

			minzhengAppModelListResult = ProjectMinzhengAppModelListResult.parseToT(str, ProjectMinzhengAppModelListResult.class);
			if (!StringUtil.isEmpty(minzhengAppModelListResult)) {
				if (minzhengAppModelListResult.getSuccess()) {
					List<ProjectMinzhengAppModel> list = minzhengAppModelListResult.getJsondata();

					if (!StringUtil.isEmpty(list)) {
						List<Map<String, String>> datas = new ArrayList<Map<String,String>>();
						Map<String, String> data = null;
						MinZhengJuTableScrollView headerScroll = (MinZhengJuTableScrollView) findViewById(R.id.item_scroll_title);
						mHScrollViews.add(headerScroll);
						mListView = (ListView) findViewById(R.id.hlistview_scroll_list);
						for(int i = 0; i < list.size(); i++) {
							data = new HashMap<String, String>();
							data.put("title", list.get(i).adl_nm);
							for (int j = 1; j < cols.length; j++) {

								switch (j) {
								case 1:

									data.put("低保户数", String.valueOf(list.get(i).dbhs));

									break;
								case 2:

									data.put("低保人数", String.valueOf(list.get(i).dbrs));

									break;
								case 3:

									data.put("低保金额(元)", String.valueOf(list.get(i).dbje));

									break;
								case 4:

									data.put("五保户数", String.valueOf(list.get(i).wbhs));

									break;
								case 5:

									data.put("五保人数", String.valueOf(list.get(i).wbrs));

									break;
								case 6:

									data.put("五保金额(元)", String.valueOf(list.get(i).wbje));

									break;
								case 7:

									data.put("兜底户数", String.valueOf(list.get(i).ddhs));

									break;
								case 8:

									data.put("兜底人数", String.valueOf(list.get(i).ddrs));

									break;
								case 9:

									data.put("兜底金额(元)", String.valueOf(list.get(i).ddje));

									break;
								default:
									break;
								}
							}

							datas.add(data);
						}
						mAdapter = new ScrollAdapter(this, datas, R.layout.listitem_poor_people_minzhengju_statistic_listitem //R.layout.item
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
										, R.id.item_datav9 });
						mListView.setAdapter(mAdapter);

						showToast("数据加载完成");
					}else {
						showToast("当前没有数据");
					}
				}
				else {
					ShowNoticDialog();
				}
			}
			break;
		default:
			break;
		}
	}


	@Override
	public void onFaileResult(String str, int flag) {
		// TODO Auto-generated method stub
		super.onFaileResult(str, flag);
		showToast(str);
	}

	/**
	 * 选择时间后进行获取数据
	 *
	 * @param time
	 *
	 * 2016年8月20日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void getData(String time) {
		// TODO Auto-generated method stub
		RequestParams rp = new RequestParams();
		if (!time.equals("")) { //立项时间
			
			int year = Integer.parseInt(time.split("-")[0]);
			int month = Integer.parseInt(time.split("-")[1]);
			
			tv_time.setText(year + "年" + month + "月份兜底汇总统计");
			rp.addBodyParameter("startData", time);
		}
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.MINZHENGJU_STISTIC, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		invalidateOptionsMenu();
		
	}

}
