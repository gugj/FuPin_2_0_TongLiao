package com.roch.fupin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.ListMonthAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.PoorFamilyAccountPrint;
import com.roch.fupin.entity.PoorFamilyAccountPrintListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 贫困户台账fragment----5个tab页中的第三个
 * @author ZhaoDongShao
 * 2016年5月9日
 */
public class PoorHouseAccountPrintFragment extends BaseFragment {

	@ViewInject(R.id.lv)
	ListView listView;

	@ViewInject(R.id.tv_gongzi)
	TextView tv_gongzi;

	@ViewInject(R.id.tv_shengchan)
	TextView tv_shengchan;

	@ViewInject(R.id.tv_caichan)
	TextView tv_caichan;

	@ViewInject(R.id.tv_jihuashengyu)
	TextView tv_jihuashengyu;

	@ViewInject(R.id.tv_dibao)
	TextView tv_dibao;

	@ViewInject(R.id.tv_wubao)
	TextView tv_wubao;

	@ViewInject(R.id.tv_yanglao)
	TextView tv_yanglao;

	@ViewInject(R.id.tv_shengtai)
	TextView tv_shengtai;

	@ViewInject(R.id.tv_qt)
	TextView tv_qt;

	@ViewInject(R.id.tv_totle)
	TextView tv_totle;
	@ViewInject(R.id.tv_rj)
	TextView tv_rj;

	@ViewInject(R.id.tv_jy_sr)
	TextView tv_jy_sr;// 经营性收入

	
	/**
	 * 所有标题
	 */
	List<View> listviews = new ArrayList<View>();
	
	/**
	 * 标志位，标志初始化已经完成
	 */
	private boolean isPrepared;

	/**
	 * 标识当前fragment是否可见
	 */
	private boolean isVisible;

	ListMonthAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_poorhouse_accountprint, container, false);
		ViewUtils.inject(this, view);
		isPrepared = true;
		lazyLoad();
		return view;
	}

	/**
	 * 判断当前的fragment是否可见，如果可见就请求网络加载数据；否则，不请求网络加载数据
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			isVisible = true;
			lazyLoad();
		} else {
			isVisible = false;
		}
	}

	/**
	 * 如果没有初始化完成或当前fragment不可见，就不去请求网络加载数据；否则开始请求网络加载数据
	 * 2016年10月27日
	 */
	private void lazyLoad() {
		if (!isPrepared || !isVisible) {
			return;
		}
		initData();
	}

	/**
	 * 2016年7月20日   ZhaoDongShao <br/>
	 * 初始化数据，即通过Bundle获取传输过来的hourseid,然后初始化请求网络数据
	 */
	private void initData() {
		Bundle bundle = getArguments();
		if (!StringUtil.isEmpty(bundle)) {
			@SuppressWarnings("unchecked")
			final String houseid = bundle.getString(Common.TITLE_KEY);
			init(houseid);
		}
	}

	/**
	 * 2016年7月21日  ZhaoDongShao <br/>
	 * 初始化网络请求，使用xUtils的post请求方式将RequestParams参数进行封装，请求参数为贫困户id
	 * @param houseid 贫困户id
	 */
	protected void init(String houseid) {
		RequestParams rp = new RequestParams();
		rp.addBodyParameter("householderid", houseid);
		System.out.println("请求服务器台账数据网址："+URLs.POOR_HOUSE_ACCENT+"?householderid="+houseid);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST, URLs.POOR_HOUSE_ACCENT, rp,
				new MyRequestCallBack(PoorHouseAccountPrintFragment.this, MyConstans.FIRST));
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		PoorFamilyAccountPrintListResult result = null;
		switch (flag) {
		case MyConstans.FIRST:
			System.out.println("请求服务器台账数据成功：===="+str);
			result = PoorFamilyAccountPrintListResult.parseToT(str, PoorFamilyAccountPrintListResult.class);
			if (result != null && result.getSuccess()) {
				final List<PoorFamilyAccountPrint> list = result.getJsondata();
				if (list != null && list.size() > 0) {
					List<String> months = new ArrayList<String>();
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getIncomeyear().equals("total")) {
							months.add("年度合计");
						} else {
							months.add(list.get(i).getIncomeyear());
						}
					}

					if (months.size() > 0) {
						adapter = new ListMonthAdapter(getActivity(), months, R.drawable.choose_item_selected_2,
								R.drawable.choose_eara_item_selector);
						listView.setAdapter(adapter);
						adapter.setSelectedPosition(0);
						adapter.setOnItemClickListener(new ListMonthAdapter.OnItemClickListener() {

							@Override
							public void onItemClick(String month, int position) {
								for (int i = 0; i < list.size(); i++) {
									String incomemonth = list.get(i).getIncomeyear();
									if (month.equals(incomemonth)||month.equals("年度合计")) {
										bindMonth(list.get(i));  
										break;
									}
								}
							}
						});
					}
					bindMonth(list.get(0));  
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
		System.out.println("请求服务器台账数据失败：==="+str);
	}

	private void bindMonth(PoorFamilyAccountPrint model) {

		tv_gongzi.setText( String.valueOf(model.getGongzi()) + "元");
		tv_shengchan.setText(String.valueOf(model.getShengchan()) + "元");
		tv_caichan.setText(String.valueOf(model.getCaichan()) + "元");
		tv_jihuashengyu.setText(String.valueOf(model.getJihuashengyu()) + "元");
		tv_wubao.setText(String.valueOf(model.getWubao()) + "元");
		tv_shengtai.setText(String.valueOf(model.getShengtai()) + "元");
		tv_qt.setText(String.valueOf(model.getQitazhuanyi()) + "元");
		tv_jy_sr.setText(String.valueOf(model.getZhichu()) + "元");

		tv_dibao.setText(String.valueOf(model.getDibao()) + "元");
		tv_yanglao.setText( String.valueOf(model.getYanglao()) + "元");

		tv_totle.setText(model.getTotal() + "元");
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumFractionDigits(2);

		tv_rj.setText(model.getTotal_perp() + "元");
		tv_rj.setTextColor(Color.RED);
	}
}
