/**
 * 
 */
package com.roch.fupin.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.MapEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 残联危房改造详情适配器
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class NoPoorProjectCanLian_WFGZ_DetailAdapter extends BaseAdapter{

	Context mContext;
	List<MapEntity> list;
	
	/**
	 * 
	 */
	public NoPoorProjectCanLian_WFGZ_DetailAdapter(Context mContext, List<MapEntity> list) {
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
			view = inflater.inflate(R.layout.listview_no_poor_project_list_item_tow, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		MapEntity entity = (MapEntity) getItem(position);
		if (entity != null) {
			
			holder.tv_key.setText(entity.getKey());
			holder.tv_value.setText(entity.getValue());
			
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_key) //项目名称
		TextView tv_key;
		@ViewInject(R.id.tv_value) //企业名称
		TextView tv_value;
	}
	
}
