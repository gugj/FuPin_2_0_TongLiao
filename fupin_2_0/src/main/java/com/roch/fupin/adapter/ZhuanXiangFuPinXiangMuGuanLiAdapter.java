package com.roch.fupin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roch.fupin.entity.ZhuanXiangFuPinXiangMuGuanLi;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 专项扶贫项目管理适配器
 * 作者：ZDS
 * 时间：2016/12/21/021 17:23
 */
public class ZhuanXiangFuPinXiangMuGuanLiAdapter extends BaseAdapter {

    private List<ZhuanXiangFuPinXiangMuGuanLi> jsondata;
    private Context context;

    public ZhuanXiangFuPinXiangMuGuanLiAdapter(Context context,List<ZhuanXiangFuPinXiangMuGuanLi> jsondata){
       this.context=context;
        this.jsondata=jsondata;
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
            convertView=View.inflate(context, R.layout.adapter_zhuanxiangfupinxiangmuguanli,null);
            viewHolder.tv_xm_name= (TextView) convertView.findViewById(R.id.tv_xm_name);
            viewHolder.tv_povertyareanm= (TextView) convertView.findViewById(R.id.tv_povertyareanm);
            viewHolder.tv_povertypercent= (TextView) convertView.findViewById(R.id.tv_povertypercent);
            viewHolder.tv_poorpeoplenm= (TextView) convertView.findViewById(R.id.tv_pinkunhushuo);
            viewHolder.tv_povertypeoplenm= (TextView) convertView.findViewById(R.id.tv_pinkunrenkou);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //赋值
        ZhuanXiangFuPinXiangMuGuanLi zhanXiang= jsondata.get(position);
        viewHolder.tv_xm_name.setText(zhanXiang.getProjectname());
        viewHolder.tv_povertyareanm.setText(zhanXiang.getDescript_text());
        viewHolder.tv_povertypercent.setText(zhanXiang.getFpdx());
        viewHolder.tv_poorpeoplenm.setText(zhanXiang.getScheinfo());
        viewHolder.tv_povertypeoplenm.setText(zhanXiang.getProjectstatusidcall());

        return convertView;
    }

    class ViewHolder{
        TextView tv_xm_name;
        TextView tv_povertyareanm;
        TextView tv_povertypercent;
        TextView tv_poorpeoplenm;
        TextView tv_povertypeoplenm;
    }
}
