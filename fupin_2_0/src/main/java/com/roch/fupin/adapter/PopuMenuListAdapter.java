package com.roch.fupin.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.ListMenu;
import com.roch.fupin_2_0.R;

import java.util.List;

public class PopuMenuListAdapter extends BaseAdapter{

	List<ListMenu> list;
	Context mContext;

	public PopuMenuListAdapter(List<ListMenu> list, Context context) {
		this.list = list;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public ListMenu getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holderView = null;
		View view = convertView;
		if (view == null) {
			holderView = new HolderView();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			view = inflater.inflate(R.layout.popu_menu_list_item, parent,false);
			holderView.tv_name = (TextView)view.findViewById(R.id.tv_name);
			view.setTag(holderView);
		}else {
			holderView = (HolderView)view.getTag();
		}

		ListMenu item = getItem(position);
		if (!item.equals("") && item != null) {
			holderView.tv_name.setText(item.getName());
			if(item.getRid()!=0){ //如果等于0，即没有设置实例照片，只有文字显示
				Drawable drawable = mContext.getResources().getDrawable(item.getRid());
				drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
				holderView.tv_name.setCompoundDrawables(drawable, null, null, null);
			}
		}
		return view;
	}

	class HolderView {
		@ViewInject(R.id.tv_name)
		TextView tv_name;
	}

}
