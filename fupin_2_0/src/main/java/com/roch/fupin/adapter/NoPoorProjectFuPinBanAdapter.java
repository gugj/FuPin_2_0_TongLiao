/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectFupinbanTrainAppModel;
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
 * 教体局项目适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectFuPinBanAdapter extends BaseAdapter{

	Context mContext;
	List<ProjectFupinbanTrainAppModel> list;
	
	/**
	 * 
	 */
	public NoPoorProjectFuPinBanAdapter(Context mContext, List<ProjectFupinbanTrainAppModel> list) {
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
			view = inflater.inflate(R.layout.activity_no_poor_project_fupinban_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectFupinbanTrainAppModel entity = (ProjectFupinbanTrainAppModel) getItem(position);
		if (entity != null) {
			if (entity.getProjectstatusidcall()!=null &&entity.getProjectstatusidcall().equals(Common.EXTS_ZH)) {
				holder.tv_xmzt.setTextColor(Color.RED);
			}else {
				holder.tv_xmzt.setTextColor(ResourceUtil.getInstance().getColorById(R.color.gray_878787));
			}
			holder.tv_name.setText(entity.getProjectname());
			holder.tv_xmlx.setText(entity.getProjectcategoryidcall());
			holder.tv_xmjd.setText(entity.getProjectscheduleidcall());
			holder.tv_xmzt.setText(entity.getProjectstatusidcall());
			
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //项目名称
		TextView tv_name;
		@ViewInject(R.id.tv_year) //项目类型
		TextView tv_xmlx;
		@ViewInject(R.id.tv_lx) //项目状态
		TextView tv_xmzt;
		@ViewInject(R.id.tv_ffjd) //培训进度
		TextView tv_xmjd;
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
	public void addList(List<ProjectFupinbanTrainAppModel> lPoorHouses) {
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
	public void onRefsh(List<ProjectFupinbanTrainAppModel> lPoorHouses) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}
	
}
