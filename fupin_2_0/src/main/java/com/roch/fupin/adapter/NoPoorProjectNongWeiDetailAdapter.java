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
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectNongWeiDetailAdapter extends BaseAdapter{

	Context mContext;
	List<ProjectNongweiItemAppModel> list;
	
	/**
	 * 
	 */
	public NoPoorProjectNongWeiDetailAdapter(Context mContext, List<ProjectNongweiItemAppModel> list) {
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
			view = inflater.inflate(R.layout.listview_no_poor_project_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectNongweiItemAppModel entity = (ProjectNongweiItemAppModel) getItem(position);
		if (entity != null) {
			
			holder.tv_name.setText(entity.getPersonname());
			holder.tv_date.setText(entity.getEntrydate());
			holder.tv_money.setText(String.valueOf(entity.getSalarymonth())+"元");
			holder.tv_time.setText(String.valueOf(entity.getWorkmonth()));
			holder.tv_work.setText(entity.getWorkposition());

		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //项目名称
		TextView tv_name;
		@ViewInject(R.id.tv_lx) //薪水
		TextView tv_money;
		@ViewInject(R.id.tv_project_state) //工作岗位
		TextView tv_work;
		@ViewInject(R.id.tv_company) //工作时间
		TextView tv_time;
		@ViewInject(R.id.tv_jindu) //入职日期
		TextView tv_date;
	}
}
