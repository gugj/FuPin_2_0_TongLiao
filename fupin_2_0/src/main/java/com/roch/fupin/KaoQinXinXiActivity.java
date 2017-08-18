package com.roch.fupin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.roch.fupin.adapter.KaoQinXinXiAdapter;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.dialog.NoPoorProject_kqxx_FilterPopWindow;
import com.roch.fupin.entity.KaoQinXinXi;
import com.roch.fupin.entity.KaoQinXinXi_ResultList;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.utils.Common;
import com.roch.fupin.utils.MyConstans;
import com.roch.fupin.utils.MyRequestCallBack;
import com.roch.fupin.utils.StringUtil;
import com.roch.fupin.utils.URLs;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 考勤信息Activity
 * 作者：ZDS
 * 时间：2016/12/21/021 15:50
 */
public class KaoQinXinXiActivity extends MainBaseActivity implements NoPoorProject_kqxx_FilterPopWindow.ShowMessageListener {

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;

    @ViewInject(R.id.tv_title)
    TextView tv_title;

    @ViewInject(R.id.lv_poor_qixian)
    PullToRefreshListView lv_poor_qixian;
    private List<KaoQinXinXi> kaoQinXinXis;

    NoPoorProject_kqxx_FilterPopWindow filterPopWindow;

    private String requestParams="2017";

    private static int FRIST = 0;
    int current_page = 1; //当前页码
    private KaoQinXinXiAdapter kaoQinXinXiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poorqixian);
        ViewUtils.inject(this);
        //初始化toolbar
        initToolbar();
        //初始化ListView的下拉刷新、上拉加载监听
        initListener();
        MyApplication.getInstance().addActivity(mActivity);

        //请求获取考勤信息的服务器数据
        requestNetData();
    }

    /**
     * 初始化ListView的下拉刷新、上拉加载监听
     */
    private void initListener() {
        lv_poor_qixian.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                FRIST = 0;
                current_page = 1;
                flag_chaxun_tiaojian = false;
                RequestParams rp = getRequestParams();
                rp.addBodyParameter("page", String.valueOf(current_page));
                MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                        URLs.KaoQinXinXi, rp,
                        new MyRequestCallBack(KaoQinXinXiActivity.this, MyConstans.FIRST));
                System.out.println("贫困户页面进行高级查询时请求服务器中的贫困户List(flag=1)的网址为：==" + URLs.POOR_HOUSE_LISE + "?&page=" + String.valueOf(current_page));
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                FRIST = 1;
                current_page++;
                if (flag_chaxun_tiaojian) { //带查询条件
                    RequestParams rp = getRequestParams();
                    if (StringUtil.isNotEmpty(name)) {
                        rp.addBodyParameter("att_personname", name);
                    }
                    if (StringUtil.isNotEmpty(phone)) {
                        rp.addBodyParameter("att_mobilephone", phone);
                    }
                    rp.addBodyParameter("page", String.valueOf(current_page));
                    MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                            URLs.KaoQinXinXi, rp,
                            new MyRequestCallBack(KaoQinXinXiActivity.this, MyConstans.FIRST));
                    System.out.println("贫困户页面进行高级查询时请求服务器中的贫困户List(flag=1)的网址为：==" + URLs.POOR_HOUSE_LISE + "?&page=" + String.valueOf(current_page));
                } else {
                    RequestParams rp = getRequestParams();
                    rp.addBodyParameter("page", String.valueOf(current_page));
                    MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST,
                            URLs.KaoQinXinXi, rp,
                            new MyRequestCallBack(KaoQinXinXiActivity.this, MyConstans.FIRST));
                    System.out.println("贫困户页面进行高级查询时请求服务器中的贫困户List(flag=1)的网址为：==" + URLs.POOR_HOUSE_LISE + "?&page=" + String.valueOf(current_page));
                }
            }
        });
//        lv_poor_qixian.setOnItemClickListener(this);
    }

    /**
     * 获取请求网络的参数---1.考勤年份
     * @return
     */
    private RequestParams getRequestParams() {
        RequestParams rp = new RequestParams();
        rp.addBodyParameter("helpyear", requestParams);
        return rp;
    }

    /**
     * 请求获取考勤信息的服务器数据
     */
    private void requestNetData() {
        RequestParams rp = getRequestParams();
//        rp.addBodyParameter("helpyear", requestParams);
        rp.addBodyParameter("page", current_page+"");
        System.out.println("请求获取考勤信息数据网址：===" + URLs.KaoQinXinXi + "?helpyear=" + requestParams);
        MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.KaoQinXinXi, rp,
                new MyRequestCallBack(this, MyConstans.FIRST));
    }

    @Override
    public void onSuccessResult(String str, int flag) {
        super.onSuccessResult(str, flag);
        switch (flag){
            case MyConstans.FIRST:
                System.out.println("获取考勤信息数据成功：===" + str);
                KaoQinXinXi_ResultList kaoQinXinXi_resultList=KaoQinXinXi_ResultList.parseToT(str,KaoQinXinXi_ResultList.class);
                if(StringUtil.isNotEmpty(kaoQinXinXi_resultList)){
                    if(kaoQinXinXi_resultList.getSuccess()){
                        kaoQinXinXis = kaoQinXinXi_resultList.getJsondata();
                        if(!StringUtil.isEmpty(kaoQinXinXis)){
                            switch (FRIST){
                                case 0: //下拉刷新
                                    if(kaoQinXinXiAdapter!=null){
                                        kaoQinXinXiAdapter.onRefsh(kaoQinXinXis);
                                    }else {
                                        kaoQinXinXiAdapter = new KaoQinXinXiAdapter(mContext, kaoQinXinXis);
                                        lv_poor_qixian.setAdapter(kaoQinXinXiAdapter);
                                    }
                                    break;

                                case 1: //上拉加载
                                    if (kaoQinXinXiAdapter == null) {
                                        kaoQinXinXiAdapter = new KaoQinXinXiAdapter(mContext, kaoQinXinXis);
                                        lv_poor_qixian.setAdapter(kaoQinXinXiAdapter);
                                    }else {
                                        kaoQinXinXiAdapter.addList(kaoQinXinXis);
                                    }
                                    break;
                            }
                        }else {
                            showToast("没有更多数据");
                            lv_poor_qixian.onRefreshComplete();
                        }
                    }
                }
                lv_poor_qixian.onRefreshComplete();
                break;

            default:
                break;
        }
    }

    @Override
    public void onFaileResult(String str, int flag) {
        super.onFaileResult(str, flag);
        showToast(str);
        System.out.println("获取考勤信息数据失败：===" + str);
        lv_poor_qixian.onRefreshComplete();
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
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.select:
                        int xPox = (int) (Common.Width * 0.9);
                        filterPopWindow = new NoPoorProject_kqxx_FilterPopWindow(mContext);
                        //监听者模式，将监听者传过来
                        filterPopWindow.setShowMessageListener(KaoQinXinXiActivity.this);
                        //设置筛选过之后的时间
                        filterPopWindow.setSelectionAdapter(maps, "","");
                        filterPopWindow.setShowShouRu(true);
                        //显示popupWindow
                        filterPopWindow.showPopupWindow(toolbar, xPox);
                        break;
                }
                return false;
            }
        });
        tv_title.setText("考勤信息");
    }

    /**
     * 当点击菜单选项时调用此方法
     * @param menu
     * @return
     */
    @SuppressLint("NewApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.select_menu, menu);
        return true;
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

    @OnItemClick(R.id.lv_poor_qixian)   //R.id.lv_poor_qixian
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        KaoQinXinXi kaoQinXinXi= (KaoQinXinXi) parent.getItemAtPosition(position);
        if(null!=kaoQinXinXi){
            Intent intent=new Intent(this,KaoQinXinXiActivityDetail_New.class);
            intent.putExtra("kaoqin",kaoQinXinXi);
            startActivity(intent);
        }
    }

    List<MapEntity> maps = new ArrayList<MapEntity>();
//    @Override
//    public void Message(String time_end,String shouru) {
//
//    }

    private boolean flag_chaxun_tiaojian=false;
    private String name;
    private String phone;
    @Override
    public void Message(String time_end, String shouru, String phone) {
        maps.clear();
        String times = "";
        RequestParams rp = new RequestParams();
        if (!time_end.equals("")) {
            int year = Integer.parseInt(time_end.split("-")[0]);
//			int months = Integer.parseInt(time_end.split("-")[1]);
            Calendar calendar = Calendar.getInstance();
            if (year > calendar.get(Calendar.YEAR)) {
                showToast("请选择正确的查询日期");
                return;
            }else if (year == calendar.get(Calendar.YEAR)) {
//				if (months > calendar.get(Calendar.MONTH) + 1) {
//					showToast("请选择正确的查询日期");
//					return;
//				}else {
//					times += year + "年" + months + "月份";
                times += year + "年";
                maps.add(new MapEntity("wejend", time_end));
//					if (months > 0 && months < 10) {
//						time_end = year + "-0" + months;
//						time_end = year+"";
//					}
//					rp.addBodyParameter("lastMonth", time_end);
//					rp.addBodyParameter("endDate", time_end);
//				}
                time_end = year+"";
                rp.addBodyParameter("helpyear", time_end);
                requestParams=time_end;
            }else {
//				times += year + "年" + months + "月份";
                times += year + "年";
                maps.add(new MapEntity("wejend", time_end));
//				if (months > 0 && months < 10) {
//					time_end = year + "-0" + months;
//					time_end = year + "";
//				}
//				rp.addBodyParameter("lastMonth", time_end);
                time_end = year + "";
                rp.addBodyParameter("helpyear", time_end);
                requestParams=time_end;
            }
        }else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
//			String a = year + "年" + month + "月份";
            String a = year + "年";
            times += a;
        }
//        rp.addBodyParameter("page", current_page+"");
        if(StringUtil.isNotEmpty(shouru)){
            rp.addBodyParameter("att_personname",shouru);
            name=shouru;
        }
        if(StringUtil.isNotEmpty(phone)){
            rp.addBodyParameter("att_mobilephone",phone);
            this.phone=phone;
        }
        FRIST = 0;
        current_page = 1;
        flag_chaxun_tiaojian=true;
        rp.addBodyParameter("page",current_page+"");
        System.out.println("请求获取考勤信息数据网址：===" + URLs.KaoQinXinXi + "?helpyear=" + requestParams+"&att_personname="+shouru+"&att_mobilephone="+phone);
        MyApplication.getInstance().getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, URLs.KaoQinXinXi, rp,
                new MyRequestCallBack(this, MyConstans.FIRST));

        filterPopWindow.dismiss();
    }
}
