package com.roch.fupin.adapter;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.utils.ResourceUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FilterPopuGridViewAdapter extends BaseAdapter {

	private List<String> list = new ArrayList<String>();
	private Context context;
	
	//状态标示位
	private int ClickTemp = -1;
	
	
	public void setSelection(int position){
		
		ClickTemp = position;
		
	}
	
	public FilterPopuGridViewAdapter(List<String> list,Context context)
	{
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public String getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		
		HolderView holderView = null;
		View view = arg1;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.filter_popu_gridview_item, arg2, false);
			holderView = new HolderView();
			ViewUtils.inject(holderView,view);
			view.setTag(holderView);
		}
		else {
			holderView = (HolderView)view.getTag();
		}
		
		String cityString = getItem(position);
		if (cityString != null && !"".equals(cityString)) {
			holderView.textview.setText(cityString);
		}
		if (ClickTemp == position) {
			holderView.linearLayout.setBackgroundResource(R.drawable.setector_city_gridview_item_bg_pressed);
			holderView.textview.setTextColor(ResourceUtil.getInstance().getColorById(R.color.color_00aff0));
		}else {
			holderView.linearLayout.setBackgroundResource(R.drawable.setector_city_gridview_item_bg);
			holderView.textview.setTextColor(ResourceUtil.getInstance().getColorById(R.color.black));
		}
		return view;
	}

	class HolderView
	{
		@ViewInject(R.id.tv_name)
		TextView textview;
		@ViewInject(R.id.ll)
		LinearLayout linearLayout;
	}
	/**
	 * 根据传递的name获取其索引值
	 *
	 * @param name
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void setSelectionPosition(String name){
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(name)) {
				ClickTemp = i;
				break;
			}
		}
		notifyDataSetChanged();
	}
}
