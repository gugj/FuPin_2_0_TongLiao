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
import com.lidroid.xutils.view.annotation.ViewInject;
import com.roch.fupin.adapter.ZhuanXiangZiJinGongLiAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.PoorQiXian_ResultList;
import com.roch.fupin.entity.ZhuanXiangZiJiGuanLi_ResultList;
import com.roch.fupin.entity.ZhuanXiangZiJinGuanLi;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 专项资金管理Activity--改为专项资金到账
 * 作者：ZDS
 * 时间：2016/12/21/021 15:50
 */
public class ZhuanXiangZiJinGuanLiActivity extends MainBaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;

    @ViewInject(R.id.tv_title)
    TextView tv_title;

    @ViewInject(R.id.lv_poor_qixian)
    ListView lv_poor_qixian;
    private List<ZhuanXiangZiJinGuanLi> zhuanXiangZiJinGuanLis;
    private String title_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanxiang_zijin);
        ViewUtils.inject(this);
        //初始化toolbar
        initToolbar();
        MyApplication.getInstance().addActivity(mActivity);

        //请求获取资金管理数据
        requestNetData();
    }

    /**
     * 请求获取资金管理数据
     */
    private void requestNetData() {
        RequestParams rp = new RequestParams();
        rp.addBodyParameter("", "");
        if(title_name.equals("专项资金到账")){
            System.out.println("请求获取专项资金到账数据网址：===" + URLs.ZhuanXiangZiJinGongLi);
            MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.ZhuanXiangZiJinGongLi, rp,
                    new MyRequestCallBack(this, MyConstans.FIRST));
        }else if(title_name.equals("专项资金下拨")){
            System.out.println("请求获取专项资金下拨数据网址：===" + URLs.ZhuanXiangZiJinXiaBo);
            MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.ZhuanXiangZiJinXiaBo, rp,
                    new MyRequestCallBack(this, MyConstans.FIRST));
        }else if(title_name.equals("本级项目管理费")){
            System.out.println("请求获取本级项目管理费数据网址：===" + URLs.BenJiXiangMuGuanLi);
            MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.BenJiXiangMuGuanLi, rp,
                    new MyRequestCallBack(this, MyConstans.FIRST));
        }else if(title_name.equals("专项资金流向")){
            System.out.println("请求获取专项资金流向数据网址：===" + URLs.ZhuanXiangZiJingLiuXiang);
            MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.ZhuanXiangZiJingLiuXiang, rp,
                    new MyRequestCallBack(this, MyConstans.FIRST));
        }
    }

    @Override
    public void onSuccessResult(String str, int flag) {
        super.onSuccessResult(str, flag);
        switch (flag){
            case MyConstans.FIRST:
                System.out.println("获取专项资金管理数据成功：===" + str);
                ZhuanXiangZiJiGuanLi_ResultList zhuanXiangZiJiGuanLi_resultList=PoorQiXian_ResultList.parseToT(str,ZhuanXiangZiJiGuanLi_ResultList.class);
                if(StringUtil.isNotEmpty(zhuanXiangZiJiGuanLi_resultList)){
                    if(zhuanXiangZiJiGuanLi_resultList.getSuccess()){
                        zhuanXiangZiJinGuanLis = zhuanXiangZiJiGuanLi_resultList.getJsondata();
                        if(zhuanXiangZiJinGuanLis !=null && zhuanXiangZiJinGuanLis.size()>0){
                            System.out.println("解析成功：id==="+ zhuanXiangZiJinGuanLis.get(0).getDataid());
                            ZhuanXiangZiJinGongLiAdapter zhuanXiangZiJinGongLiAdapter=new ZhuanXiangZiJinGongLiAdapter(mContext, zhuanXiangZiJinGuanLis);
                            if(title_name.equals("专项资金流向")){
                                zhuanXiangZiJinGongLiAdapter.setType(2);
                            }
                            lv_poor_qixian.setAdapter(zhuanXiangZiJinGongLiAdapter);
                            lv_poor_qixian.setOnItemClickListener(this);
                        }else {
                            showToast("暂无数据！");
                        }
                    }
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onFaileResult(String str, int flag) {
        super.onFaileResult(str, flag);
        showToast(str);
        System.out.println("获取专项资金管理数据失败：===" + str);
    }

    /**
     * 初始化toolbar
     * 2016年8月5日
     * ZhaoDongShao
     */
    private void initToolbar() {
        Intent intent = getIntent();
        title_name = intent.getStringExtra(Common.INTENT_KEY);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        if(StringUtil.isNotEmpty(title_name)){
            tv_title.setText(title_name);
        }
    }

    /**
     * 当点击了toolbar的返回箭头时，关闭该activity回到上一级的activity
     */
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
        Intent intent=new Intent(this,ZhuanXiangZiJinGuanLiDetailActivity.class);
        intent.putExtra("qixian", zhuanXiangZiJinGuanLis.get(position));
        intent.putExtra("id", zhuanXiangZiJinGuanLis.get(position).getDataid());
        if(title_name.equals("专项资金流向")){
            intent.putExtra("type", "专项资金流向");
        }else {
            intent.putExtra("type", "");
        }
        startActivity(intent);
    }
}
