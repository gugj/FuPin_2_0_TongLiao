/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.HelpPeople;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 帮扶责任人
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月25日 
 *
 */
public class HelpPeopleListAdapter extends BaseAdapter{


	Context mContext;
	List<HelpPeople> list;
	/**
	 * 
	 */
	public HelpPeopleListAdapter(Context mContext,List<HelpPeople> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list != null ? list.size() : 0;
	}

	@Override
	public HelpPeople getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_helppeople_list_item, parent, false);
			viewHolder = new ViewHolder();
			ViewUtils.inject(viewHolder, view);
			view.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) view.getTag();
		}
		HelpPeople people = getItem(position);
		if (people != null) {
			
			viewHolder.tv_company.setText(people.getCompanyname());
			viewHolder.tv_zhiwei.setText(people.getTitle());
			viewHolder.tv_name.setText(people.getName());
			viewHolder.tv_phone.setText("电话:" + people.getPhone());

		}
		return view;
	}

	static class ViewHolder{
		@ViewInject(R.id.tv_name)
		TextView tv_name;
		@ViewInject(R.id.tv_phone)
		TextView tv_phone;
		@ViewInject(R.id.tv_zhiwei)
		TextView tv_zhiwei;//职位
		@ViewInject(R.id.tv_company)
		TextView tv_company;
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
	public void addList(List<HelpPeople> lPoorHouses) {
		// TODO Auto-generated method stub
		list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

	/**
	 *
	 * @param lPoorHouses
	 *
	 * 2016年7月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void onRefsh(List<HelpPeople> lPoorHouses) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

}
