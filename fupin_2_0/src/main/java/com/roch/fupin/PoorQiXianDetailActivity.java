package com.roch.fupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.PoorHouseFamilyPeopleDetailAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.PoorQiXian;
import com.roch.fupin.entity.ZhuanXiangFuPinXiangMuGuanLi;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin_2_0.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 专项扶贫项目管理、贫困旗县公用详情页
 * 作者：ZDS
 * 时间：2016/12/22/022 10:36
 */
@ContentView(R.layout.activity_poor_qixian_detail)
public class PoorQiXianDetailActivity extends MainBaseActivity {

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    private PoorQiXian poorQiXian;
    private ZhuanXiangFuPinXiangMuGuanLi zhuanXiangFuPinXiangMuGuanLi;
    @ViewInject(R.id.tv_xiangmu_photo)
    TextView tv_xiangmu_photo;
    @ViewInject(R.id.lv_poor)
    ListView lv;

    PoorHouseFamilyPeopleDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(mActivity);
        ViewUtils.inject(this);
        initToolbar();
        Intent intent=getIntent();
        int flag=intent.getIntExtra("flag",1);
        if(flag==1){
            poorQiXian = (PoorQiXian) intent.getSerializableExtra("qixian");
            initData();
        }else if(flag==2){
            zhuanXiangFuPinXiangMuGuanLi = (ZhuanXiangFuPinXiangMuGuanLi) intent.getSerializableExtra("qixian");
            tv_xiangmu_photo.setVisibility(View.VISIBLE);
            tv_xiangmu_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.tv_xiangmu_photo:
              //			showToast("点击了项目照片");
                            Intent intent=new Intent(PoorQiXianDetailActivity.this,XiangMuZhaoPianActivity.class);
                            intent.putExtra("xiangmu_id", zhuanXiangFuPinXiangMuGuanLi.getId());
                            startActivity(intent);
                            break;

                        default:
                            break;
                    }
                }
            });
            initData2();
        }
    }

    private void initData() {
        if(StringUtil.isNotEmpty(poorQiXian)){
            List<MapEntity> mapEntities = new ArrayList<MapEntity>();
            MapEntity mapEntity = null;

            mapEntity = new MapEntity("旗县名", poorQiXian.getPovertyareanm());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("旗县贫困属性", poorQiXian.getPoortypeidname());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("旗县贫困情况", poorQiXian.getPovertyalleviation());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("总户数", poorQiXian.getPovertyhousenm()+"");
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("总人数", poorQiXian.getPovertypeoplenm()+"");
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("贫困户数", poorQiXian.getPoorhousenm()+"");
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("贫困人数", poorQiXian.getPoorpeoplenm()+"");
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("贫困发生率", poorQiXian.getPovertypercent());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("旗县负责人", poorQiXian.getPovertyhead());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("旗县负责人职务", poorQiXian.getPovertyheadoffice());
            mapEntities.add(mapEntity);

            adapter = new PoorHouseFamilyPeopleDetailAdapter(mContext, mapEntities);
            lv.setAdapter(adapter);
        }
    }

    private void initData2() {
        if(StringUtil.isNotEmpty(zhuanXiangFuPinXiangMuGuanLi)){
            List<MapEntity> mapEntities = new ArrayList<MapEntity>();
            MapEntity mapEntity = null;

            mapEntity = new MapEntity("项目名称", zhuanXiangFuPinXiangMuGuanLi.getProjectname());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("项目类型", zhuanXiangFuPinXiangMuGuanLi.getDescript_text());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("建设内容", zhuanXiangFuPinXiangMuGuanLi.getBuildcontent());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("项目效益", zhuanXiangFuPinXiangMuGuanLi.getProjecteffect());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("备注", zhuanXiangFuPinXiangMuGuanLi.getRemark());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("项目责任单位", zhuanXiangFuPinXiangMuGuanLi.getDutydeptname());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("项目责任人", zhuanXiangFuPinXiangMuGuanLi.getDutyperson());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("联系方式", zhuanXiangFuPinXiangMuGuanLi.getDutypersonphone());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("帮扶主体", zhuanXiangFuPinXiangMuGuanLi.getFpdx());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("项目进度", zhuanXiangFuPinXiangMuGuanLi.getScheinfo());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("项目状态", zhuanXiangFuPinXiangMuGuanLi.getProjectstatusidcall());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("完成百分比", zhuanXiangFuPinXiangMuGuanLi.getCompletepercent());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("立项日期", zhuanXiangFuPinXiangMuGuanLi.getLixiangdate());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("报备日期", zhuanXiangFuPinXiangMuGuanLi.getBaobeidate());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("招标日期", zhuanXiangFuPinXiangMuGuanLi.getZhaobiaodate());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("开工日期", zhuanXiangFuPinXiangMuGuanLi.getKaigongdate());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("竣工日期", zhuanXiangFuPinXiangMuGuanLi.getJungongdate());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("验收日期", zhuanXiangFuPinXiangMuGuanLi.getYanshoudate());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("项目资金", zhuanXiangFuPinXiangMuGuanLi.getInvesttotal());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("已完成资金", zhuanXiangFuPinXiangMuGuanLi.getApproveamount());
            mapEntities.add(mapEntity);
            mapEntity = new MapEntity("市财政已拨付金额合计", zhuanXiangFuPinXiangMuGuanLi.getPaidamount());
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
        tv_title.setText("详情");
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

}
