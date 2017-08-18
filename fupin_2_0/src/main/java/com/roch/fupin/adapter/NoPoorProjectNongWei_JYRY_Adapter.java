/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectNongweiItemAppModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 教体局项目发放人员适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectNongWei_JYRY_Adapter extends BaseAdapter{

	Context mContext;
	List<ProjectNongweiItemAppModel> list;
	
	/**
	 * 
	 */
	public NoPoorProjectNongWei_JYRY_Adapter(Context mContext, List<ProjectNongweiItemAppModel> list) {
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
			view = inflater.inflate(R.layout.activity_no_poor_project_nongwei_jyry_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectNongweiItemAppModel entity = (ProjectNongweiItemAppModel) getItem(position);
		if (entity != null) {
			
//			holder.tv_address.setText(entity.getCountryName() + entity.getTownName() + entity.getVillageName());
			holder.tv_address.setText(entity.getLocation());
			holder.tv_name.setText(entity.getPersonname());
			holder.tv_sex.setText(entity.getSexName());
			holder.tv_date.setText(entity.getEntrydate() != null ? entity.getEntrydate().split(" ")[0] : "");
			holder.tv_time.setText(String.valueOf(entity.getWorkmonth()) + "个月");
			holder.tv_money.setText(String.valueOf(entity.getSalarymonth())+"元");
			
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //姓名
		TextView tv_name;
		@ViewInject(R.id.tv_sex) //性别
		TextView tv_sex;
		@ViewInject(R.id.tv_address) //工作岗位
		TextView tv_address;
		@ViewInject(R.id.tv_money) //月工资
		TextView tv_money;
		@ViewInject(R.id.tv_time) //工作时间
		TextView tv_time;
		@ViewInject(R.id.tv_date) //入职日期
		TextView tv_date;
	}
	
}
