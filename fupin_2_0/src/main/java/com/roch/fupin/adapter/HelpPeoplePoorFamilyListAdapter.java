/**
 * 
 */
package com.roch.fupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.PoorFamilyBase;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 帮扶责任人负责的贫困户
 * @author ZhaoDongShao
 * 2016年5月25日
 */
public class HelpPeoplePoorFamilyListAdapter extends BaseAdapter{

	Context mContext;
	List<PoorFamilyBase> list;

	public HelpPeoplePoorFamilyListAdapter(Context mContext,List<PoorFamilyBase> list) {
		this.list = list;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public PoorFamilyBase getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_helppeople_poorfamily_list_item, parent, false);
			viewHolder = new ViewHolder();
			ViewUtils.inject(viewHolder, view);
			viewHolder.cb_man.setEnabled(false);
			viewHolder.cb_woman.setEnabled(false);
			view.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) view.getTag();
		}
		PoorFamilyBase poorFamilyBase = getItem(position);
		if (poorFamilyBase != null) {
			if (poorFamilyBase.getSextext() != null && poorFamilyBase.getSextext().equals("男")) {
				viewHolder.cb_man.setChecked(true);
				viewHolder.cb_woman.setChecked(false);
			}else if (poorFamilyBase.getSextext() != null && poorFamilyBase.getSextext().equals("女")) {
				viewHolder.cb_man.setChecked(false);
				viewHolder.cb_woman.setChecked(true);
			}
			viewHolder.tv_name.setText(poorFamilyBase.getPersonname());
			viewHolder.tv_address.setText(poorFamilyBase.getLocation() + poorFamilyBase.getGroupname());
			viewHolder.tv_people_count.setText("家庭人口"+poorFamilyBase.getHousecount()+"人");
		}
		return view;
	}

	static class ViewHolder{
		@ViewInject(R.id.tv_name)
		TextView tv_name;
		@ViewInject(R.id.cb_man)
		CheckBox cb_man;
		@ViewInject(R.id.cb_woman)
		CheckBox cb_woman;
		@ViewInject(R.id.tv_address)
		TextView tv_address;
		@ViewInject(R.id.tv_people_count)
		TextView tv_people_count;//家庭总人数
	}

	/**
	 * @param lPoorHouses
	 * 2016年5月26日
	 * ZhaoDongShao
	 */
	public void addList(List<PoorFamilyBase> lPoorHouses) {
		list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

}
