package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.PoorHouseFamilyPeopleDetailAdapter;
import com.roch.fupin.adapter.ZhuanXiangZiJinGongLiAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.PoorQiXian_ResultList;
import com.roch.fupin.entity.ZhuanXiangZiJiGuanLi_ResultList;
import com.roch.fupin.entity.ZhuanXiangZiJinGuanLi;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 专项资金到账详情页
 * 作者：ZDS
 * 时间：2016/12/22/022 10:36
 */
@ContentView(R.layout.activity_poor_qixian_detail)
public class ZhuanXiangZiJinGuanLiDetail3Activity extends MainBaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    private ZhuanXiangZiJinGuanLi zhuanXiangZiJinGuanLi;

    @ViewInject(R.id.lv_poor)
    ListView lv;

    PoorHouseFamilyPeopleDetailAdapter adapter;

    private List<ZhuanXiangZiJinGuanLi> zhuanXiangZiJinGuanLis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(mActivity);
        ViewUtils.inject(this);
        initToolbar();
//        initData();
        //请求获取专项资金流向二级页面详情数据
        requestNetData();
    }

    /**
     * 请求获取专项资金流向二级页面详情数据
     */
    private void requestNetData() {
        Intent intent=getIntent();
        String id= intent.getStringExtra("id");
        RequestParams rp = new RequestParams();
        rp.addBodyParameter("id", id);

        System.out.println("请求获取专项资金流向三级页面详情数据网址：===" + URLs.ZhuanXiangZiJingLiuXiang_3Ji+"&id="+id);
        MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.ZhuanXiangZiJingLiuXiang_3Ji, rp,
                new MyRequestCallBack(this, MyConstans.FIRST));
    }

    @Override
    public void onSuccessResult(String str, int flag) {
        super.onSuccessResult(str, flag);
        System.out.println("请求获取专项资金流向三级页面详情数据成功：===" + str);
        ZhuanXiangZiJiGuanLi_ResultList zhuanXiangZiJiGuanLi_resultList= PoorQiXian_ResultList.parseToT(str, ZhuanXiangZiJiGuanLi_ResultList.class);
        if(StringUtil.isNotEmpty(zhuanXiangZiJiGuanLi_resultList)){
            if(zhuanXiangZiJiGuanLi_resultList.getSuccess()){
                zhuanXiangZiJinGuanLis = zhuanXiangZiJiGuanLi_resultList.getJsondata();
                if(zhuanXiangZiJinGuanLis !=null && zhuanXiangZiJinGuanLis.size()>0){
                    System.out.println("解析成功：id==="+ zhuanXiangZiJinGuanLis.get(0).getDataid());
                    ZhuanXiangZiJinGongLiAdapter zhuanXiangZiJinGongLiAdapter=new ZhuanXiangZiJinGongLiAdapter(mContext, zhuanXiangZiJinGuanLis);
                    zhuanXiangZiJinGongLiAdapter.setType(2);
                    lv.setAdapter(zhuanXiangZiJinGongLiAdapter);
                    lv.setOnItemClickListener(this);
                }
            }
        }
    }

    @Override
    public void onFaileResult(String str, int flag) {
        super.onFaileResult(str, flag);
        showToast(str);
        System.out.println("请求获取专项资金流向三级页面详情数据失败：==="+str);
    }

    private void initData() {
        Intent intent=getIntent();
        zhuanXiangZiJinGuanLi = (ZhuanXiangZiJinGuanLi) intent.getSerializableExtra("qixian");
        if(StringUtil.isNotEmpty(zhuanXiangZiJinGuanLi)){
            List<MapEntity> mapEntities = new ArrayList<MapEntity>();
            MapEntity mapEntity = null;

            mapEntity = new MapEntity("资金来源", zhuanXiangZiJinGuanLi.getSourcesfunds());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("资金类型", zhuanXiangZiJinGuanLi.getFundstypename());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("拨付时间", zhuanXiangZiJinGuanLi.getAllocationdate());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("到账时间", zhuanXiangZiJinGuanLi.getPaymentdate());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("金额", zhuanXiangZiJinGuanLi.getAmount()+"万元");
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("批次", zhuanXiangZiJinGuanLi.getBatch());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("年度", zhuanXiangZiJinGuanLi.getAllocationyear());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("拨付对象", zhuanXiangZiJinGuanLi.getAllocatedobjectname());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("文件号", zhuanXiangZiJinGuanLi.getFileno());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("下拨情况", zhuanXiangZiJinGuanLi.getAllocationsituationname());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("备注", zhuanXiangZiJinGuanLi.getNt());
            mapEntities.add(mapEntity);

            adapter = new PoorHouseFamilyPeopleDetailAdapter(mContext, mapEntities);
            lv.setAdapter(adapter);
        }
    }

    /**
     * 2016年8月5日
     * ZhaoDongShao
     */
    private void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        tv_title.setText("三级资金拨付详情");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                MyApplication.getInstance().finishActivity(this);
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent=new Intent(this,ZhuanXiangZiJinGuanLiDetail3Activity.class);
//        intent.putExtra("qixian", zhuanXiangZiJinGuanLis.get(position));
//        intent.putExtra("id", zhuanXiangZiJinGuanLis.get(position).getDataid());
//        startActivity(intent);
    }
}
