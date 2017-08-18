package com.roch.fupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.PoorFamilyPeople;
import com.roch.fupin.entity.Whcd;
import com.roch.fupin.entity.Yhzgx;
import com.roch.fupin.utils.IDCardUtil;
import com.roch.fupin_2_0.R;

import java.util.List;

public class PoorFamilyPeopleAdapter extends BaseAdapter{

	Context mContext;

	List<PoorFamilyPeople> poorHouses;

	public PoorFamilyPeopleAdapter(Context mContext,List<PoorFamilyPeople> poorHouses) {
		this.mContext = mContext;
		this.poorHouses = poorHouses;
	}

	@Override
	public int getCount() {
		if (poorHouses != null) {
			return poorHouses.size();
		}
		return 0;
	}

	@Override
	public PoorFamilyPeople getItem(int position) {
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
			view = LayoutInflater.from(mContext).inflate(R.layout.listview_poorfamily_people_list_item, parent, false);
			ViewUtils.inject(holderView,view);
			view.setTag(holderView);
		}else {
			holderView = (HolderView)view.getTag();
		}

		PoorFamilyPeople poorHouse = getItem(position);
		if (poorHouse != null) {
			holderView.tv_card_num.setText(poorHouse.getIdno());
			try {
				holderView.tv_age.setText(String.valueOf(IDCardUtil.getAge(poorHouse.getIdno())+"岁"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			String culturelevelid = poorHouse.getCulturelevelid();//学历id
			if (culturelevelid!=null&&!culturelevelid.equals("")) {
				List<Whcd> list = MyApplication.lWhcd;
				if (list != null && list.size() > 0) {
					for (Whcd whcd : list) {
						if (whcd.getValue().equals(culturelevelid)) {
							holderView.tv_xl.setText(whcd.getText());
						}
					}
				}
			}

			String familyrelationid = poorHouse.getFamilyrelationid();//与户主关系id
			if (familyrelationid!=null&&!familyrelationid.equals("")) {
				List<Yhzgx> list = MyApplication.lYhzgx;
				if (list != null && list.size() > 0) {
					for (Yhzgx yhzgx : list) {
						if (yhzgx.getValue().equals(familyrelationid)) {
							holderView.tv_yhzgx.setText(yhzgx.getText());
						}
					}
				}
			}

			holderView.tv_name.setText(poorHouse.getPersonname());
			String sexName = "";
			if (poorHouse.getSex() == 1) {
				sexName = "男";
			}else if (poorHouse.getSex() == 2) {
				sexName = "女";
			}
			holderView.tv_sex.setText(sexName);
		}
		return view;
	}

	class HolderView {
		@ViewInject(R.id.tv_name)
		TextView tv_name;
		@ViewInject(R.id.tv_sex)
		TextView tv_sex;
		@ViewInject(R.id.tv_yhzgx)
		TextView tv_yhzgx;
		@ViewInject(R.id.tv_age)
		TextView tv_age;
		@ViewInject(R.id.tv_xl)
		TextView tv_xl;
		@ViewInject(R.id.tv_card_num)
		TextView tv_card_num;
	}
}
