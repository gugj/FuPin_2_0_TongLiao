package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.roch.fupin.adapter.NoPoorProjectAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.ProjectInfoAppEntity;
import com.roch.fupin.entity.ProjectInfoAppEntityListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 危房改造Fragment
 * @author ZhaoDongShao
 * 2016年6月2日 
 */
public class LinYeJu_Dllh_Fragment extends BaseFragment{

	private static final String EXTS_PAGE = "page";

	@ViewInject(R.id.refresh_poorhouse)
	PullToRefreshListView listview;

	String xmjd = "",xmzt = "";
	NoPoorProjectAdapter adapter;
	int current_page = 1;

	private static int FRIST = 0;//状态

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_noticnoard, container, false);
		mContext = getContext();
		ViewUtils.inject(this,view);
		MyApplication.getInstance().addActivity(mActivity);

		initData();
		//		View view2 = View.inflate(getActivity().getApplicationContext(), R.layout.listview_head, null);
		//		ListView listView = listview.getRefreshableView();
		//		listView.addHeaderView(view2);
		listview.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				current_page = 1;
				FRIST = 0;

				loadDate();
				//				RequestParams rp = getRequestParams();
				//				rp.addBodyParameter(EXTS_PAGE, Common.PAGR);
				//				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				//						URLs.NO_POOR_PROJECT_LINYEJU_DLLH , rp,
				//						new MyRequestCallBack(LinYeJu_Dllh_Fragment.this, MyConstans.SEVEN));
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				current_page++;
				FRIST = 1;
				loadDate();
				//				Common.PAGR = String.valueOf(Integer.valueOf(Common.PAGR) + 1 );
				//				RequestParams rp = getRequestParams();
				//				if (!xmjd.equals("")) {
				//					rp.addBodyParameter("projectscheduleidcall", xmjd);
				//				}
				//				if (!xmzt.equals("")) {
				//					rp.addBodyParameter("projectstatusidcall", xmzt);
				//				}
				//				rp.addBodyParameter(EXTS_PAGE, Common.PAGR);
				//				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				//						URLs.NO_POOR_PROJECT_LINYEJU_DLLH , rp,
				//						new MyRequestCallBack(LinYeJu_Dllh_Fragment.this, MyConstans.REFERSH));
			}
		});
		return view;
	}

	/**
	 * 加载数据
	 * 2016年8月6日
	 * ZhaoDongShao
	 */
	private void loadDate(){
		RequestParams rp = getRequestParams();
		rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NO_POOR_PROJECT_LINYEJU_DLLH , rp,
				new MyRequestCallBack(LinYeJu_Dllh_Fragment.this, MyConstans.FIRST));
	}

	/**
	 * @return
	 * 2016年7月1日
	 * ZhaoDongShao
	 */
	protected RequestParams getRequestParams() {
		RequestParams rp = new RequestParams();
		if (!xmjd.equals("")) {
			rp.addBodyParameter("projectscheduleidcall", xmjd);
		}
		if (!xmzt.equals("")) {
			rp.addBodyParameter("projectstatusidcall", xmzt);
		}
		String adl_cd = getAdl_Cd();
		if (!adl_cd.equals("")) {
			rp.addBodyParameter("adl_cd", adl_cd);
		}
		return rp;
	}

	/**
	 * 2016年5月6日
	 * ZhaoDongShao
	 */
	@SuppressWarnings("unchecked")
	private void initData() {
		Bundle bundle = getArguments();
		xmjd = bundle.getString(Common.BUNDEL_FILTER_KEY_1);
		xmzt = bundle.getString(Common.BUNDEL_FILTER_KEY_2);
		List<ProjectInfoAppEntity> list = (List<ProjectInfoAppEntity>) bundle.getSerializable(Common.BUNDEL_KEY);
		adapter = new NoPoorProjectAdapter(mContext, list);
		listview.setAdapter(adapter);
	}

	@OnItemClick(R.id.refresh_poorhouse)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		if (position - 1 == 0) {
//			Intent intent = new Intent(mContext, SearchActivity1.class);
//			intent.putExtra(Common.INTENT_KEY, Common.EXTS_NO_POOR_PROJECT_LINYEJU_DLLH);
//			intent.putExtra(Common.TEXT_HINI, ResourceUtil.getInstance().getStringById(R.string.edt_hini_projectname));
//			startActivity(intent);
//		}else {
		ProjectInfoAppEntity appEntity = (ProjectInfoAppEntity)parent.getItemAtPosition(position);
		if (appEntity != null) {
			Intent intent = new Intent(mContext, NoPoorProjectLinYeJu_DLLH_DetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable(Common.BUNDEL_KEY, appEntity);
			intent.putExtra(Common.INTENT_KEY, bundle);
			startActivity(intent);
		}
//		}
	}

	@Override
	public void onSuccessResult(String str, int flag) {
		super.onSuccessResult(str, flag);
		ProjectInfoAppEntityListResult listResult = null;
		switch (flag) {
			case MyConstans.FIRST:
				listResult = ProjectInfoAppEntityListResult.parseToT(str, ProjectInfoAppEntityListResult.class);
				if (!StringUtil.isEmpty(listResult)) {
					if (listResult.getSuccess()) {
						List<ProjectInfoAppEntity> lPoorHouses = listResult.getJsondata();
						if (!StringUtil.isEmpty(lPoorHouses)) {
							switch (FRIST) {
								case 0:
									if (adapter != null) {
										adapter.onRefsh(lPoorHouses);
									}else {
										adapter = new NoPoorProjectAdapter(mContext, lPoorHouses);
										listview.setAdapter(adapter);
									}
									break;

								case 1:
									if (adapter == null) {
										adapter = new NoPoorProjectAdapter(mContext, lPoorHouses);
										listview.setAdapter(adapter);
									}else {
										adapter.addList(lPoorHouses);
									}
									break;

								default:
									break;
							}
						}else {
							ShowToast("当前没有更多数据");
							listview.onRefreshComplete();
						}
					}
					else {
						ShowNoticDialog();
					}
				}
				listview.onRefreshComplete();
				break;

			default:
				break;
		}
	}

	@Override
	public void onFaileResult(String str, int flag) {
		super.onFaileResult(str, flag);
		ShowToast(str);
		listview.onRefreshComplete();
	}

}
