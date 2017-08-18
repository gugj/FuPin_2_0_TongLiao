/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectInfoAppEntity;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.CommonUtil;
import com.roch.fupin.utils.ResourceUtil;

import android.content.Context;
import android.graphics.Color;
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
public class NoPoorProjectAdapter extends BaseAdapter{

	Context mContext;
	List<ProjectInfoAppEntity> list;
	
	/**
	 * 
	 */
	public NoPoorProjectAdapter(Context mContext, List<ProjectInfoAppEntity> list) {
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
		
		ProjectInfoAppEntity entity = (ProjectInfoAppEntity) getItem(position);
		if (entity != null) {
			
			if (entity.getProjectstatusidcall()!=null &&entity.getProjectstatusidcall().equals(Common.EXTS_ZH)) {
				holder.tv_project_state.setTextColor(Color.RED);
			}else {
				holder.tv_project_state.setTextColor(ResourceUtil.getInstance().getColorById(R.color.gray_878787));
			}
			
			holder.tv_name.setText(entity.getProjectname().replace("\n", ""));
			holder.tv_company.setText(entity.getDutydeptname().replace("\n", "、"));
			holder.tv_lx.setText(entity.getProjectcategoryidcall());
			holder.tv_project_state.setText(entity.getProjectstatusidcall());
			holder.tv_jindu.setText(entity.getProjectscheduleidcall());
			holder.tv_date.setText("立项日期：" + CommonUtil.getSpliteDate(entity.getLixiangdate()));
//			if (entity.getIszhaobiao() == 0) {
//				holder.tv_zhaobiao.setText("未招标");
//			}else {
//				holder.tv_zhaobiao.setText("已招标");
//			}
//			
//			if (entity.getIsyanshou() == 0) { //未验收
//				if (entity.getIsjungong() == 0) { //未竣工
//					if (entity.getIskaigong() == 0) { //未开工
//						holder.tv_project_jd.setText("未开工");
//					}else {
//						holder.tv_project_jd.setText("已开工");
//					}
//				}else {
//					holder.tv_project_jd.setText("已竣工");
//				}
//			}else {
//				holder.tv_project_jd.setText("已验收");
//			}
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //项目名称
		TextView tv_name;
		@ViewInject(R.id.tv_lx) //类型
		TextView tv_lx;
		@ViewInject(R.id.tv_project_state) //项目状态
		TextView tv_project_state;
		@ViewInject(R.id.tv_company) //单位
		TextView tv_company;
		@ViewInject(R.id.tv_jindu) //项目进度
		TextView tv_jindu;
		@ViewInject(R.id.tv_lx_date)
		TextView tv_date;
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
	public void addList(List<ProjectInfoAppEntity> lPoorHouses) {
		// TODO Auto-generated method stub
		list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}

	/**
	 *
	 * @param lPoorHouses
	 *
	 * 2016年6月13日
	 *
	 * ZhaoDongShao
	 *
	 */
	public void onRefsh(List<ProjectInfoAppEntity> lPoorHouses) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}
	
}
