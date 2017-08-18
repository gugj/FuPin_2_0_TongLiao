package com.roch.fupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.ProjectCaizhengjuBofuAppModel;
import com.roch.fupin.utils.CommonUtil;
import com.roch.fupin_2_0.R;
import java.util.List;

/**
 * 教体局项目发放人员适配器
 * @author ZhaoDongShao
 * 2016年6月1日
 */
public class NoPoorProjectCaiZhengJu_BFMX_Adapter extends BaseAdapter{

	Context mContext;
	List<ProjectCaizhengjuBofuAppModel> list;

	public NoPoorProjectCaiZhengJu_BFMX_Adapter(Context mContext, List<ProjectCaizhengjuBofuAppModel> list) {
		this.list = list;
		this.mContext = mContext;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		View view = convertView;
		if (view == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.activity_no_poor_project_caizhengju_bfmx_list_item, parent, false);
			ViewUtils.inject(holder, view);
			view.setTag(holder);
		}else {
			holder = (ViewHolder)view.getTag();
		}
		
		ProjectCaizhengjuBofuAppModel entity = (ProjectCaizhengjuBofuAppModel) getItem(position);
		if (entity != null) {
			holder.tv_bf_zh.setText("拨付金额总和：" + String.valueOf(entity.getTotalamount()));
			if (entity.getGrantdate() != null) {
				holder.tv_sjbfje.setText(CommonUtil.getSpliteDate(entity.getGrantdate()));
			}else {
				holder.tv_sjbfje.setText("");
			}
			
			holder.tv_bfry.setText("拨付人员：" + entity.getLouser());
			holder.tv_jw.setText(entity.getProjectjw());
			holder.tv_project.setText(entity.getProjectname());
			holder.tv_sjbfje.setText("实际拨付金额："+String.valueOf(entity.getFactamount()));
		}
		return view;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_jw) //局委
		TextView tv_jw;
		@ViewInject(R.id.tv_sjbfje) //实际拨付金额
		TextView tv_sjbfje;
		@ViewInject(R.id.tv_sjbf_time) //实际拨付时间
		TextView tv_sjbf_time;
		@ViewInject(R.id.tv_bfry) //拨付人员
		TextView tv_bfry;
		@ViewInject(R.id.tv_bf_zh) //实际拨付金额总和
		TextView tv_bf_zh;
		@ViewInject(R.id.tv_project) //项目名称
		TextView tv_project;
	}
}
