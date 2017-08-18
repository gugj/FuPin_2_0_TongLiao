/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectZhujianAppModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 住建局适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectZhuJianJuAdapter extends BaseAdapter{

	Context mContext;
	List<ProjectZhujianAppModel> list;
	
	/**
	 * 
	 */
	public NoPoorProjectZhuJianJuAdapter(Context mContext, List<ProjectZhujianAppModel> list) {
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
			view = inflater.inflate(R.layout.activity_no_poor_project_zhujianju_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectZhujianAppModel entity = (ProjectZhujianAppModel) getItem(position);
		if (entity != null) {
			
			holder.tv_name.setText(entity.getRebuildreasonidcall());
			holder.tv_project_name.setText(entity.getProjectname());
			holder.tv_gzfs.setText(entity.getRebuildmodeidcall());
			holder.tv_sqjd.setText(entity.getApprovestatusidcall());
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //项目名称
		TextView tv_project_name;
		@ViewInject(R.id.tv_year) //户主姓名
		TextView tv_name;
		@ViewInject(R.id.tv_lx) //改造方式
		TextView tv_gzfs;
		@ViewInject(R.id.tv_ffjd) //申请进度
		TextView tv_sqjd;
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
	public void addList(List<ProjectZhujianAppModel> lPoorHouses) {
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
	public void onRefsh(List<ProjectZhujianAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}
	
}
