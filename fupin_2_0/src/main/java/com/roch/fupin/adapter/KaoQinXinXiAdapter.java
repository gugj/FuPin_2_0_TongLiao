package com.roch.fupin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roch.fupin.entity.KaoQinXinXi;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 考勤信息适配器
 * 作者：ZDS
 * 时间：2016/12/21/021 17:23
 */
public class KaoQinXinXiAdapter extends BaseAdapter {

    private List<KaoQinXinXi> jsondata;
    private Context context;

    public KaoQinXinXiAdapter(Context context, List<KaoQinXinXi> jsondata){
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
            convertView=View.inflate(context, R.layout.adapter_kaoqinxinxi,null);
            viewHolder.tv_povertyareanm= (TextView) convertView.findViewById(R.id.tv_povertyareanm);
            viewHolder.tv_povertypercent= (TextView) convertView.findViewById(R.id.tv_povertypercent);
            viewHolder.tv_pinkunhushuo= (TextView) convertView.findViewById(R.id.tv_pinkunhushuo);
            viewHolder.tv_pinkunrenkou= (TextView) convertView.findViewById(R.id.tv_pinkunrenkou);
            viewHolder.tv_kaoqinduixiang= (TextView) convertView.findViewById(R.id.tv_kaoqinduixiang);
            viewHolder.tv_phone= (TextView) convertView.findViewById(R.id.tv_phone);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //赋值
        KaoQinXinXi kaoQinXinXi= jsondata.get(position);
        viewHolder.tv_povertyareanm.setText(kaoQinXinXi.getAdl_nm()); //所属行政区
        viewHolder.tv_povertypercent.setText(kaoQinXinXi.getAtt_persontypename()); //对象类型
        viewHolder.tv_pinkunhushuo.setText(kaoQinXinXi.getAtt_relcount() + ""); //实际考勤次数
        viewHolder.tv_pinkunrenkou.setText(kaoQinXinXi.getAtt_valcount()); //有效考勤次数
        viewHolder.tv_kaoqinduixiang.setText(kaoQinXinXi.getAtt_personname()); //考勤对象
        viewHolder.tv_phone.setText(kaoQinXinXi.getAtt_mobilephone()); //考勤对象的电话

        return convertView;
    }

    class ViewHolder{
        TextView tv_povertyareanm; //所属行政区
        TextView tv_povertypercent; //对象类型
        TextView tv_pinkunhushuo; //实际考勤次数
        TextView tv_pinkunrenkou; //有效考勤次数
        TextView tv_kaoqinduixiang; //考勤对象
        TextView tv_phone; //电话
    }

    /**
     * 增加刷新后的数据
     * 2016年5月17日
     * ZhaoDongShao
     */
    public void addList(List<KaoQinXinXi> list) {
        this.jsondata.addAll(list);
        notifyDataSetChanged();
    }


    public void onRefsh(List<KaoQinXinXi> lPoorHouses) {
        this.jsondata.clear();
        this.jsondata.addAll(lPoorHouses);
        notifyDataSetChanged();
    }

}
