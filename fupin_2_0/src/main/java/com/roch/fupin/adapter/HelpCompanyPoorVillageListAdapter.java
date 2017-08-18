/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.PoorVillageBase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author ZhaoDongShao
 *
 * 2016年5月26日 
 *
 */
public class HelpCompanyPoorVillageListAdapter extends BaseAdapter{

	Context mContext;
	List<PoorVillageBase> list;
	/**
	 * 
	 */
	public HelpCompanyPoorVillageListAdapter(Context mContext,List<PoorVillageBase> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.mContext = mContext;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list != null ? list.size() : 0;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public PoorVillageBase getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_helpcompany_poorvillage_list_item, parent, false);
			viewHolder = new ViewHolder();
			ViewUtils.inject(viewHolder, view);
			view.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) view.getTag();
		}
		PoorVillageBase poorVillageBase = getItem(position);
		if (poorVillageBase != null) {
			
			viewHolder.tv_name.setText(poorVillageBase.getVillagename());
			viewHolder.tv_address.setText(poorVillageBase.getCountryname());
			viewHolder.tv_family_count.setText("贫困户数"+poorVillageBase.getPopulationnumber()+"户");
		}
		return view;
	}

	static class ViewHolder{
		@ViewInject(R.id.tv_name)
		TextView tv_name;
		@ViewInject(R.id.tv_address)
		TextView tv_address;
		@ViewInject(R.id.tv_family_count)
		TextView tv_family_count;//家庭总人数
	}

	/**
	 *
	 * @param lPoorHouses
	 *
	 * 2016年5月26日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void addList(List<PoorVillageBase> lPoorHouses) {
		// TODO Auto-generated method stub
		list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}
	
}
