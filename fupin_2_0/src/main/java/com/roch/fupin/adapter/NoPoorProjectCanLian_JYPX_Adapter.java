/**
 * 
 */
package com.roch.fupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.ProjectCanlianTrainAppModel;
import com.roch.fupin.utils.CommonUtil;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 就业培训适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectCanLian_JYPX_Adapter extends BaseAdapter{

	Context mContext;
	List<ProjectCanlianTrainAppModel> list;

	public NoPoorProjectCanLian_JYPX_Adapter(Context mContext, List<ProjectCanlianTrainAppModel> list) {
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
			view = inflater.inflate(R.layout.listview_no_poor_project_canlian_jypx_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectCanlianTrainAppModel entity = (ProjectCanlianTrainAppModel) getItem(position);
		if (entity != null) {
			holder.tv_name.setText(entity.getProjectname());
			holder.tv_px_name.setText(entity.getTrainname());
			holder.tv_data.setText(CommonUtil.getSpliteDate(entity.getTraindate()));
			holder.tv_pxlb.setText(entity.getTraintypeidcall());
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //项目名称
		TextView tv_name;
		@ViewInject(R.id.tv_company) //培训名称
		TextView tv_px_name;
		@ViewInject(R.id.tv_lx) //培训类别
		TextView tv_pxlb;
		@ViewInject(R.id.tv_money) //培训日期
		TextView tv_data;
	}

	/**
	 * @param lPoorHouses
	 * 2016年6月1日
	 * ZhaoDongShao
	 */
	public void addList(List<ProjectCanlianTrainAppModel> lPoorHouses) {
		list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

	/**
	 * @param lPoorHouses
	 * 2016年7月2日
	 * ZhaoDongShao
	 */
	public void onRefsh(List<ProjectCanlianTrainAppModel> lPoorHouses) {
		this.list.clear();
		this.list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}
	
}
