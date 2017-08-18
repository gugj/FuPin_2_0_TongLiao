/**
 * 
 */
package com.roch.fupin;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.adapter.NoPoorProjectJiaoTiJu_XMXX_Adapter;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ProjectCanlianTrainAppModel;
import com.roch.fupin.utils.Common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * 残联就业培训项目信息
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月27日 
 *
 */
public class CanLian_Jypf_XmxxFragment extends BaseFragment{

	@ViewInject(R.id.lv_poor)
	ListView listview;

	Context mContext;
	Activity mActivity;

	NoPoorProjectJiaoTiJu_XMXX_Adapter adapter;

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
	private void initData() {

		Bundle bundle = getArguments();
		ProjectCanlianTrainAppModel appModel = (ProjectCanlianTrainAppModel) bundle.getSerializable(Common.BUNDEL_KEY);
		if (appModel != null) {
			List<MapEntity> xmxxs = new ArrayList<MapEntity>(); //项目信息
			xmxxs.add(new MapEntity("项目名称", appModel.getProjectname()));
			xmxxs.add(new MapEntity("立项时间", appModel.getLixiangdate() != null ? appModel.getLixiangdate().split(" ")[0] : ""));
			xmxxs.add(new MapEntity("中省资金（万元）", String.valueOf(appModel.getZszj())));
			xmxxs.add(new MapEntity("市级资金（万元）", String.valueOf(appModel.getSjzj())));
			xmxxs.add(new MapEntity("镇村配套（万元）", String.valueOf(appModel.getZcpt())));

			xmxxs.add(new MapEntity("培训名称", appModel.getTrainname()));
			xmxxs.add(new MapEntity("培训机构名称", appModel.getTraincompanyname()));
			xmxxs.add(new MapEntity("培训类别", appModel.getTraintypeidcall()));
			xmxxs.add(new MapEntity("培训时间", appModel.getTraindate()));
			xmxxs.add(new MapEntity("培训地点", appModel.getTrainaddress()));
			xmxxs.add(new MapEntity("培训方式", appModel.getTrainmodeidcall()));
			xmxxs.add(new MapEntity("培训负责人", appModel.getPersonname()));
			xmxxs.add(new MapEntity("负责人联系方式", appModel.getPersonphone()));
			xmxxs.add(new MapEntity("备注", appModel.getRemark()));


			adapter = new NoPoorProjectJiaoTiJu_XMXX_Adapter(mContext, xmxxs);
			listview.setAdapter(adapter);
		}

	}
}
