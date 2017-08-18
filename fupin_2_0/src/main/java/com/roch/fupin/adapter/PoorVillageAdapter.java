/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.PoorVillageBase;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.FileUtils;
import com.roch.fupin.view.CircleImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 贫困村适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月24日 
 *
 */
public class PoorVillageAdapter extends BaseAdapter{

	Context mContext;

	List<PoorVillageBase> poorVillages;

	BitmapUtils utils;

	public PoorVillageAdapter(Context mContext,List<PoorVillageBase> poorVillages) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.poorVillages = poorVillages;

		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cache = maxMemory / 8;
		FileUtils fileUtil = new FileUtils(mContext, Common.CACHE_DIR);
		utils = new BitmapUtils(mContext, fileUtil.getCacheDir(), cache);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (poorVillages != null) {
			return poorVillages.size();
		}
		return 0;
	}

	@Override
	public PoorVillageBase getItem(int position) {
		// TODO Auto-generated method stub
		return poorVillages != null ? poorVillages.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HolderView holderView = null;
		View view = convertView;
		if (view == null) {
			holderView = new HolderView();
			view = LayoutInflater.from(mContext).inflate(R.layout.poorvillage_itemview, parent, false);
			ViewUtils.inject(holderView,view);
			
			LayoutParams lParams = holderView.iv_photo.getLayoutParams();

			lParams.width = Common.Width / 5;
			lParams.height = Common.Width / 5;

			holderView.iv_photo.setLayoutParams(lParams);
			view.setTag(holderView);
		}else {
			holderView = (HolderView)view.getTag();
		}

		PoorVillageBase poorHouse = getItem(position);
		if (poorHouse != null) {
			if (poorHouse.getPicturePath()!=null && !poorHouse.getPicturePath().equals("")) {
				utils.display(holderView.iv_photo, poorHouse.getPicturePath());
			}
			holderView.tv_address.setText(poorHouse.getCountryname()+poorHouse.getTownName());
			holderView.tv_name.setText(poorHouse.getVillagename());
			String people_count = "共有"+poorHouse.getPopulationnumber()+"人";
			holderView.tv_people_count.setText(people_count);
			String poor_count = "贫困户"+poorHouse.getPoorhousenm()+"户";
			holderView.tv_poor_count.setText(poor_count);
			holderView.tv_phone.setText(poorHouse.getPersonphone());
			holderView.tv_zeren_people.setText(poorHouse.getPersonname());
			holderView.tv_zhiwei.setText("负责人职务：" + poorHouse.getPersontitle());
		}
		return view;
	}

	class HolderView
	{
		@ViewInject(R.id.tv_poor_count)
		TextView tv_poor_count;//贫困人数

		@ViewInject(R.id.tv_people_count)
		TextView tv_people_count;//总人数

		@ViewInject(R.id.iv_user_head)
		CircleImageView iv_photo;

		@ViewInject(R.id.tv_phone)
		TextView tv_phone;//电话
		@ViewInject(R.id.tv_zeren_people)
		TextView tv_zeren_people;//责任人

		@ViewInject(R.id.tv_name)
		TextView tv_name;//村名
		@ViewInject(R.id.tv_address)
		TextView tv_address;//所在乡
		
		@ViewInject(R.id.tv_zhiwei)
		TextView tv_zhiwei;
	}


	/**
	 * 增加刷新后的数据
	 *
	 *
	 * 2016年5月17日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void addList(List<PoorVillageBase> list)
	{
		this.poorVillages.addAll(list);
		notifyDataSetChanged();
	}

	/**
	 * 刷新数据
	 *
	 * @param lPoorHouses
	 *
	 * 2016年6月13日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void onRefsh(List<PoorVillageBase> lPoorHouses) {
		// TODO Auto-generated method stub
		this.poorVillages.clear();
		this.poorVillages.addAll(lPoorHouses);
		notifyDataSetChanged();
		
	}
}
