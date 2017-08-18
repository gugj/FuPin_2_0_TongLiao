/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.roch.fupin.entity.HelpCompany;
import com.roch.fupin.photo.ViewHolder;
import com.roch.fupin_2_0.R;

import android.content.Context;

/**
 * @author ZhaoDongShao
 *
 * 2016年7月12日 
 *
 */
public class DutyCompanyAdapter extends CommonAdapter<HelpCompany>{

	/**
	 * @param context
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public DutyCompanyAdapter(Context context, List<HelpCompany> mDatas, int itemLayoutId) {
		super(context, mDatas, itemLayoutId);
	}

	@Override
	public void convert(ViewHolder helper, HelpCompany item, int position) {
		// TODO Auto-generated method stub
		helper.setText(R.id.tv_xingzhengqu, item.adl_nm);
		helper.setText(R.id.tv_name, item.name);
		helper.setText(R.id.tv_phone, item.phone);
		helper.setText(R.id.tv_leixing, item.typename);
	}

}
