package com.roch.fupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.PoorFamilyPeople;
import com.roch.fupin.entity.Yhzgx;
import com.roch.fupin.utils.IDCardUtil;
import com.roch.fupin_2_0.R;

import java.util.List;

public class PoorFamilyPeopleAdapter_SaoYiSao extends BaseAdapter {

    Context mContext;

    List<PoorFamilyPeople> poorHouses;

    public PoorFamilyPeopleAdapter_SaoYiSao(Context mContext, List<PoorFamilyPeople> poorHouses) {
        this.mContext = mContext;
        this.poorHouses = poorHouses;
    }

    @Override
    public int getCount() {
        if (poorHouses != null) {
            return poorHouses.size();
        }
        return 0;
    }

    @Override
    public PoorFamilyPeople getItem(int position) {
        return poorHouses != null ? poorHouses.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holderView = null;
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.listview_poorfamily_people_list_item_saoyisao, parent, false);
            holderView = new ViewHolder(view);
            view.setTag(holderView);
        } else {
            holderView = (ViewHolder) view.getTag();
        }

        PoorFamilyPeople poorHouse = getItem(position);
        if (poorHouse != null) {
            holderView.tv_jiangkangzhuangkuang.setText(poorHouse.getPp_healthtext()); //健康状况  5
            try {
                holderView.tv_age.setText(String.valueOf(IDCardUtil.getAge(poorHouse.getIdno()) + "岁")); //年龄 4
            } catch (Exception e) {
                e.printStackTrace();
            }

            holderView.tv_xl.setText(poorHouse.getInschoolstatustext());  // 在校生状况 6
            holderView.tv_jibingleixing.setText(poorHouse.getDiseasetypetext());  // 疾病类型 7
            holderView.tv_xl.setText(poorHouse.getPersoninfo());  // 个人情况 8

            String familyrelationid = poorHouse.getFamilyrelationid();//与户主关系id 3
            if (familyrelationid != null && !familyrelationid.equals("")) {
                List<Yhzgx> list = MyApplication.lYhzgx;
                if (list != null && list.size() > 0) {
                    for (Yhzgx yhzgx : list) {
                        if (yhzgx.getValue().equals(familyrelationid)) {
                            holderView.tv_yhzgx.setText(yhzgx.getText());
                        }
                    }
                }
            }

            holderView.tv_name.setText(poorHouse.getPersonname()); //名字  1
            String sexName = "";
            if (poorHouse.getSex() == 1) {
                sexName = "男";
            } else if (poorHouse.getSex() == 2) {
                sexName = "女";
            }
            holderView.tv_sex.setText(sexName); //性别  2
        }
        return view;
    }

    class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public TextView tv_yhzgx;
        public LinearLayout lin;
        public TextView tv_sex;
        public TextView tv_age;
        public TextView tv_xl;
        public TextView tv_jiangkangzhuangkuang;
        public TextView tv_jibingleixing;
        public TextView tv_gerenqingkuang;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_yhzgx = (TextView) rootView.findViewById(R.id.tv_yhzgx);
            this.lin = (LinearLayout) rootView.findViewById(R.id.lin);
            this.tv_sex = (TextView) rootView.findViewById(R.id.tv_sex);
            this.tv_age = (TextView) rootView.findViewById(R.id.tv_age);
            this.tv_xl = (TextView) rootView.findViewById(R.id.tv_xl);
            this.tv_jiangkangzhuangkuang = (TextView) rootView.findViewById(R.id.tv_jiangkangzhuangkuang);
            this.tv_jibingleixing = (TextView) rootView.findViewById(R.id.tv_jibingleixing);
            this.tv_gerenqingkuang = (TextView) rootView.findViewById(R.id.tv_gerenqingkuang);
        }
    }
}
