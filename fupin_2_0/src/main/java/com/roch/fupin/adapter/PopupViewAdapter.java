package com.roch.fupin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ReadFragment中PopupWindow的ListView的适配器
 * 时间：2016/12/7/007 10:01
 */
public class PopupViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> deviceNames = new ArrayList<String>();

    public PopupViewAdapter(Context context, List<String> deviceNames) {
        this.context = context;
        this.deviceNames = deviceNames;
    }

    @Override
    public int getCount() {
        return deviceNames.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_lv_popup_view, null);
            viewHolder=new ViewHolder();
            viewHolder.tvDeviceName=(TextView) convertView.findViewById(R.id.tv_device_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //赋值
        viewHolder.tvDeviceName.setText(deviceNames.get(position));

        return convertView;
    }

     class ViewHolder {
        TextView tvDeviceName;
    }
}
