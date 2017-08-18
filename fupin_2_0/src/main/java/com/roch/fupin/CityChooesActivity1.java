package com.roch.fupin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.AdlCode;
import com.roch.fupin.entity.AdlCodeListResult;
import com.roch.fupin.entity.CityItem;
import com.roch.fupin.entity.User;
import com.roch.fupin.utils.AdlcdUtil;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.PingYinUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择定位城市的CityChooesActivity1，继承自MainBaseActivity，父类里面仅只有一个方法，就是在onCreate中根据版本确定状态栏的颜色
 * @author ZhaoDongShao 2016年7月13日
 */
@ContentView(R.layout.activity_chooes_city)
public class CityChooesActivity1 extends MainBaseActivity {

	/**
	 * 选择当前用户所管辖的城市的ListView列表
	 */
	@ViewInject(R.id.listview)
	ListView list_view;
	
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;

	@ViewInject(R.id.tv_title)
	TextView tv_title;
	
	Activity mActivity;
	Context mContext;
	private BaseAdapter adapter;
	DbUtils dbUtils = null;
	
	/**
	 * 存有城市对象CityItem的所有城市的集合
	 */
	private List<CityItem> allCity_lists; 
	
	/**
	 * 存有城市对象CityItem的城市列表的集合
	 */
	private List<CityItem> city_lists;
	
	/**
	 * 存有行政区对象AdlCode的当前所管辖城市的集合
	 */
	private List<AdlCode> currentlist; 

	/**
	 * 记录当前定位的状态 正在定位-定位成功-定位失败
	 */
	private int locateProcess = 1; 
	
	/**
	 * 是否需要刷新
	 */
	private boolean isNeedFresh;
	
	/**
	 * 用于保存定位到的城市
	 */
	private String currentCity; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mActivity = this;
		this.mContext = this;
		ViewUtils.inject(this);

		initToolbar();
		MyApplication.getInstance().addActivity(mActivity);

		allCity_lists = new ArrayList<CityItem>();
		currentlist = new ArrayList<AdlCode>();
		city_lists = new ArrayList<CityItem>();
		isNeedFresh = true;

		//获取点击城市后传进来的Title和当前登陆的用户User
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra(Common.TITLE_KEY);
		tv_title.setText(bundle.getString(Common.TITLE_KEY));
		User user = (User) bundle.getSerializable(Common.BUNDEL_KEY);

		dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);

		// 根据当前登陆的用户是否为市级或县级，然后异步请求网络数据，即当前用户所管辖的城市的List集合
		requestNetData(user);
	}

	/**
	 * 根据当前登陆的用户是否为市级或县级，然后异步请求网络数据，即当前用户所管辖的城市的List集合
	 * @param user 当前登陆的用户
	 * 2016年11月4日
	 */
	private void requestNetData(User user) {
		String adl_cd = "";
		if (user != null) {
			// 判断该登陆用户是否为市级或县级
			if (!AdlcdUtil.isCity(user.getAdl_cd()) || !AdlcdUtil.isCountry(user.getAdl_cd())) {
				adl_cd = AdlcdUtil.generateCountryCode(user.getAdl_cd());
			}
			RequestParams rp = new RequestParams();
			rp.addBodyParameter("ad_cd", adl_cd);
			System.out.println("请求市县级管辖区域的服务器地址：===" + URLs.COUNTY + "?ad_cd=" + adl_cd);
			MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST, URLs.COUNTY, rp,
					new MyRequestCallBack(this, MyConstans.FIRST));
		}
	}

	/**
	 * 2016年8月5日 ZhaoDongShao
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
			MyApplication.getInstance().finishActivity(mActivity);
			break;

		default:
			break;
		}
		return true;
	}

	/**
	 * 请求服务器成功----县市管辖的区域
	 * @param str
	 * @param flag
	 */
	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		switch (flag) {
			case MyConstans.FIRST:
				System.out.println("请求该县市级管辖的区域的服务器数据：==="+str);
				AdlCodeListResult adlCodeListResult = AdlCodeListResult.parseToT(str, AdlCodeListResult.class);
			if (adlCodeListResult.getSuccess()) {
				List<AdlCode> lAdlCodes = adlCodeListResult.getJsondata();
				dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
				if (lAdlCodes != null && lAdlCodes.size() > 0) {
					for (int i = 0; i < lAdlCodes.size(); i++) {
						lAdlCodes.get(i).setPinyi(PingYinUtil.getPingYin(lAdlCodes.get(i).getAd_nm()));
					}
					try {
						dbUtils.deleteAll(AdlCode.class);
						dbUtils.saveAll(lAdlCodes);
						// 初始化所有的ListView的信息，设置适配器并刷新定位后的位置信息
						initEvent();
					} catch (DbException e) {
						e.printStackTrace();
					}
				}
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 请求服务器失败----县市管辖的区域
	 * @param str
	 * @param flag
	 */
	@Override
	public void onFaileResult(String str, int flag) {
		System.out.println("请求该县市级管辖的区域的服务器数据：==="+str);
	}

	/**
	 * 初始化所有的ListView的信息，设置适配器并刷新定位后的位置信息
	 * 2016年7月13日 ZhaoDongShao
	 */
	private void initEvent() {
		locateProcess = 1; // 默认为正在定位

		// 初始化所有城市和城市列表中List集合的数据
		LocationCity();
		// 初始化当前用户所管辖城市,默认的行政区为“巩义市”，更换接口后需要改为其他市
		currentCity();
		// 初始化城市列表和所有城市List集合的数据
		cityInit();

		// 给当前用户所管辖的城市或区县设置适配器
		setAdapter(allCity_lists, currentlist);
		// 刷新定位后的位置信息
		InitLocation();
	}

	/**
	 * 初始化所有城市和城市列表中List集合的数据
	 * 2016年7月13日
	 * ZhaoDongShao
	 */
	private void LocationCity() {
		List<AdlCode> list = new ArrayList<AdlCode>();
		String city = MyApplication.now_address;
		if (city != null && !city.equals("")) {
			list.add(new AdlCode("", city, PingYinUtil.getPingYin(city)));
			city_lists = new ArrayList<CityItem>();
			city_lists.add(new CityItem(0, list));
			allCity_lists.addAll(city_lists);
		}
	}

	/**
	 * 初始化当前用户所管辖城市,默认的行政区为“巩义市”，更换接口后需要改为其他市  <br/>
	 * 2016年7月13日 ZhaoDongShao
	 */
	private void currentCity() {
		User user = MyApplication.getInstance().getLogin(Common.LoginName);
		AdlCode city = new AdlCode(user.getAdl_cd(), user.getAdl_nm(), PingYinUtil.getPingYin("巩义市"));

		currentlist.add(city);
		allCity_lists.add(new CityItem(1, currentlist));
	}

	/**
	 * 初始化城市列表和所有城市List集合的数据  <br/>
	 * 2016年7月13日 ZhaoDongShao
	 */
	private void cityInit() {
		// AdlCode city = new AdlCode("", "定位", "0"); // 当前定位城市
		// allCity_lists.add(city);
		// city = new AdlCode("", "当前", "1"); // 热门城市
		// allCity_lists.add(city);
		// city = new AdlCode("", "全部", "2"); // 全部城市
		// allCity_lists.add(city);
		CityItem item = new CityItem();
		item.setList(getCityList());
		item.setType(2);
		city_lists = new ArrayList<CityItem>();
		city_lists.add(item);
		allCity_lists.addAll(city_lists);
	}

	/**
	 * 获取当前登录用户所管辖的所有城市的集合
	 * @return 2016年7月13日 ZhaoDongShao
	 */
	private List<AdlCode> getCityList() {
		List<AdlCode> cities = new ArrayList<AdlCode>();
		try {
			dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
			List<AdlCode> list = dbUtils.findAll(AdlCode.class);
			AdlCode city;
			for (int i = 0; i < list.size(); i++) {
				city = new AdlCode(list.get(i).getAd_cd(), list.get(i).getAd_nm(), list.get(i).getPinyi());
				cities.add(city);
			}
			// Collections.sort(cities, comparator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cities;
	}

	/**
	 * 刷新定位后的位置信息   <br/>
	 * 2016年11月4日
	 */
	private void InitLocation() {
		if (!isNeedFresh) {
			return;
		}
		isNeedFresh = false;
		if (MyApplication.now_address == null) {
			locateProcess = 3; // 定位失败
			list_view.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			return;
		}
		currentCity = MyApplication.now_address;
		locateProcess = 2; // 定位成功
		list_view.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	/**
	 * 给当前用户所管辖的城市或区县设置适配器
	 * @param list 
	 * @param currentlist
	 * 2016年11月4日
	 */
	private void setAdapter(List<CityItem> list, List<AdlCode> currentlist) {
		adapter = new ListAdapter(this, list, currentlist);
		list_view.setAdapter(adapter);
	}

	/**
	 * 整个选择城市Activity界面中ListView的adapter
	 * @author ZhaoDongShao 2016年7月13日
	 */
	public class ListAdapter extends BaseAdapter {

		/**
		 * 所有城市的List集合
		 */
		private List<CityItem> list; 
		
		/**
		 * 当前所管辖城市的List集合
		 */
		private List<AdlCode> CurrentList; 
		private Context context;
		private LayoutInflater inflater;
		final int VIEW_TYPE = 3;

		public ListAdapter(Context mContext, List<CityItem> list, List<AdlCode> CurrentList) {
			this.inflater = LayoutInflater.from(mContext);
			this.list = list;
			this.context = mContext;
			this.CurrentList = CurrentList;
		}

		@Override
		public int getViewTypeCount() {
			return VIEW_TYPE;
		}

		@Override
		public int getItemViewType(int position) {
			return list.get(position).getType();
		}

		@Override
		public int getCount() {
			return list != null ? list.size() : 0;
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final TextView city;
			final int viewType = getItemViewType(position);
			if (viewType == 0) { // 第一种城市选择页面ListView的行视图---当前定位城市
				convertView = inflater.inflate(R.layout.frist_list_item, parent, false);
				TextView locateHint = (TextView) convertView.findViewById(R.id.locateHint);
				city = (TextView) convertView.findViewById(R.id.lng_city);
				city.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (locateProcess == 2) { // 定位成功
							Intent intent = new Intent();
							intent.putExtra(Common.INTENT_KEY, new AdlCode("", city.getText().toString(),
									PingYinUtil.getPingYin(city.getText().toString())));
							setResult(RESULT_OK, intent);
							finish();
							Toast.makeText(getApplicationContext(), city.getText().toString(), Toast.LENGTH_SHORT)
									.show();
						} else if (locateProcess == 3) { // 定位失败
							locateProcess = 1; // 将定位状态改为正在定位
							list_view.setAdapter(adapter);
							adapter.notifyDataSetChanged();
							mLocationClient.stop();
							isNeedFresh = true;
							// 重新设置百度地图定位的参数信息，然后开始定位 
							initLocation();
							currentCity = "";
							mLocationClient.start();
						}
					}
				});
				ProgressBar pbLocate = (ProgressBar) convertView.findViewById(R.id.pbLocate);
				if (locateProcess == 1) { // 正在定位
					locateHint.setText("正在定位");
					city.setVisibility(View.GONE);
					pbLocate.setVisibility(View.VISIBLE);
				} else if (locateProcess == 2) { // 定位成功
					locateHint.setText("当前定位城市");
					city.setVisibility(View.VISIBLE);
					city.setText(currentCity);
					mLocationClient.stop();
					pbLocate.setVisibility(View.GONE);
				} else if (locateProcess == 3) {
					locateHint.setText("未定位到城市,请选择");
					city.setVisibility(View.VISIBLE);
					city.setText("重新选择");
					pbLocate.setVisibility(View.GONE);
				}
			} else if (viewType == 1) { // 第二种城市选择页面ListView的行视图---当前用户所管辖的城市
				convertView = inflater.inflate(R.layout.recent_city, parent, false);
				GridView hotCity = (GridView) convertView.findViewById(R.id.recent_city);
				hotCity.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Intent intent = new Intent();
						intent.putExtra(Common.INTENT_KEY, CurrentList.get(position));
						setResult(RESULT_OK, intent);
						finish();
					}
				});
				hotCity.setAdapter(new HotCityAdapter(context, this.CurrentList));
				TextView hotHint = (TextView) convertView.findViewById(R.id.recentHint);
				hotHint.setText("当前用户所管辖城市");
			} else if (viewType == 2) { // 第三种城市选择页面ListView的行视图---当前城市所管辖的区县
				convertView = inflater.inflate(R.layout.recent_city, parent, false);
				GridView allCity = (GridView) convertView.findViewById(R.id.recent_city);
				allCity.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Intent intent = new Intent();
						intent.putExtra(Common.INTENT_KEY, list.get(viewType).getList().get(position));
						setResult(RESULT_OK, intent);
						finish();
					}
				});
				allCity.setAdapter(new HotCityAdapter(context, this.list.get(viewType).getList()));
				TextView hotHint = (TextView) convertView.findViewById(R.id.recentHint);
				hotHint.setText("当前用户所管辖区县");
			}
			return convertView;
		}
	}

	/**
	 * 当前用户所管辖城市或区县的adapter,里面的convertView其实就只是一个TextView,用于展示当前用户所管辖的城市或区县
	 * @author ZhaoDongShao 2016年7月13日
	 */
	class HotCityAdapter extends BaseAdapter {
		
		private Context context;
		private LayoutInflater inflater;
		private List<AdlCode> Citys;

		public HotCityAdapter(Context context, List<AdlCode> Citys) {
			this.context = context;
			inflater = LayoutInflater.from(this.context);
			this.Citys = Citys;
		}

		@Override
		public int getCount() {
			return Citys.size();
		}

		@Override
		public Object getItem(int position) {
			return Citys.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressLint("ViewHolder")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = inflater.inflate(R.layout.item_city, parent, false);
			TextView city = (TextView) convertView.findViewById(R.id.tv_city);
			city.setText(Citys.get(position).getAd_nm());
			return convertView;
		}
	}
}
