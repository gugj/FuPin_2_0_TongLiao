package com.roch.fupin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roch.fupin.entity.ZhuanXiangZiJinGuanLi;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 专项资金管理适配器
 * 作者：ZDS
 * 时间：2016/12/21/021 17:23
 */
public class ZhuanXiangZiJinGongLiAdapter extends BaseAdapter {

    private List<ZhuanXiangZiJinGuanLi> jsondata;
    private Context context;
    private int resType=1;

    public ZhuanXiangZiJinGongLiAdapter(Context context, List<ZhuanXiangZiJinGuanLi> jsondata){
       this.context=context;
        this.jsondata=jsondata;
   }

    public void setType(int resType){
        this.resType=resType;
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
            if(resType==1){
                convertView=View.inflate(context, R.layout.adapter_zhuanxiangzijinguanli,null);
            }else {
                convertView=View.inflate(context, R.layout.adapter_zhuanxiangzijinguanl2i,null);
            }
            viewHolder.tv_wenjianhao= (TextView) convertView.findViewById(R.id.tv_wenjianhao);
            viewHolder.tv_povertyareanm= (TextView) convertView.findViewById(R.id.tv_povertyareanm);
            viewHolder.tv_povertypercent= (TextView) convertView.findViewById(R.id.tv_povertypercent);
            viewHolder.tv_poorpeoplenm= (TextView) convertView.findViewById(R.id.tv_pinkunhushuo);
            viewHolder.tv_povertypeoplenm= (TextView) convertView.findViewById(R.id.tv_pinkunrenkou);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //赋值
        ZhuanXiangZiJinGuanLi zhuanXiangZiJinGuanLi= jsondata.get(position);
        viewHolder.tv_wenjianhao.setText(zhuanXiangZiJinGuanLi.getFileno()); //资金来源
        viewHolder.tv_povertyareanm.setText(zhuanXiangZiJinGuanLi.getSourcesfunds()); //资金来源
        viewHolder.tv_povertypercent.setText(zhuanXiangZiJinGuanLi.getFundstypename()); //资金类型
        viewHolder.tv_poorpeoplenm.setText(zhuanXiangZiJinGuanLi.getAllocatedobjectname()); //下拨对象
        if(resType==1){
            String date=zhuanXiangZiJinGuanLi.getPaymentdate();
            if(StringUtil.isNotEmpty(date)){
                if(date.split(" ").length!=0){
                    viewHolder.tv_povertypeoplenm.setText(date.split(" ")[0]); //到账时间
                }else {
                    viewHolder.tv_povertypeoplenm.setText(zhuanXiangZiJinGuanLi.getPaymentdate()); //到账时间
                }
            }
        }else {
            viewHolder.tv_povertypeoplenm.setText(zhuanXiangZiJinGuanLi.getAmount()); //下拨金额
        }

        return convertView;
    }

    class ViewHolder{
        TextView tv_wenjianhao;
        TextView tv_povertyareanm;
        TextView tv_povertypercent;
        TextView tv_poorpeoplenm;
        TextView tv_povertypeoplenm;
    }
}
