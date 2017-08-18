/**
 * 
 */
package com.roch.fupin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.HelpingMeasure;
import com.roch.fupin.entity.HelpingMeasureListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.URLs;
import com.roch.fupin.view.TableScrollView;
import com.roch.fupin_2_0.R;

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

/**
 * 
 * 帮扶措施统计
 * 
 * @author ZhaoDongShao
 *
 * 2016年8月11日 
 *
 */

@ContentView(R.layout.activity_poor_people_bfcs_statistic)
public class PoorPeopleBFCSActivity extends MainBaseActivity{

	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.tv_title)
	TextView tv_title;

	private ListView mListView;
	protected List<TableScrollView> mHScrollViews =new ArrayList<TableScrollView>();

	public TableScrollView mTouchView;

	//创建数组保存表头
	private String[] cols = {"title","户数","人数","户数1","人数1","户数3","人数3","户数4","人数4","户数2","人数2","户数5","人数5"};

	private  ScrollAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		MyApplication.getInstance().addActivity(mActivity);

		initToolbar();
		initViews();
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
	/**
	 *
	 *
	 * 2016年8月11日
	 *
	 * ZhaoDongShao
	 *
	 */
	private void initViews() {

		Intent intent = getIntent();
		String name = intent.getStringExtra(Common.INTENT_KEY);

		if (name != null && !name.equals("")) {
			tv_title.setText(name);
		}

		MyApplication.getInstance().getHttpUtilsInstance().send(
				HttpMethod.POST, URLs.POOR_PEOPLE_BFCS, 
				new MyRequestCallBack(mActivity, MyConstans.FIRST));
	}


	@Override
	public void onSuccessResult(String str, int flag) {
		// TODO Auto-generated method stub
		super.onSuccessResult(str, flag);

		List<HelpingMeasure> list = new ArrayList<HelpingMeasure>();

		HelpingMeasureListResult listResult = HelpingMeasureListResult.parseToT(str, HelpingMeasureListResult.class);
		if (listResult != null ) {

			if (listResult.getSuccess()) {

				list.addAll(listResult.jsondata);

				List<Map<String, String>> datas = new ArrayList<Map<String,String>>();
				Map<String, String> data = null;
				TableScrollView headerScroll = (TableScrollView) findViewById(R.id.item_scroll_title);
				mHScrollViews.add(headerScroll);
				mListView = (ListView) findViewById(R.id.hlistview_scroll_list);
				for(int i = 0; i < list.size(); i++) {
					data = new HashMap<String, String>();
					data.put("title", list.get(i).adl_nm);
					for (int j = 1; j < cols.length; j++) {

						switch (j) {
						case 1:

							data.put("户数", String.valueOf(list.get(i).px_f_c));

							break;
						case 2:

							data.put("人数", String.valueOf(list.get(i).px_p_c));

							break;
						case 3:

							data.put("户数1", String.valueOf(list.get(i).jy_f_c));

							break;
						case 4:

							data.put("人数1", String.valueOf(list.get(i).jy_p_c));

							break;
						case 5:

							data.put("户数3", String.valueOf(list.get(i).dhzc_f_c));

							break;
						case 6:

							data.put("人数3", String.valueOf(list.get(i).dhzc_p_c));

							break;
						case 7:

							data.put("户数4", String.valueOf(list.get(i).cyfc_f_c));

							break;
						case 8:

							data.put("人数4", String.valueOf(list.get(i).cyfc_p_c));

							break;
						case 9:

							data.put("户数2", String.valueOf(list.get(i).tpd_f_c));

							break;
						case 10:

							data.put("人数2", String.valueOf(list.get(i).tpd_p_c));

							break;
						case 11:

							data.put("户数5", String.valueOf(list.get(i).fpbq_f_c));

							break;
						case 12:

							data.put("人数5", String.valueOf(list.get(i).fpbq_p_c));

							break;
						default:
							break;
						}
					}

					datas.add(data);
				}
				mAdapter = new ScrollAdapter(this, datas, R.layout.listitem_poor_people_bfcs_statistic_listitem //R.layout.item
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
								, R.id.item_datav12});
				mListView.setAdapter(mAdapter);
				showToast("数据加载完成");
			}

		}
	}





	public void addHViews(final TableScrollView hScrollView) {
		if(!mHScrollViews.isEmpty()) {
			int size = mHScrollViews.size();
			TableScrollView scrollView = mHScrollViews.get(size - 1);
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

		for (TableScrollView tableScrollView : mHScrollViews) {

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
				addHViews((TableScrollView) v.findViewById(R.id.item_chscroll_scroll));
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

				////				((TextView)holders[i]).setText(name);
				//				((TextView)holders[i]).setText(this.datas.get(position).get(from[i]).toString());
				//				Map<String, ?> map = this.datas.get(j);
				//				
				//				for(int i = 0 ; i < len; i++) {
				//					
				//					String name = (String)map.get(from[i]);
				//					
				//					TextView textView = (TextView)holders[i];
				//					
				//					textView.setText(name);
				////					((TextView)holders[i]).setText(name);
				//				}

			}

			return v;
		}

	}

}
