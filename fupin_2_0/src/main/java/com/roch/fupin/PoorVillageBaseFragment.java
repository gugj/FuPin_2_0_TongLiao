/**
 * 
 */
package com.roch.fupin;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.roch.fupin.dialog.DutyCompanyDialog;
import com.roch.fupin.entity.HelpCompany;
import com.roch.fupin.entity.PoorVillageBase;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;

import java.io.Serializable;
import java.util.List;

/**
 * 贫困村基本情况
 * @author ZhaoDongShao
 * 2016年5月24日
 */
public class PoorVillageBaseFragment extends BaseFragment {
	@ViewInject(R.id.tv_poor)
	TextView tv_poor;  //脱贫情况
	@ViewInject(R.id.tv_fuzeren)
	TextView tv_fuzeren;
	@ViewInject(R.id.tv_phone)
	TextView tv_phone;  //负责人电话
	@ViewInject(R.id.tv_num)
	TextView tv_num; //总人数
	@ViewInject(R.id.tv_poor_num)
	TextView tv_poor_num; //贫困户数
	@ViewInject(R.id.rl_dutycompany)
	RelativeLayout rl_help_company; //帮扶单位


	@ViewInject(R.id.tv_cunzhishu)
	TextView tv_cunzhishu;
	@ViewInject(R.id.tv_cunzhishu_phone)
	TextView tv_cunzhishu_phone;
	@ViewInject(R.id.tv_first_shuji)
	TextView tv_first_shuji;
	@ViewInject(R.id.tv_first_shuji_phone)
	TextView tv_first_shuji_phone;
	@ViewInject(R.id.tv_duizhang_name)
	TextView tv_duizhang_name;
	@ViewInject(R.id.tv_duizhang_phohe)
	TextView tv_duizhang_phohe;
	/**
	 * 标志位，标志初始化已经完成
	 */
	private boolean isPrepared;

	PoorVillageBase lBases = null;
	/**
	 * 标识当前fragment是否可见
	 */
	private boolean isVisible;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_poorvillage_base, container, false);
		ViewUtils.inject(this, view);
		isPrepared = true;
		lazyLoad();
		return view;
	}


	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			isVisible = true;
			lazyLoad();
		}else {
			isVisible = false;
		}
	}

	private void lazyLoad() {
		if (!isPrepared || !isVisible) {
			return;
		}
		initData();
	}

	/**
	 * 2016年5月17日
	 * ZhaoDongShao
	 */
	private void initData() {
		Bundle bundle = getArguments();
		if (!StringUtil.isEmpty(bundle)) {
			lBases = (PoorVillageBase) bundle.getSerializable(Common.BUNDEL_KEY);
			tv_num.setText(String.valueOf(lBases.getPopulationnumber()));
			tv_fuzeren.setText(lBases.getPersonname());
			tv_phone.setText(lBases.getPersonphone());
			tv_poor.setText(lBases.getTpName());
			tv_poor_num.setText(String.valueOf(lBases.getPoorhousenm()));

			tv_cunzhishu.setText(lBases.getSecretaryname());
			tv_cunzhishu_phone.setText(lBases.getSecretaryphone());
			tv_first_shuji.setText(lBases.getFirsecretaryname());
			tv_first_shuji_phone.setText(lBases.getFirsecretaryphone());
			tv_duizhang_name.setText(lBases.getCaptainname());
			tv_duizhang_phohe.setText(lBases.getCaptainphone());

			if (StringUtil.checkPhone(getTextView(tv_duizhang_phohe))) { //队长电话
				tv_duizhang_phohe.setTextColor(ResourceUtil.getInstance().getColorById(R.color.color_00aff0));
				tv_duizhang_phohe.setClickable(true);
			}else {
				tv_duizhang_phohe.setTextColor(ResourceUtil.getInstance().getColorById(R.color.black));
				tv_duizhang_phohe.setClickable(false);
			}

			if (StringUtil.checkPhone(getTextView(tv_first_shuji_phone))) { //第一书记电话
				tv_first_shuji_phone.setTextColor(ResourceUtil.getInstance().getColorById(R.color.color_00aff0));
				tv_first_shuji_phone.setClickable(true);
			}else {
				tv_first_shuji_phone.setTextColor(ResourceUtil.getInstance().getColorById(R.color.black));
				tv_first_shuji_phone.setClickable(false);
			}
			
			if (StringUtil.checkPhone(getTextView(tv_cunzhishu_phone))) { //村支书电话
				tv_cunzhishu_phone.setTextColor(ResourceUtil.getInstance().getColorById(R.color.color_00aff0));
				tv_cunzhishu_phone.setClickable(true);
			}else {
				tv_cunzhishu_phone.setTextColor(ResourceUtil.getInstance().getColorById(R.color.black));
				tv_cunzhishu_phone.setClickable(false);
			}

			if (StringUtil.checkPhone(getTextView(tv_phone))) { //负责人电话
				tv_phone.setTextColor(ResourceUtil.getInstance().getColorById(R.color.color_00aff0));
				tv_phone.setClickable(true);
			}else {
				tv_phone.setTextColor(ResourceUtil.getInstance().getColorById(R.color.black));
				tv_phone.setClickable(false);
			}
		}
	}

	@OnClick({R.id.rl_dutycompany,R.id.tv_first_shuji_phone,R.id.tv_cunzhishu_phone,R.id.tv_phone,R.id.tv_duizhang_phohe})
	public void onClick(View v){
		if (v.getId() == R.id.rl_dutycompany) {
			List<HelpCompany> list = lBases.getDutycompany();
			if (list!= null) {
				if (list.size()>0) {
					DutyCompanyDialog dialog = new DutyCompanyDialog();
					Bundle bundle = new Bundle();
					bundle.putSerializable(Common.BUNDEL_KEY, (Serializable) lBases.getDutycompany());
					dialog.setArguments(bundle);
					dialog.show(getFragmentManager(), "PoorVillageBaseFragment");
				}else {
					ShowToast("没有帮扶单位数据");
				}
			}else {
				ShowToast("没有帮扶单位数据");
			}
		}else if (R.id.tv_first_shuji_phone == v.getId()) { //第一书记电话
			showPhoneDialog(getTextView(tv_first_shuji_phone));
		}else if (R.id.tv_cunzhishu_phone == v.getId()) { //村支书电话
			showPhoneDialog(getTextView(tv_cunzhishu_phone));
		}else if (R.id.tv_phone == v.getId()) { //负责人电话
			showPhoneDialog(getTextView(tv_phone));
		}else if (R.id.tv_duizhang_phohe == v.getId()) { //队长电话
			showPhoneDialog(getTextView(tv_duizhang_phohe));
		}
	}
}
