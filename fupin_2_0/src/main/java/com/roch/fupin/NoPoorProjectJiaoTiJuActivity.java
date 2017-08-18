/**
 * 
 */
package com.roch.fupin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NoPoorProject_jiaotiju_FilterPopWindow;
import com.roch.fupin.dialog.NoPoorProject_jiaotiju_FilterPopWindow.ShowMessageListener;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ProjectJiaotiAppModel;
import com.roch.fupin.entity.ProjectJiaotiAppModelListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin.view.JiaoTiJuTableScrollView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * @author ZhaoDongShao
 *
 * 2016年6月2日 
 *
 */
@ContentView(R.layout.activity_poor_people_jiaotiju_statistic)
public class NoPoorProjectJiaoTiJuActivity extends MainBaseActivity implements ShowMessageListener{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;
	@ViewInject(R.id.tv_time)
	TextView tv_time;

	private ListView mListView;
	protected List<JiaoTiJuTableScrollView> mHScrollViews =new ArrayList<JiaoTiJuTableScrollView>();

	public JiaoTiJuTableScrollView mTouchView;

	//创建数组保存表头
	private String[] cols = {"title","学前教育资助人数","高中资助人数","高校资助人数","义教救助（初中）人数","义教救助（小学）人数","中职资助人数","学前教育资助金额","高中资助金额",
			"高校资助金额","义教救助（初中金额）","义教救助（小学金额）","中职资助金额","学前教育资助人数"};

	private  ScrollAdapter mAdapter;


	NoPoorProject_jiaotiju_FilterPopWindow filterPopWindow;

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

					int xPox = (int)(Common.Width * 0.9);
					filterPopWindow = new NoPoorProject_jiaotiju_FilterPopWindow(mContext);
					filterPopWindow.setShowMessageListener(NoPoorProjectJiaoTiJuActivity.this);
					filterPopWindow.setSelectionAdapter(maps);
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
	private void initData() {

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		String time = year + "年" + month + "月份";
		tv_time.setText(time + "教育资助汇总统计");
		
		RequestParams rp = getRequestParams();

		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.JIAOTIJU_STISTIC, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));

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


	public void addHViews(final JiaoTiJuTableScrollView hScrollView) {
		if(!mHScrollViews.isEmpty()) {
			int size = mHScrollViews.size();
			JiaoTiJuTableScrollView scrollView = mHScrollViews.get(size - 1);
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

		for (JiaoTiJuTableScrollView tableScrollView : mHScrollViews) {

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
				addHViews((JiaoTiJuTableScrollView) v.findViewById(R.id.item_chscroll_scroll));
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


	/* (non-Javadoc)
	 * @see com.roch.fupin.BaseActivity#onSuccessResult(java.lang.String, int)
	 */
	@Override
	public void onSuccessResult(String str, int flag) {
		// TODO Auto-generated method stub
		super.onSuccessResult(str, flag);

		ProjectJiaotiAppModelListResult jiaotiAppModelListResult = null;

		switch (flag) {
		case MyConstans.FIRST:

			jiaotiAppModelListResult = ProjectJiaotiAppModelListResult.parseToT(str, ProjectJiaotiAppModelListResult.class);
			if (!StringUtil.isEmpty(jiaotiAppModelListResult)) {
				if (jiaotiAppModelListResult.getSuccess()) {
					List<ProjectJiaotiAppModel> list = jiaotiAppModelListResult.getJsondata();

					if (!StringUtil.isEmpty(list)) {


						List<Map<String, String>> datas = new ArrayList<Map<String,String>>();
						Map<String, String> data = null;
						JiaoTiJuTableScrollView headerScroll = (JiaoTiJuTableScrollView) findViewById(R.id.item_scroll_title);
						mHScrollViews.add(headerScroll);
						mListView = (ListView) findViewById(R.id.hlistview_scroll_list);
						for(int i = 0; i < list.size(); i++) {
							data = new HashMap<String, String>();
							data.put("title", list.get(i).ad_nm);
							for (int j = 1; j < cols.length; j++) {

								switch (j) {
								case 1:

									data.put("学前教育资助人数", String.valueOf(list.get(i).xqjyrs));

									break;
								case 2:

									data.put("高中资助人数", String.valueOf(list.get(i).gzrs));

									break;
								case 3:

									data.put("高校资助人数", String.valueOf(list.get(i).gxrs));

									break;
								case 4:

									data.put("义教救助（初中）人数", String.valueOf(list.get(i).yjcrs));

									break;
								case 5:

									data.put("义教救助（小学）人数", String.valueOf(list.get(i).yjxrs));

									break;
								case 6:

									data.put("中职资助人数", String.valueOf(list.get(i).zzrs));

									break;
								case 7:

									data.put("学前教育资助金额", String.valueOf(list.get(i).xqjyje));

									break;
								case 8:

									data.put("高中资助金额", String.valueOf(list.get(i).gzje));

									break;
								case 9:

									data.put("高校资助金额", String.valueOf(list.get(i).gxje));

									break;
								case 10:

									data.put("义教救助（初中金额）", String.valueOf(list.get(i).yjcje));

									break;
								case 11:

									data.put("义教救助（小学金额）", String.valueOf(list.get(i).yjxje));

									break;
								case 12:

									data.put("中职资助金额", String.valueOf(list.get(i).zzje));

									break;
								default:
									break;
								}
							}

							datas.add(data);
						}
						mAdapter = new ScrollAdapter(this, datas, R.layout.listitem_poor_people_jiaotiju_statistic_listitem //R.layout.item
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
						mListView.setAdapter(mAdapter);
						showToast("数据加载完成");

					}else {
						showToast("当前没有更多数据");
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


	List<MapEntity> maps = new ArrayList<MapEntity>();
	@Override
	public void Message(String time_start, String time_end) {
		// TODO Auto-generated method stub
		maps.clear();
		String times = "";
		RequestParams rp = new RequestParams();
		if (!time_start.equals("")) {
			
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
					
					times += year + "年" + months + "月份";
					rp.addBodyParameter("wejstart", time_start);
					maps.add(new MapEntity("wejstart", time_start));
					
				}
			}else {
				times += year + "年" + months + "月份";
				rp.addBodyParameter("wejstart", time_start);
				maps.add(new MapEntity("wejstart", time_start));
			}
			
			
		}else {
			
			
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;

			String a = year + "年" + month + "月份";

			times += a;

		}
		if (!time_end.equals("")) {
			
			int year = Integer.parseInt(time_end.split("-")[0]);
			int months = Integer.parseInt(time_end.split("-")[1]);
			
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
					}else if(start_year == year){
						
						if (start_months > months) {
							
							showToast("开始日期不能在结束日期之后");
							return;
							
						}else {
							
							times += "~" + year + "年" + months + "月份";
							maps.add(new MapEntity("wejend", time_end));
							rp.addBodyParameter("wejend", time_end);
						}
					}else {
						times += "~" + year + "年" + months + "月份";
						maps.add(new MapEntity("wejend", time_end));
						rp.addBodyParameter("wejend", time_end);
					}
				}
			}else {
				int start_year = Integer.parseInt(time_start.split("-")[0]);
				int start_months = Integer.parseInt(time_start.split("-")[1]);
				
				if (start_year > year) {
					showToast("开始日期不能在结束日期之后");
					return;
				}else if(start_year == year){
					
					if (start_months > months) {
						
						showToast("开始日期不能在结束日期之后");
						return;
						
					}else {
						
						times += "~" + year + "年" + months + "月份";
						maps.add(new MapEntity("wejend", time_end));
						rp.addBodyParameter("wejend", time_end);
					}
				}else {
					
					times += "~" + year + "年" + months + "月份";
					maps.add(new MapEntity("wejend", time_end));
					rp.addBodyParameter("wejend", time_end);
					
				}
			}
			
			
		}else {

			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			
			String a = year + "年" + month + "月份";

			times += "~" + a;
		}
		tv_time.setText(times + "教育资助汇总统计");
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.JIAOTIJU_STISTIC, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));
		filterPopWindow.dismiss();
	}
}
