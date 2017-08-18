package com.roch.fupin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.NoPoorProjectCaiZhengJu_BFMX_Adapter;
import com.roch.fupin.entity.ProjectCaizhengjuBofuAppModel;
import com.roch.fupin.utils.Common;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 拨付明细
 * @author ZhaoDongShao
 *   2016年5月27日
 */
public class CaiZhengJu_BfqkFragment extends BaseFragment {

    @ViewInject(R.id.lv_poor)
    ListView listview;

    Context mContext;
    Activity mActivity;

    NoPoorProjectCaiZhengJu_BFMX_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poorhouse_familypeople, container, false);
        ViewUtils.inject(this, view);
        this.mContext = getActivity();
        this.mActivity = getActivity();
        initData();
        return view;
    }

    /**
     * 2016年5月25日
     * ZhaoDongShao
     */
    @SuppressWarnings("unchecked")
    private void initData() {
        Bundle bundle = getArguments();
        List<ProjectCaizhengjuBofuAppModel> appModel = (List<ProjectCaizhengjuBofuAppModel>) bundle.getSerializable(Common.BUNDEL_KEY);
        if (appModel != null) {
            adapter = new NoPoorProjectCaiZhengJu_BFMX_Adapter(mContext, appModel);
            listview.setAdapter(adapter);
        }
    }
}
