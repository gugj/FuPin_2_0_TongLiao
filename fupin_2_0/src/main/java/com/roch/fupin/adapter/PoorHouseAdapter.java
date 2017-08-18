package com.roch.fupin.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.PoorFamilyBase;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.FileUtils;
import com.roch.fupin.utils.ResourceUtil;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.view.CircleImageView;
import com.roch.fupin_2_0.R;

import java.util.List;

public class PoorHouseAdapter extends BaseAdapter{

	Context mContext;

	List<PoorFamilyBase> poorHouses;

	private BitmapUtils utils;
	
	public PoorHouseAdapter(Context mContext,List<PoorFamilyBase> poorHouses) {
		this.mContext = mContext;
		this.poorHouses = poorHouses;
		
		int maxMemory = (int)Runtime.getRuntime().maxMemory();
		int chcheSize = maxMemory / 8;
		FileUtils fileUtil = new FileUtils(mContext, Common.CACHE_DIR);
		utils = new BitmapUtils(mContext, fileUtil.getCacheDir(), chcheSize);
		
	}

	@Override
	public int getCount() {
		if (poorHouses != null) {
			return poorHouses.size();
		}
		return 0;
	}

	@Override
	public PoorFamilyBase getItem(int position) {
		return poorHouses != null ? poorHouses.get(position) : null;
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
			view = LayoutInflater.from(mContext).inflate(R.layout.poorhouse_itemview, parent, false);
			ViewUtils.inject(holderView, view);
//			holderView.cb_job.setClickable(false);
			holderView.cb_man.setClickable(false);
			holderView.cb_train.setClickable(false);
			holderView.cb_woman.setClickable(false);
			
			LayoutParams lParams = holderView.iv_photo.getLayoutParams();
			
			lParams.width = Common.Width / 5;
			lParams.height = Common.Width / 5;
			
			holderView.iv_photo.setLayoutParams(lParams);
			
			view.setTag(holderView);
		}else {
			holderView = (HolderView)view.getTag();
		}

		PoorFamilyBase poorHouse = getItem(position);
		if (poorHouse != null) {
			if (poorHouse.getPicturePath()!=null && !poorHouse.getPicturePath().equals("")) {
				String url = poorHouse.getPicturePath();
				utils.display(holderView.iv_photo, url);
			}else {
				Drawable drawable = ResourceUtil.getInstance().getDrawableByID(R.drawable.small_icon_03);
				holderView.iv_photo.setImageDrawable(drawable);
			}
			
			if (poorHouse.getJob_info()!=null && poorHouse.getJob_info().equals("有务工人员")) {
				holderView.cb_train.setChecked(true);
			}else if(poorHouse.getJob_info()!=null && poorHouse.getJob_info().equals("无务工人员")) {
				holderView.cb_train.setChecked(false);
			}
	
			holderView.tv_house_boss.setText(poorHouse.getPersonname());
			holderView.tv_card_num.setText(poorHouse.getIdno());
			
//			NumberFormat format = NumberFormat.getInstance();
//			format.setMaximumFractionDigits(2);
//			String rj = format.format(poorHouse.getTotal_income() / poorHouse.getHousecount());
//			String income = "人均收入"+ rj +"元";
			if(StringUtil.isEmpty(poorHouse.getYear_income_perp())){
				holderView.tv_income.setText("人均收入"+ 0 +"元");
			}else {
				holderView.tv_income.setText("人均收入"+ poorHouse.getYear_income_perp() +"元");
			}
			String peoplenum = "家庭人口" + poorHouse.getHousecount() + "人";
			holderView.tv_family_num.setText(peoplenum);
			holderView.tv_phone.setText(poorHouse.getPhone());
//			if (poorHouse.getIfhasjob()==1) {
//				holderView.cb_job.setChecked(true);
//			}else if(poorHouse.getIfhasjob()==0) {
//				holderView.cb_job.setChecked(false);
//			}
			
			holderView.tv_family_address.setText(poorHouse.getLocation()); //+poorHouse.getGroupname()
			if(null!=poorHouse.getSextext()){
				if (poorHouse.getSextext().equals("男")) {
					holderView.cb_man.setChecked(true);
					holderView.cb_woman.setChecked(false);
				}else if(poorHouse.getSextext().equals("女")){
					holderView.cb_man.setChecked(false);
					holderView.cb_woman.setChecked(true);
				}
			}
		}
		return view;
	}

	class HolderView {
		@ViewInject(R.id.tv_card_num)
		TextView tv_card_num;//身份证号
		@ViewInject(R.id.tv_income)
		TextView tv_income;//收入
//		@ViewInject(R.id.cb_job)
//		CheckBox cb_job;//是否就业
		@ViewInject(R.id.cb_train)
		CheckBox cb_train;//是否培训
		@ViewInject(R.id.tv_name)
		TextView tv_house_boss;//户主
		@ViewInject(R.id.cb_man)
		CheckBox cb_man;//性别
		@ViewInject(R.id.cb_woman)
		CheckBox cb_woman; 

		@ViewInject(R.id.iv_user_head)
		CircleImageView iv_photo;
		
		@ViewInject(R.id.tv_num)
		TextView tv_family_num;//人口
		@ViewInject(R.id.tv_address)
		TextView tv_family_address;//地址

		@ViewInject(R.id.tv_phone)
		TextView tv_phone;//电话
	}

	/**
	 * 增加刷新后的数据
	 * 2016年5月17日
	 * ZhaoDongShao
	 */
	public void addList(List<PoorFamilyBase> list) {
		this.poorHouses.addAll(list);
		notifyDataSetChanged();
	}

	/**
	 * @param lPoorHouses
	 * 2016年6月13日
	 * ZhaoDongShao
	 */
	public void onRefsh(List<PoorFamilyBase> lPoorHouses) {
		this.poorHouses.clear();
		this.poorHouses.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

}
