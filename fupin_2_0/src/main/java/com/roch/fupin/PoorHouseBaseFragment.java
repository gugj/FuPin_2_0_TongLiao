
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
import com.roch.fupin.dialog.DutyPersonDialog;
import com.roch.fupin.entity.DutyPerson;
import com.roch.fupin.entity.PoorFamilyBase;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;

import java.io.Serializable;
import java.util.List;

/**
 * 贫困户基本情况fragment
 * @author ZhaoDongShao
 * 2016年5月9日
 */
public class PoorHouseBaseFragment extends BaseFragment {

	@ViewInject(R.id.tv_poor)
	TextView tv_poor;  //脱贫情况
	@ViewInject(R.id.tv_phone)
	TextView tv_phone;  //户主电话
	@ViewInject(R.id.tv_num)
	TextView tv_num; //家庭人数
	@ViewInject(R.id.tv_plan)
	TextView tv_plan; //脱贫计划
	@ViewInject(R.id.tv_cause)
	TextView tv_cause; //致贫原因
	@ViewInject(R.id.tv_train_num)
	TextView tv_job_num; //就业人数
	@ViewInject(R.id.rl_dutyperson)
	RelativeLayout rl_zeren_people; //责任人

	@ViewInject(R.id.tv_poorfamilypropert)
	TextView tv_poorfamilypropert;
	@ViewInject(R.id.tv_ifooperative)
	TextView tv_ifooperative;
	@ViewInject(R.id.tv_productionpower)
	TextView tv_productionpower;
//	@ViewInject(R.id.tv_housesecurity)
//	TextView tv_housesecurity;
//	@ViewInject(R.id.tv_ifagreetomove)
//	TextView tv_ifagreetomove;
//	@ViewInject(R.id.tv_housingtype)
//	TextView tv_housingtype;
//	@ViewInject(R.id.tv_ifnoroom)
//	TextView tv_ifnoroom;
	
//	@ViewInject(R.id.water_is_aq)
//	TextView tv_water_is_aq;
//	@ViewInject(R.id.house_is_d)
//	TextView tv_house_is_d;
//	@ViewInject(R.id.woter_is_kn)
//	TextView tv_water_is_kn;
//	@ViewInject(R.id.house_area)
//	TextView tv_house_area;
	
	/**
	 * 标志位，标志初始化已经完成
	 */
	private boolean isPrepared;
	PoorFamilyBase lBases = null;
	/**
	 * 标识当前fragment是否可见
	 */
	private boolean isVisible;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_poorhouse_base, container, false);
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
		if (!isPrepared || !isVisible) { //如果没有走onCreate()进行初始化或者当前fragment不可见
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
			lBases = (PoorFamilyBase) bundle.getSerializable(Common.BUNDEL_KEY);
			if (!StringUtil.isEmpty(lBases)) {
				tv_poor.setText(lBases.getTpname()); //脱贫情况
				tv_cause.setText(lBases.getPp_poorreason()); //主要致贫原因
				tv_num.setText(String.valueOf(lBases.getHousecount())); //家庭人口
				tv_phone.setText(lBases.getPhone()); //户主电话
				tv_plan.setText(lBases.getHelpplan()); //帮扶措施
				tv_job_num.setText(lBases.getSe_poorreason()); //其他致贫原因
				tv_poorfamilypropert.setText(lBases.getPkhsxname()); //贫困户属性
				tv_ifooperative.setText(lBases.getLocation()); //所属行政区
				tv_productionpower.setText(lBases.getIfjunlieshutext()); //是否军烈属

//				tv_housesecurity.setText(lBases.getHousesecuritytext());//住房安全
//				tv_ifagreetomove.setText(lBases.getIfagreetomove());//
//				tv_housingtype.setText(lBases.getHousingtypetext());// 房屋类型
//				tv_ifnoroom.setText(lBases.getIfnoroom());//是否无房
//				tv_water_is_aq.setText(lBases.getWatersafe());
//				tv_water_is_kn.setText(lBases.getWatertrouble());
//				//家里是否通电
//				tv_house_is_d.setText(lBases.getIselectricity());
////				tv_house_is_poor.setText(lBases.getHousesafe());
//				tv_house_area.setText(lBases.getHousearea());
				
				if (StringUtil.checkPhone(getTextView(tv_phone))) {
					tv_phone.setTextColor(ResourceUtil.getInstance().getColorById(R.color.color_00aff0));
					tv_phone.setClickable(true);
				}else {
					tv_phone.setTextColor(ResourceUtil.getInstance().getColorById(R.color.black));
					tv_phone.setClickable(false);
				}
			}
		}
	}

	@OnClick({R.id.tv_phone,R.id.rl_dutyperson})
	public void onClick(View v){
		if (v.getId() == R.id.tv_phone) {
			showPhoneDialog(getTextView(tv_phone));
		}else if (v.getId() == R.id.rl_dutyperson) {
			List<DutyPerson> list = lBases.getDutyPerson();
			if (list != null && list.size() >0 ) {
				DutyPersonDialog dialog = new DutyPersonDialog();
				Bundle bundle = new Bundle();
				bundle.putSerializable(Common.BUNDEL_KEY, (Serializable) lBases.getDutyPerson());
				dialog.setArguments(bundle);
				dialog.show(getFragmentManager(), "PoorHouseBaseFragment");
			}else {
				ShowToast("没有帮扶责任人数据");
			}
		}
	}
}
