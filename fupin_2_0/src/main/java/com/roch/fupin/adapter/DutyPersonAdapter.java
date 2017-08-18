/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.roch.fupin.entity.DutyPerson;
import com.roch.fupin.photo.ViewHolder;
import com.roch.fupin_2_0.R;

import android.content.Context;

/**
 * @author ZhaoDongShao
 *
 * 2016年7月12日 
 *
 */
public class DutyPersonAdapter extends CommonAdapter<DutyPerson>{

	/**
	 * @param context
	 * @param mDatas
	 * @param itemLayoutId
	 */
	public DutyPersonAdapter(Context context, List<DutyPerson> mDatas, int itemLayoutId) {
		super(context, mDatas, itemLayoutId);
	}

	@Override
	public void convert(ViewHolder helper, DutyPerson item, int position) {
		// TODO Auto-generated method stub
		helper.setText(R.id.tv_name, item.getName());
		helper.setText(R.id.tv_phone, "电话："+item.getPhone());
		helper.setText(R.id.tv_zhiwei, "职位："+item.getTitle());
		helper.setText(R.id.tv_company, item.getCompanyname());
	}

}
