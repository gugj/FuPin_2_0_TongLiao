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
import com.roch.fupin.entity.ProjectNongweiAppModel;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.CommonUtil;
import com.roch.fupin.utils.ResourceUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * 项目信息
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月27日 
 *
 */
public class NongWei_XmxxFragment extends BaseFragment{

	@ViewInject(R.id.lv_poor)
	ListView listview;

	Context mContext;
	Activity mActivity;

	NoPoorProjectJiaoTiJu_XMXX_Adapter adapter;

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
		ProjectNongweiAppModel appModel = (ProjectNongweiAppModel) bundle.getSerializable(Common.BUNDEL_KEY);
		if (appModel != null) {
			List<MapEntity> xmxxs = new ArrayList<MapEntity>(); //项目信息
			xmxxs.add(new MapEntity("项目名称", appModel.getProjectname()));
			xmxxs.add(new MapEntity("立项时间", appModel.getLixiangdate() != null ? appModel.getLixiangdate().split(" ")[0] : ""));
			xmxxs.add(new MapEntity("中省资金（万元）", String.valueOf(appModel.getZszj()) + "万元"));
			xmxxs.add(new MapEntity("市级资金（万元）", String.valueOf(appModel.getSjzj()) + "万元"));
			xmxxs.add(new MapEntity("镇村配套（万元）", String.valueOf(appModel.getZcpt()) + "万元"));
			xmxxs.add(new MapEntity("群众自筹（万元）", String.valueOf(appModel.getQzzc()) + "万元"));
			xmxxs.add(new MapEntity("已完成投资", String.valueOf(appModel.getYwctz()) + "万元"));
			xmxxs.add(new MapEntity("投资比例", String.valueOf(CommonUtil.getBili(appModel.getYwctz(), appModel.getInvesttotal()))));
			
			
			xmxxs.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmxx_company), appModel.getCompanyname()));
			xmxxs.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmxx_company_lx), appModel.getCompanytypeidcall()));
			xmxxs.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmxx_company_address), appModel.getAddress()));
			xmxxs.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmxx_dkyt), appModel.getLoanpurpose()));
			xmxxs.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmxx_dkje), String.valueOf(appModel.getLoanamount())));
			xmxxs.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmxx_dkqx), String.valueOf(appModel.getLoanmonth())));
			xmxxs.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmxx_spjd), appModel.getApprovalstatusidcall()));
			xmxxs.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmxx_fuzeren), appModel.getPersonname()));
			xmxxs.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmxx_phone), appModel.getPersonphone()));
			xmxxs.add(new MapEntity(ResourceUtil.getInstance().getStringById(R.string.xmxx_remark), appModel.getRemark()));
			
			adapter = new NoPoorProjectJiaoTiJu_XMXX_Adapter(mContext, xmxxs);
			listview.setAdapter(adapter);
		}
		
	}
}
