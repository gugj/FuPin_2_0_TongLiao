package com.roch.fupin.adapter;

import java.util.ArrayList;
import java.util.List;

import com.roch.fupin_2_0.R;
import com.roch.fupin.entity.AdlCode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ResultCityAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<AdlCode> results = new ArrayList<AdlCode>();

	public ResultCityAdapter(Context context, List<AdlCode> city_result) {
		inflater = LayoutInflater.from(context);
		this.results = city_result;
	}

	@Override
	public int getCount() {
		return results.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name.setText(results.get(position).getAd_nm());
		return convertView;
	}

	class ViewHolder {
		TextView name;
	}

}
