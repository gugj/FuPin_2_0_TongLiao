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
import com.roch.fupin.adapter.PoorQiXianAdapter;
import com.roch.fupin.adapter.ZhuanXiangFuPinXiangMuGuanLiAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.PoorQiXian;
import com.roch.fupin.entity.PoorQiXian_ResultList;
import com.roch.fupin.entity.ZhuanXiangFuPinXiangMuGuanLi;
import com.roch.fupin.entity.ZhuanXiangFuPinXiangMuGuanLi_ResultList;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.List;

/**
 * 贫困旗县Activity
 * 作者：ZDS
 * 时间：2016/12/21/021 15:50
 */
public class PoorQiXianActivity extends MainBaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;

    @ViewInject(R.id.tv_title)
    TextView tv_title;

    @ViewInject(R.id.lv_poor_qixian)
    ListView lv_poor_qixian;
    private List<PoorQiXian> poorQiXians=null;
    private List<ZhuanXiangFuPinXiangMuGuanLi> zhuanXiangFuPinXiangMuGuanLis=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanxiang_zijin);
        ViewUtils.inject(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra(Common.INTENT_KEY);
        if(title.contains("贫困旗县")){
            tv_title.setText("贫困旗县");
        }else if(title.contains("专项扶贫项目管理")){
            tv_title.setText("专项扶贫项目管理");
        }
        //初始化toolbar
        initToolbar();
        MyApplication.getInstance().addActivity(mActivity);

        //请求获取贫困旗县数据
        requestNetData(title);
    }

    /**
     * 请求获取贫困旗县数据
     * @param title
     */
    private void requestNetData(String title) {
        RequestParams rp = new RequestParams();
        rp.addBodyParameter("", "");
        if(title.contains("贫困旗县")){
            System.out.println("请求获取贫困旗县数据网址：===" + URLs.Poor_QiXian);
            MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.Poor_QiXian, rp,
                    new MyRequestCallBack(this, MyConstans.FIRST));
        }else if(title.contains("专项扶贫项目管理")){
            System.out.println("请求获取专项扶贫项目管理数据网址：===" + URLs.ZhuanXiangFuPinXiangMuGuanLi);
            MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.ZhuanXiangFuPinXiangMuGuanLi, rp,
                    new MyRequestCallBack(this, MyConstans.SECOND));
        }
    }

    @Override
    public void onSuccessResult(String str, int flag) {
        super.onSuccessResult(str, flag);
        switch (flag){
            case MyConstans.FIRST:
                System.out.println("获取贫困旗县数据成功：===" + str);
                PoorQiXian_ResultList poorQiXian_resultList=PoorQiXian_ResultList.parseToT(str,PoorQiXian_ResultList.class);
                if(StringUtil.isNotEmpty(poorQiXian_resultList)){
                    if(poorQiXian_resultList.getSuccess()){
                        poorQiXians = poorQiXian_resultList.getJsondata();
                        if(poorQiXians !=null && poorQiXians.size()>0){
                            System.out.println("解析成功："+ poorQiXians.get(0).getPoortypeidname());
                            PoorQiXianAdapter poorQiXianAdapter=new PoorQiXianAdapter(mContext, poorQiXians);
                            lv_poor_qixian.setAdapter(poorQiXianAdapter);
                            lv_poor_qixian.setOnItemClickListener(this);
                        }
                    }
                }
                break;

            case MyConstans.SECOND:
                System.out.println("获取专项扶贫项目管理数据成功：===" + str);
                ZhuanXiangFuPinXiangMuGuanLi_ResultList resultList=ZhuanXiangFuPinXiangMuGuanLi_ResultList.parseToT(str,ZhuanXiangFuPinXiangMuGuanLi_ResultList.class);
                if(StringUtil.isNotEmpty(resultList)){
                    if(resultList.getSuccess()){
                        zhuanXiangFuPinXiangMuGuanLis = resultList.getJsondata();
                        if(zhuanXiangFuPinXiangMuGuanLis !=null && zhuanXiangFuPinXiangMuGuanLis.size()>0){
                            System.out.println("解析成功："+ zhuanXiangFuPinXiangMuGuanLis.get(0).getCountryName());
                            ZhuanXiangFuPinXiangMuGuanLiAdapter Adapter=new ZhuanXiangFuPinXiangMuGuanLiAdapter(mContext,zhuanXiangFuPinXiangMuGuanLis);
                            lv_poor_qixian.setAdapter(Adapter);
                            lv_poor_qixian.setOnItemClickListener(this);
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
        System.out.println("获取贫困旗县数据失败：===" + str);
    }

    /**
     * 初始化toolbar
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
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(StringUtil.isNotEmpty(poorQiXians)){
            Intent intent=new Intent(this,PoorQiXianDetailActivity.class);
            intent.putExtra("qixian",poorQiXians.get(position));
            intent.putExtra("flag",1);
            startActivity(intent);
        }else if(StringUtil.isNotEmpty(zhuanXiangFuPinXiangMuGuanLis)){
            Intent intent=new Intent(this,PoorQiXianDetailActivity.class);
            intent.putExtra("qixian",zhuanXiangFuPinXiangMuGuanLis.get(position));
            intent.putExtra("flag",2);
            startActivity(intent);
        }
    }
}
