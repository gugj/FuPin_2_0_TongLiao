/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectCanlianRebuildAppModel;
import com.roch.fupin.utils.CommonUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 危房改造适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectCanLian_WFGZ_Adapter extends BaseAdapter{

	Context mContext;
	List<ProjectCanlianRebuildAppModel> list;
	
	/**
	 * 
	 */
	public NoPoorProjectCanLian_WFGZ_Adapter(Context mContext, List<ProjectCanlianRebuildAppModel> list) {
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
			view = inflater.inflate(R.layout.listview_no_poor_project_canlian_wfgz_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectCanlianRebuildAppModel entity = (ProjectCanlianRebuildAppModel) getItem(position);
		if (entity != null) {
			
			holder.tv_name.setText(entity.getProjectname());
			holder.tv_gzfs.setText(entity.getRebuildmodeidcall());
			if (entity.getStartdate() != null) {
				holder.tv_data.setText(CommonUtil.getSpliteDate(entity.getLixiangdate()));
			}
			holder.tv_people.setText(entity.getPersonname());
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //项目名称
		TextView tv_name;
		@ViewInject(R.id.tv_company) //户主名称
		TextView tv_people;
		@ViewInject(R.id.tv_lx) //改造方式
		TextView tv_gzfs;
		@ViewInject(R.id.tv_money) //动工日期
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
	public void addList(List<ProjectCanlianRebuildAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

	/**
	 *
	 * @param lPoorHouses
	 *
	 * 2016年8月6日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void onResh(List<ProjectCanlianRebuildAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}
	
}
