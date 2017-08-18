package com.roch.fupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.Menu;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.FileUtils;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 自定义的Menu菜单的适配器类，继承自BaseAdapter
 * @author ZhaoDongShao
 * 2016年11月3日 
 */
public class MenuListAdapter extends BaseAdapter {

	List<Menu> list;
	Context mContext;
	private BitmapUtils utils;

	public MenuListAdapter(List<Menu> list, Context context) {
		this.list = list;
		this.mContext = context;

		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int chcheSize = maxMemory / 8;
		FileUtils fileUtil = new FileUtils(context, Common.CACHE_DIR);
		utils = new BitmapUtils(context, fileUtil.getCacheDir(), chcheSize);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Menu getItem(int position) {
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
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.menu_list_item, parent, false);
			ViewUtils.inject(holderView, view);
			view.setTag(holderView);
		} else {
			holderView = (HolderView) view.getTag();
		}

		//赋值
		Menu item = getItem(position);
		if (!item.equals("") && item != null) {
			holderView.tv_name.setText(item.getName());
			utils.display(holderView.iv_icon, URLs.IMAGE_URL + item.getIcon());
		}
		return view;
	}

	class HolderView {
		@ViewInject(R.id.tv_name)
		TextView tv_name;
		@ViewInject(R.id.iv_icon)
		ImageView iv_icon;
	}
}
