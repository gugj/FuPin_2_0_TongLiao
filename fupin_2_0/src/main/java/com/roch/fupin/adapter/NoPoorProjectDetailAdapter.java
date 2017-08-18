/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.ViewItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 项目详情页适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectDetailAdapter extends BaseAdapter{

	/**
	 * 布局一
	 */
	final int ITEM_TYPE_ONE = 0;
	/**
	 * 布局二
	 */
	final int ITEM_TYPE_TOW = 1;
	/**
	 * 布局三
	 */
	final int ITEM_TYPE_THREE = 2;

	private LayoutInflater inflater;
	private Context mContext;

	private List<ViewItem> mItems;
	/**
	 * 
	 */
	public NoPoorProjectDetailAdapter(Context mContext, List<ViewItem> mItems) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.mItems = mItems;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder_ONE viewHolder_ONE = null;
		ViewHolder_TOW viewHolder_TOW = null;
		ViewHolder_THREE viewHolder_THREE = null;

		int type = getItemViewType(position);
		if (convertView == null) {
			inflater = LayoutInflater.from(mContext);
			switch (type) {
			case ITEM_TYPE_ONE:
				convertView = inflater.inflate(R.layout.listview_no_poor_project_list_item_one, parent,false);
				viewHolder_ONE = new ViewHolder_ONE();
				viewHolder_ONE.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
				convertView.setTag(viewHolder_ONE);
				break;
			case ITEM_TYPE_TOW:
				convertView = inflater.inflate(R.layout.listview_no_poor_project_list_item_tow, parent,false);
				viewHolder_TOW = new ViewHolder_TOW();
				viewHolder_TOW.tv_key = (TextView)convertView.findViewById(R.id.tv_key);
				viewHolder_TOW.tv_value = (TextView)convertView.findViewById(R.id.tv_value);
				convertView.setTag(viewHolder_TOW);
				break;
			case ITEM_TYPE_THREE:
				convertView = inflater.inflate(R.layout.listview_no_poor_project_list_item_three, parent,false);
				viewHolder_THREE = new ViewHolder_THREE();
				viewHolder_THREE.tv_key = (TextView)convertView.findViewById(R.id.tv_key);
				viewHolder_THREE.tv_value = (TextView)convertView.findViewById(R.id.tv_value);
				convertView.setTag(viewHolder_THREE);
				break;
			default:
				break;
			}
		}else {
			switch (type) {  
			case ITEM_TYPE_ONE:  
				viewHolder_ONE = (ViewHolder_ONE) convertView.getTag();  
				break;  
			case ITEM_TYPE_TOW:  
				viewHolder_TOW = (ViewHolder_TOW) convertView.getTag();  
				break;  
			case ITEM_TYPE_THREE:  
				viewHolder_THREE = (ViewHolder_THREE) convertView.getTag();  
				break;  
			}  
		}
		ViewItem item = null;
		// 设置资源  
		switch (type) {  
		case ITEM_TYPE_ONE:

			item = (ViewItem)getItem(position);
			if (item != null) {
				MapEntity map = item.getMap();
				viewHolder_ONE.tv_name.setText(map.getKey()); 
			}
			break;  
		case ITEM_TYPE_TOW:  

			item = (ViewItem)getItem(position);
			if (item != null) {
				MapEntity map = item.getMap();
				viewHolder_TOW.tv_key.setText(map.getKey());
				viewHolder_TOW.tv_value.setText(map.getValue());
			}

			break;  
		case ITEM_TYPE_THREE:  

			item = (ViewItem)getItem(position);
			if (item != null) {
				MapEntity map = item.getMap();
				viewHolder_THREE.tv_key.setText(map.getKey());
				viewHolder_THREE.tv_value.setText(map.getValue());
			}

			break;  
		}  
		return convertView;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return mItems.get(position).getType();
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	static class ViewHolder_ONE
	{
		TextView tv_name;
	}

	static class ViewHolder_TOW
	{
		TextView tv_key;
		TextView tv_value;
	}

	static class ViewHolder_THREE
	{
		TextView tv_key;
		TextView tv_value;
	}
}
