/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.ProjectFupinbanTrainItemAppModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 教体局项目发放人员适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectFuPinBan_PXRY_Adapter extends BaseAdapter{

	Context mContext;
	List<ProjectFupinbanTrainItemAppModel> list;
	
	/**
	 * 
	 */
	public NoPoorProjectFuPinBan_PXRY_Adapter(Context mContext, List<ProjectFupinbanTrainItemAppModel> list) {
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
			view = inflater.inflate(R.layout.listview_no_poor_fupinban_pxry_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectFupinbanTrainItemAppModel entity = (ProjectFupinbanTrainItemAppModel) getItem(position);
		if (entity != null) {
			
			holder.tv_address.setText(entity.getLocation());
			holder.tv_name.setText(entity.getPersonname());
			holder.tv_sex.setText(entity.getSexName());
			holder.tv_card.setText(entity.getIdno());
			holder.tv_is_jy.setText(entity.getFinishName());
			
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name) //姓名
		TextView tv_name;
		@ViewInject(R.id.tv_address) //地址
		TextView tv_address;
		@ViewInject(R.id.tv_sex) //性别
		TextView tv_sex;
		@ViewInject(R.id.tv_card_num) //身份证号
		TextView tv_card;
		@ViewInject(R.id.tv_finsh) //是否结业
		TextView tv_is_jy;
	}
	
}
