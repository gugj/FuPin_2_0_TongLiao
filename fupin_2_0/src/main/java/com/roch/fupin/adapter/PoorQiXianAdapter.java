package com.roch.fupin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roch.fupin.entity.PoorQiXian;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 贫困旗县适配器
 * 作者：ZDS
 * 时间：2016/12/21/021 17:23
 */
public class PoorQiXianAdapter extends BaseAdapter {

    private List<PoorQiXian> jsondata;
    private Context context;

    public PoorQiXianAdapter(Context context,List<PoorQiXian> jsondata){
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
            convertView=View.inflate(context, R.layout.adapter_poorqixian,null);
            viewHolder.tv_povertyareanm= (TextView) convertView.findViewById(R.id.tv_povertyareanm);
            viewHolder.tv_povertypercent= (TextView) convertView.findViewById(R.id.tv_povertypercent);
            viewHolder.tv_poorpeoplenm= (TextView) convertView.findViewById(R.id.tv_pinkunhushuo);
            viewHolder.tv_povertypeoplenm= (TextView) convertView.findViewById(R.id.tv_pinkunrenkou);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //赋值
        PoorQiXian poorQiXian= jsondata.get(position);
        viewHolder.tv_povertyareanm.setText(poorQiXian.getPovertyareanm());
        viewHolder.tv_povertypercent.setText(poorQiXian.getPovertypercent());
        viewHolder.tv_poorpeoplenm.setText(poorQiXian.getPoorhousenm()+"");
        viewHolder.tv_povertypeoplenm.setText(poorQiXian.getPoorpeoplenm()+"");

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
