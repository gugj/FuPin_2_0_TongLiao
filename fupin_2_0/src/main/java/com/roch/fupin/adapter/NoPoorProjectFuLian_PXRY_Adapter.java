/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectFulianItemAppModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 就业培训培训人员适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectFuLian_PXRY_Adapter extends BaseAdapter{

	Context mContext;
	List<ProjectFulianItemAppModel> list;
	
	/**
	 * 
	 */
	public NoPoorProjectFuLian_PXRY_Adapter(Context mContext, List<ProjectFulianItemAppModel> list) {
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
			view = inflater.inflate(R.layout.listview_no_poor_project_fulian_pxry_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectFulianItemAppModel entity = (ProjectFulianItemAppModel) getItem(position);
		if (entity != null) {
			
			holder.tv_name.setText(entity.getPeoplename());
			holder.tv_company.setText(entity.getWorkcompany());
			if (entity.getEntrydate() != null) {
				holder.tv_data.setText(entity.getEntrydate().split(" ")[0]);
			}
			holder.tv_jy_state.setText(entity.getFinishName());
			holder.tv_money.setText(String.valueOf(entity.getSalarymonth()));
			holder.tv_work_state.setText(entity.getWorkstatusidcall());
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //姓名
		TextView tv_name;
		@ViewInject(R.id.tv_jy_state) //是否已结业
		TextView tv_jy_state;
		@ViewInject(R.id.tv_work_state) //培训后就业情况
		TextView tv_work_state;
		@ViewInject(R.id.tv_money) //月工资
		TextView tv_money;
		@ViewInject(R.id.tv_company) //就业单位名称
		TextView tv_company;
		@ViewInject(R.id.tv_date) //入职日期
		TextView tv_data;
	}
}
