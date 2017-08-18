/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectCaizhengjuAppModel;
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
 * 财政局项目适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectCaiZhengJuAdapter extends BaseAdapter{

	Context mContext;
	List<ProjectCaizhengjuAppModel> list;
	/**
	 * 
	 */
	public NoPoorProjectCaiZhengJuAdapter(Context mContext, List<ProjectCaizhengjuAppModel> list) {
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
			view = inflater.inflate(R.layout.activity_no_poor_project_caizhengju_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}

		ProjectCaizhengjuAppModel entity = (ProjectCaizhengjuAppModel) getItem(position);
		if (entity != null) {
			if (entity.getProjectstatusidcall()!=null && entity.getProjectstatusidcall().equals(Common.EXTS_ZH)) {
				holder.tv_xmzt.setTextColor(Color.RED);
			}else {
				holder.tv_xmzt.setTextColor(ResourceUtil.getInstance().getColorById(R.color.gray_878787));
			}
			holder.tv_project.setText(entity.getProjectname());
			holder.tv_name.setText(entity.getJuweicall());
			holder.tv_xmjd.setText(entity.getProjectscheduleidcall());
			holder.tv_xmzt.setText(entity.getProjectstatusidcall());
			holder.tv_xmlx.setText(entity.getProjectcategoryidcall());

		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //局委
		TextView tv_name;
		@ViewInject(R.id.tv_project) //项目类型
		TextView tv_project;
		@ViewInject(R.id.tv_xmjd) //项目进度
		TextView tv_xmjd;
		@ViewInject(R.id.tv_xmzt) //项目状态
		TextView tv_xmzt;
		@ViewInject(R.id.tv_xmlx) //责任单位
		TextView tv_xmlx;
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
	public void addList(List<ProjectCaizhengjuAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

	/**
	 *
	 * @param lPoorHouses
	 *
	 * 2016年6月14日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void onRefsh(List<ProjectCaizhengjuAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

}
