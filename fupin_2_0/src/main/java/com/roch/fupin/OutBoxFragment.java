/**
 * 
 */
package com.roch.fupin;

import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.roch.fupin_2_0.R;
import com.roch.fupin.adapter.NoticBoardAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.NoticBoard;
import com.roch.fupin.entity.NoticBoardListResult;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * @author ZhaoDongShao
 *
 * 2016年5月27日 
 *
 */
public class OutBoxFragment extends BaseFragment{

	@ViewInject(R.id.refresh_poorhouse)
	PullToRefreshListView listview;

	Context mContext;
	Activity mActivity;

	NoticBoardAdapter adapter;

	/* (non-Javadoc)
	 * @see com.roch.fupin.BaseFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_noticnoard, container, false);
		ViewUtils.inject(this, view);
		this.mContext = getActivity();
		this.mActivity = getActivity();
		Common.PAGR = "1";
		initData();

		listview.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				Common.PAGR = "1";
				initData();

			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				Common.PAGR = String.valueOf(Integer.valueOf(Common.PAGR) + 1 );
				RequestParams rp = new RequestParams();
				rp.addBodyParameter(PoorHouseActivity.EXTS_PAGE, Common.PAGR);
				MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
						URLs.NOTIC_BOARD_OUTBOX, rp,
						new MyRequestCallBack(OutBoxFragment.this, MyConstans.REFERSH));
			}
		});
		return view;
	}
	
	
	/**
	 *
	 *
	 * 2016年5月25日
	 *
	 * ZhaoDongShao
	 *
	 */
	private void initData() {
		
		RequestParams rp = new RequestParams();
		rp.addBodyParameter(PoorHouseActivity.EXTS_PAGE, Common.PAGR);
		MyApplication.getInstance().getHttpUtilsInstance().send(HttpMethod.POST,
				URLs.NOTIC_BOARD_OUTBOX, rp,
				new MyRequestCallBack(this, MyConstans.FIRST));

	}


	@OnItemClick(R.id.refresh_poorhouse)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		NoticBoard noticBoard = (NoticBoard)parent.getItemAtPosition(position);
		if (noticBoard != null) {
			Intent intent = new Intent(mContext, InformationDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString(Common.TITLE_KEY, Common.EXTS_NOTIC_BOARD_NAME + "——发件箱");
			bundle.putSerializable(Common.BUNDEL_KEY, noticBoard);
			intent.putExtra(Common.INTENT_KEY, bundle);
			startActivity(intent);
		}
	}

	/* (non-Javadoc)
	 * @see com.roch.fupin.BaseActivity#onSuccessResult(java.lang.String, int)
	 */
	@Override
	public void onSuccessResult(String str, int flag) {
		// TODO Auto-generated method stub
		super.onSuccessResult(str, flag);
		NoticBoardListResult noticBoardListResult = null;
		switch (flag) {
		case MyConstans.FIRST:

			noticBoardListResult = NoticBoardListResult.parseToT(str, NoticBoardListResult.class);
			if (!StringUtil.isEmpty(noticBoardListResult)) {
				if (noticBoardListResult.getSuccess()) {
					List<NoticBoard> noticBoards = noticBoardListResult.getJsondata();

					if (!StringUtil.isEmpty(noticBoards)) {
						
						if (adapter != null) {
							adapter.onRefsh(noticBoards);
						}
						else {
							adapter = new NoticBoardAdapter(mContext, noticBoards);
							listview.setAdapter(adapter);
						}
						
					}else {
						ShowToast("当前没有更多数据");
					}

				}
				else {
					ShowNoticDialog();
				}
			}
			listview.onRefreshComplete();
			break;
		case MyConstans.REFERSH:

			noticBoardListResult = NoticBoardListResult.parseToT(str, NoticBoardListResult.class);
			if (!StringUtil.isEmpty(noticBoardListResult)) {
				if (noticBoardListResult.getSuccess()) {
					List<NoticBoard> noticBoards = noticBoardListResult.getJsondata();
					
					if (!StringUtil.isEmpty(noticBoards) && noticBoards.size() > 0) {
						
						if (adapter != null) {
							adapter.addList(noticBoards);
						}
						else {
							adapter = new NoticBoardAdapter(mContext, noticBoards);
							listview.setAdapter(adapter);
						}
						
					}else {
						ShowToast("当前没有更多数据");
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


	/* (non-Javadoc)
	 * @see com.roch.fupin.BaseActivity#onFaileResult(java.lang.String, int)
	 */
	@Override
	public void onFaileResult(String str, int flag) {
		// TODO Auto-generated method stub
		super.onFaileResult(str, flag);
	}
	
}
