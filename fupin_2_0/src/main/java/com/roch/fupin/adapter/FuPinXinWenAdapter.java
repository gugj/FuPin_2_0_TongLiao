/**
 * 
 */
package com.roch.fupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.FuPinXinWen;
import com.roch.fupin.utils.HTMLUtils;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年5月26日
 */
public class FuPinXinWenAdapter extends BaseAdapter{

	List<FuPinXinWen> list;
	Context mContext;

	public FuPinXinWenAdapter(Context mContext, List<FuPinXinWen> list) {
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
			view = inflater.inflate(R.layout.listview_information_list_item, parent, false);
			viewHolder = new ViewHolder();
			ViewUtils.inject(viewHolder, view);
			view.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) view.getTag();
		}

		//赋值
		FuPinXinWen people = (FuPinXinWen) getItem(position);
		if (people != null) {
			String date=people.getTs();
			String[] split = date.split(" ");
			viewHolder.tv_data.setText(split[0]);
			viewHolder.tv_title.setText(people.getTitle());
			viewHolder.tv_name.setText(people.getHouseholderNm());
			String msg = HTMLUtils.delTag(people.getDetail());
			viewHolder.tv_content.setText(msg);
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
		@ViewInject(R.id.tv_title)
		TextView tv_title;//职位
	}

	/**
	 * @param fuPinXinWens
	 * 2016年6月16日
	 * ZhaoDongShao
	 */
	public void onRefsh(List<FuPinXinWen> fuPinXinWens) {
		this.list.clear();
		this.list.addAll(fuPinXinWens);
		notifyDataSetChanged();
	}

	/**
	 * @param fuPinXinWens
	 * 2016年6月16日
	 * ZhaoDongShao
	 */
	public void addList(List<FuPinXinWen> fuPinXinWens) {
		this.list.addAll(fuPinXinWens);
		notifyDataSetChanged();
	}
}
