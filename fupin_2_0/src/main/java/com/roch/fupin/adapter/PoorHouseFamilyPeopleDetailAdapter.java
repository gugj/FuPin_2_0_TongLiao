package com.roch.fupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin_2_0.R;
import java.util.List;

/**
 * 贫困户中家庭成员详情信息适配器
 * @author ZhaoDongShao
 * 2016年6月1日
 */
public class PoorHouseFamilyPeopleDetailAdapter extends BaseAdapter{

	Context mContext;
	List<MapEntity> list;

	public PoorHouseFamilyPeopleDetailAdapter(Context mContext, List<MapEntity> list) {
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
		ViewHolder holder = null;
		View view = convertView;
		if (view == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_no_poor_house_people_detail, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}

		MapEntity entity = (MapEntity) getItem(position);
		if (entity != null) {
			holder.tv_key.setText(entity.getKey());
			holder.tv_value.setText(entity.getValue());
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_key)
		TextView tv_key;
		@ViewInject(R.id.tv_value)
		TextView tv_value;
	}

}
