package com.roch.fupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.HuiFuXinXi;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 回复信息adapter
 * @author ZhaoDongShao
 * 2016年5月26日
 */
public class HuiFuXinXiAdapter extends BaseAdapter{

	List<HuiFuXinXi> list;
	Context mContext;

	public HuiFuXinXiAdapter(Context mContext, List<HuiFuXinXi> list) {
		this.list = list;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
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
			view = inflater.inflate(R.layout.listview_huifuxinxi_item, parent, false);
			viewHolder = new ViewHolder();
			ViewUtils.inject(viewHolder, view);
			view.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) view.getTag();
		}

		//赋值
		HuiFuXinXi people = (HuiFuXinXi) getItem(position);
		if (people != null) {
			viewHolder.tv_data.setText(people.getTs()); //回复时间
			viewHolder.tv_name.setText(people.getReplyname()); //回复人
			viewHolder.tv_content.setText(people.getDetail()); //回复内容
		}
		return view;
	}

	static class ViewHolder{
		@ViewInject(R.id.tv_name)
		TextView tv_name;
		@ViewInject(R.id.tv_date)
		TextView tv_data;
		@ViewInject(R.id.tv_content)
		TextView tv_content;//内容
	}

	/**
	 * @param huiFuXinXis
	 * 2016年6月16日
	 * ZhaoDongShao
	 */
	public void onRefsh(List<HuiFuXinXi> huiFuXinXis) {
		this.list.clear();
		this.list.addAll(huiFuXinXis);
		notifyDataSetChanged();
	}

	/**
	 * @param huiFuXinXis
	 * 2016年6月16日
	 * ZhaoDongShao
	 */
	public void addList(List<HuiFuXinXi> huiFuXinXis) {
		this.list.addAll(huiFuXinXis);
		notifyDataSetChanged();
	}
}
