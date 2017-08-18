package com.roch.fupin;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.roch.fupin.dialog.DutyPersonDialog;
import com.roch.fupin.entity.DutyPerson;
import com.roch.fupin.entity.PoorFamilyBase;
import com.roch.fupin.entity.PoorFamilyBase_ResultList;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;

import java.io.Serializable;
import java.util.List;

/**
 * 贫困户基本情况fragment
 *
 * @author ZhaoDongShao
 *         2016年5月9日
 */
public class PoorHouseBaseFragment_SaoYiSao extends BaseFragment {

//	@ViewInject(R.id.tv_poor)
//	TextView tv_poor;  //脱贫情况
//	@ViewInject(R.id.tv_phone)
//	TextView tv_phone;  //户主电话
//	@ViewInject(R.id.tv_num)
//	TextView tv_num; //家庭人数
//	@ViewInject(R.id.tv_plan)
//	TextView tv_plan; //脱贫计划
//	@ViewInject(R.id.tv_cause)
//	TextView tv_cause; //致贫原因
//	@ViewInject(R.id.tv_train_num)
//	TextView tv_job_num; //就业人数
//	@ViewInject(R.id.rl_dutyperson)
//	RelativeLayout rl_zeren_people; //责任人
//
//	@ViewInject(R.id.tv_poorfamilypropert)
//	TextView tv_poorfamilypropert;
//	@ViewInject(R.id.tv_ifooperative)
//	TextView tv_ifooperative;
//	@ViewInject(R.id.tv_productionpower)
//	TextView tv_productionpower;
//	@ViewInject(R.id.tv_housesecurity)
//	TextView tv_housesecurity;
//	@ViewInject(R.id.tv_ifagreetomove)
//	TextView tv_ifagreetomove;
//	@ViewInject(R.id.tv_housingtype)
//	TextView tv_housingtype;
//	@ViewInject(R.id.tv_ifnoroom)
//	TextView tv_ifnoroom;
//
//	@ViewInject(R.id.water_is_aq)
//	TextView tv_water_is_aq;
//	@ViewInject(R.id.house_is_d)
//	TextView tv_house_is_d;
//	@ViewInject(R.id.woter_is_kn)
//	TextView tv_water_is_kn;
//	@ViewInject(R.id.house_area)
//	TextView tv_house_area;

    PoorFamilyBase lBases = null;
    @ViewInject(R.id.tv_xingzhengqu)
    private TextView tv_xingzhengqu;

    @ViewInject(R.id.tv_poor)
    private TextView tv_poor;

    @ViewInject(R.id.tv_phone)
    private TextView tv_phone;

    @ViewInject(R.id.tv_num)
    private TextView tv_num;

    @ViewInject(R.id.tv_poorfamilypropert)
    private TextView tv_poorfamilypropert;

    @ViewInject(R.id.tv_plan)
    private TextView tv_plan;

    @ViewInject(R.id.tv_cause)
    private TextView tv_cause;

    @ViewInject(R.id.tv_cause_qita)
    private TextView tv_cause_qita;

    @ViewInject(R.id.tv_junlieshu)
    private TextView tv_junlieshu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poorhouse_base_saoyisao, container, false);
        ViewUtils.inject(this, view);
        initData();
        initView(view);
        return view;
    }

    /**
     * 2016年5月17日
     * ZhaoDongShao
     */
    private void initData() {
        Bundle bundle = getArguments();
        if (!StringUtil.isEmpty(bundle)) {
            String str1 = bundle.getString("str1");
            if (!StringUtil.isEmpty(str1)) {
                System.out.println("str1===" + str1);
                PoorFamilyBase_ResultList poorFamilyBase_resultList = PoorFamilyBase_ResultList.parseToT(str1, PoorFamilyBase_ResultList.class);
                if (poorFamilyBase_resultList.getSuccess()) {
                    List<PoorFamilyBase> jsondata = poorFamilyBase_resultList.getJsondata();
                    if (StringUtil.isNotEmpty(jsondata) && jsondata.size() > 0) {
                        lBases = jsondata.get(0);
                        tv_xingzhengqu.setText(lBases.getLocation()); //行政区  1
                        tv_poor.setText(lBases.getPovertystatustext()); //脱贫情况  3
                        tv_cause.setText(lBases.getPp_poorreason()); //主要致贫原因  6
                        tv_cause_qita.setText(lBases.getSe_poorreason()); //其他致贫原因 7
                        tv_num.setText(String.valueOf(lBases.getHousecount())); //家庭人口  5
                        tv_phone.setText(lBases.getPhone()); //户主电话
                        tv_plan.setText(lBases.getHelpplan()); //帮扶措施  8
                        tv_poorfamilypropert.setText(lBases.getPoorfamilyproperttest()); // 贫困户属性  2
                        tv_junlieshu.setText(lBases.getIfjunlieshutext()); //是否军烈属  9

                        if (StringUtil.checkPhone(getTextView(tv_phone))) {  // 4
                            tv_phone.setTextColor(ResourceUtil.getInstance().getColorById(R.color.color_00aff0));
                            tv_phone.setClickable(true);
                        } else {
                            tv_phone.setTextColor(ResourceUtil.getInstance().getColorById(R.color.black));
                            tv_phone.setClickable(false);
                        }
                    }
                }
            }
        }
    }

    @OnClick({R.id.tv_phone, R.id.rl_dutyperson})
    public void onClick(View v) {
        if (v.getId() == R.id.tv_phone) {
            showPhoneDialog(getTextView(tv_phone));
        } else if (v.getId() == R.id.rl_dutyperson) {
            List<DutyPerson> list = lBases.getDutyPerson();
            if (list != null && list.size() > 0) {
                DutyPersonDialog dialog = new DutyPersonDialog();
                Bundle bundle = new Bundle();
                bundle.putSerializable(Common.BUNDEL_KEY, (Serializable) lBases.getDutyPerson());
                dialog.setArguments(bundle);
                dialog.show(getFragmentManager(), "PoorHouseBaseFragment");
            } else {
                ShowToast("没有帮扶责任人数据");
            }
        }
    }

    private void initView(View view) {
        tv_xingzhengqu = (TextView) view.findViewById(R.id.tv_xingzhengqu);
        tv_poor = (TextView) view.findViewById(R.id.tv_poor);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        tv_num = (TextView) view.findViewById(R.id.tv_num);
        tv_poorfamilypropert = (TextView) view.findViewById(R.id.tv_poorfamilypropert);
        tv_plan = (TextView) view.findViewById(R.id.tv_plan);
        tv_cause = (TextView) view.findViewById(R.id.tv_cause);
        tv_cause_qita = (TextView) view.findViewById(R.id.tv_cause_qita);
        tv_junlieshu = (TextView) view.findViewById(R.id.tv_junlieshu);
    }
}
