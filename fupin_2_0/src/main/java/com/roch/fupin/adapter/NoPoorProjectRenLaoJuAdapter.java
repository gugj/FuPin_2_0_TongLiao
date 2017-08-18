/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectRenlaoAppModel;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.ResourceUtil;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 人劳局适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectRenLaoJuAdapter extends BaseAdapter{

	Context mContext;
	List<ProjectRenlaoAppModel> list;
	
	/**
	 * 
	 */
	public NoPoorProjectRenLaoJuAdapter(Context mContext, List<ProjectRenlaoAppModel> list) {
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
			view = inflater.inflate(R.layout.listview_no_poor_project_renlaoju_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectRenlaoAppModel entity = (ProjectRenlaoAppModel) getItem(position);
		if (entity != null) {
			if (entity.getProjectstatusidcall()!=null &&entity.getProjectstatusidcall().equals(Common.EXTS_ZH)) {
				holder.tv_xmzt.setTextColor(Color.RED);
			}else {
				holder.tv_xmzt.setTextColor(ResourceUtil.getInstance().getColorById(R.color.gray_878787));
			}
			holder.tv_name.setText(entity.getProjectname());
			holder.tv_company.setText(entity.getDutydeptname());
			holder.tv_xmjd.setText(entity.getProjectscheduleidcall());
			holder.tv_xmzt.setText(entity.getProjectstatusidcall());
			holder.tv_lxrq.setText(entity.getLixiangdate() != null ? entity.getLixiangdate().split(" ")[0] : "" );
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //项目名称
		TextView tv_name;
		@ViewInject(R.id.tv_xmjd) 
		TextView tv_xmjd;
		@ViewInject(R.id.tv_xmzt) 
		TextView tv_xmzt;
		@ViewInject(R.id.tv_lxrq) 
		TextView tv_lxrq;
		@ViewInject(R.id.tv_company) 
		TextView tv_company;
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
	public void addList(List<ProjectRenlaoAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

	/**
	 *
	 * @param lPoorHouses
	 *
	 * 2016年6月15日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void onRefsh(List<ProjectRenlaoAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}
	
}
