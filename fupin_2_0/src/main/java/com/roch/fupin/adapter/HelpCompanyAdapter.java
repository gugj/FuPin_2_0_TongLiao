/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.HelpCompany;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 帮扶单位
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月25日 
 *
 */
public class HelpCompanyAdapter extends BaseAdapter{


	Context mContext;
	List<HelpCompany> list;
	/**
	 * 
	 */
	public HelpCompanyAdapter(Context mContext,List<HelpCompany> list) {
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
	public HelpCompany getItem(int position) {
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
			view = inflater.inflate(R.layout.listview_helpcompany_list_item, parent, false);
			viewHolder = new ViewHolder();
			ViewUtils.inject(viewHolder, view);
			view.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) view.getTag();
		}
		HelpCompany helpCompany = getItem(position);
		if (helpCompany != null) {
			viewHolder.tv_name.setText(helpCompany.typename);
			viewHolder.tv_company.setText(helpCompany.name);
			viewHolder.tv_phone.setText(helpCompany.phone);
			viewHolder.tv_xzq.setText(helpCompany.adl_nm);
		}
		return view;
	}

	static class ViewHolder{
		@ViewInject(R.id.tv_name)//领导
		TextView tv_name;
		@ViewInject(R.id.tv_phone) //电话
		TextView tv_phone;
		@ViewInject(R.id.tv_company)//单位
		TextView tv_company;
		@ViewInject(R.id.tv_xingzhengqu)//行政区
		TextView tv_xzq;
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
	public void addList(List<HelpCompany> lPoorHouses) {
		// TODO Auto-generated method stub
		list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

	/**
	 * 刷新数据
	 *
	 * @param helpCompanies
	 *
	 * 2016年6月30日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void onRefsh(List<HelpCompany> helpCompanies) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.list.addAll(helpCompanies);
		notifyDataSetChanged();
	}

}
