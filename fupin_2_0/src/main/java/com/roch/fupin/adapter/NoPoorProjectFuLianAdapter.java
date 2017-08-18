/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectFulianAppModel;
import com.roch.fupin.utils.CommonUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 妇联适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectFuLianAdapter extends BaseAdapter{

	Context mContext;
	List<ProjectFulianAppModel> list;
	
	/**
	 * 
	 */
	public NoPoorProjectFuLianAdapter(Context mContext, List<ProjectFulianAppModel> list) {
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
			view = inflater.inflate(R.layout.activity_no_poor_project_fulian_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectFulianAppModel entity = (ProjectFulianAppModel) getItem(position);
		if (entity != null) {
			
			holder.tv_name.setText(entity.getProjectname());
			holder.tv_px_name.setText(entity.getTrainname());
			if (entity.getTraindate() != null) {
				holder.tv_data.setText(CommonUtil.getSpliteDate(entity.getTraindate()));
			}
			holder.tv_lx.setText(entity.getTraintypeidcall());
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //项目名称
		TextView tv_name;
		@ViewInject(R.id.tv_year) //培训名称
		TextView tv_px_name;
		@ViewInject(R.id.tv_lx) //培训类型
		TextView tv_lx;
		@ViewInject(R.id.tv_ffjd) //培训时间
		TextView tv_data;
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
	public void addList(List<ProjectFulianAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

	/**
	 *
	 * @param lPoorHouses
	 *
	 * 2016年7月12日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void onRefsh(List<ProjectFulianAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}
	
}
