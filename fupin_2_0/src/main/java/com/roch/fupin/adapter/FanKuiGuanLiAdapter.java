package com.roch.fupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.entity.FanKuiGuanLi;
import com.roch.fupin.utils.HTMLUtils;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 反馈管理adapter
 * @author ZhaoDongShao
 * 2016年5月26日
 */
public class FanKuiGuanLiAdapter extends BaseAdapter{

	List<FanKuiGuanLi> list;
	Context mContext;

	private int positionId=-1;
	private int commentCount;

	public FanKuiGuanLiAdapter(Context mContext, List<FanKuiGuanLi> list) {
		this.list = list;
		this.mContext = mContext;
	}

	public void setCommentCount(int positionId,int commentCount){
		this.positionId=positionId;
		this.commentCount=commentCount;
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
		ViewHolder viewHolder = null;
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_information_list_item, parent, false);
			viewHolder = new ViewHolder();
			ViewUtils.inject(viewHolder, view);
			view.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) view.getTag();
		}

		FanKuiGuanLi people = (FanKuiGuanLi) getItem(position);
		if (people != null) {
			//标题
			viewHolder.tv_title.setText(people.getTitle());
			String msg = HTMLUtils.delTag(people.getTitle());
			//内容
			viewHolder.tv_content.setText(msg);
			//发布人
			viewHolder.tv_name.setText(people.getPersonname());
			String date=people.getTs();
			String[] split = date.split(" ");
			//发布时间
			viewHolder.tv_data.setText(split[0]);
			//回复数量
			viewHolder.tv_replaycount.setVisibility(View.VISIBLE);
			if(positionId==position){
				viewHolder.tv_replaycount.setText("回复数（"+commentCount+"）");
			}else {
				viewHolder.tv_replaycount.setText("回复数（"+people.getReplycount()+"）");
			}
		}
		return view;
	}

	static class ViewHolder{
		@ViewInject(R.id.tv_name)
		TextView tv_name;//发布人
		@ViewInject(R.id.tv_date)
		TextView tv_data;//发布时间
		@ViewInject(R.id.tv_content)
		TextView tv_content;//内容
		@ViewInject(R.id.tv_title)
		TextView tv_title;//标题
		@ViewInject(R.id.tv_replaycount)
		TextView tv_replaycount;//回复数量
	}

	/**
	 * @param fanKuiGuanLis
	 * 2016年6月16日
	 * ZhaoDongShao
	 */
	public void onRefsh(List<FanKuiGuanLi> fanKuiGuanLis) {
		this.list.clear();
		this.list.addAll(fanKuiGuanLis);
		notifyDataSetChanged();
	}

	/**
	 * @param fanKuiGuanLis
	 * 2016年6月16日
	 * ZhaoDongShao
	 */
	public void addList(List<FanKuiGuanLi> fanKuiGuanLis) {
		this.list.addAll(fanKuiGuanLis);
		notifyDataSetChanged();
	}
}
