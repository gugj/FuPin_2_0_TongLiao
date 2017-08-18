/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectLinyeEconomyAppModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 就业培训适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectLinYeJu_LXJJ_Adapter extends BaseAdapter{

	Context mContext;
	List<ProjectLinyeEconomyAppModel> list;

	/**
	 * 
	 */
	public NoPoorProjectLinYeJu_LXJJ_Adapter(Context mContext, List<ProjectLinyeEconomyAppModel> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		View view = convertView;
		if (view == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.activity_no_poor_linyeju_lxjj_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}

		ProjectLinyeEconomyAppModel entity = (ProjectLinyeEconomyAppModel) getItem(position);
		if (entity != null) {
			
			holder.tv_name.setText(entity.getProjectname());
			holder.tv_house_name.setText(entity.getPersonname());
			holder.tv_pz.setText(entity.getBreedtypeidcall());
			holder.tv_area.setText(String.valueOf(entity.getBreedarea()));
			holder.tv_num.setText(String.valueOf(entity.getBreednumber()));
			holder.tv_money.setText(String.valueOf(entity.getAllowance()) + "元");
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //项目名称
		TextView tv_name;
		@ViewInject(R.id.tv_house_name) //户主姓名
		TextView tv_house_name;
		@ViewInject(R.id.tv_pz) //品种
		TextView tv_pz;
		@ViewInject(R.id.tv_area) //面积
		TextView tv_area;
		@ViewInject(R.id.tv_num) //数量
		TextView tv_num;
		@ViewInject(R.id.tv_money) //补助金额
		TextView tv_money;
	}

	/**
	 *
	 * @param lPoorHouses
	 *
	 * 2016年6月1日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void addList(List<ProjectLinyeEconomyAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

	/**
	 *
	 * @param lPoorHouses
	 *
	 * 2016年6月30日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void onRefsh(List<ProjectLinyeEconomyAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

}
