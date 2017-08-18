package com.roch.fupin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roch.fupin.entity.PoorHouseBangFuJiLu.BangFuJiLu;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 *  贫困户帮扶记录的adapter，用于填充贫困户的ListView中的数据  <br/>
 *
 * 2016年11月1日 
 *
 */
public class BangFuJiLuAdapter extends BaseAdapter{

	
	private List<BangFuJiLu> jsondata;
	private Context context;

	public BangFuJiLuAdapter(List<BangFuJiLu> jsondata,Context context) {
		this.jsondata=jsondata;
		this.context=context;
	}
	
	@Override
	public int getCount() {
		return jsondata.size();
	}

	@Override
	public Object getItem(int position) {
		return jsondata.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=View.inflate(context,R.layout.adapter_poorhouse_bangfujilu, null);
			viewHolder.tv_bfjl_helptitle=(TextView) convertView.findViewById(R.id.tv_bfjl_helptitle);
			viewHolder.tv_bfjl_helpdate=(TextView) convertView.findViewById(R.id.tv_bfjl_helpdate);
			convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder) convertView.getTag();
		}
		
		viewHolder.tv_bfjl_helptitle.setText(jsondata.get(position).helptitle);
		if(StringUtil.isNotEmpty(jsondata.get(position).helpdate)){
			viewHolder.tv_bfjl_helpdate.setText(jsondata.get(position).helpdate.split(" ")[0]);
		}

		return convertView;
	}

	/**
	 * 帮扶记录的ViewHolder
	 *
	 * 2016年11月1日 
	 *
	 */
	class ViewHolder {
		/**
		 * 帮扶记录的title和时间
		 */
		public TextView tv_bfjl_helptitle,tv_bfjl_helpdate;
	}
}
