/**
 * 
 */
package com.roch.fupin;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.roch.fupin.entity.PoorVillageAccountPrint;
import com.roch.fupin.utils.AccountPrintUtils;
import com.roch.fupin.utils.Common;
import com.roch.fupin_2_0.R;

/**
 * 贫困村台账信息
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月24日
 */
public class PoorVillageAccountPrintFragment extends BaseFragment {


	@ViewInject(R.id.rl_tudi_info)
	RelativeLayout rl_tudi_info; //土地信息
	@ViewInject(R.id.rl_renkou_info)
	RelativeLayout rl_renkou_info;//人口
	@ViewInject(R.id.rl_shbz_info)
	RelativeLayout rl_shbz_info;//社会保障
	@ViewInject(R.id.rl_cjdl_info)
	RelativeLayout rl_cjdl_info;//村级道路
	@ViewInject(R.id.rl_ysaq_info)
	RelativeLayout rl_ysaq_info;//饮水安全
	@ViewInject(R.id.rl_wfgz_info)
	RelativeLayout rl_wfgz_info;//危房改造
	@ViewInject(R.id.rl_tscyzs_info)
	RelativeLayout rl_tscyzs_info;//特色产业增收
	@ViewInject(R.id.rl_ws_and_jhsy_info)
	RelativeLayout rl_ws_and_jhsy_info;//卫生和计划生育
	@ViewInject(R.id.rl_whjs_info)
	RelativeLayout rl_whjs_info;//文化建设
	@ViewInject(R.id.rl_pkcxxh_info)
	RelativeLayout rl_pkcxxh_info;//贫困村信息化



	Context mContext;
	/* (non-Javadoc)
	 * @see com.roch.fupin.BaseFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_poorvillage_accountprint, container, false);
		ViewUtils.inject(this, view);
		this.mContext = getActivity();
		return view;
	}

	@OnClick({R.id.rl_tudi_info,R.id.rl_renkou_info,R.id.rl_shbz_info,R.id.rl_cjdl_info,R.id.rl_ysaq_info,R.id.rl_wfgz_info,
		R.id.rl_tscyzs_info,R.id.rl_ws_and_jhsy_info,R.id.rl_whjs_info,R.id.rl_pkcxxh_info})
	public void onClick(View v)
	{
		Intent intent = new Intent(mContext, PoorVillageAccountPrintDetailActivity.class);
		Bundle bundle2 = getArguments();

		if (bundle2 != null) {
			PoorVillageAccountPrint accountPrint = (PoorVillageAccountPrint) bundle2.getSerializable(Common.BUNDEL_KEY);
			//			PoorVillageAccountPrint accountPrint = PoorVillageBaseFragment.poorVillage.getPt();
			if (accountPrint == null) {
				ShowToast("服务器开小差了，请稍后再试o(╯□╰)o");
				return;
			}

			Bundle bundle = null;
			TextView textView = null;
			if (v.getId() == R.id.rl_tudi_info) {

				textView = (TextView) rl_tudi_info.getChildAt(0);
				bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, AccountPrintUtils.getTudi(accountPrint.getTd()));

			}else if (v.getId() == R.id.rl_renkou_info) {

				textView = (TextView) rl_renkou_info.getChildAt(0);
				bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, AccountPrintUtils.getRenkou(accountPrint.getRenkou()));

			}else if (v.getId() == R.id.rl_shbz_info) {

				textView = (TextView) rl_shbz_info.getChildAt(0);
				bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, AccountPrintUtils.getSheHuiBaoZhang(accountPrint.getShbz()));

			}else if (v.getId() == R.id.rl_cjdl_info) {

				textView = (TextView) rl_cjdl_info.getChildAt(0);
				bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, AccountPrintUtils.getCunjiDaolu(accountPrint.getCjdl()));

			}else if (v.getId() == R.id.rl_ysaq_info) {

				textView = (TextView) rl_ysaq_info.getChildAt(0);
				bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, AccountPrintUtils.getYinshui(accountPrint.getYsaq()));

			}else if (v.getId() == R.id.rl_wfgz_info) {

				textView = (TextView) rl_wfgz_info.getChildAt(0);
				bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, AccountPrintUtils.getWeiFang(accountPrint.getWfgz()));

			}else if (v.getId() == R.id.rl_tscyzs_info) {

				textView = (TextView) rl_tscyzs_info.getChildAt(0);
				bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, AccountPrintUtils.getTeseChanye(accountPrint.getTscy()));

			}else if (v.getId() == R.id.rl_ws_and_jhsy_info) {

				textView = (TextView) rl_ws_and_jhsy_info.getChildAt(0);
				bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, AccountPrintUtils.getWeiSheng(accountPrint.getWsjhsy()));

			}else if (v.getId() == R.id.rl_whjs_info) {

				textView = (TextView) rl_whjs_info.getChildAt(0);
				bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, AccountPrintUtils.getWenhua(accountPrint.getWhjs()));

			}else if (v.getId() == R.id.rl_pkcxxh_info) {

				textView = (TextView) rl_pkcxxh_info.getChildAt(0);
				bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, AccountPrintUtils.getPkcXinxihua(accountPrint.getPkcxxh()));

			}

			if (bundle != null && textView != null) {
				bundle.putString(Common.TITLE_KEY, getTextView(textView));
				intent.putExtra(Common.INTENT_KEY, bundle);
				startActivity(intent);
			}

		}
	}

}
