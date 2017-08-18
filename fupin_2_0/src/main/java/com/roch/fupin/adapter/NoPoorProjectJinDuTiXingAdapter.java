/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectJindutixingModel;
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
 * @author ZhaoDongShao
 *
 * 2016年6月4日 
 *
 */
public class NoPoorProjectJinDuTiXingAdapter extends BaseAdapter{

	Context mContext;
	List<ProjectJindutixingModel> list;
	
	/**
	 * 
	 */
	public NoPoorProjectJinDuTiXingAdapter(Context mContext, List<ProjectJindutixingModel> list) {
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
			view = inflater.inflate(R.layout.listview_no_poor_project_jindutixing_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectJindutixingModel entity = (ProjectJindutixingModel) getItem(position);
		
		if (entity != null) {
			
			holder.tv_name.setText(entity.getProjectname());
			holder.tv_company.setText(entity.getDutydeptname());
			if (entity.getProjectstatusidcall()!=null &&entity.getProjectstatusidcall().equals(Common.EXTS_ZH)) {
				holder.tv_state.setTextColor(Color.RED);
			}else {
				holder.tv_state.setTextColor(ResourceUtil.getInstance().getColorById(R.color.gray_878787));
			}
			holder.tv_jindu.setText(entity.getProjectscheduleidcall());
			holder.tv_lx.setText(entity.getProjectcategoryidcall());
			holder.tv_money.setText(String.valueOf(entity.getInvesttotal()));
			holder.tv_phone.setText(entity.getDutypersonphone());
			holder.tv_state.setText(entity.getProjectstatusidcall());
			holder.tv_xgzndw.setText(entity.getProjectjw());
			holder.tv_zeren_people.setText(entity.getDutyperson());
			holder.tv_lx_date.setText("立项日期：" + (entity.getLixiangdate() != null ? entity.getLixiangdate().split(" ")[0] : ""));
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //项目名称
		TextView tv_name;
		@ViewInject(R.id.tv_lx) //项目类型
		TextView tv_lx;
		@ViewInject(R.id.tv_money) //投资金额
		TextView tv_money;
		@ViewInject(R.id.tv_jindu) //项目进度
		TextView tv_jindu;
		@ViewInject(R.id.tv_state) //项目状态
		TextView tv_state;
		@ViewInject(R.id.tv_xgzndw) //培训相关职能部门
		TextView tv_xgzndw;
		@ViewInject(R.id.tv_company) //责任单位
		TextView tv_company;
		@ViewInject(R.id.tv_zeren_people) //责任人
		TextView tv_zeren_people;
		@ViewInject(R.id.tv_phone) //联系方式
		TextView tv_phone;
		@ViewInject(R.id.tv_lx_date)
		TextView tv_lx_date;
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
	public void addList(List<ProjectJindutixingModel> lPoorHouses) {
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
	public void onRefsh(List<ProjectJindutixingModel> lPoorHouses) {
		// TODO Auto-generated method stub
		this.list.clear();
		this.list.addAll(lPoorHouses);
		notifyDataSetChanged();
	}
	
}
