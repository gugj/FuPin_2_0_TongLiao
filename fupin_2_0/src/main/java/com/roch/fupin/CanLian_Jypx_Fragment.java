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
import com.roch.fupin.adapter.NoPoorProjectCanLian_JYPX_Adapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.ProjectCanlianTrainAppModel;
import com.roch.fupin.entity.ProjectCanlianTrainAppModelListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;
import java.util.List;

/**
 * 残联项目下的就业培训fragment
 * @author ZhaoDongShao
 * 2016年5月27日 
 */
public class CanLian_Jypx_Fragment extends BaseFragment{

	@ViewInject(R.id.refresh_poorhouse)
	PullToRefreshListView listview;

	NoPoorProjectCanLian_JYPX_Adapter adapter;
	private static final String EXTS_PAGE = "page";
	int current_page = 1; //当前页码
	private static int FRIST = 0;//状态

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_noticnoard, container, false);
		mContext = getContext();
		ViewUtils.inject(this, view);
		initData();
		//		View view2 = View.inflate(getActivity().getApplicationContext(), R.layout.listview_head, null);
		//		TextView tv_name = (TextView) view2.findViewById(R.id.tv_search);
		//		tv_name.setText(ResourceUtil.getInstance().getStringById(R.string.edt_hini_projectname));
		//		ListView listView = listview.getRefreshableView();
		//		listView.addHeaderView(view2);

		listview.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				NoPoorProjectCanLianActivity.pxlb = "";
				current_page = 1;
				FRIST = 0;
				loadData();
				//				RequestParams rp = getRequestParams();
				//				rp.addBodyParameter(Common.EXTS_PAGE, Common.PAGR);
				//				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				//						URLs.NO_POOR_PROJECT_CANLIAN_JYPX , rp,
				//						new MyRequestCallBack(CanLian_Jypx_Fragment.this, MyConstans.SEVEN));
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				current_page++;
				FRIST = 1;
				loadData();
				//				Common.PAGR = String.valueOf(Integer.valueOf(Common.PAGR) + 1 );
				//				RequestParams rp = getRequestParams();
				//				if (!NoPoorProjectCanLianActivity.pxlb.equals("")) {
				//					rp.addBodyParameter(Common.BUNDEL_FILTER_KEY_1, NoPoorProjectCanLianActivity.pxlb);
				//				}
				//				rp.addBodyParameter(Common.EXTS_PAGE, Common.PAGR);
				//				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				//						URLs.NO_POOR_PROJECT_CANLIAN_JYPX , rp,
				//						new MyRequestCallBack(CanLian_Jypx_Fragment.this, MyConstans.REFERSH));
			}
		});
		return view;
	}

	/**
	 * 加载数据
	 * 2016年8月6日
	 * ZhaoDongShao
	 */
	private void loadData(){
		RequestParams rp = getRequestParams();
		rp.addBodyParameter(EXTS_PAGE, String.valueOf(current_page));
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NO_POOR_PROJECT_CANLIAN_JYPX , rp,
				new MyRequestCallBack(CanLian_Jypx_Fragment.this, MyConstans.FIRST));
	}

	/**
	 * @return
	 * 2016年7月1日
	 * ZhaoDongShao
	 */
	protected RequestParams getRequestParams() {
		RequestParams rp = new RequestParams();
		if (!NoPoorProjectCanLianActivity.pxlb.equals("")) {
			rp.addBodyParameter("rebuildmodeidcall", NoPoorProjectCanLianActivity.pxlb);
		}
		String adl_cd = getAdl_Cd();
		if (!adl_cd.equals("")) {
			rp.addBodyParameter("adl_cd", adl_cd);
		}
		return rp;
	}

	/**
	 * 2016年5月25日
	 * ZhaoDongShao
	 */
	private void initData() {
		Bundle bundle = getArguments();
		List<ProjectCanlianTrainAppModel> appModel = (List<ProjectCanlianTrainAppModel>) bundle.getSerializable(Common.BUNDEL_KEY);
		if (appModel != null) {
			adapter = new NoPoorProjectCanLian_JYPX_Adapter(mContext, appModel);
			listview.setAdapter(adapter);
		}
	}

	@OnItemClick(R.id.refresh_poorhouse)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//		if (position - 1 == 0) {
		//			Intent intent = new Intent(mContext, SearchActivity1.class);
		//			intent.putExtra(Common.INTENT_KEY, Common.EXTS_NO_POOR_PROJECT_CANLIAN_JYPX);
		//			intent.putExtra(Common.TEXT_HINI, ResourceUtil.getInstance().getStringById(R.string.edt_hini_trainname));
		//			startActivity(intent);
		//
		//		}else {
		ProjectCanlianTrainAppModel appEntity = (ProjectCanlianTrainAppModel)parent.getItemAtPosition(position);
		if (appEntity != null) {
			Intent intent = new Intent(mContext, CanLian_Jypx_Fragment_Activity.class);
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
		ProjectCanlianTrainAppModelListResult projectCanlianTrainAppModelListResult = null;
		switch (flag) {
			case MyConstans.FIRST:
				projectCanlianTrainAppModelListResult = ProjectCanlianTrainAppModelListResult.parseToT(str, ProjectCanlianTrainAppModelListResult.class);
				if (!StringUtil.isEmpty(projectCanlianTrainAppModelListResult)) {
					if (projectCanlianTrainAppModelListResult.getSuccess()) {
						List<ProjectCanlianTrainAppModel> lPoorHouses = projectCanlianTrainAppModelListResult.getJsondata();
						if (!StringUtil.isEmpty(lPoorHouses)) {
							switch (FRIST) {
								case 0:
									if (adapter != null) {
										adapter.onRefsh(lPoorHouses);
									}else {
										adapter = new NoPoorProjectCanLian_JYPX_Adapter(mContext, lPoorHouses);
										listview.setAdapter(adapter);
									}
									break;

								case 1:
									if (adapter == null) {
										adapter = new NoPoorProjectCanLian_JYPX_Adapter(mContext, lPoorHouses);
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
