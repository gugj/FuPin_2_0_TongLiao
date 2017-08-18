/**
 * 
 */
package com.roch.fupin;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.adapter.NoPoorProjectFuLian_PXRY_Adapter;
import com.roch.fupin.entity.ProjectFulianItemAppModel;
import com.roch.fupin.utils.Common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * 资金管理
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月27日 
 *
 */
public class FuLian_PxryFragment extends BaseFragment{

	@ViewInject(R.id.lv_poor)
	ListView listview;

	Context mContext;
	Activity mActivity;

	NoPoorProjectFuLian_PXRY_Adapter adapter;

	/* (non-Javadoc)
	 * @see com.roch.fupin.BaseFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_poorhouse_familypeople, container, false);
		ViewUtils.inject(this, view);
		this.mContext = getActivity();
		this.mActivity = getActivity();
		initData();
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
	@SuppressWarnings("unchecked")
	private void initData() {

		Bundle bundle = getArguments();
		List<ProjectFulianItemAppModel> appModel = (List<ProjectFulianItemAppModel>) bundle.getSerializable(Common.BUNDEL_KEY);
		if (appModel != null) {
			adapter = new NoPoorProjectFuLian_PXRY_Adapter(mContext, appModel);
			listview.setAdapter(adapter);
		}
		
	}
}
