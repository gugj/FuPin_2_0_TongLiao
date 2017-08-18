package com.roch.fupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.roch.fupin.entity.GongZuoRiZhi;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.FileUtils;
import com.roch.fupin.utils.LogUtil;
import com.roch.fupin_2_0.R;

import java.util.List;

public class GongZuoRiZhi_GV_Adapter extends BaseAdapter {

	private List<GongZuoRiZhi> gongZuoRiZhis;
	private Context context;
	private BitmapUtils utils;

	public GongZuoRiZhi_GV_Adapter(Context context, List<GongZuoRiZhi> gongZuoRiZhis) {
		this.context = context;
		this.gongZuoRiZhis = gongZuoRiZhis;
		
		int maxMemory = (int)Runtime.getRuntime().maxMemory();
		int chcheSize = maxMemory / 8;
		FileUtils fileUtil = new FileUtils(context, Common.CACHE_DIR);
		utils = new BitmapUtils(context, fileUtil.getCacheDir(), chcheSize);
	}

	@Override
	public int getCount() {
		return gongZuoRiZhis == null ? 0 : gongZuoRiZhis.size();
	}

	@Override
	public Object getItem(int position) {
		if (gongZuoRiZhis != null && gongZuoRiZhis.size() != 0) {
			return gongZuoRiZhis.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, final View convertView, ViewGroup parent) {
		HolderView holderView = null;
		View view = null;
		if (view == null) {
			holderView = new HolderView();
			view = LayoutInflater.from(context).inflate(R.layout.item_gongzuorizhi_gv, parent,false);
			holderView.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
			holderView.tv_name = (TextView) view.findViewById(R.id.tv_name);
			holderView.tv_name2 = (TextView) view.findViewById(R.id.tv_name2);
			holderView.tv_date = (TextView) view.findViewById(R.id.tv_date);
			holderView.tv_location = (TextView) view.findViewById(R.id.tv_location);
			holderView.tv_content = (TextView) view.findViewById(R.id.tv_content);

			//重新计算icon的大小
//			LayoutParams mLayoutParams = holderView.iv_icon.getLayoutParams();
//			mLayoutParams.width = (int) (Common.Width / 8);
//			mLayoutParams.height = (int) (Common.Width / 8);
//			holderView.iv_icon.setLayoutParams(mLayoutParams);
			view.setTag(holderView);
		}else {
			holderView = (HolderView)view.getTag();
		}

		//赋值
		GongZuoRiZhi gongZuoRiZhi = (GongZuoRiZhi) getItem(position);
//		utils.display(holderView.iv_icon, gongZuoRiZhi.getHelpdetail());
		utils.display(holderView.iv_icon, gongZuoRiZhi.getImagePath());
		LogUtil.println("工作日志adapter中照片的路径为：=="+gongZuoRiZhi.getImagePath());
		holderView.tv_name.setText(gongZuoRiZhi.getPoorobject());
		holderView.tv_content.setText(gongZuoRiZhi.getHelptitle());
		holderView.tv_name2.setText(gongZuoRiZhi.getZhutiname());
		String date=gongZuoRiZhi.getHelpdate();
		String[] split = date.split(" ");
		holderView.tv_date.setText(split[0]);
		holderView.tv_location.setText(gongZuoRiZhi.getLocation());

		return view;
	}

	class HolderView {
		private TextView tv_name;
		private ImageView iv_icon;
		private TextView tv_name2;
		private TextView tv_date;
		private TextView tv_location;
		private TextView tv_content;
	}

	public void addList(List<GongZuoRiZhi> gongZuoRiZhis_add) {
		this.gongZuoRiZhis.addAll(gongZuoRiZhis_add);
		notifyDataSetChanged();
	}

	public void onRefsh(List<GongZuoRiZhi> gongZuoRiZhis_del) {
		this.gongZuoRiZhis.clear();
		this.gongZuoRiZhis.addAll(gongZuoRiZhis_del);
		notifyDataSetChanged();
	}
}